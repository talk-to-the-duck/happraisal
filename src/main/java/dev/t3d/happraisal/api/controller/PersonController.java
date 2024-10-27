/* (C)2024 */
package dev.t3d.happraisal.api.controller;

import dev.t3d.happraisal.domain.model.Person;
import dev.t3d.happraisal.domain.service.PersonService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

  private final PersonService personService;

  @GetMapping
  public ResponseEntity<List<Person>> findAll() {
    return ResponseEntity.ok(personService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Person> findById(@PathVariable("id") UUID id) {

    return ResponseEntity.ok(personService.getById(id));
  }

  @PostMapping
  public ResponseEntity<Person> create(@RequestBody Person person) {
    return ResponseEntity.ok(personService.create(person));
  }
}
