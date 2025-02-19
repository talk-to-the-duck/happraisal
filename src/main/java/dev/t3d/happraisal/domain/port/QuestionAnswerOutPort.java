/* (C)2024 */
package dev.t3d.happraisal.domain.port;

import dev.t3d.happraisal.domain.model.QuestionAnswer;

public interface QuestionAnswerOutPort {
  QuestionAnswer save(QuestionAnswer question);
}
