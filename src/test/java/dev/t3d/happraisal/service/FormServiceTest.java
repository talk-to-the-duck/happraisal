package dev.t3d.happraisal.service;

import dev.t3d.happraisal.entity.Form;
import dev.t3d.happraisal.entity.QuestionAnswer;
import dev.t3d.happraisal.repository.FormRepository;
import dev.t3d.happraisal.repository.QuestionAnswerRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.BDDAssertions;
import org.assertj.core.api.BDDSoftAssertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/** Tests on the class {@link FormService}. */
@ExtendWith(MockitoExtension.class)
class FormServiceTest {

  @InjectMocks private FormService formService;

  @Mock private FormRepository formRepository;

  @Mock private QuestionAnswerRepository questionAnswerRepository;

  @Test
  void should_list_all_forms() {
    // given
    var expectedForm = new Form();
    expectedForm.setId(UUID.fromString("3a1635ee-7ac1-4557-964a-2c73c7728e7b"));
    BDDMockito.given(formRepository.findAll()).willReturn(List.of(expectedForm));

    // when
    var actualForms = formService.findAll();

    // then
    BDDAssertions.then(actualForms).containsExactly(expectedForm);
    BDDMockito.then(formRepository).should().findAll();
  }

  @Test
  void should_get_form_by_id() {
    // given
    var expectedForm = new Form();
    var formId = UUID.fromString("3a1635ee-7ac1-4557-964a-2c73c7728e7b");
    expectedForm.setId(formId);
    BDDMockito.given(formRepository.getById(formId)).willReturn(expectedForm);

    // when
    var actualForms = formService.getById(formId);

    // then
    BDDAssertions.then(actualForms).isEqualTo(expectedForm);
    BDDMockito.then(formRepository).should().getById(formId);
  }

  @Test
  void should_create_a_form() {
    // given
    var formToSave = new Form();
    formToSave.setQuestions(
        Set.of(new QuestionAnswer(null, "What is your Name", null, formToSave)));

    var formId = UUID.fromString("18b8f2e2-f41c-4ca4-a007-c328390cd099");
    var savedForm = new Form();
    savedForm.setQuestions(
        Set.of(new QuestionAnswer(formId, "What is your Name", null, formToSave)));
    savedForm.setId(formId);

    BDDMockito.given(formRepository.save(formToSave)).willReturn(savedForm);

    // when
    var actualForm = formService.create(formToSave);

    // then
    BDDAssertions.then(actualForm)
        .as("A form should be create")
        .isNotNull()
        .hasFieldOrPropertyWithValue("id", formId);

    BDDMockito.then(formRepository).should().save(formToSave);
  }

  @Test
  void should_add_question_to_form() {
    // given
    var formId = UUID.fromString("18b8f2e2-f41c-4ca4-a007-c328390cd099");
    var savedForm = new Form();
    var existingQuestion = new QuestionAnswer(formId, "What is your first name", null, savedForm);
    var questions = new HashSet<QuestionAnswer>();
    questions.add(existingQuestion);
    savedForm.setQuestions(questions);
    savedForm.setId(formId);

    BDDMockito.given(formRepository.getById(formId)).willReturn(savedForm);

    var questionToSave =
        new QuestionAnswer(null, "What are your objectives for the next year", null, null);

    // when
    formService.createQuestion(formId, questionToSave);

    // then
    var questionAnswerArgumentCaptor = ArgumentCaptor.forClass(QuestionAnswer.class);

    BDDMockito.then(questionAnswerRepository).should().save(questionAnswerArgumentCaptor.capture());
    var actualQuestion   = questionAnswerArgumentCaptor.getValue();
    BDDSoftAssertions.thenSoftly(
        softly -> {
          softly.then(actualQuestion).isNotNull();
          softly
              .then(actualQuestion)
              .extracting("form.questions", Assertions.as(InstanceOfAssertFactories.COLLECTION))
              .hasSize(2);
        });
  }
}
