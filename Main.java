import java.util.Scanner;

import Controlador.ControladorAgenda;

public class Main {
    public static void main(String[] args) {
        ControladorAgenda controlador = new ControladorAgenda();
        Scanner sc = new Scanner(System.in);
        controlador.cargarAgenda("agenda.dat"); // Cargar agenda al inicio

        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Crear contacto");
            System.out.println("2. Mostrar todos los contactos");
            System.out.println("3. Navegar contactos (circular)");
            System.out.println("4. Editar contacto");
            System.out.println("5. Eliminar contacto");
            System.out.println("6. Filtrar contactos"); // Nueva opción
            System.out.println("7. Guardar y salir"); // Opción 7 para salir
            System.out.print("Opción: ");
            try {
                switch (Integer.parseInt(sc.nextLine())) {
                    case 1 -> controlador.crearContacto(sc);
                    case 2 -> controlador.mostrarContactos();
                    case 3 -> controlador.navegarContactos(sc);
                    case 4 -> controlador.editarContacto(sc);
                    case 5 -> controlador.eliminarContacto(sc);
                    case 6 -> controlador.menuFiltrarContactos(sc); // Llama al nuevo menú de filtros
                    case 7 -> {
                        controlador.guardarAgenda("agenda.dat");
                        salir = true;
                    }
                    default -> System.out.println("Opción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
            }
        }
        sc.close();
        System.out.println("¡Hasta luego!");
    }
}

