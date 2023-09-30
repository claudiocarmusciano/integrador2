package com.example.tpintegrador2;

import com.example.tpintegrador2.CSV.CSV;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

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
