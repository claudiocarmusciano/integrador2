package com.example.tpintegrador2.Factory;

import com.example.tpintegrador2.Interfaces.CarreraRepository;
import com.example.tpintegrador2.Interfaces.EstudianteRepository;
import com.example.tpintegrador2.Interfaces.Estudiante_CarreraRepository;
import com.example.tpintegrador2.Repository.CarreraRepositoryImpl;
import com.example.tpintegrador2.Repository.EstudianteRepositoryImpl;
import com.example.tpintegrador2.Repository.Estudiante_CarreraRepositoryImpl;

public class FactoryRepositoryImpl implements FactoryRepository {
	private static FactoryRepositoryImpl fri;
	private final EstudianteRepository er;
	private final CarreraRepository cr;
	private final Estudiante_CarreraRepository ecr;
	
	
	
	public FactoryRepositoryImpl() {
		er = new EstudianteRepositoryImpl();
		cr = new CarreraRepositoryImpl();
		ecr = new Estudiante_CarreraRepositoryImpl();
	}
	

	public static FactoryRepositoryImpl getInstancia() {
		if(fri == null) {
			fri = new FactoryRepositoryImpl();
		}
		return fri;
	}

	@Override
	public EstudianteRepository getEstudianteRepository() {

		return er;
	}

	@Override
	public CarreraRepository getCarreraRepository() {
		// TODO Auto-generated method stub
		return cr;
	}

	@Override
	public Estudiante_CarreraRepository getEstudiante_CarreraRepository() {
		// TODO Auto-generated method stub
		return ecr;
	}

}
