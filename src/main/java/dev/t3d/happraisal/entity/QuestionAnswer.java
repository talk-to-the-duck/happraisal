package dev.t3d.happraisal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswer {

    @Id
    @Getter
    @Setter
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column
    @Getter
    @Setter
    private String question;

    @Column
    @Getter
    @Setter
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    private Form form;
}
