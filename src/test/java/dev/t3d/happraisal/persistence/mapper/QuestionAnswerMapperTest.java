/* (C)2024 */
package dev.t3d.happraisal.persistence.mapper;

import dev.t3d.happraisal.domain.model.QuestionAnswer;
import dev.t3d.happraisal.persistence.entity.QuestionAnswerEntity;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuestionAnswerMapperTest {

  @Test
  void should_map_entity_to_domain_object() {

    // given
    var id = UUID.randomUUID();
    var question = "What is your name";
    var answer = "Emmanuel";
    QuestionAnswerEntity questionAnswerEntity =
        new QuestionAnswerEntity(id, question, answer, null);

    // when
    QuestionAnswer actualQuestionAnswer =
        QuestionAnswerMapper.INSTANCE.toDomainObject(questionAnswerEntity);

    // then
    Assertions.assertThat(actualQuestionAnswer)
        .as("Check that the entity has been mapped into domain object")
        .hasFieldOrPropertyWithValue("id", id)
        .hasFieldOrPropertyWithValue("question", question)
        .hasFieldOrPropertyWithValue("answer", answer);
  }
}
