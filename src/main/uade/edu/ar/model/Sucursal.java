package main.uade.edu.ar.model;

import main.uade.edu.ar.dto.UsuarioDto;

public class Sucursal {
    private int id;
    private int numero;
    private String direccion;
    private Usuario responsableTecnico;

    public Sucursal(int id, int numero, String direccion, Usuario responsableTecnico) {
        this.id = id;
        this.numero = numero;
        this.direccion = direccion;
        this.responsableTecnico = responsableTecnico;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Usuario getResponsableTecnico() {
        return responsableTecnico;
    }

    public void setResponsableTecnico(Usuario responsableTecnico) {
        this.responsableTecnico = responsableTecnico;
    }
}
