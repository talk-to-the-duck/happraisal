/* (C)2023 */
package dev.t3d.happraisal.entity;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"form"})
public class QuestionAnswer {

  @Id
  @Column(columnDefinition = "uuid")
  @Getter
  @Setter
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  @Column @Getter @Setter private String question;

  @Column @Getter @Setter private String answer;

  @ManyToOne(fetch = FetchType.LAZY)
  @Setter
  private Form form;
}
