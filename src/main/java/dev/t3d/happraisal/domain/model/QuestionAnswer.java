/* (C)2023-2024 */
package dev.t3d.happraisal.domain.model;

import java.util.UUID;
import lombok.*;

public record QuestionAnswer(UUID id, String question, String answer) {}
