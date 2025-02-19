/* (C)2024 */
package dev.t3d.happraisal.domain.port;

import dev.t3d.happraisal.domain.model.Interview;
import java.util.List;
import java.util.UUID;

public interface InterviewOutPort {
  Interview save(Interview interview);

  Interview getById(UUID interviewId);

  List<Interview> findAll();
}
