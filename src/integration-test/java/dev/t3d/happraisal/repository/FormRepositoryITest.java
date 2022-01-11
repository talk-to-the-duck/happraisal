package dev.t3d.happraisal.repository;

import dev.t3d.happraisal.entity.Form;
import dev.t3d.happraisal.entity.QuestionAnswer;
import org.assertj.core.api.BDDAssertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Set;

@DataJpaTest
@ActiveProfiles("it")
public class FormRepositoryITest {
    @Autowired
    private FormRepository formRepository;

    @Test
    void should_create_form() {
        // given
        var questionAnswer = new QuestionAnswer(null, "What do you think about your work this year", null, null);
        var formToSave = new Form(null, Set.of(questionAnswer));

        // when
        var actualForm = formRepository.save(formToSave);

        // then
        var idNotNull = new Condition<Form>((Form form) -> form.getId() != null, "form id not null");
        var asOneQuestion = new Condition<Form>((Form form) -> form.getQuestions().size() == 1, "form has pnly one question");
        BDDAssertions.then(actualForm)
                .as("Check if a new form has been save.")
                .has(idNotNull)
                .has(asOneQuestion);
    }


    @Test
    @Sql(scripts = {"/sql_scripts/initialize_form.sql"} )
    void should_find_all_form() {
        // when
        var actualForms = formRepository.findAll();

        // then
        BDDAssertions.then(actualForms)
                .as("Check if all forms has been found")
                .hasSize(2);
    }
}
