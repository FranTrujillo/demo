package com.trujillo.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Persona {

    @Id
    @Column(name = "identificacion")
    private String identificacion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "genero")
    private String genero;
    @Column(name = "edad")
    private int edad;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;

    public Persona() {
    }

    public Persona(String identificacion, String nombre, String genero, int edad, String direccion, String telefono) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
