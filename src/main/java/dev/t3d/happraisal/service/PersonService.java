package dev.t3d.happraisal.service;

import dev.t3d.happraisal.entity.Person;
import dev.t3d.happraisal.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public Person getById(UUID personId) {
        return personRepository.getById(personId);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
