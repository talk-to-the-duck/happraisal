package dev.t3d.happraisal.service;

import dev.t3d.happraisal.repository.InterviewRepository;
import dev.t3d.happraisal.entity.Interview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InterviewService {

    private final InterviewRepository interviewRepository;

    public Interview create(Interview interview) {


        return interviewRepository.save(interview);
    }

    public Interview getById(UUID interviewId) {
        return interviewRepository.getById(interviewId);
    }

    public List<Interview> findAll() {
        return interviewRepository.findAll();
    }
}
