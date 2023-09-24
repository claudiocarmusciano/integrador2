package com.example.tpintegrador2.Interfaces;

import com.example.tpintegrador2.Entidades.Carrera;
import com.example.tpintegrador2.Entidades.Estudiante;
import com.example.tpintegrador2.Entidades.Estudiante_Carrera;

import javax.persistence.EntityTransaction;
import java.util.List;

public abstract interface EstudianteRepository {

    void altaEstudiante (int id,  String nombre, String apellido, int edad, String genero, int nroDocumento, String ciudadResidencia, int nroLibreta);
    List <Estudiante> recuperarEstudiantesOrdenados (String criterioOrdenamiento);
    Estudiante recuperarEstudiantePorLibreta (int nroLibreta);
    List<Estudiante> recuperarEstudiantesPorGenero (String genero);
    void matricularEstudianteEnCarrera(int idEstudiante, int idCarrera);


}
