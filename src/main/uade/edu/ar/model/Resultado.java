package main.uade.edu.ar.model;

import main.uade.edu.ar.enums.TipoResultado;

public class Resultado {

    private String valor;
    private TipoResultado tipoResultado;

    public Resultado(String valor, TipoResultado tipoResultado) {
        this.valor = valor;
        this.tipoResultado = tipoResultado;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public TipoResultado getTipoResultado() {
        return tipoResultado;
    }

    public void setTipoResultado(TipoResultado tipoResultado) {
        this.tipoResultado = tipoResultado;
    }

}
