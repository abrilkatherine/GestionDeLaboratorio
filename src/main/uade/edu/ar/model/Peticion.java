package main.uade.edu.ar.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Peticion {
    private int id;
    private String obraSocial;
    private Date fechaCarga;
    private Date fechaEntrega;
    private List<Practica> practicas;
    private Sucursal sucursal;
    private boolean estado;

    public Peticion(int id, String obraSocial, Date fechaCarga, Date fechaEntrega, Sucursal sucursal) {
        this.id = id;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.fechaEntrega = fechaEntrega;
        this.sucursal = sucursal;
        this.estado = false;
    }

    public Peticion(int id, String obraSocial, Date fechaCarga, Date fechaEntrega, Sucursal sucursal, List<Practica> practicas) {
        this.id = id;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.fechaEntrega = fechaEntrega;
        this.sucursal = sucursal;
        this.practicas = practicas;
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

    public boolean tieneResultado() {
        return practicas.stream()
                .anyMatch(practica -> practica.getResultado() != null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Peticion peticion = (Peticion) o;
        return id == peticion.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
