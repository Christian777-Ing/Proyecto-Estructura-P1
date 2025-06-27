public class Empresa extends Contacto {
    private String nombreLegal;

    public Empresa(String nombre, String nombreLegal) {
        super(nombre);
        this.nombreLegal = nombreLegal;
    }

    public String getNombreLegal() { return nombreLegal; }
    public void setNombreLegal(String nombreLegal) { this.nombreLegal = nombreLegal; }

}
