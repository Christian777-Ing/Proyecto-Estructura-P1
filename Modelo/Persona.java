package Modelo;
import java.util.Map;

import Modelo.Listas.ListAgenda;

public class Persona extends Contacto {
    private static final long serialVersionUID = 1L;
    private String apellido;

        public Persona(String nombre, String apellido, ListAgenda<Telefono> telefonos, ListAgenda<String> correos,
            ListAgenda<Direccion> direcciones, ListAgenda<Foto> fotos, ListAgenda<FechaImportante> fechasImportantes,
            ListAgenda<Contacto> contactosRelacionados, Map<String, String> atributosGenerales) {
        super(nombre, telefonos, correos, direcciones, fotos, fechasImportantes, contactosRelacionados, atributosGenerales);
        this.apellido= apellido;
    }


    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

 @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tipo: Persona\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Apellido: ").append(apellido).append("\n");

        sb.append("Teléfonos:\n");
        if (telefonos != null && !telefonos.isEmpty()) {
            for (int i = 0; i < telefonos.size(); i++) {
                Telefono tel = telefonos.get(i); // Obtener un objeto Telefono
                sb.append("  - ").append(tel.toString()).append("\n"); // Llama al toString() de Telefono
            }
        } else {
            sb.append("  (No hay teléfonos)\n");
        }

        sb.append("Correos:\n");
        if (correos != null && !correos.isEmpty()) {
            for (int i = 0; i < correos.size(); i++) {
                sb.append("  - ").append(correos.get(i)).append("\n");
            }
        } else {
            sb.append("  (No hay correos)\n");
        }

        sb.append("Direcciones:\n");
        if (direcciones != null && !direcciones.isEmpty()) {
            for (int i = 0; i < direcciones.size(); i++) {
                Direccion dir = direcciones.get(i);
                sb.append("  - ").append(dir.toString()).append("\n");
            }
        } else {
            sb.append("  (No hay direcciones)\n");
        }

        sb.append("Fechas Importantes:\n");
        if (fechasImportantes != null && !fechasImportantes.isEmpty()) {
            for (int i = 0; i < fechasImportantes.size(); i++) {
                FechaImportante fecha = fechasImportantes.get(i);
                sb.append("  - ").append(fecha.toString()).append("\n");
            }
        } else {
            sb.append("  (No hay fechas importantes)\n");
        }

        sb.append("Contactos Relacionados:\n");
        if (contactosRelacionados != null && !contactosRelacionados.isEmpty()) {
            for (int i = 0; i < contactosRelacionados.size(); i++) {
                Contacto relacionado = contactosRelacionados.get(i);
                sb.append("  - ").append(relacionado.getNombre()).append(" (Teléfono Principal: ").append(relacionado.getTelefonoPrincipal()).append(")\n");
            }
        } else {
            sb.append("  (No hay contactos relacionados)\n");
        }

        sb.append("Atributos Generales:\n");
        if (atributosGenerales != null && !atributosGenerales.isEmpty()) {
            for (Map.Entry<String, String> entry : atributosGenerales.entrySet()) {
                sb.append("  - ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
        } else {
            sb.append("  (No hay atributos generales)\n");
        }

        return sb.toString();
    }
}

