package com.example.tpintegrador2.Repository;

import java.util.ArrayList;
import java.util.List;
import com.example.tpintegrador2.DTO.CarreraDTO;
import com.example.tpintegrador2.Entidades.Carrera;
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
		} catch (Exception e) {

		} finally {
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
			String jpql = "Select c from Carrera c where c.idCarrera = :idCarrera";
			TypedQuery<Carrera> query = em.createQuery(jpql, Carrera.class);
			query.setParameter("idCarrera", idCarrera);
			Carrera carrera = query.getSingleResult();

			CarreraDTO carreraDto = convertirCarreraDTO(carrera);
			return carreraDto;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public Carrera getCarreraById(int idCarrera) {
		EntityManager em = EntityFactory.getInstance().createEntityManager();
		try {
			String jpql = "Select c from Carrera c where c.idCarrera = :idCarrera";
			TypedQuery<Carrera> query = em.createQuery(jpql, Carrera.class);
			query.setParameter("idCarrera", idCarrera);
			Carrera carrera = query.getSingleResult();

			return carrera;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	private CarreraDTO convertirCarreraDTO(Carrera carrera) {
		CarreraDTO carreraDto = new CarreraDTO(
				carrera.getNombreCarrera(),
				(long) 0 // Duracion de CarreraDTO
		);
		return carreraDto;
	}

	private List<CarreraDTO> convertirCarreraDTO(List<Carrera> carreras) {
		List<CarreraDTO> carreraDto = new ArrayList<>();

		for (Carrera carrera : carreras) {
			carreraDto.add(convertirCarreraDTO(carrera));
		}
		return carreraDto;
	}
	
	public List<CarreraDTO> obtenerCarrerasInscriptos() {
		EntityManager em = EntityFactory.getInstance().createEntityManager();
		List<CarreraDTO> lista = new ArrayList<>();
		String pr= "holas";
		int num = 33;

		try {
			TypedQuery<CarreraDTO> query = em.createQuery(
				    "SELECT NEW com.example.tpintegrador2.DTO.CarreraDTO(c.nombreCarrera, COUNT(ec)) " +
				    " FROM Estudiante_Carrera ec " +
				    " JOIN ec.carrera c " +
				    " GROUP BY c.nombreCarrera " +
				    " ORDER BY COUNT(ec) DESC", CarreraDTO.class);

			lista.addAll(query.getResultList());

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return lista;
	}

	public List<CarreraDTO> obtenerTodasLasCarreras() {
		EntityManager em = EntityFactory.getInstance().createEntityManager();

		try {
			String jpql = "Select c from Carrera c";
			TypedQuery<Carrera> query = em.createQuery(jpql, Carrera.class);
			List<Carrera> carreras = query.getResultList();

			return convertirCarreraDTO(carreras);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}