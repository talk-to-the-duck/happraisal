/* (C)2024 */
package dev.t3d.happraisal.application;

import dev.t3d.happraisal.domain.port.FormOutPort;
import dev.t3d.happraisal.domain.port.InterviewOutPort;
import dev.t3d.happraisal.domain.port.PersonOutPort;
import dev.t3d.happraisal.domain.port.QuestionAnswerOutPort;
import dev.t3d.happraisal.domain.service.FormService;
import dev.t3d.happraisal.domain.service.InterviewService;
import dev.t3d.happraisal.domain.service.PersonService;
import dev.t3d.happraisal.persistence.adapter.FormAdapter;
import dev.t3d.happraisal.persistence.adapter.InterviewAdapter;
import dev.t3d.happraisal.persistence.adapter.PersonAdapter;
import dev.t3d.happraisal.persistence.adapter.QuestionAnswerAdapter;
import dev.t3d.happraisal.persistence.repository.FormRepository;
import dev.t3d.happraisal.persistence.repository.InterviewRepository;
import dev.t3d.happraisal.persistence.repository.PersonRepository;
import dev.t3d.happraisal.persistence.repository.QuestionAnswerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HappraisalConfiguration {

  @Bean
  FormOutPort getFormOutPort(FormRepository formRepository) {
    return new FormAdapter(formRepository);
  }

  @Bean
  InterviewOutPort getInterviewOutPort(InterviewRepository interviewRepository) {
    return new InterviewAdapter(interviewRepository);
  }

  @Bean
  PersonOutPort getPersonOutPort(PersonRepository personRepository) {
    return new PersonAdapter(personRepository);
  }

  @Bean
  QuestionAnswerOutPort getQuestionAnswerOutPort(
      QuestionAnswerRepository questionAnswerRepository) {
    return new QuestionAnswerAdapter(questionAnswerRepository);
  }

  @Bean
  FormService getFormService(FormOutPort formOutPort, QuestionAnswerOutPort questionAnswerOutPort) {
    return new FormService(formOutPort, questionAnswerOutPort);
  }

  @Bean
  PersonService getPersonService(PersonOutPort personOutPort) {
    return new PersonService(personOutPort);
  }

  @Bean
  InterviewService getInterviewService(InterviewOutPort interviewOutPort) {
    return new InterviewService(interviewOutPort);
  }
}
