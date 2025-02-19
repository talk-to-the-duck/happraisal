package dev.t3d.happraisal.application;

import dev.t3d.happraisal.persistence.repository.FormRepository;
import dev.t3d.happraisal.persistence.repository.InterviewRepository;
import dev.t3d.happraisal.persistence.repository.PersonRepository;
import dev.t3d.happraisal.persistence.repository.QuestionAnswerRepository;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
@Import(value = {
        HappraisalConfiguration.class,
        HappraisalConfigurationTest.HappraisalConfiguratonForTest.class})
class HappraisalConfigurationTest {

    @Autowired
    HappraisalConfiguration happraisalConfiguration;
    @Autowired
    FormRepository formRepository;


    @Test
    void should_create_instance_of_InterviewOutPort() {
        //given


        //when
        var actualFormOutPort = happraisalConfiguration.getFormOutPort(formRepository);

        //then
        BDDAssertions.then(actualFormOutPort)
                .as("Checks that the formOutPort has been instantiated.")
                .isNotNull();
    }


    @TestConfiguration
    public static class HappraisalConfiguratonForTest {

        @TestConfiguration
        public static class FormReposotoryConfiguration {

            @Bean
            FormRepository formRepository() {
                return Mockito.mock(FormRepository.class);
            }

        }

        @TestConfiguration
        public static class PersonRepositoryConfiguration {

            @Bean
            PersonRepository personRepository() {
                return Mockito.mock(PersonRepository.class);
            }

        }

        @TestConfiguration
        public static class QuestionAnswerRepositoryConfiguration {

            @Bean
            QuestionAnswerRepository questionAnswerRepository() {
                return Mockito.mock(QuestionAnswerRepository.class);
            }

        }

        @TestConfiguration
        public static class InterviewRepositoryConfiguration {

            @Bean
            InterviewRepository interviewRepository() {
                return Mockito.mock(InterviewRepository.class);
            }

        }


    }
}