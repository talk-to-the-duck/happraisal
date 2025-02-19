/* (C)2024 */
package dev.t3d.happraisal.persistence.mapper;

import dev.t3d.happraisal.domain.model.Person;
import dev.t3d.happraisal.persistence.entity.PersonEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PersonMapper {

  PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

  @Mapping(target = "id", source = "id")
  Person toDomainObject(PersonEntity entity);

  @Mapping(target = "id", source = "id")
  PersonEntity toEntity(Person domainObject);

  List<Person> toDomainObjects(List<PersonEntity> personEntities);
}
