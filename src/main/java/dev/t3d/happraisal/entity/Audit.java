package dev.t3d.happraisal.entity;

import lombok.ToString;

import java.time.LocalDate;

@ToString
public class Audit {

    private final String entity;
    private final String objectId;
    private final LocalDate date;
    private final String action;

    public Audit(Form object, String action) {
        entity = object.getClass().getName();
        objectId = String.valueOf(object.getId());
        date = LocalDate.now();
        this.action = action;


    }


}
