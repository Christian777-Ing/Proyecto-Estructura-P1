package Comparadores;

import java.util.Comparator;
import Modelo.Contacto;
import Modelo.Listas.CircledDoubleLinkedList;
import Modelo.Listas.ListAgenda;

public class FiltrosAgenda {


    public static ListAgenda<Contacto> filtrarPorPais(ListAgenda<Contacto> lista, String paisBuscado) {
        // Instanciamos el comparador con el país que queremos filtrar
        Comparator<Contacto> comparadorFiltro = new ComparadorPorPais(paisBuscado);

        ListAgenda<Contacto> resultado = new CircledDoubleLinkedList<>();
        for (int i = 0; i < lista.size(); i++) {
            Contacto c = lista.get(i);
            // Comparamos el contacto 'c' con 'null' o cualquier otro Contacto,
            // ya que la lógica importante está en el comparator al comparar 'c'
            // contra el 'paisAFiltrar' interno del comparador.
            // Si el 'compare' devuelve 0, significa que 'c' cumple el filtro.
            if (comparadorFiltro.compare(c, null) == 0) { // El segundo argumento no se usa en esta lógica de filtro
                resultado.add(c);
            }
        }
        return resultado;
    }


    public static ListAgenda<Contacto> filtrarPorTipo(ListAgenda<Contacto> lista, String tipoDeseado) {
        // Instanciamos el comparador con el tipo que queremos filtrar
        Comparator<Contacto> comparadorFiltro = new ComparadorPorTipo(tipoDeseado);

        ListAgenda<Contacto> resultado = new CircledDoubleLinkedList<>();
        for (int i = 0; i < lista.size(); i++) {
            Contacto c = lista.get(i);
            // Si el 'compare' devuelve 0, significa que 'c' cumple el filtro de tipo.
            if (comparadorFiltro.compare(c, null) == 0) {
                resultado.add(c);
            }
        }
        return resultado;
    }


    public static ListAgenda<Contacto> filtrarPorApellidoYPrimerNombre(ListAgenda<Contacto> lista, char inicialApellido) {
        Comparator<Contacto> comparadorFiltro = new ComparadorPorApellidoYNombre(inicialApellido);
        ListAgenda<Contacto> resultado = new CircledDoubleLinkedList<>();
        for (int i = 0; i < lista.size(); i++) {
            Contacto c = lista.get(i);
            if (comparadorFiltro.compare(c, null) == 0) {
                resultado.add(c);
            }
        }
        return resultado;
    }







}