package com.example.tpintegrador2;

import com.example.tpintegrador2.Entidades.Estudiante;
import com.example.tpintegrador2.Factory.EntityFactory;
import com.example.tpintegrador2.Factory.FactoryRepository;
import com.example.tpintegrador2.Factory.FactoryRepositoryImpl;
import com.example.tpintegrador2.Interfaces.EstudianteRepository;
import com.example.tpintegrador2.Repository.EstudianteRepositoryImpl;

//import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;
//import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityFactory entityFactory = EntityFactory.getInstance();

        EntityManager em = entityFactory.createEntityManager();

        try {

            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            
            //creo la instancia del factoryImpl
            FactoryRepository fr = FactoryRepositoryImpl.getInstancia();
            //llamo a estudiante
            //int id , String nombre, String apellido, int edad, String genero, int nroDocumento, String ciudadResidencia, int nroLibreta
            fr.getEstudianteRepository().altaEstudiante(1,"Leandro","Ramos", 25, "M" , 40943011, "Olavarria" , 4325);

            

            // a) Dar de alta un estudiante

            // DEBEMOS CREAR UNA CLASE EstudianteRepositoryImpl QUE IMPLEMENTE EstudianteRepository
   /*         // YA QUE EstudianteRepository NO PUEDE SER INSTANCIADA.
            EstudianteRepository estudianteRepository = new EstudianteRepositoryImpl();
            estudianteRepository.altaEstudiante(1, "Nombre1", "Apellido1", 20, "M", 123456, "Ciudad1", 101);



            // b) Matricular un estudiante en una carrera

            int estudianteId = 1;
            int carreraId = 101;
            estudianteRepository.matricularEstudianteEnCarrera(estudianteId, carreraId);



            // c) Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple

         //   List<Estudiante> estudiantesOrdenados = estudianteRepository.recuperarEstudiantesOrdenados("nombre");



            // d) Recuperar un estudiante, en base a su número de libreta universitaria

      //      int estudiantePorLibreta = estudianteRepository.recuperarEstudiantePorLibreta(101);



            // e) Recuperar todos los estudiantes, en base a su género

         //   List<Estudiante> estudiantesPorGenero = estudianteRepository.recuperarEstudiantesPorGenero("M");



            // f) Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos

            // Hacer un método en EstudianteRepository



            // g) Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia

            // Hacer un método en EstudianteRepository

*/

            transaction.commit();

            // Generar reporte de las carreras

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            entityFactory.closeEntityManagerFactory();
        }
    }
}
