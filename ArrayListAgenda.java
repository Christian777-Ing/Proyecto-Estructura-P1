import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class ArrayListAgenda<E> implements ListAgenda<E> {
    
    private E[] array;        // Array para almacenar elementos
    private int tamaño = 0;   // Número actual de elementos en el ArrayList
    private int capacidad;    // Capacidad del ArrayList, inicializable por el usuario
    private Class<E> tipo;    // Clase de tipo genérico para crear arrays del tipo E

    // Constructor con capacidad inicial predeterminada (10)
    public ArrayListAgenda(Class<E> tipo) {
        this(tipo, 10); // Llama al constructor con capacidad personalizada
    }

    // Constructor que permite especificar una capacidad inicial personalizada
    @SuppressWarnings("unchecked")
    public ArrayListAgenda(Class<E> tipo, int capacidadInicial) {
        this.tipo = tipo;
        this.capacidad = capacidadInicial;
        this.array = (E[]) Array.newInstance(tipo, capacidad); // Crear un array de tipo E con la capacidad inicial
    }

    // Método para añadir un elemento al final de la lista
    public void add(E elemento) {
        if (tamaño == capacidad) {
            expandirCapacidad(); // Expandir si se alcanza la capacidad
        }
        array[tamaño] = elemento; // Añadir el elemento al final
        tamaño++; // Incrementar el tamaño
    }

    // Método para añadir un elemento en una posición específica
    public void add(int index, E elemento) {
        if (index < 0 || index > tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        if (tamaño == capacidad) {
            expandirCapacidad(); // Expandir si se alcanza la capacidad
        }
        // Desplazar elementos a la derecha para hacer espacio
        for (int i = tamaño; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = elemento; // Insertar el nuevo elemento
        tamaño++;
    }

    @Override
    public void addFirst(E element) {
        if(tamaño== capacidad){
            expandirCapacidad();
        }else{
            for (int i=tamaño;i>0;i-- ){
                array[i]= array[i-1];
            }
            array[0]= element;
            tamaño++;
        }
    }

    // Método para obtener el elemento en una posición específica
    public E get(int index) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        return array[index];
    }

    // Método para establecer un nuevo valor en una posición específica
    public void set(int index, E elemento) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        array[index] = elemento;
    }

    // Método para eliminar un elemento en una posición específica
    public E remove(int index) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        E elementoEliminado = array[index];
        // Desplazar elementos a la izquierda para llenar el espacio vacío
        for (int i = index; i < tamaño - 1; i++) {
            array[i] = array[i + 1];
        }
        array[tamaño - 1] = null; // Eliminar la referencia del último elemento
        tamaño--;
        return elementoEliminado; // Devolver el elemento eliminado
    }

    @Override
    public E removeFirst() {
        if (isEmpty()){
            throw new NoSuchElementException("Lista Vacía");
        }else{

            E elementoEliminado= array[0];
            for (int i=0; i<tamaño-1; i++){
                array[i]= array[i+1];
            }
        array[tamaño - 1] = null;
        tamaño --;
        return elementoEliminado;
        }
    }

    @Override
    public E removeLast() {
        if (isEmpty()){
            throw new NoSuchElementException("Lista Vacía");
        }else{
            E elementoEliminado= array[size()-1];
            array[tamaño - 1] = null;
            tamaño --;
            return elementoEliminado;
        }
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

    // Método para verificar si la lista está vacía
    public boolean isEmpty() {
        return tamaño == 0;
    }

    // Método para expandir la capacidad del ArrayList cuando se llena
    @SuppressWarnings("unchecked")
    private void expandirCapacidad() {
        capacidad = capacidad + (capacidad / 2); // Incremento del 50%
        E[] nuevoArray = (E[]) Array.newInstance(tipo, capacidad); // Crear un nuevo array de tipo E con capacidad expandida
        // Copiar elementos del array antiguo al nuevo
        for (int i = 0; i < tamaño; i++) {
            nuevoArray[i] = array[i];
        }
        array = nuevoArray; // Reemplazar el array antiguo por el nuevo
    }

    // Método para imprimir la lista completa
    public void printList() {
        for (int i = 0; i < tamaño; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    @Override
    public E getFirst() {
        if(isEmpty()){
            throw new NoSuchElementException("Lista Vacía");
        }
        return array[0];
    }

    @Override
    public E getLast() {
        if(isEmpty()){
            throw new NoSuchElementException("Lista Vacía");
        }
        return array[size()-1];
    }

    
}
