package com.example.tpintegrador2.DTO;

import java.io.Serializable;

public class Estudiante_CarreraDTO implements Serializable {
    
	private String nombreCarrera;
	private int year;
    private long inscriptos;
    private long egresados;

    
    public Estudiante_CarreraDTO() {
         
    }

   

    //constructor
    public Estudiante_CarreraDTO(String nombreCarrera, int year, long inscriptos, long egresados) {
        this.nombreCarrera = nombreCarrera;
        this.year = year;
        this.inscriptos = inscriptos;
        this.egresados = egresados;
    }

    //getters

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    
    public int getYear() {
        return year;

    }

    public long getInscriptos() {
        return inscriptos;
    }


    public long getEgresados() {
        return egresados;
    }


    //toString
//    @Override
//    public String toString() {
//        //	 	year||cantInscriptos|cantGraduados|NombreCarrera
//        return year + "|" + inscriptos + "|" + egresados + "|" + nombreCarrera;
//    }

    @Override
    public String toString() {
        return String.format("%-10s %-15s %-12s %20s",
                year, inscriptos, egresados, nombreCarrera);
    }
    








}
