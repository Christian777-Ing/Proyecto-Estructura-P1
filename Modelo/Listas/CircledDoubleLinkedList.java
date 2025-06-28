package Modelo.Listas;

import java.util.Iterator;


// Clase de Lista circular doblemente enlazada

public class CircledDoubleLinkedList<E> implements ListAgenda<E> {
    

    private NodoDobleCircular<E> cabeza; // Referencia al primer nodo de la lista
    private int tamaño; // Tamaño de la lista (número de nodos)


    // Clase NodoDobleCircular para representar cada nodo en la lista

class NodoDobleCircular<E> {
    E dato;                        // Dato almacenado en el nodo
    NodoDobleCircular<E> siguiente; // Referencia al siguiente nodo
    NodoDobleCircular<E> anterior;  // Referencia al nodo anterior

    // Constructor para inicializar un nodo con un dato
    public NodoDobleCircular(E dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }
}

    // Constructor para inicializar la lista como vacía
    public CircledDoubleLinkedList() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    // Método para añadir un elemento al final de la lista
    public void add(E dato) {
        NodoDobleCircular<E> nuevoNodo = new NodoDobleCircular<>(dato);
        if (cabeza == null) {                 // Si la lista está vacía, el nuevo nodo es cabeza y se enlaza a sí mismo
            cabeza = nuevoNodo;
            cabeza.siguiente = cabeza;
            cabeza.anterior = cabeza;
        } else {
            NodoDobleCircular<E> cola = cabeza.anterior; // Obtener la cola desde la referencia de cabeza
            cola.siguiente = nuevoNodo;       // El último nodo apunta al nuevo nodo
            nuevoNodo.anterior = cola;        // El nuevo nodo apunta al anterior (antigua cola)
            nuevoNodo.siguiente = cabeza;     // El nuevo nodo apunta a la cabeza
            cabeza.anterior = nuevoNodo;      // La cabeza apunta de nuevo al nuevo nodo (nueva cola)
        }
        tamaño++; // Incrementa el tamaño de la lista
    }

    // Método para añadir un elemento al inicio de la lista
    public void addFirst(E dato) {
        NodoDobleCircular<E> nuevoNodo = new NodoDobleCircular<>(dato);
        if (cabeza == null) {                 // Si la lista está vacía, el nuevo nodo es cabeza y se enlaza a sí mismo
            cabeza = nuevoNodo;
            cabeza.siguiente = cabeza;
            cabeza.anterior = cabeza;
        } else {
            NodoDobleCircular<E> cola = cabeza.anterior; // Obtener la cola desde la referencia de cabeza
            nuevoNodo.siguiente = cabeza;     // El nuevo nodo apunta al antiguo primer nodo
            nuevoNodo.anterior = cola;        // El nuevo nodo apunta a la antigua cola
            cola.siguiente = nuevoNodo;       // La cola apunta al nuevo nodo
            cabeza.anterior = nuevoNodo;      // El antiguo primer nodo apunta al nuevo nodo
            cabeza = nuevoNodo;               // El nuevo nodo se convierte en la cabeza
        }
        tamaño++;
    }

    // Método para obtener el primer elemento de la lista
    public E getFirst() {
        if (cabeza == null) {
            throw new IllegalStateException("La lista está vacía");
        }
        return cabeza.dato;
    }

    // Método para obtener el último elemento de la lista
    public E getLast() {
        if (cabeza == null) {
            throw new IllegalStateException("La lista está vacía");
        }
        return cabeza.anterior.dato; // La cola está almacenada en cabeza.anterior
    }

    public E get(int index) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }

        NodoDobleCircular<E> actual = cabeza;
        for (int i = 0; i < index; i++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }


    // Método para eliminar el primer elemento de la lista
    public E removeFirst() {
        if (cabeza == null) {
            throw new IllegalStateException("La lista está vacía");
        }
        E datoEliminado = cabeza.dato;
        if (cabeza.siguiente == cabeza) { // Si solo hay un elemento
            cabeza = null;
        } else {
            NodoDobleCircular<E> cola = cabeza.anterior;
            cabeza = cabeza.siguiente;         // La cabeza ahora apunta al segundo nodo
            cabeza.anterior = cola;            // La nueva cabeza apunta a la cola
            cola.siguiente = cabeza;           // La cola apunta de nuevo a la nueva cabeza
        }
        tamaño--;
        return datoEliminado;
    }

    // Método para eliminar el último elemento de la lista
    public E removeLast() {
        if (cabeza == null) {
            throw new IllegalStateException("La lista está vacía");
        }
        E datoEliminado = cabeza.anterior.dato;
        if (cabeza.siguiente == cabeza) { // Si solo hay un elemento
            cabeza = null;
        } else {
            NodoDobleCircular<E> cola = cabeza.anterior;
            cola.anterior.siguiente = cabeza;  // El penúltimo nodo apunta a la cabeza
            cabeza.anterior = cola.anterior;   // La cabeza apunta al penúltimo nodo (nueva cola)
        }
        tamaño--;
        return datoEliminado;
    }

    public E remove(int index) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }

        NodoDobleCircular<E> actual = cabeza;
        for (int i = 0; i < index; i++) {
            actual = actual.siguiente;
        }

        if (tamaño == 1) {
            cabeza = null;
        } else {
            actual.anterior.siguiente = actual.siguiente;
            actual.siguiente.anterior = actual.anterior;
            if (actual == cabeza) {
                cabeza = actual.siguiente;
            }
        }

        tamaño--;
        return actual.dato;
    }


    // Método para verificar si un elemento está en la lista
    public boolean contains(E dato) {
        if (cabeza == null) return false;

        NodoDobleCircular<E> actual = cabeza;
        do {
            if (actual.dato.equals(dato)) {
                return true; // Dato encontrado
            }
            actual = actual.siguiente;
        } while (actual != cabeza);
        return false; // Dato no encontrado
    }

    // Método para obtener el tamaño actual de la lista
    public int size() {
        return tamaño;
    }

    // Método para verificar si la lista está vacía
    public boolean isEmpty() {
        return tamaño == 0;
    }

    // Método para imprimir la lista completa desde la cabeza hasta la cola
    public void printList() {
        if (cabeza == null) {
            System.out.println("La lista está vacía");
            return;
        }
        NodoDobleCircular<E> actual = cabeza;
        do {
            System.out.print(actual.dato + " <-> ");
            actual = actual.siguiente;
        } while (actual != cabeza);
        System.out.println("(cabeza)"); // Indicar el final de la lista y la referencia a la cabeza
    }

    // Método para imprimir la lista completa desde la cola hasta la cabeza (recorrido inverso)
    public void printListReverse() {
        if (cabeza == null) {
            System.out.println("La lista está vacía");
            return;
        }
        NodoDobleCircular<E> actual = cabeza.anterior; // Comienza desde la cola
        do {
            System.out.print(actual.dato + " <-> ");
            actual = actual.anterior;
        } while (actual != cabeza.anterior);
        System.out.println("(cabeza en reversa)"); // Indicar el final de la lista y referencia inversa
    }

    @Override
    public Iterator<E> iterator() {
        return new CircularDoublyLinkedListIterator();
    }

    public class CircularDoublyLinkedListIterator implements Iterator<E> {
    private NodoDobleCircular<E> current = cabeza;
    private int count = 0;

    @Override
    public boolean hasNext() {
        return count < tamaño;  // size debe estar definido en la clase de la lista
    }

    @Override
    public E next() {
        if (!hasNext()) throw new IllegalStateException("No more elements");
        E data = current.dato;
        current = current.siguiente;
        count++;
        return data;
    }

    public E previous() {
        if (current == null || current.anterior == null) throw new IllegalStateException("No previous element");
        current = current.anterior;
        return current.dato;
    }

    public void reset() {
        current = cabeza;
        count = 0;
    }
}



}
