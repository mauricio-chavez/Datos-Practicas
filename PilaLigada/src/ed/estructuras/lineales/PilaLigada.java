package ed.estructuras.lineales;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PilaLigada<E> extends ColeccionAbstracta<E> implements IPila<E>{
	private Nodo head;
	private int size;

	public PilaLigada(){

	}

	public PilaLigada(Nodo head,int size){
		this.head = head;
		this.size = size;
	}

	private class Iterador implements Iterator<E>{
		private Nodo pos;

		public Iterador(){
			this.pos = head;
		}

		public E next(){
            if(!hasNext())
            	throw new NoSuchElementException("La iteracion no tiene mas elementos");
            E temp = (E) pos.getDatum();
        	pos = pos.getNext();
            return temp;
        }

        public boolean hasNext() {
            return size;
        }

        public void remove(){
        	throw new UnsupportedOperationException();
        }
	}

	/**
     * Muestra el elemento al tope de la pila.
	 * Devuelve <code>null</code> si está vacía.
     * @return Una referencia al elemento siguiente.
     */
	public E mira(){
		return (size==0) ? null : (E) head.getDatum();
	}

	/**
     * Devuelve el elemento al tope de la pila y lo elimina.
	 * Devuelve <code>null</code> si está vacía.
     * @return Una referencia al elemento siguiente.
     */
	public E expulsa(){
		if(size==0)
			return null;
		E temp = (E) head.getDatum();
		head = head.getNext();
		size--;
		return temp;
	}

	/**
     * Agrega un elemento al tope de la pila.
     * @param e Referencia al elemento a agregar.
	 * @throws IllegalArgumentException si se intenta agregar <code>null</code>.
     */
	public void empuja(E a){
		if(a==null)
			throw new IllegalArgumentException("No es posible agregar null a la pila");

		if(head==null)
			head = new Nodo(a);
		else{
			Nodo temp = new Nodo(a, head);
			head = temp;
		}

		size++;
	}

	public Iterator<E> iterator(){
		return new Iterador();
	}

	public boolean isEmpty(){
		return size==0;
	}

	public int size(){
		return size;	
	}

	public boolean add(E e){
		this.empuja(e);
		return true;
	}
}