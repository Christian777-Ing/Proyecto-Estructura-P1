package Controlador;

import java.util.Scanner;

import Comparadores.FiltrosAgenda;
import Modelo.Agenda;
import Modelo.Contacto;
import Modelo.Direccion;
import Modelo.Empresa;
import Modelo.FechaImportante;
import Modelo.Foto;
import Modelo.Persona;
import Modelo.Telefono;
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

        ListAgenda<Telefono> telefonos = new ArrayListAgenda<>();
        ListAgenda<String> correos = new ArrayListAgenda<>();
        ListAgenda<Direccion> direcciones = new ArrayListAgenda<>();
        ListAgenda<Foto> fotos = new CircledDoubleLinkedList<>();
        ListAgenda<FechaImportante> fechasImportantes = new ArrayListAgenda<>();
        ListAgenda<Contacto> contactosRelacionados = new CircledDoubleLinkedList<>();
        Map<String, String> atributosGenerales = new HashMap<>();

        // Teléfonos: al menos uno (el primero será el principal)
        Telefono telefonoPrincipalObj = null;
        while (telefonoPrincipalObj == null) {
            System.out.print("Ingrese el TIPO de teléfono (ej: Principal, Móvil, Trabajo) (obligatorio): ");
            String tipoTel = sc.nextLine();
            System.out.print("Ingrese el NÚMERO de teléfono (obligatorio): ");
            String numTel = sc.nextLine();
            if (!tipoTel.isEmpty() && !numTel.isEmpty()) {
                telefonoPrincipalObj = new Telefono(tipoTel, numTel);
                telefonos.add(telefonoPrincipalObj);
            } else {
                System.out.println("Debe ingresar al menos un teléfono con tipo y número.");
            }
        }

        System.out.print("¿Desea añadir otro teléfono? (s/n): ");
        while (sc.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Tipo de teléfono (ej: Móvil, Casa): ");
            String tipoTel = sc.nextLine();
            System.out.print("Número de teléfono: ");
            String numTel = sc.nextLine();
            if (!tipoTel.isEmpty() && !numTel.isEmpty()) {
                telefonos.add(new Telefono(tipoTel, numTel));
            } else {
                System.out.println("Tipo o número de teléfono no pueden estar vacíos.");
            }
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
                System.out.print("Ingrese el teléfono PRINCIPAL del contacto que desea relacionar: ");
                String telRelacion = sc.nextLine();
                Contacto cRelacion = agenda.buscarPorTelefono(telRelacion);
                if (cRelacion != null) {
                    contactosRelacionados.add(cRelacion);
                    System.out.println("Contacto relacionado agregado: " + cRelacion.getNombre());
                } else {
                    System.out.println("No se encontró un contacto con ese teléfono principal.");
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
        System.out.println("Contacto creado con teléfono principal: " + telefonoPrincipalObj.toString());
    }

    public void mostrarContactos() {
        if (agenda.getContactos().isEmpty()) {
            System.out.println("No hay contactos en la agenda.");
            return;
        }
        System.out.println("\n--- Resumen de Todos los Contactos ---");
        for (int i = 0; i < agenda.getContactos().size(); i++) {
            Contacto c = agenda.getContactos().get(i);
            String tipoContacto = (c instanceof Persona) ? "Persona" : "Empresa";
            String nombreCompletoORazonSocial = c.getNombre(); // Nombre base

            // Añadir apellido o razón social si aplica
            if (c instanceof Persona) {
                nombreCompletoORazonSocial += " " + ((Persona) c).getApellido();
            } else if (c instanceof Empresa) {
                // Si getNombreLegal() es el método para la razón social en Empresa
                nombreCompletoORazonSocial += " (" + ((Empresa) c).getNombreLegal() + ")";
            }

            // Asegúrate de que getTelefonoPrincipal() en Contacto devuelva solo el número como String
            String telefonoPrincipal = c.getTelefonoPrincipal();

            System.out.println("- " + nombreCompletoORazonSocial + " [" + tipoContacto + "] - Teléfono Principal: " + telefonoPrincipal);
        }
    }

    public void navegarContactos(Scanner sc) {
        if (agenda.getContactos().isEmpty()) {
            System.out.println("No hay contactos.");
            return;
        }

        CircledDoubleLinkedList<Contacto> circularList = null;
        if (agenda.getContactos() instanceof CircledDoubleLinkedList) {
            circularList = (CircledDoubleLinkedList<Contacto>) agenda.getContactos();
        } else {
            System.out.println("La navegación circular solo funciona con CircledDoubleLinkedList.");
            return;
        }

        CircledDoubleLinkedList<Contacto>.CircularDoublyLinkedListIterator iter =
                circularList.new CircularDoublyLinkedListIterator();

        String op;
        do {
            Contacto currentContact = iter.currentData();
            if (currentContact != null) {
                System.out.println("\n--- Contacto Actual ---");
                System.out.println(currentContact);

                // Mostrar contactos relacionados si los hay
                if (currentContact.getContactosRelacionados() != null && !currentContact.getContactosRelacionados().isEmpty()) {
                    System.out.println("\n--- Contactos Relacionados ---");
                    for (int i = 0; i < currentContact.getContactosRelacionados().size(); i++) {
                        Contacto related = currentContact.getContactosRelacionados().get(i);
                        System.out.println((i + 1) + ". " + related.getNombre() + " (" + related.getTelefonoPrincipal() + ")");
                    }
                    System.out.print("Ingrese el número del contacto relacionado para ver detalles, o 0 para ignorar: ");
                    try {
                        int choice = Integer.parseInt(sc.nextLine());
                        if (choice > 0 && choice <= currentContact.getContactosRelacionados().size()) {
                            Contacto selectedRelated = currentContact.getContactosRelacionados().get(choice - 1);
                            System.out.println("\n--- Detalles del Contacto Relacionado: " + selectedRelated.getNombre() + " ---");
                            System.out.println(selectedRelated.toString());
                            System.out.println("\nPresione Enter para continuar...");
                            sc.nextLine();
                        } else if (choice != 0) {
                            System.out.println("Opción de contacto relacionado inválida.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    }
                }

            } else {
                System.out.println("\nNo hay contactos para navegar.");
                break;
            }

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
        System.out.print("Ingrese el teléfono principal (solo el número) del contacto a eliminar: ");
        String telefono = sc.nextLine();
        if (agenda.eliminarPorTelefono(telefono)) {
            System.out.println("Contacto eliminado correctamente.");
        } else {
            System.out.println("No se encontró un contacto con ese teléfono principal.");
        }
    }

    public void editarContacto(Scanner sc) {
        System.out.print("Ingrese el teléfono principal (solo el número) del contacto a editar: ");
        String telefonoBusqueda = sc.nextLine();
        Contacto contacto = agenda.buscarPorTelefono(telefonoBusqueda);

        if (contacto == null) {
            System.out.println("No se encontró un contacto con ese teléfono principal.");
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

        // Añadir nuevos teléfonos (sin 'break')
        System.out.print("¿Desea añadir otro teléfono? (s/n): ");
        String respuestaTel = sc.nextLine();
        while (respuestaTel.equalsIgnoreCase("s")) {
            System.out.print("Tipo de teléfono (ej: Móvil, Casa): ");
            String tipoTel = sc.nextLine();
            System.out.print("Número de teléfono: ");
            String numTel = sc.nextLine();
            if (!tipoTel.isEmpty() && !numTel.isEmpty()) {
                contacto.getTelefonos().add(new Telefono(tipoTel, numTel));
            } else {
                System.out.println("Tipo o número de teléfono no pueden estar vacíos.");
            }
            System.out.print("¿Desea añadir otro teléfono? (s/n): ");
            respuestaTel = sc.nextLine(); // Leer la respuesta para la próxima iteración
        }

        // Añadir nuevos correos (sin 'break')
        System.out.print("¿Desea añadir un correo? (s/n): ");
        String respuestaCorreo = sc.nextLine();
        while (respuestaCorreo.equalsIgnoreCase("s")) {
            System.out.print("Correo: ");
            String correo = sc.nextLine();
            if (!correo.isEmpty()) contacto.getCorreos().add(correo);
            System.out.print("¿Desea añadir otro correo? (s/n): ");
            respuestaCorreo = sc.nextLine();
        }

        // Añadir nuevas direcciones (sin 'break')
        System.out.print("¿Desea añadir una dirección? (s/n): ");
        String respuestaDir = sc.nextLine();
        while (respuestaDir.equalsIgnoreCase("s")) {
            System.out.print("Tipo de dirección (ej. Domicilio): ");
            String tipoDir = sc.nextLine();
            System.out.print("Calle principal: ");
            String cp = sc.nextLine();
            System.out.print("Calle secundaria: ");
            String cs = sc.nextLine();
            System.out.print("Ciudad: ");
            String ciudad = sc.nextLine();
            System.out.print("País: ");
            String pais = sc.nextLine();
            contacto.getDirecciones().add(new Direccion(tipoDir, cp, cs, ciudad, pais));
            System.out.print("¿Desea añadir otra dirección? (s/n): ");
            respuestaDir = sc.nextLine();
        }

        // Añadir nuevas redes sociales (atributos generales) (sin 'break')
        System.out.print("¿Desea añadir una red social? (s/n): ");
        String respuestaRed = sc.nextLine();
        while (respuestaRed.equalsIgnoreCase("s")) {
            System.out.print("Nombre de la red social: ");
            String red = sc.nextLine();
            System.out.print("Usuario: ");
            String user = sc.nextLine();
            contacto.getAtributosGenerales().put(red, user);
            System.out.print("¿Desea añadir otra red social? (s/n): ");
            respuestaRed = sc.nextLine();
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

    public void menuFiltrarContactos(Scanner sc) {
        if (agenda.getContactos().isEmpty()) {
            System.out.println("No hay contactos para filtrar.");
            return;
        }

        ListAgenda<Contacto> contactosFiltrados = null;
        boolean salirFiltro = false;

        while (!salirFiltro) {
            System.out.println("\n--- Filtrar Contactos ---");
            System.out.println("1. Filtrar por País de Residencia");
            System.out.println("2. Filtrar por Tipo de Contacto (Persona/Empresa)");
            System.out.println("3. Filtrar por Inicial de Apellido");
            System.out.println("0. Volver al menú principal");
            System.out.print("Opción de filtro: ");

            try {
                int opcion = Integer.parseInt(sc.nextLine());
                contactosFiltrados = null;

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el país a buscar: ");
                        String pais = sc.nextLine();
                        contactosFiltrados = FiltrosAgenda.filtrarPorPais(agenda.getContactos(), pais);
                        break;
                    case 2:
                        System.out.print("Ingrese el tipo (Persona/Empresa): ");
                        String tipo = sc.nextLine();
                        contactosFiltrados = FiltrosAgenda.filtrarPorTipo(agenda.getContactos(), tipo);
                        break;
                    case 3:
                        System.out.print("Ingrese la inicial del apellido: ");
                        char inicial = sc.nextLine().toUpperCase().charAt(0);
                        contactosFiltrados = FiltrosAgenda.filtrarPorApellidoYPrimerNombre(agenda.getContactos(), inicial);
                        break;
                    case 0:
                        salirFiltro = true;
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }

                if (contactosFiltrados != null && !contactosFiltrados.isEmpty()) {
                    System.out.println("\n--- Contactos Filtrados (" + contactosFiltrados.size() + " encontrados) ---");
                    for (int i = 0; i < contactosFiltrados.size(); i++) {
                        System.out.println("\n" + contactosFiltrados.get(i).toString());
                    }
                } else if (contactosFiltrados != null) {
                    System.out.println("No se encontraron contactos que cumplan el criterio.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido para la opción.");
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }
    }
}