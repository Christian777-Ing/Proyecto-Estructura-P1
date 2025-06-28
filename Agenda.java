import java.util.HashMap;
import java.util.Map;


import Listas.CircledDoubleLinkedList;
import Listas.ListAgenda;

public class Agenda {
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

    public boolean eliminarPorTelefono(String telefono) {
        Contacto c = contactosPorTelefono.remove(telefono);
        if (c != null) {
            for (int i = 0; i < contactos.size(); i++) {
                if (contactos.get(i).equals(c)) {
                    contactos.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public Contacto buscarPorTelefono(String telefono) {
        return contactosPorTelefono.get(telefono);
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

