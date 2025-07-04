package Modelo;

import java.io.Serializable;

public class Foto implements Serializable{
    private String descripcion; // Breve texto para identificar la imagen
    private String urlImagen;   // Ruta local o URL en la nube

    public Foto(String descripcion, String urlImagen) {
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    @Override
    public String toString() {
        return descripcion + " (" + urlImagen + ")";
    }

}
