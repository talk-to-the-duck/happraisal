/* (C)2023 */
package dev.t3d.happraisal.entity;

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
public class Interview {

  @Id
  @Column(columnDefinition = "uuid")
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  @Column private Instant date;

  @ManyToOne private Person manager;

  @ManyToOne private Person employee;

  @OneToOne
  @JoinColumn(name = "form_id", referencedColumnName = "id")
  private Form form;
}
