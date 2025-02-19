/* (C)2024 */
package dev.t3d.happraisal.domain.service;

import dev.t3d.happraisal.domain.model.Person;
import dev.t3d.happraisal.domain.port.PersonOutPort;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonService {

  private final PersonOutPort personOutPort;

  public Person create(Person person) {
    return personOutPort.save(person);
  }

  public Person getById(UUID personId) {
    return personOutPort.getById(personId);
  }

  public List<Person> findAll() {
    return personOutPort.findAll();
  }
}
