import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ControladorAgenda controlador = new ControladorAgenda();
        Scanner sc = new Scanner(System.in);

        controlador.cargarAgenda("agenda.dat");

        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Crear contacto");
            System.out.println("2. Mostrar contactos");
            System.out.println("3. Guardar y salir");
            System.out.print("Opción: ");
            int op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> controlador.crearContacto(sc);
                case 2 -> controlador.mostrarContactos();
                case 3 -> {
                    controlador.guardarAgenda("agenda.dat");
                    salir = true;
                }
                default -> System.out.println("Opción no válida.");
            }
        }

        sc.close();
    }
}
