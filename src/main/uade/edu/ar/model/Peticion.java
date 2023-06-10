package main.uade.edu.ar.model;

import main.uade.edu.ar.enums.TipoResultado;

import java.util.Date;
import java.util.List;

public class Peticion {
    private int id;
    private String obraSocial;
    private Date fechaCarga;
    private Date fechaEntrega;
    private List<Practica> practicas;
    private Sucursal sucursal;

    public Peticion(int id, String obraSocial, Date fechaCarga, Date fechaEntrega, List<Practica> practicas, Sucursal sucursal) {
        this.id = id;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.fechaEntrega = fechaEntrega;
        this.practicas = practicas;
        this.sucursal = sucursal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public List<Practica> getPracticas() {
        return practicas;
    }

    public void setPracticas(List<Practica> practicas) {
        this.practicas = practicas;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
}
