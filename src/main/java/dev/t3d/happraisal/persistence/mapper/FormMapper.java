/* (C)2024 */
package dev.t3d.happraisal.persistence.mapper;

import dev.t3d.happraisal.domain.model.Form;
import dev.t3d.happraisal.persistence.entity.FormEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface FormMapper {

  FormMapper INSTANCE = Mappers.getMapper(FormMapper.class);

  @Mapping(target = "id", source = "id")
  Form toDomainObject(FormEntity entity);

  @Mapping(target = "questions", source = "questions")
  FormEntity toEntity(Form form);

  List<Form> toDomainObjects(List<FormEntity> formEntities);
}
