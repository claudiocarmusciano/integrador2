package com.example.tpintegrador2.Entidades;

//import javax.persistence.*;
import jakarta.persistence.*;

@Entity
public class Estudiante_Carrera {
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumns({
            @JoinColumn(name = "nroDocumento", referencedColumnName = "nroDocumento"),
            @JoinColumn(name = "nroLibreta", referencedColumnName = "nroLibreta")
    })
    private Estudiante estudiante;


    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "carrera", referencedColumnName = "idCarrera")
    private Carrera carrera;

    private int antiguedad;
    private boolean graduado;

    public Estudiante_Carrera() {
        super();
    }

    public Estudiante_Carrera(Estudiante estudiante, Carrera carrera, int antiguedad, boolean graduado) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.antiguedad = antiguedad;
        this.graduado = graduado;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public boolean isGraduado() {
        return graduado;
    }

    public void setGraduado(boolean graduado) {
        this.graduado = graduado;
    }

    @Override
    public String toString() {
        return "Estudiante_Carrera{" +
                "estudiante=" + estudiante +
                ", carrera=" + carrera +
                ", antiguedad=" + antiguedad +
                ", graduado=" + graduado +
                '}';
    }
}
