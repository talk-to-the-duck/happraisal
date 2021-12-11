package dev.t3d.happraisal.controller;

import dev.t3d.happraisal.service.InterviewService;
import dev.t3d.happraisal.entity.Interview;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<Interview> findById(@PathParam("id")UUID id) {
        return ResponseEntity.ok(interviewService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Interview> create(@RequestBody Interview interview) {
        return ResponseEntity.ok(interviewService.create(interview));
    }
}
