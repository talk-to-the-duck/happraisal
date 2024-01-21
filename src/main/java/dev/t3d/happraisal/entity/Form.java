/* (C)2023 */
package dev.t3d.happraisal.entity;

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
public class Form {

  @Id
  @Column(columnDefinition = "uuid")
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  @OneToMany(mappedBy = "form", targetEntity = QuestionAnswer.class, fetch = FetchType.LAZY)
  private Set<QuestionAnswer> questions;
}
