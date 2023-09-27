package com.example.tpintegrador2.DTO;

import java.io.Serializable;

public class CarreraDTO implements Serializable {
    private String nombre;
    private int duracion;

    public CarreraDTO(String nombre, int duracion){
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    @Override
    public String toString() {
        return "CarreraDTO{" +
                "nombre='" + nombre + '\'' +
                ", duracion=" + duracion +
                '}';
    }
}
