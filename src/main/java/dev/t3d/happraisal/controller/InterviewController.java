/* (C)2023 */
package dev.t3d.happraisal.controller;

import dev.t3d.happraisal.entity.Interview;
import dev.t3d.happraisal.service.InterviewService;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/interviews")
@RequiredArgsConstructor
public class InterviewController {

  private final InterviewService interviewService;

  @GetMapping
  public ResponseEntity<List<Interview>> findAll() {
    return ResponseEntity.ok(interviewService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Interview> findById(@PathVariable("id") UUID id) {
    return ResponseEntity.ok(interviewService.getById(id));
  }

  @PostMapping
  public ResponseEntity<Interview> create(@RequestBody Interview interview) {
    var savedInterview = interviewService.create(interview);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedInterview.getId())
            .toUri();
    return ResponseEntity.created(location).build();
  }
}
