/* (C)2023-2024 */
package dev.t3d.happraisal.domain.model;

import java.util.UUID;

public record Person(UUID id, String lastName, String firstName, Boolean isManager) {}
