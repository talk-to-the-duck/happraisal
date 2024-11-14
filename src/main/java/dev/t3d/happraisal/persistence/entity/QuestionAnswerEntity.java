/* (C)2023-2024 */
package dev.t3d.happraisal.persistence.entity;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"form"})
@Table(name="question_answer")
public class QuestionAnswerEntity {

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
  private FormEntity form;
}
