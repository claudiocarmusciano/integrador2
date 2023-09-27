package com.example.tpintegrador2.DTO;

import java.io.Serializable;

public class Estudiante_CarreraDTO implements Serializable {
    private int estudiante;
    private int carrera;
    private int antiguedad;
    boolean graduado;

    public Estudiante_CarreraDTO() {
    }

    public Estudiante_CarreraDTO(int estudiante, int carrera, int antiguedad, boolean graduado) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.antiguedad = antiguedad;
        this.graduado = graduado;
    }

    public int getEstudiante() {
        return estudiante;
    }

    public int getCarrera() {
        return carrera;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public boolean isGraduado() {
        return graduado;
    }

    @Override
    public String toString() {
        return "Estudiante_CarreraDTO{" +
                "estudiante=" + estudiante +
                ", carrera=" + carrera +
                ", antiguedad=" + antiguedad +
                ", graduado=" + graduado +
                '}';
    }
}
