package dev.t3d.happraisal.entity;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

public class AuditForm {


    @PrePersist
    private void beforeCreate(AuditableEntity object) {
        var audit = new Audit(object, "CREATE");
        System.out.println(audit.toString());
    }


    @PreUpdate
    private void beforeUpdate(Object object) {
        var audit = new Audit((Form) object, "UPDATE");
        System.out.println(audit.toString());
    }



    @PreRemove
    private void beforeDelete(Object object) {
        var audit = new Audit((Form) object, "DELETE");
        System.out.println(audit.toString());
    }


}
