package ed.estructuras.lineales;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ColaArreglo<E> extends ColeccionAbstracta<E> implements ICola<E>{
    private E[] buffer;
    private int head; 
    private int tail; 
    private int size;

    public ColaArreglo(E[] a, int tamInicial){
        if(a.length!=0 || tamInicial<=0)
            throw new IllegalArgumentException("El tamaño del arreglo no puede ser diferente de cero y el tamaño inicial no puede ser cero");
        this.buffer = Arrays.copyOf(a,tamInicial);
        this.head = -1;
        this.tail = 0;
        this.size = 0;
    }

    public ColaArreglo(E[] a){
        this(a,10);
    }

    protected class Iterador implements Iterator<E>{
        int index;
        int i;

        public Iterador(){
            if(head==-1)
                index = 0;
            else
                index = head;
            i = 0;
        }

        public boolean hasNext(){
            return i!=size;
        }

        public E next(){
            if(!hasNext())
                throw new NoSuchElementException("La iteracion no tiene mas elementos");
            E tempE = buffer[index];
            index = (index+1) % buffer.length;
            i++;
            return tempE;
        }
    }

    /**
     * Muestra el elemento al inicio de la fila.
     * Devuelve <code>null</code> si está vacía.
     * @return Una referencia al elemento siguiente.
     */
    public E mira(){
        if(isEmpty())
            return null;
        return buffer[head];
    }
    
    /**
     * Devuelve el elemento al inicio de la fila y lo elimina.
     * Devuelve <code>null</code> si está vacía.
     * @return Una referencia al elemento siguiente.
     */
    public E atiende() {
        if(isEmpty())
            return null;

        E temp = buffer[head];
        buffer[head] = null;

        if(tail==head) {
            head=-1;
            tail=0;
        } else {
            head = (head+1) % buffer.length;
        }

        size--;

        return temp;
    }
    
    /**
     * Agrega un elemento al final de la fila.
     * @param e Referencia al elemento a agregar.
     * @throws IllegalArgumentException si se intenta agregar <code>null</code>.
     */
    public void forma(E e){
        if(e==null)
            throw new IllegalArgumentException("No se puede agregar null");

        if(size==buffer.length) {
            agranda();
        }

        if(isEmpty()) {
            buffer[0] = e;
            head = 0;
            tail = 0;
        }

        else{
            tail = (tail + 1) % buffer.length;
            buffer[tail] = e;
        }

        size++;
    }

    public boolean add(E e){
        forma(e);
        return true;
    }

    public boolean isEmpty(){
        return head == -1;
    }

    public Iterator<E> iterator(){
        return new Iterador();
    }

    public int size(){
        return size;
    }

    private void agranda() {
        int bufferLength = buffer.length;

        if(bufferLength<=0) {
          bufferLength = 1;  
        }

        E[] temp = Arrays.copyOf(buffer, 2*bufferLength);
        
        for(int i = head, j = 0; j<buffer.length; i = (i+1) % buffer.length, j++) {
            temp[j] = buffer[i];
        }
        
        for(int i = buffer.length; i<temp.length; i++) {
            temp[i] = null;
        }

        head = 0;
        tail = buffer.length -1;

        buffer = temp;
    }
}