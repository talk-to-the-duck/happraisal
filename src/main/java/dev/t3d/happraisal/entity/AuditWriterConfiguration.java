package dev.t3d.happraisal.entity;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditWriterConfiguration {

    @Bean
    AuditWriter auditWriter(EntityManagerFactory entityManagerFactory) {
        var auditeDatabaseWriter = new AuditeDatabaseWriter(entityManagerFactory);
        AuditListener.setAuditWriter(auditeDatabaseWriter);
        return auditeDatabaseWriter;
    }


}
