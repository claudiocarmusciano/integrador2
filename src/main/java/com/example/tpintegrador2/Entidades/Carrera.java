package com.example.tpintegrador2.Entidades;

import jakarta.persistence.*;

import java.util.LinkedList;

@Entity
public class Carrera {
    @Id
    private int idCarrera;
    @Column
    private String nombreCarrera;
    @OneToMany(mappedBy = "carrera")
    private LinkedList<Estudiante_Carrera> estudianteCarrera;

    public Carrera() {
        super();
    }

    public Carrera(int idCarrera, String nombreCarrera) {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
        this.estudianteCarrera = new LinkedList<>();
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "idCarrera=" + idCarrera +
                ", nombreCarrera='" + nombreCarrera + '\'' +
                '}';
    }
}
