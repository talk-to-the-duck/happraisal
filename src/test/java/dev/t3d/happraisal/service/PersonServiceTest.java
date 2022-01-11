package dev.t3d.happraisal.service;

import dev.t3d.happraisal.entity.Person;
import dev.t3d.happraisal.repository.PersonRepository;
import org.assertj.core.api.BDDAssertions;
import org.assertj.core.api.BDDSoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

  @Mock private PersonRepository personRepository;

  @InjectMocks private PersonService personService;

  @Test
  void should_create_person() {
    // given
    var expectedPersonToSave =
        new Person(
            UUID.fromString("34d1a1f9-25f9-4aa4-98cd-01531cb8ec34"),
            "lastName",
            "firstName",
            false);

    // when
    personService.create(expectedPersonToSave);

    // then
    var personArgumentCaptor = ArgumentCaptor.forClass(Person.class);
    BDDMockito.then(personRepository).should().save(personArgumentCaptor.capture());
    var actualPersonToSave = personArgumentCaptor.getValue();
    BDDAssertions.then(actualPersonToSave)
        .as("The saved person should be same as person to save with an id")
        .isEqualTo(expectedPersonToSave);
  }

  @Test
  void should_get_person_by_id() {
    // given
    var personId = UUID.fromString("34d1a1f9-25f9-4aa4-98cd-01531cb8ec34");
    final Person expectedPerson = new Person(personId, "lastName", "firstName", false);
    BDDMockito.given(personRepository.getById(personId)).willReturn(expectedPerson);

    // when
    final Person actualPerson = personService.getById(personId);

    // then
    BDDAssertions.then(actualPerson).isEqualTo(expectedPerson);
  }

  @Test
  void should_return_all_persons() {
    // Setup
    // Configure PersonRepository.findAll(...).
    final List<Person> expectedPeople =
        List.of(
            new Person(
                UUID.fromString("34d1a1f9-25f9-4aa4-98cd-01531cb8ec34"),
                "lastName",
                "firstName",
                false));
    BDDMockito.given(personRepository.findAll()).willReturn(expectedPeople);

    // when
    final List<Person> actualPeople = personService.findAll();

    // then
    BDDMockito.then(personRepository).should().findAll();
    BDDSoftAssertions.thenSoftly(
        softly -> {
          softly.then(actualPeople).isNotNull();
          softly.then(actualPeople).hasSize(1);
        });
  }

  @Test
  void should_return_an_empty_list_when_no_person_exists() {
    // Setup
    BDDMockito.given(personRepository.findAll()).willReturn(Collections.emptyList());

    // when
    final List<Person> actualPeople = personService.findAll();

    // then
    BDDMockito.then(personRepository).should().findAll();
    BDDAssertions
            .then(actualPeople)
            .as("the people list should be empty")
            .isEqualTo(Collections.emptyList());
  }
}
