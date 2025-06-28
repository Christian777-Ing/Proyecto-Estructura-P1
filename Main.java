import java.util.Scanner;

import Controlador.ControladorAgenda;

public class Main {
    public static void main(String[] args) {
        ControladorAgenda controlador = new ControladorAgenda();
        Scanner sc = new Scanner(System.in);
        controlador.cargarAgenda("agenda.dat");

        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Crear contacto");
            System.out.println("2. Mostrar todos");
            System.out.println("3. Navegar uno a uno");
            System.out.println("4. Guardar y salir");
            System.out.print("Opción: ");
            switch (Integer.parseInt(sc.nextLine())) {
                case 1 -> controlador.crearContacto(sc);
                case 2 -> controlador.mostrarContactos();
                case 3 -> controlador.navegarContactos(sc);
                case 4 -> {
                    controlador.guardarAgenda("agenda.dat");
                    salir = true;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
        sc.close();
    }
}
