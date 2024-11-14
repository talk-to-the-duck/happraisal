/* (C)2023-2024 */
package dev.t3d.happraisal.persistence.entity;

import jakarta.persistence.*;

import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "person")
public class PersonEntity {

  @Id
  @Column(columnDefinition = "uuid")
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  @Column private String lastName;

  @Column private String firstName;

  @Column private Boolean isManager;
}
