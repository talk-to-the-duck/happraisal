/* (C)2024 */
package dev.t3d.happraisal.persistence.repository;

import dev.t3d.happraisal.persistence.entity.FormEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends JpaRepository<FormEntity, UUID> {}
