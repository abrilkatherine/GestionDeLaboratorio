package main.uade.edu.ar.dto;

import main.uade.edu.ar.model.Practica;
import main.uade.edu.ar.model.Sucursal;

import java.util.Date;

import java.util.List;

public class PeticionDto {

    private int id;
    private String obraSocial;
    private Date fechaCarga;
    private Date fechaEntrega;
    private List<PracticaDto> practicas;
    private SucursalDto sucursal;

    public PeticionDto(int id, String obraSocial, Date fechaCarga, Date fechaEntrega, SucursalDto sucursal) {
        this.id = id;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.fechaEntrega = fechaEntrega;
        this.sucursal = sucursal;
    }

    public PeticionDto(int id, String obraSocial, Date fechaCarga, Date fechaEntrega, SucursalDto sucursal, List<PracticaDto> practicas) {
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

    public List<PracticaDto> getPracticas() {
        return practicas;
    }

    public void setPracticas(List<PracticaDto> practicas) {
        this.practicas = practicas;
    }

    public SucursalDto getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalDto sucursal) {
        this.sucursal = sucursal;
    }
}
