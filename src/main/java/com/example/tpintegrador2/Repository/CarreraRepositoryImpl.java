package com.example.tpintegrador2.Repository;

import java.util.ArrayList;
import java.util.List;

import com.example.tpintegrador2.DTO.CarreraDTO;
import com.example.tpintegrador2.DTO.EstudianteDTO;
import com.example.tpintegrador2.Entidades.Carrera;
import com.example.tpintegrador2.Entidades.Estudiante;
import com.example.tpintegrador2.Factory.EntityFactory;
import com.example.tpintegrador2.Interfaces.CarreraRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class CarreraRepositoryImpl implements CarreraRepository {


	private void agregarCarrera(Carrera carrera) {
		EntityManager em = EntityFactory.getInstance().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(carrera);
			em.getTransaction().commit();
			
		}catch (Exception e) {
			
		}finally {
			em.close();
		}
	}
	
	public void altaCarreras(String nombreCarrera) {
		Carrera carrera = new Carrera(nombreCarrera);
		agregarCarrera(carrera);
	}
		
	public CarreraDTO obtenerCarreraPorId(int idCarrera) {
		EntityManager em = EntityFactory.getInstance().createEntityManager();
		try {
			String jpql = "Select c from Carrera c where e.idCarrera = :idCarrera";
			TypedQuery<Carrera> query = em.createQuery(jpql, Carrera.class);
			query.setParameter("idCarrera", idCarrera);
			Carrera carrera = query.getSingleResult();
			
			CarreraDTO carreraDto = convertirCarreraDTO(carrera);
			return carreraDto;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	private CarreraDTO convertirCarreraDTO(Carrera carrera) {
		 CarreraDTO carreraDto= new CarreraDTO(
			       	carrera.getNombreCarrera(),
			       	0  //Duracion de CarreraDTO
		
				 );
			    return carreraDto;
	}
	
	private List<CarreraDTO> convertirCarreraDTO(List<Carrera> carreras) {
		List<CarreraDTO> carreraDto = new ArrayList<>();
	    
	    for (Carrera carrera: carreras) {
	    	carreraDto.add(convertirCarreraDTO(carrera));
	    }
	    
	    return carreraDto;
	}
	
	
	public List<CarreraDTO> obtenerCarrerasInscriptos(){
		EntityManager em = EntityFactory.getInstance().createEntityManager();
		try {
			
			String jpql = "SELECT c FROM Carrera c WHERE SIZE(c.estudiantes) > 0 ORDER BY SIZE(c.estudiantes) DESC";
			TypedQuery<Carrera> query= em.createQuery(jpql,Carrera.class);
			List<Carrera> carreras = query.getResultList();
			
			
			return convertirCarreraDTO(carreras);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;

		
	}

	
	public List<CarreraDTO> obtenerTodasLasCarreras() {
		EntityManager em = EntityFactory.getInstance().createEntityManager();
		try {
			String jpql ="Select c from Carrera c";
			TypedQuery<Carrera> query = em.createQuery(jpql,Carrera.class);
			List<Carrera> carreras = query.getResultList();
			
			return convertirCarreraDTO(carreras);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
}



/*
public void agregarCarrera(Carrera carrera) **
public void altaCarrera()**
public Carrera obtenerCarreraPorId(int id)**
public List<CarreraInscriptosDTO> obtenerCarrerasConInscriptos()**
public List<Carrera> obtenerTodasLasCarreras() **
*/