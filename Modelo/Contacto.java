package Modelo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import Modelo.Listas.ArrayListAgenda;
import Modelo.Listas.CircledDoubleLinkedList;
import Modelo.Listas.ListAgenda;

public abstract class Contacto implements Serializable{
    private static final long serialVersionUID = 1L;
    String nombre;
    ListAgenda<Telefono> telefonos = new ArrayListAgenda<>();
    ListAgenda<String> correos = new ArrayListAgenda<>();
    ListAgenda<Direccion> direcciones = new ArrayListAgenda<>();
    ListAgenda<Foto> fotos = new CircledDoubleLinkedList<Foto>();
    ListAgenda<FechaImportante> fechasImportantes = new ArrayListAgenda<>();
    ListAgenda<Contacto> contactosRelacionados = new CircledDoubleLinkedList<>();

    // Atributos personalizados (ej. Instagram, Cargo, Departamento)
    protected Map<String, String> atributosGenerales = new HashMap<>();



    public Contacto(String nombre, ListAgenda<Telefono> telefonos, ListAgenda<String> correos,
            ListAgenda<Direccion> direcciones, ListAgenda<Foto> fotos, ListAgenda<FechaImportante> fechasImportantes,
            ListAgenda<Contacto> contactosRelacionados, Map<String, String> atributosGenerales) {
        this.nombre = nombre;
        this.telefonos = telefonos;
        this.correos = correos;
        this.direcciones = direcciones;
        this.fotos = fotos;
        this.fechasImportantes = fechasImportantes;
        this.contactosRelacionados = contactosRelacionados;
        this.atributosGenerales = atributosGenerales;
    }

    // Getters y Setters básicos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListAgenda<Telefono> getTelefonos() {
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

    public String getTelefonoPrincipal() {
        if (telefonos != null && !telefonos.isEmpty()) {
            return telefonos.get(0).getNumero(); // El primer teléfono ingresado es el principal
        }
        return "Ningun telefono asociado"; // O lanza una excepción si un contacto debe tener siempre un teléfono principal
    }
    
}
