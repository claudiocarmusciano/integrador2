package com.example.tpintegrador2.DTO;

import com.example.tpintegrador2.Entidades.Carrera;

import java.io.Serializable;

public class CarreraDTO implements Serializable {
    private String nombre;

    public CarreraDTO(){
        super();
    }
    public CarreraDTO(String nombre) {
        this.nombre = nombre;
    }
}
