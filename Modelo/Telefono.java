package Modelo;

import java.io.Serializable;

public class Telefono implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tipo;
    private String numero;

    public Telefono(String tipo, String numero) {
        this.tipo = tipo;
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return tipo + ": " + numero;
    }

}
