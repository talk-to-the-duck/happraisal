/* (C)2024 */
package dev.t3d.happraisal.api.controller;

import dev.t3d.happraisal.domain.model.Form;
import dev.t3d.happraisal.domain.model.QuestionAnswer;
import dev.t3d.happraisal.domain.service.FormService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forms")
@RequiredArgsConstructor
public class FormController {

  private final FormService formService;

  @GetMapping
  public ResponseEntity<List<Form>> findAll() {
    return ResponseEntity.ok(formService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Form> findById(@PathVariable("id") UUID id) {
    return ResponseEntity.ok(formService.getById(id));
  }

  @PostMapping
  public ResponseEntity<Form> create(@RequestBody Form person) {
    return ResponseEntity.ok(formService.create(person));
  }

  @PostMapping("/{formId}/questions")
  public ResponseEntity<QuestionAnswer> createQuestions(
      @PathVariable("formId") UUID formId, @RequestBody QuestionAnswer question) {
    return ResponseEntity.ok(formService.createQuestion(formId, question));
  }
}
