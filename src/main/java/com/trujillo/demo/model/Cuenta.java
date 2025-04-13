package com.trujillo.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cuenta")
public class Cuenta {

    @Id
    @Column(name = "numerocuenta")
    private String numeroCuenta;
    @Column(name = "clientid")
    private String clienteId;
    @Column(name = "tipocuenta")
    private String tipoCuenta;
    @Column(name = "saldoinicial")
    private double saldoInicial;
    @Column(name = "estado")
    private boolean estado;
    
    public Cuenta() {
    }

    public Cuenta(String numeroCuenta, String clienteId,String tipoCuenta, double saldoInicial, boolean estado) {
        this.numeroCuenta = numeroCuenta;
        this.clienteId = clienteId;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }


    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
