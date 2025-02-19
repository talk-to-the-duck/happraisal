/* (C)2024 */
package dev.t3d.happraisal.persistence.repository;

import dev.t3d.happraisal.persistence.entity.PersonEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, UUID> {}
