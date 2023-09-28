package com.example.tpintegrador2.Repository;

import com.example.tpintegrador2.DTO.EstudianteDTO;
import com.example.tpintegrador2.Entidades.Carrera;
import com.example.tpintegrador2.Entidades.Estudiante;
import com.example.tpintegrador2.Entidades.Estudiante_Carrera;

import com.example.tpintegrador2.Factory.EntityFactory;
import com.example.tpintegrador2.Interfaces.EstudianteRepository;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class EstudianteRepositoryImpl implements EstudianteRepository {
	
	@Override
    public void altaEstudiante(int id, String nombre, String apellido, int edad, String genero, int nroDocumento, String ciudadResidencia, int nroLibreta) {
		Estudiante estudiante = new Estudiante(id, nombre, apellido, edad, genero, nroDocumento, ciudadResidencia, nroLibreta);
    	agregarEstudiante(estudiante);
    }

	private void agregarEstudiante(Estudiante estudiante) {
		 EntityManager em = EntityFactory.getInstance().createEntityManager();		 
	        try {
	            em.getTransaction().begin();
	            em.persist(estudiante);
	            em.getTransaction().commit();
	        } catch (Exception e) {
	            if (em.getTransaction() != null && em.getTransaction().isActive()) {
	                em.getTransaction().rollback();
	            }
	            throw e;
	        } finally {
	            em.close();
	        }
	}

	@Override
	public EstudianteDTO recuperarEstudiantePorLibreta(int nroLibreta) {
		EntityManager em = EntityFactory.getInstance().createEntityManager();		 
		 try {
		        String jpql = "SELECT e FROM Estudiante e WHERE e.nroLibreta = :nroLibreta";
		        TypedQuery<Estudiante> query = em.createQuery(jpql, Estudiante.class);

		        query.setParameter("nroLibreta", nroLibreta);
		        Estudiante estudiante = query.getSingleResult(); // Esta línea ejecuta la consulta y obtiene el estudiante
		         
		        EstudianteDTO estudianteDTO = convertEstudianteDTO(estudiante);
		        
		        return estudianteDTO;
		    } catch (Exception e) {
		        return null;
		    } finally {
		        em.close();
		    }
	
	}

	private EstudianteDTO convertEstudianteDTO(Estudiante estudiante) {
		 EstudianteDTO estudianteDTO = new EstudianteDTO(
			        estudiante.getNombre() + " " + estudiante.getApellido(), // fullName
			        estudiante.getGenero(),
			        estudiante.getCiudadResidencia(),
			        estudiante.getEdad(),
			        estudiante.getNroDocumento(),
			        estudiante.getNroLibreta(),
			        estudiante.getEstudianteCarrera().size() // estamos contando cuántas carreras tiene el estudiante
			    );
			    return estudianteDTO;
	}
	
	private List<EstudianteDTO> convertEstudianteDTO(List<Estudiante> estudiantes) {
		List<EstudianteDTO> estudiantesDTO = new ArrayList<>();
	    
	    for (Estudiante estudiante : estudiantes) {
	    	estudiantesDTO.add(convertEstudianteDTO(estudiante));
	    }
	    
	    return estudiantesDTO;
	}
  //  @Override
	public List<EstudianteDTO> recuperarEstudiantesOrdenados(String criterioOrdenamiento) {
		 EntityManager em = EntityFactory.getInstance().createEntityManager();
		    
		    String jpql = "SELECT e FROM Estudiante e ORDER BY ";
		
		    
		    switch (criterioOrdenamiento) {
		        case "nombre":
		            jpql += "e.nombre ASC";
		            break;
		        case "edad":
		            jpql += "e.edad ASC";
		            break;
		        default:
		            jpql += "e.nombre ASC"; 
		            break;
		    }
		    
		    try {
		        TypedQuery<Estudiante> query = em.createQuery(jpql, Estudiante.class);
		        List<Estudiante> estudiantes = query.getResultList();
		    
		        
		        return convertEstudianteDTO(estudiantes);
		    } finally {
		        em.close();
		    }
	}

	//no se que hace , **PREGUNTAR**
	private Query createQuery(String consulta) {
        return null;
    }

	@Override
	public List<EstudianteDTO> recuperarEstudiantesPorGenero(String genero) {
		EntityManager em = EntityFactory.getInstance().createEntityManager();
		String consulta = "Select e from Estudiante e where e.genero = ?1";

		try {		
			Query query = createQuery(consulta);
	        query.setParameter("1", genero);
	        List<Estudiante> estudiantes = query.getResultList();	        

	        return convertEstudianteDTO(estudiantes);
			
		}catch (Exception e) {
		} finally {
	        em.close();
	    }
		
		return null;
				
	}


    @Override
    public void matricularEstudianteEnCarrera(int idEstudiante, int idCarrera) {

    	
    	EntityManager em = EntityFactory.getInstance().createEntityManager();
   	 	EntityTransaction tx = em.getTransaction();
   	    
   	    try {
   	        tx.begin();
   	        
   	        Estudiante estudiante = em.find(Estudiante.class, idEstudiante);
   	        Carrera carrera = em.find(Carrera.class, idCarrera);
   	        
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
    
    public List<EstudianteDTO> getEstudiantesPorCarreraYCiudad(String nombreCarrera, String ciudadResidencia){
    	EntityManager em = EntityFactory.getInstance().createEntityManager();
    	
    	try {
    		String jpql = "SELECT e FROM Estudiante e " +
                    	 "JOIN e.carreras c " +
                    	 "WHERE c.nombre = :nombreCarrera " +
                    	 "AND e.ciudadResidencia = :ciudadResidencia";
    		TypedQuery<Estudiante> query = em.createQuery(jpql,Estudiante.class);
    		query.setParameter("nombreCarrera", nombreCarrera);
    		query.setParameter("ciudadResidencia", ciudadResidencia);
    		
    		List<Estudiante> estudiantes= query.getResultList();
    		
    		return convertEstudianteDTO(estudiantes);
    		
    		
    	}catch (Exception e) {
			// TODO: handle exception
		}
    	
    	return null;
    }

}
