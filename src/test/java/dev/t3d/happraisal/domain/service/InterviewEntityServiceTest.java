/* (C)2024 */
package dev.t3d.happraisal.domain.service;

import static org.mockito.Mockito.verify;

import dev.t3d.happraisal.domain.model.Form;
import dev.t3d.happraisal.domain.model.Interview;
import dev.t3d.happraisal.domain.model.Person;
import dev.t3d.happraisal.domain.model.QuestionAnswer;
import dev.t3d.happraisal.domain.port.InterviewOutPort;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InterviewEntityServiceTest {

  @Mock private InterviewOutPort interviewOutPort;

  @InjectMocks private InterviewService interviewService;

  @Test
  void should_create_interview() {
    // given
    final Interview expectedInterviewToSave = getInterview();

    // when
    interviewService.create(expectedInterviewToSave);

    // then
    var interviewArgumentCaptor = ArgumentCaptor.forClass(Interview.class);
    BDDMockito.verify(interviewOutPort).save(interviewArgumentCaptor.capture());
    var actualInterviewToSave = interviewArgumentCaptor.getValue();
    BDDAssertions.then(actualInterviewToSave)
        .as("Check if an interview has been saved")
        .isEqualTo(expectedInterviewToSave);
  }

  @Test
  void should_get_interview_by_id() {
    // given
    final Interview expectedInterview = getInterview();
    var interviewId = UUID.fromString("149e7f49-e66e-4cfc-8485-37993311d9e2");
    BDDMockito.given(interviewOutPort.getById(interviewId)).willReturn(expectedInterview);

    // when
    final Interview actualInterview = interviewService.getById(interviewId);

    // then
    BDDAssertions.then(actualInterview)
        .as("Check is an interview has been return by its id")
        .isEqualTo(expectedInterview);
  }

  @Test
  void should_find_all_interviews() {
    // given
    Interview interview = getInterview();
    BDDMockito.given(interviewOutPort.findAll()).willReturn(List.of(interview));

    // when
    final List<Interview> actualInterviews = interviewService.findAll();

    // when
    verify(interviewOutPort).findAll();
    BDDAssertions.then(actualInterviews).as("Check the returned interview list size").hasSize(1);
  }

  private Interview getInterview() {
    var form = new Form(UUID.fromString("43793644-4323-407a-9418-279500b42f56"), new HashSet<>());

    var questionAnswer =
        new QuestionAnswer(
            UUID.fromString("b78cea0e-8622-42da-86c1-c0f32721c7b1"), "question", "answer");
    form.questions().add(questionAnswer);

    return new Interview(
        null,
        LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC),
        new Person(
            UUID.fromString("e04ac226-ac21-46af-8fd6-c5e808e4c762"), "lastName", "firstName", true),
        new Person(
            UUID.fromString("e04ac226-ac21-46af-8fd6-c5e808e4c762"),
            "employeeLastName",
            "employeeFirstName",
            false),
        form);
  }
}
