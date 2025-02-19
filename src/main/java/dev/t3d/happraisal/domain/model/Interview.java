/* (C)2023-2024 */
package dev.t3d.happraisal.domain.model;

import java.time.Instant;
import java.util.UUID;

public record Interview(UUID id, Instant date, Person manager, Person employee, Form form) {}
