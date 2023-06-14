package main.uade.edu.ar.model;

import main.uade.edu.ar.enums.TipoResultado;

public class Practica {
    private int id;
    private int codigo;
    private String nombre;
    private int grupo;
    private float horasFaltantes;
    private Resultado resultado;

    public Practica(int id, int codigo, String nombre, int grupo, float horasFaltantes, Resultado resultado) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.grupo = grupo;
        this.horasFaltantes = horasFaltantes;
        this.resultado = resultado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public float getHorasFaltantes() {
        return horasFaltantes;
    }

    public void setHorasFaltantes(float horasFaltantes) {
        this.horasFaltantes = horasFaltantes;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public boolean esCritica() {
        return TipoResultado.CRITICO.equals(resultado.getTipoResultado());
    }
}
