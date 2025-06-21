
import java.util.HashMap;
import java.util.Map;

import Listas.ArrayListAgenda;
import Listas.CircledDoubleLinkedList;
import Listas.ListAgenda;

public abstract class Contacto {

    private String nombre;
    private ListAgenda<String> telefonos = new ArrayListAgenda<>();
    private ListAgenda<String> correos = new ArrayListAgenda<>();
    private ListAgenda<Direccion> direcciones = new ArrayListAgenda<>();
    private ListAgenda<Foto> fotos = new ArrayListAgenda<>();
    private ListAgenda<FechaImportante> fechasImportantes = new ArrayListAgenda<>();
    private ListAgenda<Contacto> contactosRelacionados = new CircledDoubleLinkedList<>();

    // Atributos personalizados (ej. Instagram, Cargo, Departamento)
    protected Map<String, String> atributosGenerales = new HashMap<>();

    // Constructor
    public Contacto(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters básicos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListAgenda<String> getTelefonos() {
        return telefonos;
    }

    public ListAgenda<String> getCorreos() {
        return correos;
    }

    public ListAgenda<Direccion> getDirecciones() {
        return direcciones;
    }

    public ListAgenda<Foto> getFotos() {
        return fotos;
    }

    public ListAgenda<FechaImportante> getFechasImportantes() {
        return fechasImportantes;
    }

    public ListAgenda<Contacto> getContactosRelacionados() {
        return contactosRelacionados;
    }

    // Métodos de atributos personalizados
    public void agregarOEditarAtributo(String nombreAtributo, String valor) {
        atributosGenerales.put(nombreAtributo, valor);
    }

    public boolean eliminarAtributo(String nombreAtributo) {
        return atributosGenerales.remove(nombreAtributo) != null;
    }

    public String getAtributo(String nombreAtributo) {
        return atributosGenerales.get(nombreAtributo);
    }

    public Map<String, String> getAtributosGenerales() {
        return atributosGenerales;
    }

    public void mostrarAtributos() {
        for (Map.Entry<String, String> entry : atributosGenerales.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    
}
