package dev.t3d.happraisal.service;

import dev.t3d.happraisal.entity.Form;
import dev.t3d.happraisal.entity.QuestionAnswer;
import dev.t3d.happraisal.repository.FormRepository;
import dev.t3d.happraisal.repository.QuestionAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FormService {

  private final FormRepository formRepository;
  private final QuestionAnswerRepository questionAnswerRepository;

  public Form create(Form form) {
    return formRepository.save(form);
  }

  public Form getById(UUID formId) {
    return formRepository.getById(formId);
  }

  public List<Form> findAll() {
    return formRepository.findAll();
  }

  public QuestionAnswer createQuestion(UUID formId, QuestionAnswer question) {

    var form = getById(formId);
    question.setForm(form);
    form.getQuestions().add(question);

    return questionAnswerRepository.save(question);
  }
}
