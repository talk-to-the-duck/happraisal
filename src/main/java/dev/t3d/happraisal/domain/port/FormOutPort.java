/* (C)2024 */
package dev.t3d.happraisal.domain.port;

import dev.t3d.happraisal.domain.model.Form;
import java.util.List;
import java.util.UUID;

public interface FormOutPort {
  Form save(Form form);

  Form getById(UUID formId);

  List<Form> findAll();
}
