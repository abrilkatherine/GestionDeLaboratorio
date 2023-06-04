package main.uade.edu.ar.dto;

public class PracticaDto {
    private int id;
    private String nombre;
    private int grupo;
    private float horasFaltantes;
    private boolean PracticaComenzada;

    public PracticaDto(int id, String nombre, int grupo, float horasFaltantes, boolean practicaComenzada) {
        this.id = id;
        this.nombre = nombre;
        this.grupo = grupo;
        this.horasFaltantes = horasFaltantes;
        PracticaComenzada = practicaComenzada;
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

    public boolean isPracticaComenzada() {
        return PracticaComenzada;
    }

    public void setPracticaComenzada(boolean practicaComenzada) {
        PracticaComenzada = practicaComenzada;
    }
}
