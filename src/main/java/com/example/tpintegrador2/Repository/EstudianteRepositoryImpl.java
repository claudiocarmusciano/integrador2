package com.example.tpintegrador2.Repository;

import com.example.tpintegrador2.Entidades.Estudiante;
import com.example.tpintegrador2.Interfaces.EstudianteRepository;

import java.util.List;

public class EstudianteRepositoryImpl implements EstudianteRepository {

    @Override
    public void altaEstudiante(int id, String nombre, String apellido, int edad, String genero, int nroDocumento, String ciudadResidencia, int nroLibreta) {

    }

    @Override
    public List<Estudiante> recuperarEstudiantesOrdenados(String criterioOrdenamiento) {
        return null;
    }

    @Override
    public Estudiante recuperarEstudiantePorLibreta(int nroLibreta) {
        return null;
    }

    @Override
    public List<Estudiante> recuperarEstudiantesPorGenero(String genero) {
        return null;
    }

    @Override
    public void matricularEstudianteEnCarrera(int idEstudiante, int idCarrera) {

    }
}
