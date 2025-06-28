import java.util.Scanner;

import Listas.ArrayListAgenda;
import Listas.CircledDoubleLinkedList;
import Listas.ListAgenda;

import java.util.HashMap;
import java.util.Map;

public class ControladorAgenda {
    private Map<String, Contacto> agenda;

    public ControladorAgenda() {
        this.agenda = new HashMap<>();
    }

    public void crearContacto(Scanner sc) {
    System.out.println("\n--- Crear nuevo contacto ---");

    System.out.print("Tipo de contacto (1=Persona, 2=Empresa): ");
    int tipo = Integer.parseInt(sc.nextLine());

    System.out.print("Nombre: ");
    String nombre = sc.nextLine();

    String apellido = "";
    String razonSocial = "";
    if (tipo == 1) {
        System.out.print("Apellido: ");
        apellido = sc.nextLine();
    } else {
        System.out.print("Razón social o sector: ");
        razonSocial = sc.nextLine();
    }

    ListAgenda<String> telefonos = new ArrayListAgenda<>();
    ListAgenda<String> correos = new ArrayListAgenda<>();
    ListAgenda<Direccion> direcciones = new ArrayListAgenda<>();
    ListAgenda<Foto> fotos = new CircledDoubleLinkedList<>();
    ListAgenda<FechaImportante> fechasImportantes = new ArrayListAgenda<>();
    ListAgenda<Contacto> contactosRelacionados = new CircledDoubleLinkedList<>();
    Map<String, String> atributosGenerales = new HashMap<>();

    // Teléfonos: obligamos a ingresar al menos uno
    String telefonoPrincipal = null;
    while (telefonoPrincipal == null) {
        System.out.print("Ingrese al menos un teléfono en formato tipo:numero: ");
        String tel = sc.nextLine();
        if (!tel.isEmpty()) {
            telefonos.add(tel);
            telefonoPrincipal = tel;
        } else {
            System.out.println("Debe ingresar al menos un teléfono.");
        }
    }

    boolean seguir = true;
    while (seguir) {
        System.out.print("¿Desea añadir otro teléfono? (s/n): ");
        String resp = sc.nextLine();
        if (resp.equalsIgnoreCase("s")) {
            System.out.print("Ingrese teléfono en formato tipo:numero: ");
            String tel = sc.nextLine();
            if (!tel.isEmpty()) {
                telefonos.add(tel);
            }
        } else {
            seguir = false;
        }
    }

    // Direcciones
    seguir = true;
    while (seguir) {
        System.out.print("¿Desea añadir una dirección? (s/n): ");
        String resp = sc.nextLine();
        if (resp.equalsIgnoreCase("s")) {
            System.out.print("Calle principal: ");
            String calle1 = sc.nextLine();
            System.out.print("Calle secundaria: ");
            String calle2 = sc.nextLine();
            System.out.print("Ciudad: ");
            String ciudad = sc.nextLine();
            System.out.print("País: ");
            String pais = sc.nextLine();
            Direccion dir = new Direccion("Domicilio", calle1, calle2, ciudad, pais);
            direcciones.add(dir);
        } else {
            seguir = false;
        }
    }

    // Correos
    seguir = true;
    while (seguir) {
        System.out.print("¿Desea añadir un correo? (s/n): ");
        String resp = sc.nextLine();
        if (resp.equalsIgnoreCase("s")) {
            System.out.print("Correo: ");
            String correo = sc.nextLine();
            if (!correo.isEmpty()) {
                correos.add(correo);
            }
        } else {
            seguir = false;
        }
    }

    // Fotos
    seguir = true;
    while (seguir) {
        System.out.print("¿Desea añadir una foto? (s/n): ");
        String resp = sc.nextLine();
        if (resp.equalsIgnoreCase("s")) {
            System.out.print("Descripción: ");
            String desc = sc.nextLine();
            System.out.print("URL o ruta: ");
            String url = sc.nextLine();
            fotos.add(new Foto(desc, url));
        } else {
            seguir = false;
        }
    }

    // Atributos generales
    seguir = true;
    while (seguir) {
        System.out.print("¿Desea añadir un atributo adicional? (s/n): ");
        String resp = sc.nextLine();
        if (resp.equalsIgnoreCase("s")) {
            System.out.print("Nombre del atributo: ");
            String attrName = sc.nextLine();
            System.out.print("Valor del atributo: ");
            String attrValue = sc.nextLine();
            atributosGenerales.put(attrName, attrValue);
        } else {
            seguir = false;
        }
    }

    // Crear contacto final
    Contacto contacto;
    if (tipo == 1) {
        contacto = new Persona(nombre, apellido, telefonos, correos, direcciones, fotos, fechasImportantes, contactosRelacionados, atributosGenerales);
    } else {
        contacto = new Empresa(nombre, razonSocial, telefonos, correos, direcciones, fotos, fechasImportantes, contactosRelacionados, atributosGenerales);
    }

    agenda.put(telefonoPrincipal, contacto);
    System.out.println("Contacto creado con teléfono principal: " + telefonoPrincipal);
}
    public void mostrarContactos() {
        System.out.println("\n--- Lista de contactos ---");
        if (agenda.isEmpty()) {
            System.out.println("No hay contactos registrados.");
        } else {
            for (Map.Entry<String, Contacto> entry : agenda.entrySet()) {
                System.out.println("\nIdentificador: " + entry.getKey());
                System.out.println(entry.getValue());
            }
        }
    }
}