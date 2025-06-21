package Listas;
public interface ListAgenda<E> {

    public void add(E element);

    public void addFirst(E element);

    public E removeLast();

    public E removeFirst();

    public boolean isEmpty();

    public int size();

    public boolean contains(E element);

    public E getFirst();

    public E getLast();
    
} 