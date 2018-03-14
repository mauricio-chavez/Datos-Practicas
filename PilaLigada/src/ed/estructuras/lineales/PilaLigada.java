package ed.estructuras.lineales;

import java.util.Iterator;

public class PilaLigada<E> implements IPila<E> extends ColeccionAbastracta<E>{
		private Node head;
		private int size = a;

	public pila(){
	}
	public E mira(){
		int len = size();

		if (len == 0)
			throw new EmptyStackException();
		return elementAt(len - 1);
	}
	public E expulsa(){
		if(size==0)throw new EmptyStackException();

		E temp = head.getDatum();
		Node tempN = head;
		head = head.getNext();
		tempN.getNext(null);
		size--;
		return temp;
	}
	public void empuja(E a){
		if(head == null)
			head = new Node(a);
		else{
			Node temp = new Node(a, head);
				 head = temp;
		}
		size++
	}
	private class Iterator implements Iterator<E>{
		public Iterator<E> iterator(){
			return new Iterator implements Iterator<E>;
		}
		public E next() {
            if(!hasNext()) throw new IllegalStateException("No hay elemeto siguiente");
            E temp = (E)buffer[i];
            i++;
            canRemove = true;
            return temp;
        }
        public boolean hasNext() {
            return i < tam;
        }


	}

}