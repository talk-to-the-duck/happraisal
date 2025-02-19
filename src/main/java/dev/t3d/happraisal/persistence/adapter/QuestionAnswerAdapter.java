/* (C)2024 */
package dev.t3d.happraisal.persistence.adapter;

import dev.t3d.happraisal.domain.model.QuestionAnswer;
import dev.t3d.happraisal.domain.port.QuestionAnswerOutPort;
import dev.t3d.happraisal.persistence.mapper.QuestionAnswerMapper;
import dev.t3d.happraisal.persistence.repository.QuestionAnswerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuestionAnswerAdapter implements QuestionAnswerOutPort {

  private final QuestionAnswerRepository questionAnswerRepository;

  @Override
  public QuestionAnswer save(QuestionAnswer question) {

    return QuestionAnswerMapper.INSTANCE.toDomainObject(
        questionAnswerRepository.save(QuestionAnswerMapper.INSTANCE.toEntity(question)));
  }
}
