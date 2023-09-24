package Consultas;

import com.example.tpintegrador2.Entidades.Carrera;
import com.example.tpintegrador2.Entidades.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class matricularEstudiante {
    Estudiante estudiante;
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("integrador2");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Carrera c = new Carrera(1, "TUDAI");
        em.persist(c);

        estudiante.setCarreras(c);



        em.getTransaction().commit();
        em.close();
        emf.close();

    }
}
