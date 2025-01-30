package dev.t3d.happraisal.repository;

import dev.t3d.happraisal.entity.Audit;
import dev.t3d.happraisal.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuditRepository extends JpaRepository<Audit, UUID> {

}
