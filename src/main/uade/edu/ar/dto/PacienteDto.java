package main.uade.edu.ar.dto;

import main.uade.edu.ar.enums.Genero;
import main.uade.edu.ar.model.Persona;

//TODO: No extender de persona, completar todos los campos acá
public class PacienteDto {
    private int id;
    private int edad;
    private Genero genero;
    private String nombre;
    private int dni;
    private String domicilio;
    private String email;
    private String apellido;

    public PacienteDto(int id, int edad, Genero genero, String nombre, int dni, String domicilio, String email, String apellido) {
        this.id = id;
        this.edad = edad;
        this.genero = genero;
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.email = email;
        this.apellido = apellido;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "edad=" + edad +
                ", genero=" + genero +
                '}';
    }
}





