package main.uade.edu.ar.model;

public class Sucursal {

    private int id;
    private int numero; // TODO: id?
    private String direccion;

    //TODO: Check atribute
    //private responsableTecnico usuario;

    public Sucursal(int id, int numero, String direccion) {
        this.id = id;
        this.numero = numero;
        this.direccion = direccion;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
