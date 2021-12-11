package dev.t3d.happraisal.controller;

import dev.t3d.happraisal.entity.Form;
import dev.t3d.happraisal.entity.Person;
import dev.t3d.happraisal.entity.QuestionAnswer;
import dev.t3d.happraisal.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;
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
    public ResponseEntity<QuestionAnswer> createQuestions(@PathVariable("formId") UUID formId , @RequestBody QuestionAnswer question) {
        return ResponseEntity.ok(formService.createQuestion(formId, question));
    }
}