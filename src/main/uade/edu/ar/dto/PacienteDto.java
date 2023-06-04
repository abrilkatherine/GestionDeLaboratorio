package main.uade.edu.ar.dto;

import main.uade.edu.ar.enums.Genero;
import main.uade.edu.ar.model.Persona;

//TODO: No extender de persona, completar todos los campos acá
public class PacienteDto extends Persona {
    private int id;
    private int edad;
    private Genero genero;

    public PacienteDto(int id, String nombre, int dni, String domicilio, String email, String apellido, int edad, Genero genero) {
        super(nombre, dni, domicilio, email, apellido);
        this.id = id;
        this.edad = edad;
        this.genero = genero;
    }
    public int getId() {
        return id;}

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

    @Override
    public String toString() {
        return "Paciente{" +
                "edad=" + edad +
                ", genero=" + genero +
                '}';
    }
}





