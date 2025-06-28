package Modelo.Listas;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayListAgenda<E> implements ListAgenda<E> {
    
    private E[] array;
    private int tamaño;
    private int capacidad;

    @SuppressWarnings("unchecked")
    public ArrayListAgenda() { //Inicializa con un array de capacidad 10
        array = (E[]) new Object[10];
        capacidad = 10;
        tamaño = 0;
    }

    public void add(E elemento) { //Añade un elemento a lo último del array
        if (tamaño == capacidad) {
            expandirCapacidad();
        }
        array[tamaño] = elemento;
        tamaño++;
    }

    public void add(int index, E elemento) {
        if (index < 0 || index > tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        if (tamaño == capacidad) {
            expandirCapacidad();
        }
        for (int i = tamaño; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = elemento;
        tamaño++;
    }

    @Override
    public void addFirst(E element) {
        if (tamaño == capacidad) {
            expandirCapacidad();
        }
        for (int i = tamaño; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = element;
        tamaño++;
    }

    public E get(int index) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        return array[index];
    }

    public void set(int index, E elemento) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        array[index] = elemento;
    }

    public E remove(int index) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        E elementoEliminado = array[index];
        for (int i = index; i < tamaño - 1; i++) {
            array[i] = array[i + 1];
        }
        array[tamaño - 1] = null;
        tamaño--;
        return elementoEliminado;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Lista Vacía");
        }
        E elementoEliminado = array[0];
        for (int i = 0; i < tamaño - 1; i++) {
            array[i] = array[i + 1];
        }
        array[tamaño - 1] = null;
        tamaño--;
        return elementoEliminado;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Lista Vacía");
        }
        E elementoEliminado = array[tamaño - 1];
        array[tamaño - 1] = null;
        tamaño--;
        return elementoEliminado;
    }


    // Método para verificar si un elemento está en la lista
    public boolean contains(E elemento) {
        for (int i = 0; i < tamaño; i++) {
            if (array[i].equals(elemento)) {
                return true; // Elemento encontrado
            }
        }
        return false; // Elemento no encontrado
    }

    // Método para obtener el tamaño actual de la lista
    public int size() {
        return tamaño;
    }

    public boolean isEmpty() {
        return tamaño == 0;
    }

    @SuppressWarnings("unchecked")
    private void expandirCapacidad() {
        capacidad = capacidad + (capacidad / 2);
        E[] nuevoArray = (E[]) new Object[capacidad];
        for (int i = 0; i < tamaño; i++) {
            nuevoArray[i] = array[i];
        }
        array = nuevoArray;
    }

    public void printList() {
        for (int i = 0; i < tamaño; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    @Override
    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Lista Vacía");
        }
        return array[0];
    }

    @Override
    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Lista Vacía");
        }
        return array[tamaño - 1];
    
    
    }

    @Override
    public Iterator<E> iterator() {
         return new CustomArrayListIterator();
    }

        public class CustomArrayListIterator implements Iterator<E> {
        private int currentIndex = 0;

        // Devuelve true si hay más elementos por iterar
        @Override
        public boolean hasNext() {
            return currentIndex < tamaño;
        }

        // Devuelve el siguiente elemento
        @Override
        public E next() {
            if (!hasNext()) throw new IllegalStateException("No hay mas contactos");
            return array[currentIndex++];
        }

        // Elimina el elemento actual
        @Override
        public void remove() {
            if (currentIndex <= 0) throw new IllegalStateException("No hay contactos para remover");
            for (int i = currentIndex - 1; i < tamaño - 1; i++) {
                array[i] = array[i + 1];
            }
            array[--tamaño] = null;
            currentIndex--;        
        }
    }
}