package com.example.tpintegrador2.Factory;

import com.example.tpintegrador2.Interfaces.CarreraRepository;
import com.example.tpintegrador2.Interfaces.EstudianteRepository;
import com.example.tpintegrador2.Interfaces.Estudiante_CarreraRepository;

public interface FactoryRepository {
    EstudianteRepository getEstudianteRepository();
    CarreraRepository getCarreraRepository();
    Estudiante_CarreraRepository getEstudiante_CarreraRepository();

}
