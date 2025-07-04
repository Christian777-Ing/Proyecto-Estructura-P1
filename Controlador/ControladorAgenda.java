package Controlador;
import java.util.Scanner;

import Modelo.Agenda;
import Modelo.Contacto;
import Modelo.Direccion;
import Modelo.Empresa;
import Modelo.FechaImportante;
import Modelo.Foto;
import Modelo.Persona;
import Modelo.Listas.ArrayListAgenda;
import Modelo.Listas.CircledDoubleLinkedList;
import Modelo.Listas.ListAgenda;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ControladorAgenda {
    private Agenda agenda;

    public ControladorAgenda() {
        this.agenda = new Agenda();
    }


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

        // Fecha Importante
        System.out.print("¿Desea agregar una fecha importante? (s/n): ");
        while (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Descripción (ej: Cumpleaños, Pago, etc.): ");
            String desc = sc.nextLine();

            System.out.print("Ingrese la fecha (yyyy-mm-dd): ");
            String fechaStr = sc.nextLine();
            LocalDate fecha = LocalDate.parse(fechaStr);

            fechasImportantes.add(new FechaImportante(fecha, desc));

            System.out.print("¿Desea añadir otra fecha importante? (s/n): ");
        }

        // Contactos relacionados
        if (!agenda.getContactos().isEmpty()) {
            System.out.print("¿Desea añadir un contacto relacionado? (s/n): ");
            while (sc.nextLine().equalsIgnoreCase("s")) {
                System.out.print("Ingrese el teléfono del contacto que desea relacionar: ");
                String telRelacion = sc.nextLine();
                Contacto cRelacion = agenda.buscarPorTelefono(telRelacion);
                if (cRelacion != null) {
                    contactosRelacionados.add(cRelacion);
                    System.out.println("Contacto relacionado agregado: " + cRelacion.getNombre());
                } else {
                    System.out.println("No se encontró un contacto con ese teléfono.");
                }
                System.out.print("¿Desea añadir otro contacto relacionado? (s/n): ");
            }
        }

        // Atributos generales
        System.out.print("¿Desea añadir una red social ? (s/n): ");
        while (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Nombre de la red social: ");
            String attrName = sc.nextLine();
            System.out.print("Ingrese usuario de red social: ");
            String attrValue = sc.nextLine();
            atributosGenerales.put(attrName, attrValue);
            System.out.print("¿Desea añadir otra red social? (s/n): ");
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

        agenda.agregarContacto(contacto);
        System.out.println("Contacto creado con teléfono principal: " + telefonoPrincipal);
    }

    public void mostrarContactos() {
        if (agenda.getContactos().isEmpty()) {
            System.out.println("No hay contactos.");
            return;
        }
        for (Contacto c : agenda.getContactos()) {
            System.out.println("\n" + c);
        }
    }

    public void navegarContactos(Scanner sc) {
        if (agenda.getContactos().isEmpty()) {
            System.out.println("No hay contactos.");
            return;
        }

        CircledDoubleLinkedList<Contacto>.CircularDoublyLinkedListIterator iter =
            ((CircledDoubleLinkedList<Contacto>) agenda.getContactos()).new CircularDoublyLinkedListIterator();

        String op;
        do {
            System.out.println("\nContacto actual:\n" + iter.currentData());
            System.out.print("[s]iguiente, [a]nterior, [q]uit: ");
            op = sc.nextLine();

            if (op.equalsIgnoreCase("s")) {
                iter.next();
            } else if (op.equalsIgnoreCase("a")) {
                iter.previous();
            }
        } while (!op.equalsIgnoreCase("q"));
    }

    public void eliminarContacto(Scanner sc) {
        System.out.print("Ingrese el teléfono principal del contacto a eliminar: ");
        String telefono = sc.nextLine();
        if (agenda.eliminarPorTelefono(telefono)) {
            System.out.println("Contacto eliminado correctamente.");
        } else {
            System.out.println("No se encontró un contacto con ese teléfono.");
        }
    }

    public void editarContacto(Scanner sc) {
        System.out.print("Ingrese el teléfono principal del contacto a editar: ");
        String telefono = sc.nextLine();
        Contacto contacto = agenda.buscarPorTelefono(telefono);

        if (contacto == null) {
            System.out.println("No se encontró un contacto con ese teléfono.");
            return;
        }

        System.out.println("Editando contacto: " + contacto.getNombre());

        // Cambiar nombre
        System.out.print("Nuevo nombre (enter para mantener '" + contacto.getNombre() + "'): ");
        String nuevoNombre = sc.nextLine();
        if (!nuevoNombre.isEmpty()) {
            contacto.setNombre(nuevoNombre);
        }

        // Cambiar apellido o razón social según tipo
        if (contacto instanceof Persona) {
            Persona p = (Persona) contacto;
            System.out.print("Nuevo apellido (enter para mantener '" + p.getApellido() + "'): ");
            String nuevoApellido = sc.nextLine();
            if (!nuevoApellido.isEmpty()) {
                p.setApellido(nuevoApellido);
            }
        } else if (contacto instanceof Empresa) {
            Empresa e = (Empresa) contacto;
            System.out.print("Nueva razón social/sector (enter para mantener '" + e.getNombreLegal() + "'): ");
            String nuevaRazon = sc.nextLine();
            if (!nuevaRazon.isEmpty()) {
                e.setNombreLegal(nuevaRazon);
            }
        }

        // Añadir nuevos teléfonos
        System.out.print("¿Desea añadir otro teléfono? (s/n): ");
        while (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Teléfono (tipo:numero): ");
            String tel = sc.nextLine();
            if (!tel.isEmpty()) contacto.getTelefonos().add(tel);
            System.out.print("¿Desea añadir otro teléfono? (s/n): ");
        }

        // Añadir nuevos correos
        System.out.print("¿Desea añadir un correo? (s/n): ");
        while (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Correo: ");
            String correo = sc.nextLine();
            if (!correo.isEmpty()) contacto.getCorreos().add(correo);
            System.out.print("¿Desea añadir otro correo? (s/n): ");
        }

        // Añadir nuevas direcciones
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
            contacto.getDirecciones().add(new Direccion("Domicilio", cp, cs, ciudad, pais));
            System.out.print("¿Desea añadir otra dirección? (s/n): ");
        }

        // Añadir nuevas redes sociales
        System.out.print("¿Desea añadir una red social? (s/n): ");
        while (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Nombre de la red social: ");
            String red = sc.nextLine();
            System.out.print("Usuario: ");
            String user = sc.nextLine();
            contacto.getAtributosGenerales().put(red, user);
            System.out.print("¿Desea añadir otra red social? (s/n): ");
        }

        System.out.println("Contacto editado exitosamente.");
    }


    public void guardarAgenda(String archivo) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            out.writeObject(agenda);
            System.out.println("Agenda guardada correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public void cargarAgenda(String archivo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            agenda = (Agenda) in.readObject();
            System.out.println("Agenda cargada correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se pudo cargar la agenda: " + e.getMessage());
        }
    }
}