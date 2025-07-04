package Modelo;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import Modelo.Listas.CircledDoubleLinkedList;
import Modelo.Listas.ListAgenda;

public class Agenda implements Serializable {
    private ListAgenda<Contacto> contactos = new CircledDoubleLinkedList<>();
    private Map<String, Contacto> contactosPorTelefono = new HashMap<>();
    public ListAgenda<Contacto> getContactos() { return contactos; }
    public boolean existeTelefono(String telefono) { return contactosPorTelefono.containsKey(telefono); }

    private int indiceActual = 0;
    public boolean agregarContacto(Contacto contacto) {
        String telefonoClave = contacto.getTelefonoPrincipal();
        if (telefonoClave == null || contactosPorTelefono.containsKey(telefonoClave)) return false;
        contactos.add(contacto);
        contactosPorTelefono.put(telefonoClave, contacto);
        return true;
    }

    public boolean eliminarPorTelefono(String numeroPrincipal) {
        Contacto contactoAEliminar = null;
        int indexParaEliminar = -1;

        // Primero busca el contacto por el número de su teléfono principal
        for (int i = 0; i < contactos.size(); i++) {
            Contacto c = contactos.get(i);
            if (c.getTelefonos() != null && !c.getTelefonos().isEmpty() &&
                c.getTelefonos().get(0).getNumero().equals(numeroPrincipal)) {
                contactoAEliminar = c;
                indexParaEliminar = i;
                break;
            }
        }

        if (contactoAEliminar != null) {
            // Eliminar referencias a este contacto en otros contactos relacionados
            for (int i = 0; i < contactos.size(); i++) {
                Contacto otroContacto = contactos.get(i);
                if (otroContacto.getContactosRelacionados() != null) {
                    ListAgenda<Contacto> relacionados = otroContacto.getContactosRelacionados();
                    for (int k = 0; k < relacionados.size(); k++) {
                        if (relacionados.get(k).equals(contactoAEliminar)) {
                            relacionados.remove(k);
                            k--; // Ajustar índice después de remover
                        }
                    }
                }
            }
            // Finalmente, eliminar el contacto de la agenda
            contactos.remove(indexParaEliminar);
            return true;
        }
        return false;
    }
    
    public Contacto buscarPorTelefono(String numeroPrincipal) {
        for (int i = 0; i < contactos.size(); i++) {
            Contacto c = contactos.get(i);
            // Compara el número del primer teléfono de la lista con el número de búsqueda
            if (c.getTelefonos() != null && !c.getTelefonos().isEmpty() &&
                c.getTelefonos().get(0).getNumero().equals(numeroPrincipal)) {
                return c;
            }
        }
        return null;
    }

    public Contacto getActual() {
        return contactos.isEmpty() ? null : contactos.get(indiceActual);
    }

    public Contacto siguiente() {
        if (contactos.isEmpty()) return null;
        indiceActual = (indiceActual + 1) % contactos.size();
        return getActual();
    }

    public Contacto anterior() {
        if (contactos.isEmpty()) return null;
        indiceActual = (indiceActual - 1 + contactos.size()) % contactos.size();
        return getActual();
    }
}

