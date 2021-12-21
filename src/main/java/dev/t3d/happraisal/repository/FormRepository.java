package dev.t3d.happraisal.repository;

import dev.t3d.happraisal.entity.Form;
import dev.t3d.happraisal.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FormRepository extends JpaRepository<Form, UUID> {

}
