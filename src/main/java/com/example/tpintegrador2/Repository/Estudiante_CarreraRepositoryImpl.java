package com.example.tpintegrador2.Repository;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import com.example.tpintegrador2.DTO.EstudianteDTO;
import com.example.tpintegrador2.DTO.Estudiante_CarreraDTO;
import com.example.tpintegrador2.Entidades.Carrera;
import com.example.tpintegrador2.Entidades.Estudiante;
import com.example.tpintegrador2.Entidades.Estudiante_Carrera;
import com.example.tpintegrador2.Factory.EntityFactory;
import com.example.tpintegrador2.Interfaces.Estudiante_CarreraRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class Estudiante_CarreraRepositoryImpl implements Estudiante_CarreraRepository {



	public void altaMatricula(Estudiante estudiante, Carrera carrera, int antiguedad, boolean graduado) {
		try (EntityManager em = EntityFactory.getInstance().createEntityManager()) {
	        em.getTransaction().begin();
			Estudiante_Carrera matricula = new Estudiante_Carrera(estudiante, carrera, antiguedad, graduado);
	        em.persist(matricula);
	        em.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	
	}

	
	@Override
	public Estudiante_CarreraDTO obtenerMatriculaPorId(int idCarrera) {
		EntityManager em = EntityFactory.getInstance().createEntityManager();
		try {
			String jpql = "SELECT ec FROM Estudiante_Carrera ec " +
		                  "WHERE  ec.idCarrera = :idCarrera";
			TypedQuery<Estudiante_Carrera> query = em.createQuery(jpql, Estudiante_Carrera.class);
		
			Estudiante_Carrera matricula = query.getSingleResult();
			
			return convertEstudianteCarreraDTO(matricula);
			
		}catch (Exception e) {

		}
		return null;
	}
	public Estudiante getEstudianteById(int idEstudiante) {
        EntityManager em = EntityFactory.getInstance().createEntityManager();
		Estudiante estudiante = em.find(Estudiante.class, idEstudiante);
		return estudiante;
    }


	//contar cantidad de estudiantes inscriptos
	public int cantidadEstudiantesInscriptos(int idCarrera) {
		EntityManager em = EntityFactory.getInstance().createEntityManager();
		try {
			String jpql = "SELECT count(ec.idEstudiante) FROM Estudiante_Carrera ec " +
		                  "WHERE  ec.idCarrera = :idCarrera";
			TypedQuery<Estudiante_Carrera> query = em.createQuery(jpql, Estudiante_Carrera.class);

			int cantidad = query.getFirstResult();

			return cantidad;

			
		}catch (Exception e) {

		}
		return 0;
	}

	//contar cantidad de estudiantes graduados
	public int cantidadEstudiantesGraduados(int idCarrera) {
		EntityManager em = EntityFactory.getInstance().createEntityManager();
		try {
			String jpql = "SELECT count(ec.graduado) FROM Estudiante_Carrera ec " +
		                  "WHERE  ec.idCarrera = :idCarrera" +
						  "AND ec.graduado = true";
			TypedQuery<Estudiante_Carrera> query = em.createQuery(jpql, Estudiante_Carrera.class);

			int cantidad = query.getFirstResult();

			return cantidad;

			
		}catch (Exception e) {

		}
		return 0;
	}

	
	private Estudiante_CarreraDTO convertEstudianteCarreraDTO(Estudiante_Carrera estudianteCarrera) {
		/*
		Estudiante_Carrera(nombreCarrea, year, inscriptos, graduados) 	
		*/
	    Estudiante_CarreraDTO estudianteCarreraDTO = new Estudiante_CarreraDTO(
   	    		estudianteCarrera.getCarrera().getNombreCarrera(),
	    		estudianteCarrera.getAntiguedad(),
				cantidadEstudiantesInscriptos(estudianteCarrera.getCarrera().getIdCarrera()),
				cantidadEstudiantesGraduados(estudianteCarrera.getCarrera().getIdCarrera())
	    );
		
	    return estudianteCarreraDTO;
	}

	private List<Estudiante_CarreraDTO> convertEstudianteCarreraDTO(List<Estudiante_Carrera> estudiantesCarrera) {
	    List<Estudiante_CarreraDTO> estudiantesCarreraDTO = new ArrayList<>();

	    for (Estudiante_Carrera estudianteCarrera : estudiantesCarrera) {
	        estudiantesCarreraDTO.add(convertEstudianteCarreraDTO(estudianteCarrera));
	    }

	    return estudiantesCarreraDTO;
	}

	@Override
	public void matricularEstudiante(Estudiante est, Carrera car) {
			EntityManager em = EntityFactory.getInstance().createEntityManager();
    		EntityTransaction tx = em.getTransaction();
	   	    
	   	    try {
	   	        tx.begin();
	   	        
	   	        Estudiante estudiante = em.find(Estudiante.class, est.getIdEstudiante());
	   	        Carrera carrera = em.find(Carrera.class, car.getIdCarrera());
	   	        
	   	        if (estudiante != null && carrera != null) {
	   	        	 
	        		 Estudiante_Carrera estudianteCarrera = new Estudiante_Carrera();
	   	             estudianteCarrera.setEstudiante(estudiante);
	   	             estudianteCarrera.setCarrera(carrera);
	
	   	            
	   	             carrera.agregarEstudianteCarrera(estudianteCarrera); // Agregar a la lista y sincronizar
	
	   	             em.persist(estudianteCarrera);
	   	             tx.commit();
	
	   	        } else {
	   	        
	   	        }
	   	    } catch (Exception e) {
	   	        if (tx != null && tx.isActive()) {
	   	            tx.rollback();
	   	        }
	   	    } finally {
	   	        em.close();
	   	    }		
	}


	@Override
	public void matricularEstudiante(Estudiante estudiante, Carrera carrera, int anioInscripcion) {
	    EntityManager em = EntityFactory.getInstance().createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	   
	    try {
	        tx.begin();
	        
	        Estudiante est = em.find(Estudiante.class, estudiante.getIdEstudiante());
	        Carrera car = em.find(Carrera.class, carrera.getIdCarrera());
	        
	        if (est != null && car != null) {
	            Estudiante_Carrera estudianteCarrera = new Estudiante_Carrera();
	            estudianteCarrera.setEstudiante(est);
	            estudianteCarrera.setCarrera(car);
	            estudianteCarrera.setAntiguedad(anioInscripcion); // Establece el año de inscripción

	            car.agregarEstudianteCarrera(estudianteCarrera); // Agregar a la lista y sincronizar

	            em.persist(estudianteCarrera);
	            tx.commit();

	        } else {
	            // Manejo de errores, por ejemplo, si no se encontraron el estudiante o la carrera.
	        }
	    } catch (Exception e) {
	        if (tx != null && tx.isActive()) {
	            tx.rollback();
	        }
	    } finally {
	        em.close();
	    }
	}

	@Override
	public List<Estudiante_CarreraDTO> obtenerTodasLasMatriculas() {
		EntityManager em = EntityFactory.getInstance().createEntityManager();
		try {
			String jpql = "Select ec from Estudiante_Carrera ec";
			TypedQuery<Estudiante_Carrera> query = em.createQuery(jpql,Estudiante_Carrera.class);
			
			List<Estudiante_Carrera> matriculas = query.getResultList();
			
			return convertEstudianteCarreraDTO(matriculas);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	@Override
	public List<EstudianteDTO> obtenerListaFiltrada(String ciudad, int idCarrera) {
		EntityManager em = EntityFactory.getInstance().createEntityManager();
		try {
			
		TypedQuery<EstudianteDTO> query = em.createQuery("SELECT NEW com.example.tpintegrador2.DTO.EstudianteDTO(e.nombre,e.apellido,e.estudianteId.libretaUniversitaria,e.estudianteId.dni) FROM Estudiante_Carrera ec"
                +" INNER JOIN Estudiante e ON e = ec.estudiante"
                +" WHERE e.ciudad = :ciudad AND ec.carrera.id = :idCarrera", EstudianteDTO.class);
			query.setParameter("ciudad", ciudad);
			query.setParameter("idCarrera", idCarrera);

		List<EstudianteDTO> estudiantes = query.getResultList();
		
		return estudiantes;
		
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/*
	 
	   Generar un reporte de las carreras, que para cada carrera incluya información de los
	   inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar
	   los años de manera cronológica
	 
	 	
	 	|NombreCarrera|cantInscriptos|cantGraduados| 
	 	
	 	las carreras deben estar ordenadas Alfabeticamente  y los años de forma cronologica
	 	
	 	Base de datos
	 	Estudiante:
	 		idEstudiante
	 		nombre
	 		apellido
	 	
	 	Carrera:
	 		idCarrera
	 		nombreCarrera
	 		
	 	estudiante_carrera:
	 		añoIngr
	 		graduado bool
	 		idEstudiante
	 		idCarrera
	 		
	 	
	 */

	@Override
	public List<Estudiante_CarreraDTO> obtenerReporte() {
        EntityManager em = EntityFactory.getInstance().createEntityManager();
		List<Estudiante_CarreraDTO> lista = new ArrayList<>();
		
		try {
			int currentYear = LocalDate.now().getYear();
		
			for (int year = 1990; year <= currentYear; year++) {
				TypedQuery<Estudiante_CarreraDTO> query = em.createQuery(
						"SELECT NEW com.example.tpintegrador2.DTO.Estudiante_CarreraDTO(c.nombreCarrera,:year, " +
					            "COUNT(ec), " +
					            "SUM(CASE WHEN ec.graduado = true THEN 1 ELSE 0 END)) " +
					            "FROM Estudiante_Carrera ec " +
					            "JOIN ec.carrera c " +
					            "WHERE ec.antiguedad = :year " +
					            "GROUP BY c.nombreCarrera " , Estudiante_CarreraDTO.class);
		
				query.setParameter("year", year);
				 
                lista.addAll(query.getResultList());


/*
for (int year = 1990; year <= currentYear; year++) {
    TypedQuery<Estudiante_CarreraDTO> query = em.createQuery(
        "SELECT NEW com.example.tpintegrador2.DTO.Estudiante_CarreraDTO(c.nombreCarrera, " +
            "COUNT(ec), " +
            "SUM(CASE WHEN ec.graduado = true THEN 1 ELSE 0 END)) " +
            "FROM Estudiante_Carrera ec " +
            "JOIN ec.carrera c " +
            "WHERE ec.antiguedad = :year " +
            "GROUP BY c.nombreCarrera " +
            "ORDER BY c.nombreCarrera ASC", Estudiante_CarreraDTO.class);
    
    query.setParameter("y", year);
    
    lista.addAll(query.getResultList());
}

 */
				
			}
				
				
			return lista;
				

			
		} finally {
			em.close();
		}
        
        /*

		
         					Estudiante_CarreraDTO(nombre,año,estudiantesInscriptos,EstudiantesGraduados)
         	"SELECT NEW com.example.tpintegrador2.DTO.EstudianteDTO(c.nombre, :y, " +

                    "(SELECT COUNT(ec) FROM Estudiante_Carrera ec WHERE ec.carrera = c AND (:yAct - ec.antiguedad) = :y), " +
                    "(SELECT COUNT(ec) FROM Estudiante_Carrera  ec WHERE ec.carrera = c AND ec.graduado = true AND (:yAct - ec.antiguedad) = :y)) 
        
         ******
         
         TypedQuery<Estudiante_CarreraDTO> query = em.createQuery(
						"SELECT NEW com.example.tpintegrador2.DTO.Estudiante_CarreraDTO(c.nombreCarrera, ec.antiguedad , " +
						        "COUNT(ec), " +
						        "SUM(CASE WHEN ec.graduado = true THEN 1 ELSE 0 END)) " +
						        "FROM Estudiante_Carrera ec " +
						        "JOIN ec.carrera c " +
						        "WHERE ec.antiguedad  = :y " +
						        "GROUP BY c.nombreCarrera, ec.antiguedad  " +
						        "ORDER BY c.nombreCarrera ASC, ec.antiguedad  ASC", Estudiante_CarreraDTO.class);
		
         
         
         */
        
        
		    
	}


}
