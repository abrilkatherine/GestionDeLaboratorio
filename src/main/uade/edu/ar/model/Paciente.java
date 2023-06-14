package main.uade.edu.ar.model;

import main.uade.edu.ar.enums.Genero;

import java.util.List;

public class Paciente extends Persona {
    private int id;
    private int edad;
    private Genero genero;
    private List<Peticion> peticiones;

    public Paciente(int id, String nombre, int dni, String domicilio, String email, String apellido, int edad, Genero genero) {
        super(nombre, dni, domicilio, email, apellido);
        this.id = id;
        this.edad = edad;
        this.genero = genero;
    }

    public Paciente(int id, String nombre, int dni, String domicilio, String email, String apellido, int edad, Genero genero, List<Peticion> peticiones) {
        super(nombre, dni, domicilio, email, apellido);
        this.id = id;
        this.edad = edad;
        this.genero = genero;
        this.peticiones = peticiones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public List<Peticion> getPeticiones() {
        return peticiones;
    }

    public void setPeticiones(List<Peticion> peticiones) {
        this.peticiones = peticiones;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "edad=" + edad +
                ", genero=" + genero +
                '}';
    }
}





