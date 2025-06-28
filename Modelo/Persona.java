package Modelo;
import java.util.Map;

import Modelo.Listas.ListAgenda;

public class Persona extends Contacto {
    private static final long serialVersionUID = 1L;
    private String apellido;

        public Persona(String nombre, String apellido, ListAgenda<String> telefonos, ListAgenda<String> correos,
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
        sb.append("Persona: ").append(getNombre()).append(" ").append(apellido).append("\n");

        sb.append("Teléfonos:\n");
        for (String tel : getTelefonos()) {
            sb.append(" - ").append(tel).append("\n");
        }

        sb.append("Direcciones:\n");
        for (Direccion dir : getDirecciones()) {
            sb.append(" - ").append(dir).append("\n");
        }

        sb.append("Fotos:\n");
        for (Foto foto : getFotos()) {
            sb.append(" - ").append(foto).append("\n");
        }

        sb.append("Correos:\n");
        for (String correo : getCorreos()) {
            sb.append(" - ").append(correo).append("\n");
        }

        sb.append("Fechas importantes:\n");
        for (FechaImportante fecha : getFechasImportantes()) {
            sb.append(" - ").append(fecha).append("\n");
        }

        sb.append("Contactos relacionados:\n");
        for (Contacto c : getContactosRelacionados()) {
            sb.append(" - ").append(c.getNombre()).append("\n");
        }

        return sb.toString();
    }
}

