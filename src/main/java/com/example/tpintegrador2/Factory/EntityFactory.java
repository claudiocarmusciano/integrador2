package com.example.tpintegrador2.Factory;

//import javax.persistence.Persistence;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityFactory {

        private final EntityManagerFactory emf;

        private EntityFactory() {

            emf = Persistence.createEntityManagerFactory("integrador2");
        }

        private static class SingletonHelper {
            private static final EntityFactory INSTANCE = new EntityFactory();
        }

        public static EntityFactory getInstance() {

            return SingletonHelper.INSTANCE;
        }

        public EntityManager createEntityManager() {

            return emf.createEntityManager();
        }

        public void closeEntityManagerFactory() {
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
}