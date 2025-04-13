package com.trujillo.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "identificacion")
public class Cliente extends Persona{

    @Column(name = "clienteid") 
    private String clienteId;
    private String contrasena;
    private boolean estado;

    public Cliente() {
        super(null, null, null, 0, null, null); 
    }

    public Cliente(String identificacion, String nombre, String genero, int edad, String direccion, String telefono, 
    String clienteId, String contrasena, boolean estado) {
        super(identificacion, nombre, genero, edad, direccion, telefono); 
        this.clienteId = clienteId;
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public Cliente(String clienteId, String contrasena, boolean estado) {
        super(null, null, null, 0, null, null); 
        this.clienteId = clienteId;
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
