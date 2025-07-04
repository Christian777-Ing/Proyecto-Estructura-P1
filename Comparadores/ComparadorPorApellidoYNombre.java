package Comparadores;
import java.util.Comparator;
import Modelo.Contacto;
import Modelo.Persona;

public class ComparadorPorApellidoYNombre implements Comparator<Contacto> {
    private char inicialApellidoAFiltrar;

    public ComparadorPorApellidoYNombre(char inicialApellido) {
        this.inicialApellidoAFiltrar = Character.toUpperCase(inicialApellido);
    }

    @Override
    public int compare(Contacto c1, Contacto c2) {
        if (c1 instanceof Persona) {
            Persona persona = (Persona) c1;
            String apellido = persona.getApellido();
            String primerNombre = persona.getNombre(); // Aunque no se use directamente en la inicial

            if (apellido != null && !apellido.isEmpty() &&
                Character.toUpperCase(apellido.charAt(0)) == inicialApellidoAFiltrar) {
                return 0; // Cumple la condición
            }
        }
        return 1; // No cumple
    }
}
