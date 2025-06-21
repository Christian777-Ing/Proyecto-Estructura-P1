public class Direccion {

    private String tipo;              // "Casa", "Trabajo", etc.
    private String callePrincipal;
    private String calleSecundaria;
    private String ciudad;
    private String pais;
    private String linkMapa;

    public Direccion(String tipo, String callePrincipal, String calleSecundaria, String ciudad, String pais) {
        this.tipo = tipo;
        this.callePrincipal = callePrincipal;
        this.calleSecundaria = calleSecundaria;
        this.ciudad = ciudad;
        this.pais = pais;
        this.linkMapa = generarLinkGoogleMaps();
    }

    private String generarLinkGoogleMaps() {
        String direccionCompleta = callePrincipal + " y " + calleSecundaria + ", " + ciudad + ", " + pais;
        String direccionCodificada = direccionCompleta.trim().replace(" ", "+");
        return "https://www.google.com/maps/search/?api=1&query=" + direccionCodificada;
    }

    // Getters y Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCallePrincipal() {
        return callePrincipal;
    }

    public void setCallePrincipal(String callePrincipal) {
        this.callePrincipal = callePrincipal;
        actualizarLink();
    }

    public String getCalleSecundaria() {
        return calleSecundaria;
    }

    public void setCalleSecundaria(String calleSecundaria) {
        this.calleSecundaria = calleSecundaria;
        actualizarLink();
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
        actualizarLink();
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
        actualizarLink();
    }

    public String getLinkMapa() {
        return linkMapa;
    }

    private void actualizarLink() {
        this.linkMapa = generarLinkGoogleMaps();
    }

    @Override
    public String toString() {
        return tipo + ": " + callePrincipal + " y " + calleSecundaria + ", " + ciudad + ", " + pais +
               " (Mapa: " + linkMapa + ")";
    }
    
}
