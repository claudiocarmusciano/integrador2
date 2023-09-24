package Consultas;

import com.example.tpintegrador2.Entidades.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InsertEstudiante {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Integrador2");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Estudiante e = new Estudiante(1,"Ariel","Salomé",19,"m",50123456,"vela",25369);
        em.persist(e);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
