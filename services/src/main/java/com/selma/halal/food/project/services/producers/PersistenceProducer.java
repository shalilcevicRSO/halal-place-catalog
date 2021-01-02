package com.selma.halal.food.project.services.producers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.inject.Qualifier;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Produces;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public class PersistenceProducer {

    @PersistenceContext(unitName = "halal-place-catalog-jpa")
    private EntityManager entityManager;

    @Produces
    @ApplicationScoped
    public EntityManager entityManager(){
        return entityManager;
    }


//    @PersistenceUnit(unitName = "halal-place-catalog-jpa")
//    private EntityManagerFactory emf;
//
//
//    @Produces
//    @ApplicationScoped
//    public EntityManager getEntityManager() {
//        return emf.createEntityManager();
//    }
//
//
//    public void disposeEntityManager(@org.jetbrains.annotations.NotNull @Disposes @NotNull EntityManager entityManager) {
//        if(entityManager.isOpen()) {
//            entityManager.close();
//        }
//    }
}
