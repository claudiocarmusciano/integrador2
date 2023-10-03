package com.example.tpintegrador2.DTO;

import java.io.Serializable;

public class EstudianteDTO implements Serializable {
    private String fullName,genero,ciudadResidencia;
    private int edad,nroDocumento, nroLibreta;
    private int carreras;// ??? no conviene un list ?

    public EstudianteDTO() {
        super();
    }

    public EstudianteDTO(String fullName, String genero, String ciudadResidencia, int edad, int nroDocumento, int nroLibreta, int carreras) {
        this.fullName = fullName;
        this.genero = genero;
        this.ciudadResidencia = ciudadResidencia;
        this.edad = edad;
        this.nroDocumento = nroDocumento;
        this.nroLibreta = nroLibreta;
        this.carreras = carreras;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGenero() {
        return genero;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public int getEdad() {
        return edad;
    }

    public int getNroDocumento() {
        return nroDocumento;
    }

    public int getNroLibreta() {
        return nroLibreta;
    }

    public int getCarreras() {
        return carreras;
    }

    @Override
    public String toString() {
        return String.format("%-40s %-10s %-30s %20d %20s %20s %20d",
                        fullName, genero, ciudadResidencia, edad, nroDocumento, nroLibreta, carreras);
    }


}
