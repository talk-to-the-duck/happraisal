package dev.t3d.happraisal.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;


public class AuditeDatabaseWriter implements AuditWriter {
    private final EntityManagerFactory entityManagerFactory;

    public AuditeDatabaseWriter(@Lazy EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    @Transactional
    public void write(Audit audit) {
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // ✅ Pas de dépendance directe
        entityManager.getTransaction().begin();

        entityManager.persist(audit);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
