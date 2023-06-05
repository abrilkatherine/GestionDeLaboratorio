package main.uade.edu.ar.model;

import main.uade.edu.ar.enums.Roles;

import java.util.Date;

public class Usuario {

    private int id;
    private String nombre;
    private String contrasenia;
    private Date nacimiento;
    private Roles rol;

    public Usuario(int id, String nombre, String contraseña, Date nacimiento, Roles rol) {
        this.id = id;
        this.nombre = nombre;
        this.contrasenia = contraseña;
        this.nacimiento = nacimiento;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }
}
