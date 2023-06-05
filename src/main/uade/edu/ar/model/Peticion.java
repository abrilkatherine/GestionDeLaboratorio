package main.uade.edu.ar.model;

import main.uade.edu.ar.enums.TipoResultado;

import java.util.Date;
import java.util.List;

public class Peticion {
    private int id;
    private String obraSocial;
    private Date fechaCarga;
    private Date fechaEntrega;
    private TipoResultado resultado;
    private Paciente paciente;
    private List<Practica> practicas;

    public Peticion(int id, String obraSocial, Date fechaCarga, Date fechaEntrega, TipoResultado resultado, Paciente paciente, List<Practica> practicas) {
        this.id = id;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.fechaEntrega = fechaEntrega;
        this.resultado = resultado;
        this.paciente = paciente;
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

    public TipoResultado getResultado() {
        return resultado;
    }

    public void setResultado(TipoResultado resultado) {
        this.resultado = resultado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Practica> getPracticas() {
        return practicas;
    }

    public void setPracticas(List<Practica> practicas) {
        this.practicas = practicas;
    }
}
