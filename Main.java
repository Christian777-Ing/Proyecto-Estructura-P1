import java.util.Scanner;

import Controlador.ControladorAgenda;
import java.io.IOException; // Necesario para el manejo de excepciones de ProcessBuilder

public class Main {

    // Método para limpiar la consola
    public static void limpiarConsola() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // Para Windows: "cmd /c cls"
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Para Linux/macOS: Usar secuencias de escape ANSI.
                // Ten en cuenta que esto puede no funcionar en todos los IDEs
                // que no emulan una terminal real (ej. algunos entornos de Eclipse, IntelliJ).
                System.out.print("\033[H\033[2J");
                System.out.flush();
                // Alternativa para Linux/macOS (a veces funciona mejor, pero también con limitaciones en IDEs):
                // new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            // Si falla la limpieza (ej. en ciertos IDEs o entornos restringidos),
            // se imprimen líneas en blanco como un "intento" de limpieza.
            System.out.println("No se pudo limpiar la consola completamente. Error: " + e.getMessage());
            for (int i = 0; i < 50; ++i) System.out.println(); // Intento de "limpiar" con saltos de línea
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ControladorAgenda controlador = new ControladorAgenda();
        String archivoAgenda = "agenda.dat"; // Nombre del archivo para guardar/cargar

        controlador.cargarAgenda(archivoAgenda); // Cargar agenda al inicio

        boolean salir = false;
        while (!salir) {
            limpiarConsola(); // Limpiamos la consola antes de mostrar el menú principal

            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Crear contacto");
            System.out.println("2. Mostrar todos los contactos (Resumen)");
            System.out.println("3. Navegar contactos (circular)");
            System.out.println("4. Editar contacto");
            System.out.println("5. Eliminar contacto");
            System.out.println("6. Filtrar contactos");
            System.out.println("7. Guardar y Salir"); // Cambié "7" a "8" para mantener la consistencia en el orden
            System.out.print("Opción: ");

            try {
                int opcion = Integer.parseInt(sc.nextLine());

                // Limpiamos la consola inmediatamente después de que el usuario selecciona una opción,
                // antes de que se muestre la salida de esa opción.
                if (opcion != 8) { // No limpiar si la opción es salir, para que se vea el mensaje de despedida
                    limpiarConsola();
                }

                switch (opcion) {
                    case 1 -> controlador.crearContacto(sc);
                    case 2 -> controlador.mostrarContactos(); // Ahora mostrará un resumen
                    case 3 -> controlador.navegarContactos(sc);
                    case 4 -> controlador.editarContacto(sc);
                    case 5 -> controlador.eliminarContacto(sc);
                    case 6 -> controlador.menuFiltrarContactos(sc); // Llama al método para ver detalles
                    case 7 -> {
                        controlador.guardarAgenda(archivoAgenda);
                        salir = true;
                        System.out.println("¡Agenda guardada! Hasta luego.");
                    }
                    default -> System.out.println("Opción inválida. Intente de nuevo.");
                }

                // Pausar solo si no vamos a salir
                if (!salir) {
                    System.out.println("\n--- Presione Enter para continuar ---");
                    sc.nextLine(); // Espera la pulsación de Enter
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                // No hay necesidad de limpiar aquí, el siguiente ciclo del bucle lo hará
                System.out.println("\n--- Presione Enter para continuar ---");
                sc.nextLine(); // Espera la pulsación de Enter antes de volver al menú
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
                // No hay necesidad de limpiar aquí, el siguiente ciclo del bucle lo hará
                System.out.println("\n--- Presione Enter para continuar ---");
                sc.nextLine(); // Espera la pulsación de Enter antes de volver al menú
            }
        }
        sc.close();
    }
}

