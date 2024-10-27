/* (C)2023-2024 */
package dev.t3d.happraisal.persistence.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class InterviewEntity {

  @Id
  @Column(columnDefinition = "uuid")
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  @Column private Instant date;

  @ManyToOne private PersonEntity manager;

  @ManyToOne private PersonEntity employee;

  @OneToOne
  @JoinColumn(name = "form_id", referencedColumnName = "id")
  private FormEntity form;
}
