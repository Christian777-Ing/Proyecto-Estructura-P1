public class Persona extends Contacto {
    private String apellido;
    public Persona(String nombre, String apellido) {
        super(nombre);
        this.apellido = apellido;
    }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
}

