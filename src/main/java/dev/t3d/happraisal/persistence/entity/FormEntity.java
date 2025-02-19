/* (C)2023-2024 */
package dev.t3d.happraisal.persistence.entity;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "form")
public class FormEntity {

  @Id
  @Column(columnDefinition = "uuid")
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  @OneToMany(mappedBy = "form", targetEntity = QuestionAnswerEntity.class, fetch = FetchType.LAZY)
  private Set<QuestionAnswerEntity> questions;
}
