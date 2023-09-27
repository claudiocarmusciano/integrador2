package com.example.tpintegrador2.Repository;

import com.example.tpintegrador2.Entidades.Estudiante;
import com.example.tpintegrador2.Interfaces.EstudianteRepository;

import java.util.List;

import jakarta.persistence.Query;

public class EstudianteRepositoryImpl implements EstudianteRepository {
	
	@Override
    public void altaEstudiante(int id, String nombre, String apellido, int edad, String genero, int nroDocumento, String ciudadResidencia, int nroLibreta) {
    	Estudiante estudiante = new Estudiante(id, nombre, apellido, edad, genero, nroDocumento, ciudadResidencia, nroLibreta);
    	agregarEstudiante(estudiante);
    }

	private void agregarEstudiante(Estudiante estudiante) {
	}

	@Override
	public int recuperarEstudiantePorLibreta(int nroLibreta) {

    	return nroLibreta;
	}

  //  @Override
	public List<Estudiante> recuperarEstudiantesOrdenados(String criterioOrdenamiento) {
		String consulta ="Select e from Estudiante e Order By e.nombre ASC";
		Query query = createQuery(consulta);
		
		List<Estudiante> estudiantes = query.getResultList();
		return estudiantes;
	}

	private Query createQuery(String consulta) {
        return null;
    }

	@Override
	public List<Estudiante> recuperarEstudiantesPorGenero(String genero) {
		String consulta = "Select e from Estudiante e where e.genero = ?1";
		Query query = createQuery(consulta);
		query.setParameter("1", genero);
				
		List<Estudiante> estudiantes = query.getResultList();
		return estudiantes;
	}


    @Override
    public void matricularEstudianteEnCarrera(int idEstudiante, int idCarrera) {

    }
}
