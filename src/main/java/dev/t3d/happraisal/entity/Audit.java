package dev.t3d.happraisal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Audit {


    @Id
    @Column(columnDefinition = "uuid")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String entity;

    private String objectId;

    private LocalDate date;

    private String action;

    public Audit(AuditableEntity object, String action) {
        entity = object.getClass().getName();
        objectId = object.getObjectId();
        date = LocalDate.now();
        this.action = action;


    }
}
