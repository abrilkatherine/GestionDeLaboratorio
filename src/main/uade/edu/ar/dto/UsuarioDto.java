package main.uade.edu.ar.dto;

import main.uade.edu.ar.enums.Roles;

import java.util.Date;

public class UsuarioDto {

    private String nombre;
    private String contraseña;
    private Date nacimiento;
    private Roles rol;

    public UsuarioDto(String nombre, String contraseña, Date nacimiento, Roles rol) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.nacimiento = nacimiento;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
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
