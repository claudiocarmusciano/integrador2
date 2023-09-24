package Consultas;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class getAllEstudiante {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("integrador2");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        //em.persist();


        em.getTransaction().commit();
        em.close();
        emf.close();

    }
}
