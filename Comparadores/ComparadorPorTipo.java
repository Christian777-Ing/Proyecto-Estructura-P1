package Comparadores;

import java.util.Comparator;

import Modelo.Contacto;
import Modelo.Empresa;
import Modelo.Persona;

public class ComparadorPorTipo implements Comparator<Contacto> { // Comparador que servirá para filtrar por Tipo

    private String tipoAFiltrar; // "Persona" o "Empresa"

    public ComparadorPorTipo(String tipoAFiltrar) {
        this.tipoAFiltrar = tipoAFiltrar;
    }

    @Override
    public int compare(Contacto c1, Contacto c2) {
        boolean cumpleTipo = false;

        if ("Persona".equalsIgnoreCase(tipoAFiltrar) && c1 instanceof Persona) {
            cumpleTipo = true;
        } else if ("Empresa".equalsIgnoreCase(tipoAFiltrar) && c1 instanceof Empresa) {
            cumpleTipo = true;
        }

        if (cumpleTipo) {
            return 0; // El contacto 'c1' es del tipo deseado
        } else {
            return 1; // El contacto 'c1' NO es del tipo deseado
        }
    }
}
