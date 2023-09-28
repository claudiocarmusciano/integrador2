package com.example.tpintegrador2.Interfaces;

import java.util.List;

import com.example.tpintegrador2.DTO.CarreraDTO;
import com.example.tpintegrador2.Entidades.Carrera;

public interface CarreraRepository {
	void altaCarreras(int idCarrera, String nombreCarrera);
	CarreraDTO obtenerCarreraPorId(int idCarrera);
	List<CarreraDTO> obtenerCarrerasInscriptos();
	List<CarreraDTO> obtenerTodasLasCarreras();
	
}
