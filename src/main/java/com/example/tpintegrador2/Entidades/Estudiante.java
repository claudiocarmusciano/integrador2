
package com.example.tpintegrador2.Entidades;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstudiante;
    @Column(nullable=false)
    private String nombre;
    @Column(nullable=false)
    private String apellido;
    @Column
    private int edad;
    @Column
    private String genero;
    @Column(nullable=false)
    private int nroDocumento;
    @Column(nullable=false)
    private String ciudadResidencia;
    @Column(nullable=false)
    private int nroLibreta;
    @OneToMany (mappedBy = "estudiante")
    private List<Estudiante_Carrera> estudianteCarrera;


    public Estudiante(int nroDocumento, int libreta, String nombre, String apellido, int edad, String genero, String ciudadResidencia, int nroLibreta) {
        super();
    }

    public Estudiante(String nombre, String apellido, int edad, String genero, int nroDocumento, String ciudadResidencia, int nroLibreta) {
        //this.idEstudiante = idEstudiante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.nroDocumento = nroDocumento;
        this.ciudadResidencia = ciudadResidencia;
        this.nroLibreta = nroLibreta;
        //this.carreras = carreras;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(int nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }

    public int getNroLibreta() {
        return nroLibreta;
    }

    public void setNroLibreta(int nroLibreta) {
        this.nroLibreta = nroLibreta;
    }

    public List<Estudiante_Carrera> getEstudianteCarrera() {
        return estudianteCarrera;
    }

    public void setEstudianteCarrera(List<Estudiante_Carrera> estudianteCarrera) {
        this.estudianteCarrera = estudianteCarrera;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "idEstudiante=" + idEstudiante +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", nroDocumento=" + nroDocumento +
                ", ciudadResidencia='" + ciudadResidencia + '\'' +
                ", nroLibreta=" + nroLibreta +
                ", carreras=" + estudianteCarrera +
                '}';
    }
}
