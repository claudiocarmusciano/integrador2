package com.example.tpintegrador2.Interfaces;

import com.example.tpintegrador2.DTO.EstudianteDTO;
import com.example.tpintegrador2.Entidades.Estudiante;
import java.util.List;

public interface EstudianteRepository {

    void altaEstudiante (int id,  String nombre, String apellido, int edad, String genero, int nroDocumento, String ciudadResidencia, int nroLibreta);
 //   List <Estudiante> recuperarEstudiantesOrdenados (String criterioOrdenamiento);
    EstudianteDTO recuperarEstudiantePorLibreta (int nroLibreta);
    List<EstudianteDTO> recuperarEstudiantesPorGenero (String genero);
    void matricularEstudianteEnCarrera(int idEstudiante, int idCarrera);


}
