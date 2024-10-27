/* (C)2024 */
package dev.t3d.happraisal.persistence.adapter;

import dev.t3d.happraisal.domain.model.Person;
import dev.t3d.happraisal.domain.port.PersonOutPort;
import dev.t3d.happraisal.persistence.mapper.PersonMapper;
import dev.t3d.happraisal.persistence.repository.PersonRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonAdapter implements PersonOutPort {
  private final PersonRepository perssonRepository;

  @Override
  public Person save(Person person) {
    return PersonMapper.INSTANCE.toDomainObject(
        perssonRepository.save(PersonMapper.INSTANCE.toEntity(person)));
  }

  @Override
  public Person getById(UUID personId) {
    return PersonMapper.INSTANCE.toDomainObject(perssonRepository.getById(personId));
  }

  @Override
  public List<Person> findAll() {
    return PersonMapper.INSTANCE.toDomainObjects(perssonRepository.findAll());
  }
}
