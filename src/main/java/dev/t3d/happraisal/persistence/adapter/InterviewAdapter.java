/* (C)2024 */
package dev.t3d.happraisal.persistence.adapter;

import dev.t3d.happraisal.domain.model.Interview;
import dev.t3d.happraisal.domain.port.InterviewOutPort;
import dev.t3d.happraisal.persistence.mapper.InterviewMapper;
import dev.t3d.happraisal.persistence.repository.InterviewRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InterviewAdapter implements InterviewOutPort {

  private final InterviewRepository interviewRepository;

  @Override
  public Interview save(Interview interview) {
    return InterviewMapper.INSTANCE.toDomainObject(
        interviewRepository.save(InterviewMapper.INSTANCE.toEntity(interview)));
  }

  @Override
  public Interview getById(UUID interviewId) {
    return InterviewMapper.INSTANCE.toDomainObject(
        interviewRepository.getReferenceById(interviewId));
  }

  @Override
  public List<Interview> findAll() {
    return InterviewMapper.INSTANCE.toDomainObjects(interviewRepository.findAll());
  }
}
