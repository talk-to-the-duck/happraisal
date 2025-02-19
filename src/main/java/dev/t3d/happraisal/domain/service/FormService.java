/* (C)2024 */
package dev.t3d.happraisal.domain.service;

import dev.t3d.happraisal.domain.model.Form;
import dev.t3d.happraisal.domain.model.QuestionAnswer;
import dev.t3d.happraisal.domain.port.FormOutPort;
import dev.t3d.happraisal.domain.port.QuestionAnswerOutPort;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FormService {

  private final FormOutPort formOutPort;
  private final QuestionAnswerOutPort questionAnswerOutPort;

  public Form create(Form form) {
    return formOutPort.save(form);
  }

  public Form getById(UUID formId) {
    return formOutPort.getById(formId);
  }

  public List<Form> findAll() {
    return formOutPort.findAll();
  }

  public QuestionAnswer createQuestion(UUID formId, QuestionAnswer question) {

    var form = getById(formId);
    // question.setForm(form); //to fix
    form.questions().add(question);

    return questionAnswerOutPort.save(question);
  }
}
