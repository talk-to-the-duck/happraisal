package dev.t3d.happraisal.entity;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.Setter;


public class AuditListener {

    @Setter
    private static AuditWriter auditWriter;


    @PrePersist
    private void beforeCreate(AuditableEntity auditableEntity) {
        var audit = new Audit(auditableEntity, "CREATE");
        System.out.println(audit.toString());
        auditWriter.write(audit);

    }


    @PreUpdate
    private void beforeUpdate(AuditableEntity object) {
        var audit = new Audit((Form) object, "UPDATE");
        System.out.println(audit.toString());
        auditWriter.write(audit);
    }


    @PreRemove
    private void beforeDelete(AuditableEntity object) {
        var audit = new Audit((Form) object, "DELETE");
        System.out.println(audit.toString());
        auditWriter.write(audit);

    }


}
