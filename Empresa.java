public class Empresa extends Contacto {
    private String razonSocial;

    public Empresa(String nombre, String razonSocial) {
        super(nombre);
        this.razonSocial = razonSocial;
    }

    public String getNombreLegal() { return razonSocial; }
    public void setNombreLegal(String razonSocial) { this.razonSocial = razonSocial; }

@Override
public String toString() {
    return "Persona: " + getNombre() + " " + razonSocial +
           ", Tel: " + getTelefonos().size() +
           ", Direcciones: " + getDirecciones().size() +
           ", Fotos: " + getFotos().size();
}
}
