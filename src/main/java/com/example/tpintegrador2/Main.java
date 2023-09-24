/*package com.example.tpintegrador2;

import com.example.tpintegrador2.Entidades.Estudiante;
import com.example.tpintegrador2.Factory.EntityFactory;
import com.example.tpintegrador2.Interfaces.EstudianteRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityFactory entityFactory = EntityFactory.getInstance();

        EntityManager em = entityFactory.createEntityManager();

        try {

            EntityTransaction transaction = em.getTransaction();
            transaction.begin();


            // a) Dar de alta un estudiante

            // DEBEMOS CREAR UNA CLASE EstudianteRepositoryImpl QUE IMPLEMENTE EstudianteRepository
            // YA QUE EstudianteRepository NO PUEDE SER INSTANCIADA.
            EstudianteRepository estudianteRepository = new EstudianteRepositoryImpl(em);
            estudianteRepository.altaEstudiante(1, "Nombre1", "Apellido1", 20, "M", 123456, "Ciudad1", 101);



            // b) Matricular un estudiante en una carrera

            int estudianteId = 1;
            int carreraId = 101;
            estudianteRepository.matricularEstudianteEnCarrera(estudianteId, carreraId);



            // c) Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple

            List<Estudiante> estudiantesOrdenados = estudianteRepository.recuperarEstudiantesOrdenados("nombre");



            // d) Recuperar un estudiante, en base a su número de libreta universitaria

            Estudiante estudiantePorLibreta = estudianteRepository.recuperarEstudiantePorLibreta(101);



            // e) Recuperar todos los estudiantes, en base a su género

            List<Estudiante> estudiantesPorGenero = estudianteRepository.recuperarEstudiantesPorGenero("M");



            // f) Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos

            // Hacer un método en EstudianteRepository



            // g) Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia

            // Hacer un método en EstudianteRepository



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
*/