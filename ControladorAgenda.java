import java.util.Scanner;

import Listas.ArrayListAgenda;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ControladorAgenda {
    private Map<String, Contacto> agenda;

    public ControladorAgenda() {
        this.agenda = new HashMap<>();
    }

    public void mostrarContactos() {
        if (agenda.isEmpty()) {
            System.out.println("No hay contactos en la agenda.");
            return;
        }

        System.out.println("\n--- Lista de Contactos ---");
        for (Map.Entry<String, Contacto> entry : agenda.entrySet()) {
            System.out.println("\nIdentificador (teléfono principal): " + entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    public void crearContacto(Scanner sc) {
        System.out.print("Tipo de contacto (1=Persona, 2=Empresa): ");
        int tipo = Integer.parseInt(sc.nextLine());

        Contacto contacto;
        if (tipo == 1) {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Apellido: ");
            String apellido = sc.nextLine();
            contacto = new Persona(nombre, apellido);
        } else if (tipo == 2) {
            System.out.print("Nombre de la empresa: ");
            String nombre = sc.nextLine();
            System.out.print("Sector o descripción: ");
            String desc = sc.nextLine();
            contacto = new Empresa(nombre, desc);
        } else {
            System.out.println("Tipo inválido.");
            return;
        }

        // --- Teléfonos ---
        String telefonoPrincipal = agregarTelefonos(sc, contacto);

        // --- Direcciones ---
        agregarDirecciones(sc, contacto);

        // --- Fotos ---
        agregarFotos(sc, contacto);

        // Añadir contacto al mapa usando el primer teléfono como clave
        if (telefonoPrincipal != null) {
            agenda.put(telefonoPrincipal, contacto);
            System.out.println("Contacto agregado correctamente con identificador (teléfono principal): " + telefonoPrincipal);
        } else {
            System.out.println("No se agregó contacto porque no ingresó al menos un teléfono.");
        }
    }

    private String agregarTelefonos(Scanner sc, Contacto contacto) {
        String telefonoPrincipal = null;

        while (true) {
            System.out.print("Ingrese un teléfono en el formato tipo:numero (o vacío para terminar): ");
            String tel = sc.nextLine();
            if (tel.isEmpty()) break;

            contacto.getTelefonos().add(tel);
            if (telefonoPrincipal == null) {
                telefonoPrincipal = tel;
            }
        }
        return telefonoPrincipal;
    }

    private void agregarDirecciones(Scanner sc, Contacto contacto) {
        while (true) {
            System.out.print("¿Desea añadir una dirección? (s/n): ");
            String resp = sc.nextLine();
            if (!resp.equalsIgnoreCase("s")) break;

            System.out.print("Calle principal: ");
            String calle1 = sc.nextLine();
            System.out.print("Calle secundaria: ");
            String calle2 = sc.nextLine();
            System.out.print("Ciudad: ");
            String ciudad = sc.nextLine();
            System.out.print("País: ");
            String pais = sc.nextLine();

            Direccion dir = new Direccion("Domicilio", calle1, calle2, ciudad, pais);
            contacto.getDirecciones().add(dir);
        }
    }

    private void agregarFotos(Scanner sc, Contacto contacto) {
        while (true) {
            System.out.print("¿Desea añadir una foto? (s/n): ");
            String resp = sc.nextLine();
            if (!resp.equalsIgnoreCase("s")) break;

            System.out.print("Descripción de la foto: ");
            String desc = sc.nextLine();
            System.out.print("URL o ruta de la imagen: ");
            String url = sc.nextLine();

            Foto foto = new Foto(desc, url);
            contacto.getFotos().add(foto);
        }
    }
}