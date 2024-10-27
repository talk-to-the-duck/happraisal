/* (C)2024 */
package dev.t3d.happraisal.persistence.mapper;

import dev.t3d.happraisal.domain.model.QuestionAnswer;
import dev.t3d.happraisal.persistence.entity.QuestionAnswerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface QuestionAnswerMapper {

  QuestionAnswerMapper INSTANCE = Mappers.getMapper(QuestionAnswerMapper.class);

  @Mapping(target = "id", source = "id")
  QuestionAnswer toDomainObject(QuestionAnswerEntity entity);

  @Mapping(target = "id", source = "id")
  QuestionAnswerEntity toEntity(QuestionAnswer domainObject);
}
