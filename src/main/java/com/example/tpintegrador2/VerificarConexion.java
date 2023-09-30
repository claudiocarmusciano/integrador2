package com.example.tpintegrador2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class VerificarConexion {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/integrador2";
        String usuario = "root";
        String contrasena = "";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasena)) {
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
