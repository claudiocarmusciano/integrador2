package com.example.tpintegrador2.Interfaces;

import java.util.List;

import com.example.tpintegrador2.DTO.EstudianteDTO;
import com.example.tpintegrador2.DTO.Estudiante_CarreraDTO;
import com.example.tpintegrador2.Entidades.Carrera;
import com.example.tpintegrador2.Entidades.Estudiante;

public interface Estudiante_CarreraRepository {

	void altaMatricula(Estudiante estudiante, Carrera carrera, int antiguedad, boolean graduado);

	List<Estudiante_CarreraDTO> obtenerReporte();

}
