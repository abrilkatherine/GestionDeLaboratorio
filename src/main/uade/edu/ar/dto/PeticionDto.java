package main.uade.edu.ar.dto;

import main.uade.edu.ar.enums.Resultado;

import java.util.Date;

public class PeticionDto {

    private int id;

    private String obraSocial;

    private Date fechaCarga;

    private Date fechaEntrega;

    private Resultado resultado;

    public PeticionDto(int id, String obraSocial, Date fechaCarga, Date fechaEntrega, Resultado resultado) {
        this.id = id;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.fechaEntrega = fechaEntrega;
        this.resultado = resultado;
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

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }
}
