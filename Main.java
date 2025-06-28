import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ControladorAgenda controlador = new ControladorAgenda();
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n===== AGENDA DE CONTACTOS =====");
            System.out.println("1. Ver contactos");
            System.out.println("2. Crear nuevo contacto");
            System.out.println("3. Salir");
            System.out.print("Elija una opción: ");

            int opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    controlador.mostrarContactos();
                    break;
                case 2:
                    controlador.crearContacto(sc);
                    break;
                case 3:
                    salir = true;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        sc.close();
    }
}
