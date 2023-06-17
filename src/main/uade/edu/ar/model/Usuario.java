package main.uade.edu.ar.model;

import main.uade.edu.ar.enums.Roles;

import java.util.Date;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
