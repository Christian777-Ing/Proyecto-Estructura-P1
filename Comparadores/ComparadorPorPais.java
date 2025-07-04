package Comparadores;

import java.util.Comparator;

import Modelo.Contacto;
import Modelo.Direccion;

public class ComparadorPorPais implements Comparator<Contacto> { // Comparador que servirá para filtrar por país

    private String paisAFiltrar;

    // Constructor que recibe el país que usaremos para el filtrado
    public ComparadorPorPais(String paisAFiltrar) {
        this.paisAFiltrar = paisAFiltrar;

    }

    @Override
    public int compare(Contacto c1, Contacto c2) {
        boolean cumplePais = false;
        if (c1.getDirecciones() != null) {
            for (Direccion d : c1.getDirecciones()) {
                if (d.getPais() != null && d.getPais().equalsIgnoreCase(paisAFiltrar)) {
                    cumplePais = true;
                    break; // Encontramos una dirección que coincide, no necesitamos buscar más
                }
            }
        }

        if (cumplePais) { // Y aquí podrías añadir tu segunda condición si existe
                          // Por ejemplo: && c1.getAlgunValor() <= algunOtroCriterioAFiltrar
            return 0; // Retorna 0 si el contacto 'c1' cumple con el país y cualquier otra condición
        } else {
            return 1; // Retorna 1 si el contacto 'c1' NO cumple con los criterios
        }
    }
}
