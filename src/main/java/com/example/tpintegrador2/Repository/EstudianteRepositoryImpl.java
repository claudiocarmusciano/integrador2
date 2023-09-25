package com.example.tpintegrador2.Repository;

import com.example.tpintegrador2.Entidades.Estudiante;
import com.example.tpintegrador2.Interfaces.EstudianteRepository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EstudianteRepositoryImpl implements EstudianteRepository {
	
	EntityManager em;
	
	public EstudianteRepositoryImpl(EntityManager em) {
        this.em = em;
    }

	public EstudianteRepositoryImpl(jakarta.persistence.EntityManager em) {
	}


	@Override
    public void altaEstudiante(int id, String nombre, String apellido, int edad, String genero, int nroDocumento, String ciudadResidencia, int nroLibreta) {
    	Estudiante estudiante = new Estudiante(id, nombre, apellido, edad, genero, nroDocumento, ciudadResidencia, nroLibreta);
    	em.persist(estudiante);
    }

    @Override
	public Estudiante recuperarEstudiantePorLibreta(int nroLibreta) {

    	return em.find(Estudiante.class, nroLibreta);
	}

    @Override
	public List<Estudiante> recuperarEstudiantesOrdenados(String criterioOrdenamiento) {
		String consulta ="Select e from Estudiante e Order By e.nombre ASC";
		Query query = em.createQuery(consulta);
		
		List<Estudiante> estudiantes = query.getResultList();
		return estudiantes;
	}

	@Override
	public List<Estudiante> recuperarEstudiantesPorGenero(String genero) {
		String consulta = "Select e from Estudiante e where e.genero = ?1";
		Query query = em.createQuery(consulta);
		query.setParameter("1", genero);
				
		List<Estudiante> estudiantes = query.getResultList();
		return estudiantes;
	}


    @Override
    public void matricularEstudianteEnCarrera(int idEstudiante, int idCarrera) {

    }
}
