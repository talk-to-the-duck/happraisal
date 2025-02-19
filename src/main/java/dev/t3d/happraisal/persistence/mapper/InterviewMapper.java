/* (C)2024 */
package dev.t3d.happraisal.persistence.mapper;

import dev.t3d.happraisal.domain.model.Interview;
import dev.t3d.happraisal.persistence.entity.InterviewEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface InterviewMapper {

  InterviewMapper INSTANCE = Mappers.getMapper(InterviewMapper.class);

  @Mapping(target = "id", source = "id")
  Interview toDomainObject(InterviewEntity entity);

  @Mapping(target = "id", source = "id")
  InterviewEntity toEntity(Interview interview);

  List<Interview> toDomainObjects(List<InterviewEntity> interviewEntities);
}
