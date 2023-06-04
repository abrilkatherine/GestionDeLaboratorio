package main.uade.edu.ar.model;

public abstract class Persona {
    private String nombre;
    private int dni;
    private String domicilio;
    private String email;
    private String apellido;

    public Persona(String nombre, int DNI, String domicilio, String email, String apellido) {
        this.nombre = nombre;
        this.dni = DNI;
        this.domicilio = domicilio;
        this.email = email;
        this.apellido = apellido;
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
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", DNI=" + dni +
                ", domicilio='" + domicilio + '\'' +
                ", email='" + email + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
