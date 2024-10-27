/* (C)2024 */
package dev.t3d.happraisal.persistence.adapter;

import dev.t3d.happraisal.domain.model.Form;
import dev.t3d.happraisal.domain.port.FormOutPort;
import dev.t3d.happraisal.persistence.mapper.FormMapper;
import dev.t3d.happraisal.persistence.repository.FormRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FormAdapter implements FormOutPort {

  private final FormRepository formRepository;

  @Override
  public Form save(Form form) {
    return FormMapper.INSTANCE.toDomainObject(
        formRepository.save(FormMapper.INSTANCE.toEntity(form)));
  }

  @Override
  public Form getById(UUID formId) {
    return FormMapper.INSTANCE.toDomainObject(formRepository.getReferenceById(formId));
  }

  @Override
  public List<Form> findAll() {
    return FormMapper.INSTANCE.toDomainObjects(formRepository.findAll());
  }
}
