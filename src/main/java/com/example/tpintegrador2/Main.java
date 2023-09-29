package com.example.tpintegrador2;

import com.example.tpintegrador2.csv.CSV;
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
        CSV csv = new CSV();
        csv.readCSV("carreras.csv", "estudiantes.csv");


//            // b) Matricular un estudiante en una carrera
//
//            int estudianteId = 1;
//            int carreraId = 101;
//            estudianteRepository.matricularEstudianteEnCarrera(estudianteId, carreraId);
//
//
//
//            // c) Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple
//
//         //   List<Estudiante> estudiantesOrdenados = estudianteRepository.recuperarEstudiantesOrdenados("nombre");
//
//
//
//            // d) Recuperar un estudiante, en base a su número de libreta universitaria
//
//      //      int estudiantePorLibreta = estudianteRepository.recuperarEstudiantePorLibreta(101);
//
//
//
//            // e) Recuperar todos los estudiantes, en base a su género
//
//         //   List<Estudiante> estudiantesPorGenero = estudianteRepository.recuperarEstudiantesPorGenero("M");
//
//
//
//            // f) Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos
//
//            // Hacer un método en EstudianteRepository
//
//
//
//            // g) Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia
//
//            // Hacer un método en EstudianteRepository
//
//*/
//
//            transaction.commit();
//
//            // Generar reporte de las carreras
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            em.close();
//            entityFactory.closeEntityManagerFactory();
//        }
    }
}
