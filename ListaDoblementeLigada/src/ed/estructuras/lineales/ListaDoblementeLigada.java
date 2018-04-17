package ed.estructuras.lineales;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ListaDoblementeLigada<E> extends ColeccionAbstracta<E> implements List<E>{
    private Nodo<E> cabeza;
    private Nodo<E> cola;
    private int tam;


    public ListaDoblementeLigada(){
        this(null,null,0);
    }
    
    public ListaDoblementeLigada(ListaDoblementeLigada<E> copy){
        this(copy.getCabeza(), copy.getCola(), copy.size());
    }

    private ListaDoblementeLigada(Nodo<E> cabeza, Nodo<E> cola, int tamanio){
        this.cabeza = cabeza;
        this.cola = cola;
        this.tam = tamanio;
    }

    public void add(int index, E elemento){
        if(elemento==null)
            throw new NullPointerException();

        if(index==tam) {
            if(tam==0)
                cabeza = cola = new Nodo<>(elemento);
            else {
                Nodo<E> nuevoElem = new Nodo<>(cola,elemento);
                cola.setNext(nuevoElem);
                cola = nuevoElem;    
            }

        } else {
            Nodo<E> nodoDesp = buscaNodo(index);
            Nodo<E> nuevoNodo = new Nodo(nodoDesp.getPrevious(),elemento,nodoDesp);
            nodoDesp.getPrevious().setNext(nuevoNodo);
            nodoDesp.setPrevious(nuevoNodo);
        }
        tam++;
    }

    /**
     * Concatena el elemento especificado al final de la lista
     * @param e Elemento que se concatenará al final de la lista
     * @throws NullPointerException si se intenta añadir <code>null</code>
     * @return <code>true</code> si se añadió el elemento
     */
    public boolean add(E e){
        if(e==null)
            throw new NullPointerException("No es posible concatenar un elemento null a la lista");
        if(tam==0)
            cabeza = cola = new Nodo<>(e);
        else {
            Nodo<E> temp = new Nodo<>(cola,e);
            cola.setNext(temp);
            cola = temp;
        }
        tam++;
        return true;
    }

    public boolean addAll(int index,Collection<? extends E> c){
        if(index==tam){
            Iterator<? extends E> it = c.iterator();
            E aux;
            
            while(it.hasNext()){
                aux = it.next();
                if(aux==null)
                    throw new NullPointerException("No es posible agregar elementos null");
                add(aux);
            }
        }

        else{
            Nodo<E> aux = buscaNodo(index);

        }
        return true;
    }

    public E get(int index){
        return buscaNodo(index).getDatum();
    }

    public Nodo<E> getCabeza(){
        return cabeza;
    }

    public Nodo<E> getCola(){
        return cola;
    }

    public int indexOf(Object o){
        int count = 0;
        for(E e:this){
            if(o.equals(e))
                return count;
            count++;
        }
        return -1;
    }

    public boolean isEmpty(){
        return tam == 0;
    }

    public Iterator<E> iterator(){
        return new Iterador();
    }

    public int lastIndexOf(Object o){
        int count = tam-1;
        IteradorLista it = listIterator(tam);
        while(it.hasPrevious()){
            if(o.equals(it.previous()))
                return count;
            count--;
        }
        return -1;
    }

    public IteradorLista listIterator(){
        return new IteradorLista();
    }

    public IteradorLista listIterator(int index){
        return new IteradorLista(index);
    }

    public E remove(int index){
        Nodo<E> aux = buscaNodo(index);
        aux.getPrevious().setNext(aux.getNext());
        aux.getNext().setPrevious(aux.getPrevious());
        tam--;
        return aux.getDatum();
    }

    public boolean remove(Object o){
        if(o==null)
            throw new NullPointerException("No es posible remover null");
        IteradorLista it = listIterator();
        while(it.hasNext()){
            if(o.equals(it.next())){
                it.remove();
                return true;
            }
        }
        return false;
    }

    public E set(int index, E elemento){
        if(elemento==null)
            throw new NullPointerException();
        E elem = buscaNodo(index).getDatum();
        buscaNodo(index).setDatum(elemento);
        return elem;
    }

    public int size(){
        return tam;
    }

    public ListaDoblementeLigada<E> subList(int fromIndex, int toIndex){
        if(fromIndex < 0 || toIndex > tam || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();

        ListaDoblementeLigada<E> nuevaLista = new ListaDoblementeLigada<>(buscaNodo(fromIndex),buscaNodo(toIndex),toIndex-fromIndex+1);
        nuevaLista.getCabeza().setPrevious(null);
        nuevaLista.getCola().setNext(null);
        return nuevaLista;
    }

    private class Iterador implements Iterator<E>{

        private Nodo<E> pos;
        private Nodo<E> cabezaColeccion;

        public Iterador(){
            this.pos = this.cabezaColeccion = cabeza;
        }

        public E next(){
            if(!hasNext())
                throw new NoSuchElementException("La iteracion no tiene mas elementos");
            if(pos==cabezaColeccion)
                pos = cabezaColeccion = cabeza;
            E temp = (E) pos.getDatum();
            pos = pos.getNext();
            return temp;
        }

        public boolean hasNext() {
            if(pos==cabezaColeccion)
                pos = cabezaColeccion = cabeza;
            if(pos==null)
                return false;
            return pos.getNext()!=null;
        }
    }

    private class IteradorLista implements ListIterator<E>{
        private int iAnterior;
        private int estado;
        private Nodo<E> anterior;
        private Nodo<E> siguiente;

        public IteradorLista(){
            iAnterior=-1;
            anterior = null;
            siguiente = cabeza;
            estado = -2;
        }

        public IteradorLista(int index){
            if(index==tam) {
                iAnterior = tam-1;
                anterior = cola;
                siguiente = null;
                estado = -2;
            } else {
                siguiente = buscaNodo(index);
                anterior = siguiente.getPrevious();
                iAnterior = index-1;
                estado = -2;
            }
        }

        public void add(E e){
            if(isEmpty()) {
                anterior = cabeza = cola = new Nodo<E>(e);
            } else {
                if(anterior==null) {
                    Nodo<E> temp = new Nodo<>(e,cabeza);
                    cabeza.setPrevious(temp);
                    cabeza = anterior = temp;
                } else if(siguiente==null) {
                    Nodo <E> temp = new Nodo<>(cola,e);
                    cola.setNext(temp);
                    cola = anterior = temp;
                } else {
                    Nodo<E> temp = new Nodo<>(anterior,e,siguiente);
                    anterior.setNext(temp);
                    siguiente.setPrevious(temp);
                    anterior = temp;
                }
            }
            tam++;
            iAnterior++;
            estado = 0;
        }
        
        public boolean hasNext(){
            return siguiente != null;
        }

        public int previousIndex(){
            return iAnterior;
        }

        public int nextIndex(){
            return iAnterior+1;
        }

        public E next(){
            if(!hasNext())
                throw new NoSuchElementException();
            anterior = siguiente;
            siguiente = siguiente.getNext();
            iAnterior++;
            estado = 1;
            return anterior.getDatum();
        }

        public E previous(){
            if(!hasPrevious())
                throw new NoSuchElementException();
            siguiente = anterior;
            anterior = anterior.getPrevious();
            iAnterior--;
            estado = -1;
            return siguiente.getDatum();
        }

        public boolean hasPrevious(){
            return anterior!=null && iAnterior >=0;
        }

        public void set(E e){
            switch(estado){
                case 0:
                throw new IllegalStateException("Neither next nor previous have been called, or remove or add have been called after the last call to next or previous");
                case 1:
                anterior.setDatum(e);
                break;
                case -1:
                siguiente.setDatum(e);
                break;
            }
        }

        public void remove(){
            switch(estado){
                case 0:
                throw new IllegalStateException("No se ha llamado a next() ni a previous(), o add() o remove() fueron llamados");
                case 1:
                iAnterior--;
                if(tam==1) {
                    cabeza = cola = null;
                } else if(cabeza==anterior) {
                    cabeza = siguiente;
                    cabeza.setPrevious(null);
                } else if(cola==anterior) {
                    cola = cola.getPrevious();
                    cola.setNext(null);
                } else {
                    anterior.getPrevious().setNext(siguiente);
                    siguiente.setPrevious(anterior.getPrevious());
                }
                anterior = anterior.getPrevious();
                break;
                case -1:
                if(tam==1) {
                    cabeza = cola = null;
                } else if(cabeza==siguiente) {
                    cabeza = siguiente.getNext();
                    cabeza.setPrevious(null);
                } else if(cola==siguiente){
                    cola = anterior;
                    cola.setNext(null);
                } else {
                    siguiente.getNext().setPrevious(anterior);
                    anterior.setNext(siguiente.getNext());
                }
                siguiente = siguiente.getNext();
                break;
            }
            estado = 0;
            tam--;
        }
    }

    private Nodo<E> buscaNodo(int index){
        if(index<0||index>=tam)
            throw new IndexOutOfBoundsException();

        if(index==0)
            return cabeza;

        else if(index==tam-1)
            return cola;

        Nodo<E> aux;
        
        if(index<=(tam-1)/2){
            aux = cabeza;

        for(int i=0;i<index;i++)
            aux = aux.getNext();
        }

        else{
            aux = cola;

            for(int i=tam-1;i>index;i--)
                aux = aux.getPrevious();
        }

        return aux;
    }
        
}
