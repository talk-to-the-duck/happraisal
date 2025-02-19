/* (C)2024 */
package dev.t3d.happraisal.repository;

import dev.t3d.happraisal.AbstractIntegrationTestWithDatabase;
import dev.t3d.happraisal.persistence.entity.FormEntity;
import dev.t3d.happraisal.persistence.entity.QuestionAnswerEntity;
import dev.t3d.happraisal.persistence.repository.FormRepository;
import java.util.Set;
import org.assertj.core.api.BDDAssertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@ActiveProfiles("it")
public class FormRepositoryITest extends AbstractIntegrationTestWithDatabase {
  @Autowired private FormRepository formRepository;

  @Test
  void should_create_form() {
    // given
    var questionAnswer =
        new QuestionAnswerEntity(null, "What do you think about your work this year", null, null);
    var formToSave = new FormEntity(null, Set.of(questionAnswer));

    // when
    var actualForm = formRepository.save(formToSave);

    // then
    var idNotNull =
        new Condition<FormEntity>((FormEntity form) -> form.getId() != null, "form id not null");
    var asOneQuestion =
        new Condition<FormEntity>(
            (FormEntity form) -> form.getQuestions().size() == 1, "form has only one question");
    BDDAssertions.then(actualForm)
        .as("Check if a new form has been saved.")
        .has(idNotNull)
        .has(asOneQuestion);
  }

  @Test
  @Sql(scripts = {"/sql_scripts/initialize_form.sql"})
  void should_find_all_form() {
    // when
    var actualForms = formRepository.findAll();

    // then
    BDDAssertions.then(actualForms).as("Check if all forms has been found").hasSize(2);
  }
}
