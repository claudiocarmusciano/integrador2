package com.example.tpintegrador2.Interfaces;

import java.util.List;

import com.example.tpintegrador2.DTO.EstudianteDTO;
import com.example.tpintegrador2.DTO.Estudiante_CarreraDTO;
import com.example.tpintegrador2.Entidades.Carrera;
import com.example.tpintegrador2.Entidades.Estudiante;
import com.example.tpintegrador2.Entidades.Estudiante_Carrera;

public interface Estudiante_CarreraRepository {

	// void agregarMatricula(Estudiante_Carrera matricula);

	void altaMatricula(Estudiante estudiante, Carrera carrera, int antiguedad, boolean graduado);

	Estudiante_CarreraDTO obtenerMatriculaPorId(int nroLibreta, int nroDoc, int idCarrera);

	void matricularEstudiante(Estudiante estudiante, Carrera carrera);

	void matricularEstudiante(Estudiante estudiante, Carrera carrera, int anioInscripcion);

	List<Estudiante_CarreraDTO> obtenerTodasLasMatriculas();

	List<EstudianteDTO> obtenerListaFiltrada(String ciudad, int idCarrera);



	List<Estudiante_CarreraDTO> obtenerReporte();

}
