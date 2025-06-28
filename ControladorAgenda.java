import java.util.Scanner;

import Listas.ArrayListAgenda;
import Listas.CircledDoubleLinkedList;
import Listas.ListAgenda;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ControladorAgenda {
    private Map<String, Contacto> agenda = new HashMap<>();

    public void crearContacto(Scanner sc) {
        System.out.println("\n--- Crear nuevo contacto ---");
        System.out.print("Tipo de contacto (1=Persona, 2=Empresa): ");
        int tipo = Integer.parseInt(sc.nextLine());

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        String apellidoORazonSocial;
        if (tipo == 1) {
            System.out.print("Apellido: ");
            apellidoORazonSocial = sc.nextLine();
        } else {
            System.out.print("Razón social o sector: ");
            apellidoORazonSocial = sc.nextLine();
        }

        ListAgenda<String> telefonos = new ArrayListAgenda<>();
        ListAgenda<String> correos = new ArrayListAgenda<>();
        ListAgenda<Direccion> direcciones = new ArrayListAgenda<>();
        ListAgenda<Foto> fotos = new CircledDoubleLinkedList<>();
        ListAgenda<FechaImportante> fechasImportantes = new ArrayListAgenda<>();
        ListAgenda<Contacto> contactosRelacionados = new CircledDoubleLinkedList<>();
        Map<String, String> atributosGenerales = new HashMap<>();

        // Teléfonos: al menos uno
        String telefonoPrincipal = null;
        while (telefonoPrincipal == null) {
            System.out.print("Ingrese teléfono en formato tipo:numero (obligatorio): ");
            String tel = sc.nextLine();
            if (!tel.isEmpty()) {
                telefonos.add(tel);
                telefonoPrincipal = tel;
            } else {
                System.out.println("Debe ingresar al menos un teléfono.");
            }
        }

        System.out.print("¿Desea añadir otro teléfono? (s/n): ");
        while (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Teléfono (tipo:numero): ");
            String tel = sc.nextLine();
            if (!tel.isEmpty()) telefonos.add(tel);
            System.out.print("¿Desea añadir otro teléfono? (s/n): ");
        }

        // Direcciones
        System.out.print("¿Desea añadir una dirección? (s/n): ");
        while (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Calle principal: ");
            String cp = sc.nextLine();
            System.out.print("Calle secundaria: ");
            String cs = sc.nextLine();
            System.out.print("Ciudad: ");
            String ciudad = sc.nextLine();
            System.out.print("País: ");
            String pais = sc.nextLine();
            direcciones.add(new Direccion("Domicilio", cp, cs, ciudad, pais));
            System.out.print("¿Desea añadir otra dirección? (s/n): ");
        }

        // Correos
        System.out.print("¿Desea añadir un correo? (s/n): ");
        while (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Correo: ");
            String correo = sc.nextLine();
            if (!correo.isEmpty()) correos.add(correo);
            System.out.print("¿Desea añadir otro correo? (s/n): ");
        }

        // Fotos
        System.out.print("¿Desea añadir una foto? (s/n): ");
        while (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Descripción: ");
            String desc = sc.nextLine();
            System.out.print("URL o ruta: ");
            String url = sc.nextLine();
            fotos.add(new Foto(desc, url));
            System.out.print("¿Desea añadir otra foto? (s/n): ");
        }

        // Atributos generales
        System.out.print("¿Desea añadir un atributo adicional? (s/n): ");
        while (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Nombre del atributo: ");
            String attrName = sc.nextLine();
            System.out.print("Valor del atributo: ");
            String attrValue = sc.nextLine();
            atributosGenerales.put(attrName, attrValue);
            System.out.print("¿Desea añadir otro atributo? (s/n): ");
        }

        // Crear contacto final
        Contacto contacto;
        if (tipo == 1) {
            contacto = new Persona(nombre, apellidoORazonSocial, telefonos, correos, direcciones,
                    fotos, fechasImportantes, contactosRelacionados, atributosGenerales);
        } else {
            contacto = new Empresa(nombre, apellidoORazonSocial, telefonos, correos, direcciones,
                    fotos, fechasImportantes, contactosRelacionados, atributosGenerales);
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
                System.out.println("\nIdentificador (teléfono principal): " + entry.getKey());
                System.out.println(entry.getValue());
            }
        }
    }

    public void guardarAgenda(String archivo) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            out.writeObject(agenda);
            System.out.println("Agenda guardada correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarAgenda(String archivo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            agenda = (Map<String, Contacto>) in.readObject();
            System.out.println("Agenda cargada correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se pudo cargar agenda: " + e.getMessage());
        }
    }

    public Map<String, Contacto> getAgenda() {
        return agenda;
    }
}