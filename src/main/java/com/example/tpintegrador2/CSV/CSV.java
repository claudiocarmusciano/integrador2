package com.example.tpintegrador2.CSV;

import com.example.tpintegrador2.Entidades.Carrera;
import com.example.tpintegrador2.Entidades.Estudiante;
import com.example.tpintegrador2.Entidades.Estudiante_Carrera;
import com.example.tpintegrador2.Interfaces.CarreraRepository;
import com.example.tpintegrador2.Interfaces.EstudianteRepository;
import com.example.tpintegrador2.Interfaces.Estudiante_CarreraRepository;
import com.example.tpintegrador2.Repository.CarreraRepositoryImpl;
import com.example.tpintegrador2.Repository.EstudianteRepositoryImpl;
import com.example.tpintegrador2.Repository.Estudiante_CarreraRepositoryImpl;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
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
    public void agregarSCV(String carrera, String estudiante, String estudianteCarrera ) throws IOException {
        String csvDir = System.getProperty("user.dir") + "/" + estudiante;
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(csvDir));

        for (CSVRecord row : parser) {
            Estudiante estud = new Estudiante(row.get("nombre"),row.get("apellido"), Integer.parseInt(row.get("edad")),
                    row.get("genero"),(Integer.parseInt(row.get("nroDocumento"))), row.get("ciudadResidencia"),
                    Integer.parseInt(row.get("nroLibreta")));

            estudiantes.add(estud);
        }

        String csvDirC = System.getProperty("user.dir") + "/" + carrera;
        CSVParser parserC = CSVFormat.DEFAULT.withHeader().parse(new FileReader(csvDirC));

        for (CSVRecord row : parser) {
            Carrera carrer = new Estudiante(row.get("nombre"),row.get("apellido"), Integer.parseInt(row.get("edad")), row.get("genero"),(Integer.parseInt(row.get("nroDocumento")), row.get("ciudadResidencia"),Integer.parseInt(row.get("nroLibreta")));

            carreras.add(carrer);
        }

        String csvDirEC = System.getProperty("user.dir") + "/" + estudianteCarrera;
        CSVParser parserEC = CSVFormat.DEFAULT.withHeader().parse(new FileReader(csvDirEC));

        for (CSVRecord row : parser) {
            Estudiante estudiante_Carrera = new Estudiante_Carrera(Integer.parseInt(row.get("antiguedad")), boolean(row.get("graduado")));

            estudiantes.add(estudiante_Carrera);
        }
        insert();
    }

    public Carrera getCarrera(int id){
        for (Carrera c:carreras){
            if (c.getId()==id){
                return c;
            }
        }
        return null;
    }

    public Estudiante getEstudiante(int id){
        for (Estudiante e:estudiantes){
            if (e.getIdEstudiante() == id){
                return e;
            }
        }
        return null;
    }

    private void insert(){
        for(Estudiante e:estudiantes){
            estudRep.altaEstudiante(e);
        }
        for(Carrera c:carreras){
            carRep.altaCarreras(c);
        }
        for (Estudiante_Carrera eC:estudianteCarrera){
            estud_CarRep.matricularEstudiante(eC);
        }
    }
}
