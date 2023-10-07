package com.example.tpintegrador2.CSV;

import com.example.tpintegrador2.Entidades.Carrera;
import com.example.tpintegrador2.Entidades.Estudiante;
import com.example.tpintegrador2.Entidades.Estudiante_Carrera;
import com.example.tpintegrador2.Factory.EntityFactory;
import com.example.tpintegrador2.Factory.FactoryRepository;
import com.example.tpintegrador2.Factory.FactoryRepositoryImpl;
import com.example.tpintegrador2.Interfaces.CarreraRepository;
import com.example.tpintegrador2.Interfaces.EstudianteRepository;
import com.example.tpintegrador2.Interfaces.Estudiante_CarreraRepository;
import com.example.tpintegrador2.Repository.CarreraRepositoryImpl;
import com.example.tpintegrador2.Repository.EstudianteRepositoryImpl;
import com.example.tpintegrador2.Repository.Estudiante_CarreraRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class CSV {
    private List<Estudiante> estudiantes;
    private List<Carrera> carreras;
    private List<Estudiante_Carrera> estudianteCarrera;
    private EstudianteRepository estudRep;
    private CarreraRepository carRep;
    private Estudiante_CarreraRepository estud_CarRep;

    public CSV(){
        this.estudiantes = new LinkedList<>();
        this.carreras = new LinkedList<>();
        this.estudianteCarrera = new LinkedList<>();
        this.estudRep = new EstudianteRepositoryImpl();
        this.carRep = new CarreraRepositoryImpl();
        this.estud_CarRep = new Estudiante_CarreraRepositoryImpl();
    }


    public void readCSV(String csvCarrera, String csvEstudiante, String csvEstudianteCarrera) {

        EntityFactory entityFactory = EntityFactory.getInstance();
        String path = "src/main/java/com/example/tpintegrador2/CSV/datos";

        try (EntityManager em = entityFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            FactoryRepository fr = FactoryRepositoryImpl.getInstancia();

            // lectura CSV Estudiante
            String csvDir = path + "/" + csvEstudiante;
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(csvDir));

            for (CSVRecord row : parser) {
                fr.getEstudianteRepository().altaEstudiante(row.get("nombre"), row.get("apellido"), Integer.parseInt(row.get("edad")),
                        row.get("genero"), Integer.parseInt(row.get("nroDocumento")), row.get("ciudadResidencia"),
                        Integer.parseInt(row.get("nroLibreta")));
            }

            // lectura CSV Carrera
            String csvDirC = path + "/" + csvCarrera;
            CSVParser parserC = CSVFormat.DEFAULT.withHeader().parse(new FileReader(csvDirC));

            for (CSVRecord row : parserC) {
                fr.getCarreraRepository().altaCarreras(row.get("nombreCarrera"));
            }
            
            
            // lectura CSV EstudianteCarrera
            String csvEstCar= path + "/" + csvEstudianteCarrera;
            CSVParser parserEc = CSVFormat.DEFAULT.withHeader().parse(new FileReader(csvEstCar));

            for (CSVRecord row : parserEc) {
                int idEstudiante = Integer.parseInt(row.get("idEstudiante"));
                int idCarrera = Integer.parseInt(row.get("idCarrera"));
                int antiguedad = Integer.parseInt(row.get("antiguedad"));
                boolean graduado = Boolean.parseBoolean(row.get("graduado"));
                
                System.err.println(idEstudiante+" "+idCarrera+" "+antiguedad+" "+graduado);

                Estudiante estudiante = fr.getEstudianteRepository().getEstudianteById(idEstudiante);
                Carrera carrera = fr.getCarreraRepository().getCarreraById(idCarrera);

                fr.getEstudiante_CarreraRepository().altaMatricula(estudiante, carrera, antiguedad,graduado);

            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}




