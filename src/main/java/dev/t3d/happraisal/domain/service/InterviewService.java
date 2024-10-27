/* (C)2024 */
package dev.t3d.happraisal.domain.service;

import dev.t3d.happraisal.domain.model.Interview;
import dev.t3d.happraisal.domain.port.InterviewOutPort;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InterviewService {

  private final InterviewOutPort interviewOutPort;

  public Interview create(Interview interview) {
    return interviewOutPort.save(interview);
  }

  public Interview getById(UUID interviewId) {
    return interviewOutPort.getById(interviewId);
  }

  public List<Interview> findAll() {
    return interviewOutPort.findAll();
  }
}
