package com.example.tpintegrador2.Interfaces;

import java.util.List;

import com.example.tpintegrador2.DTO.EstudianteDTO;
import com.example.tpintegrador2.Entidades.Carrera;
import com.example.tpintegrador2.Entidades.Estudiante;
import com.example.tpintegrador2.Entidades.Estudiante_Carrera;

public interface Estudiante_CarreraRepository {
	 	
	  void agregarMatricula(Estudiante_Carrera matricula);

	    void agregarMatricula(String csv);

	    Estudiante_Carrera obtenerMatriculaPorId(int id);

	    void matricularEstudiante(Estudiante estudiante, Carrera carrera);

	    void matricularEstudiante(Estudiante estudiante, Carrera carrera, int anioInscripcion);

	    List<Estudiante_Carrera> obtenerTodasLasMatriculas();

	    List<EstudianteDTO> obtenerListaFiltrada(String ciudad, int idCarrera);

	    void actualizarMatricula(Estudiante_Carrera matricula);

	    List<Estudiante_Carrera> obtenerReporte();

	    void eliminarMatricula(int id);
}
