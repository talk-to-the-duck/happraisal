/* (C)2024 */
package dev.t3d.happraisal.domain.service;

import dev.t3d.happraisal.domain.model.Form;
import dev.t3d.happraisal.domain.model.QuestionAnswer;
import dev.t3d.happraisal.domain.port.FormOutPort;
import dev.t3d.happraisal.domain.port.QuestionAnswerOutPort;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** Tests on the class {@link FormService}. */
@ExtendWith(MockitoExtension.class)
class FormServiceTest {

  @InjectMocks private FormService formService;

  @Mock private FormOutPort formOutPort;

  @Mock private QuestionAnswerOutPort questionAnswerOutPort;

  @Test
  void should_list_all_forms() {
    // given
    var expectedForm = new Form(UUID.fromString("3a1635ee-7ac1-4557-964a-2c73c7728e7b"), Set.of());
    BDDMockito.given(formOutPort.findAll()).willReturn(List.of(expectedForm));

    // when
    var actualForms = formService.findAll();

    // then
    BDDAssertions.then(actualForms).containsExactly(expectedForm);
    BDDMockito.then(formOutPort).should().findAll();
  }

  @Test
  void should_get_form_by_id() {
    // given
    var formId = UUID.fromString("3a1635ee-7ac1-4557-964a-2c73c7728e7b");
    var expectedForm = new Form(formId, Set.of());
    BDDMockito.given(formOutPort.getById(formId)).willReturn(expectedForm);

    // when
    var actualForms = formService.getById(formId);

    // then
    BDDAssertions.then(actualForms).isEqualTo(expectedForm);
    BDDMockito.then(formOutPort).should().getById(formId);
  }

  @Test
  void should_create_a_form() {
    // given
    Set<QuestionAnswer> questionAnswersToSave =
        Set.of(new QuestionAnswer(null, "What is your Name", null));
    var formToSave = new Form(null, questionAnswersToSave);

    var formId = UUID.fromString("18b8f2e2-f41c-4ca4-a007-c328390cd099");
    var questionAnswers = Set.of(new QuestionAnswer(formId, "What is your Name", null));
    var savedForm = new Form(formId, questionAnswers);

    BDDMockito.given(formOutPort.save(formToSave)).willReturn(savedForm);

    // when
    var actualForm = formService.create(formToSave);

    // then
    BDDAssertions.then(actualForm)
        .as("A form should be create")
        .isNotNull()
        .hasFieldOrPropertyWithValue("id", formId);

    BDDMockito.then(formOutPort).should().save(formToSave);
  }

  @Test
  void should_add_question_to_form() {
    // given
    var formId = UUID.fromString("18b8f2e2-f41c-4ca4-a007-c328390cd099");
    var existingQuestion = new QuestionAnswer(formId, "What is your first name", null);
    Set<QuestionAnswer> questions = new HashSet<>();
    questions.add(existingQuestion);
    var savedForm = new Form(formId, questions);

    BDDMockito.given(formOutPort.getById(formId)).willReturn(savedForm);

    var questionToSave =
        new QuestionAnswer(null, "What are your objectives for the next year", null);

    // when
    formService.createQuestion(formId, questionToSave);

    // then
    BDDMockito.then(questionAnswerOutPort).should().save(questionToSave);
    /*
    var questionAnswerArgumentCaptor = ArgumentCaptor.forClass(QuestionAnswer.class);

     no longer sens because I remove form in QuestionAnswser in domain
    BDDMockito.then(questionAnswerOutPort).should().save(questionAnswerArgumentCaptor.capture());
    var actualQuestion = questionAnswerArgumentCaptor.getValue();
    BDDAssertions.assertThat(actualQuestion)
        .as("The actual question must to be not null")
        .isNotNull();
    BDDSoftAssertions.thenSoftly(
        softly -> {
          softly
              .then(actualQuestion)
              .extracting("form.questions", Assertions.as(InstanceOfAssertFactories.COLLECTION))
              .hasSize(2);
        });

     */
  }
}
