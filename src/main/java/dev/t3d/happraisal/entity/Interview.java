package dev.t3d.happraisal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Interview {

    @Id
    @Column(columnDefinition = "uuid")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column
    private Instant date;

    @ManyToOne
    private Person manager;

    @ManyToOne
    private Person employee;

    @OneToOne(cascade =  CascadeType.DETACH)
    @JoinColumn(name = "form_id", referencedColumnName = "id")
    private Form form;
}
