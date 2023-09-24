package com.example.tpintegrador2.Entidades;

import javax.persistence.*;
import java.util.List;

@Entity
public class Carrera {
    @Id
    private int idCarrera;
    @Column
    private String nombreCarrera;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "carreras")
    private List<Estudiante> estudiantes;

    public Carrera() {
        super();
    }

    public Carrera(int idCarrera, String nombreCarrera) {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
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
