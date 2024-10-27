/* (C)2024 */
package dev.t3d.happraisal.domain.model;

import java.util.Set;

public record FormCreationRequest(Set<QuestionAnswer> questionAnswers) {}
