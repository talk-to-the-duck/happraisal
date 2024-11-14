/* (C)2023-2024 */
package dev.t3d.happraisal.domain.model;

import java.util.Set;
import java.util.UUID;

public record Form(UUID id, Set<QuestionAnswer> questions) {}
