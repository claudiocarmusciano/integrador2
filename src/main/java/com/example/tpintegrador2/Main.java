package com.example.tpintegrador2;

import com.example.tpintegrador2.CSV.CSV;
import com.example.tpintegrador2.DTO.EstudianteDTO;
import com.example.tpintegrador2.Entidades.Estudiante;
import com.example.tpintegrador2.Interfaces.EstudianteRepository;
import com.example.tpintegrador2.Repository.EstudianteRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {

        ///////  Código para borrar las tablas de la base de datos  /////////////////////
        String url = "jdbc:mysql://localhost:3306/integrador2"; // Cambia esto por la URL de tu base de datos
        String usuario = "root"; // Cambia esto por tu nombre de usuario
        String contrasena = ""; // Cambia esto por tu contraseña

        // Nombres de las tablas a eliminar
        String[] tablas = {"Estudiante_Carrera", "Carrera", "Estudiante"};

        // Conectarse a la base de datos y eliminar las tablas
        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasena)) {
            Statement statement = conexion.createStatement();

            for (String tabla : tablas) {
                String sentenciaSQL = "DROP TABLE IF EXISTS " + tabla;
                statement.executeUpdate(sentenciaSQL);
                System.out.println("Tabla " + tabla + " eliminada exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al eliminar las tablas.");
        }

        CSV csv = new CSV();
        csv.readCSV("carreras.csv", "estudiantes.csv","estudianteCarrera.csv");

        // a) Dar de alta un estudiante

        System.out.println("Dar de alta un estudiante:");
        EstudianteRepository estudianteRepository = new EstudianteRepositoryImpl();
        estudianteRepository.altaEstudiante("Andrea", "Gonzalez", 25, "Fame", 40833743, "Tandil", 4632);
        System.out.println();

        // b) Matricular un estudiante en una carrera

        System.out.println("Matricular un estudiante en una carrera:");
        int estudianteId = 105;  // Gonzalez Andrea
        int carreraId = 1;  // TUDAI
        estudianteRepository.matricularEstudianteEnCarrera(estudianteId, carreraId);
        System.out.println();


        //c) Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple

        System.out.println("Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple");
        System.out.printf("%-40s\t%-10s\t%-30s\t%20s\t%20s\t%20s\t%20s %n", "Nombre", "Género", "Ciudad", "Edad", "DNI", "Nro. Libreta", "Carreras");
        List<EstudianteDTO> estudiantesOrdenados = estudianteRepository.recuperarEstudiantesOrdenados("nombre");
        for (EstudianteDTO estudiante : estudiantesOrdenados) {
            System.out.println(estudiante);
        }
        System.out.println();


       // d) Recuperar un estudiante, en base a su número de libreta universitaria

        System.out.println("Recuperar un estudiante, en base a su número de libreta universitaria");
        EstudianteDTO estudiantePorLibreta = estudianteRepository.recuperarEstudiantePorLibreta(34978);
        System.out.println(estudiantePorLibreta);
        System.out.println();



        // e) Recuperar todos los estudiantes, en base a su género

        System.out.println("Recuperar todos los estudiantes, en base a su género");
        System.out.printf("%-40s\t%-10s\t%-30s\t%20s\t%20s\t%20s\t%20s %n", "Nombre", "Género", "Ciudad", "Edad", "DNI", "Nro. Libreta", "Carreras");
        List<EstudianteDTO> estudiantesPorGenero = estudianteRepository.recuperarEstudiantesPorGenero("Male");
        for (EstudianteDTO estudiante : estudiantesPorGenero) {
            System.out.println(estudiante);
        }
        System.out.println();


       // f) Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos





          // g) Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia

            System.out.println("Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia");
            List<EstudianteDTO> estudiantesRecuperados = estudianteRepository.getEstudiantesPorCarreraYCiudad("TUDAI", "Ingenieria de Sistemas");
            for (EstudianteDTO estudiante : estudiantesRecuperados) {
                System.out.println(estudiante);
            }
            System.out.println();


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
