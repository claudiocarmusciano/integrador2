package com.example.tpintegrador2;

import com.example.tpintegrador2.CSV.CSV;
import com.example.tpintegrador2.DTO.CarreraDTO;
import com.example.tpintegrador2.DTO.EstudianteDTO;
import com.example.tpintegrador2.DTO.Estudiante_CarreraDTO;
import com.example.tpintegrador2.Entidades.Estudiante;
import com.example.tpintegrador2.Interfaces.CarreraRepository;
import com.example.tpintegrador2.Interfaces.EstudianteRepository;
import com.example.tpintegrador2.Interfaces.Estudiante_CarreraRepository;
import com.example.tpintegrador2.Repository.CarreraRepositoryImpl;
import com.example.tpintegrador2.Repository.EstudianteRepositoryImpl;
import com.example.tpintegrador2.Repository.Estudiante_CarreraRepositoryImpl;

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
        EstudianteRepository estudianteRepository = new EstudianteRepositoryImpl();
        CarreraRepository carreraRepository = new CarreraRepositoryImpl();

        // a) Dar de alta un estudiante

        String nombre = "Lionel";
        String apellido = "Messi";
        int edad = 33;
        String genero = "Male";
        int dni = 23128286;
        String ciudad = "Miami";
        int libreta = 2342234;

        System.out.println("Dar de alta un estudiante (" + nombre + " " + apellido + ", " + edad + " años, " + genero + ", DNI " + dni + ", vive en " + ciudad + ", con libreta nro. " + libreta);

        estudianteRepository.altaEstudiante(nombre, apellido, edad, genero, dni, ciudad, libreta);
        System.out.println();

        // b) Matricular un estudiante en una carrera

        int estudianteId = 105;  // Gonzalez Andrea
        int carreraId = 1;  // TUDAI
        System.out.println("Matricular un estudiante (idEstudiante " + estudianteId + ") en una carrera (idCarrera: " + carreraId + ")");
        estudianteRepository.matricularEstudianteEnCarrera(estudianteId, carreraId);
        System.out.println();


        //c) Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple

        String criterioOrdenamiento = "nombre";
            System.out.println("Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple (ordenado por " + criterioOrdenamiento + ")");
        System.out.printf("%-37s\t%-10s\t%-37s\t%11s\t%20s\t%20s\t%20s %n", "Nombre", "Género", "Ciudad", "Edad", "DNI", "Nro. Libreta", "Carreras");
        List<EstudianteDTO> estudiantesOrdenados = estudianteRepository.recuperarEstudiantesOrdenados(criterioOrdenamiento);
        for (EstudianteDTO estudiante : estudiantesOrdenados) {
            System.out.println(estudiante);
        }
        System.out.println();


       // d) Recuperar un estudiante, en base a su número de libreta universitaria
        int nroLibreta = 34978;
        System.out.println("Recuperar un estudiante, en base a su número de libreta universitaria (nro. de libreta " + nroLibreta + ")");
        EstudianteDTO estudiantePorLibreta = estudianteRepository.recuperarEstudiantePorLibreta(nroLibreta);
        System.out.println(estudiantePorLibreta);
        System.out.println();



        // e) Recuperar todos los estudiantes, en base a su género
        String filtroGenero = "Male";
        System.out.println("Recuperar todos los estudiantes, en base a su género (" + filtroGenero + ")");
        System.out.printf("%-37s\t%-10s\t%-37s\t%11s\t%20s\t%20s\t%20s %n", "Nombre", "Género", "Ciudad", "Edad", "DNI", "Nro. Libreta", "Carreras");
        List<EstudianteDTO> estudiantesPorGenero = estudianteRepository.recuperarEstudiantesPorGenero(genero);
        for (EstudianteDTO estudiante : estudiantesPorGenero) {
            System.out.println(estudiante);
        }
        System.out.println();


       // f) Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos

        //obtenerCarrerasInscriptos

        System.out.println("Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos");
        List<CarreraDTO> carreraRecuperados = carreraRepository.obtenerCarrerasInscriptos();
        for (CarreraDTO carrera : carreraRecuperados) {
            System.out.println(carrera);
        }
        System.out.println();


          // g) Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia

            String carreraFiltro = "TUDAI";
            String ciudadFiltro = "Arroio do Meio";
            System.out.println("Recuperar los estudiantes de una determinada carrera (" + carreraFiltro + "), filtrado por ciudad de residencia (" + ciudadFiltro + ")");
            List<EstudianteDTO> estudiantesRecuperados = estudianteRepository.getEstudiantesPorCarreraYCiudad(carreraFiltro, ciudadFiltro);
            for (EstudianteDTO estudiante : estudiantesRecuperados) {
                System.out.println(estudiante);
            }
            System.out.println();

            
           /*Ejercicio 3.-
            
            
            Generar un reporte de las carreras, que para cada carrera incluya información de los
			inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar
			los años de manera cronológica
            
          */


        System.out.println("Reporte de carreras, con información de los inscriptos y egresados por año.");
        Estudiante_CarreraRepository estudianteCarreraRepository = new Estudiante_CarreraRepositoryImpl();
        System.out.printf("%-9s\t%-15s\t%-10s\t%20s %n", "Año", "Inscriptos", "Egresados", "Carrera");
        List<Estudiante_CarreraDTO> reporteEstudiante = estudianteCarreraRepository.obtenerReporte();
        for (Estudiante_CarreraDTO estudiante : reporteEstudiante) {
            System.out.println(estudiante);
        }
        System.out.println();
        	
            

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
