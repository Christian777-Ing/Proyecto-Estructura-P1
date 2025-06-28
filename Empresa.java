import java.util.Map;

import Listas.ListAgenda;

public class Empresa extends Contacto {
    private static final long serialVersionUID = 1L;

    private String razonSocial;

        public Empresa(String nombre, String razonSocial, ListAgenda<String> telefonos, ListAgenda<String> correos,
            ListAgenda<Direccion> direcciones, ListAgenda<Foto> fotos, ListAgenda<FechaImportante> fechasImportantes,
            ListAgenda<Contacto> contactosRelacionados, Map<String, String> atributosGenerales) {
        super(nombre, telefonos, correos, direcciones, fotos, fechasImportantes, contactosRelacionados, atributosGenerales);
        this.razonSocial= razonSocial;

    }


    public String getNombreLegal() { return razonSocial; }
    public void setNombreLegal(String razonSocial) { this.razonSocial = razonSocial; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empresa: ").append(getNombre()).append(" ").append(razonSocial).append("\n");

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
