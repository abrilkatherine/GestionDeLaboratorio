package main.uade.edu.ar.dto;

public class SucursalDto {

    private int id;
    private int numero;
    private String direccion;
    private UsuarioDto responsableTecnico;

    public SucursalDto(int id, int numero, String direccion, UsuarioDto responsableTecnico) {
        this.id = id;
        this.numero = numero;
        this.direccion = direccion;
        this.responsableTecnico = responsableTecnico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UsuarioDto getResponsableTecnico() {
        return responsableTecnico;
    }

    public void setResponsableTecnico(UsuarioDto responsableTecnico) {
        this.responsableTecnico = responsableTecnico;
    }
}
