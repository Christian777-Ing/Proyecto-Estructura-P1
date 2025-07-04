package Comparadores;

import java.util.Comparator;

import Modelo.Contacto;
import Modelo.Direccion;

public class ComparadorPorPais implements Comparator<Contacto> {

    private String paisAFiltrar;
    // Si necesitas una segunda condición, como en tu ejemplo de precio, la añadirías aquí.
    // Por ejemplo: private int algunOtroCriterioAFiltrar;

    // Constructor que recibe el país que usaremos para el filtrado
    public ComparadorPorPais(String paisAFiltrar) {
        this.paisAFiltrar = paisAFiltrar;
        // Inicializa el segundo criterio si lo tienes
    }

    @Override
    public int compare(Contacto c1, Contacto c2) {
        // La lógica aquí se basa en si 'c1' (el contacto real) cumple la condición
        // del 'paisAFiltrar' definido en este Comparator.
        // El parámetro 'c2' en este caso es irrelevante para el filtro, ya que la comparación es contra un criterio interno.
        // Por convención, un Comparator devuelve 0 si son "iguales" según el criterio de comparación,
        // un número negativo si c1 es "menor" y un positivo si c1 es "mayor".
        // Para filtrado, generalmente nos interesa si cumple (0) o no cumple (distinto de 0).

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
