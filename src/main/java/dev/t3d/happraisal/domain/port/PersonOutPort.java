/* (C)2024 */
package dev.t3d.happraisal.domain.port;

import dev.t3d.happraisal.domain.model.Person;
import java.util.List;
import java.util.UUID;

public interface PersonOutPort {
  Person save(Person person);

  Person getById(UUID personId);

  List<Person> findAll();
}
