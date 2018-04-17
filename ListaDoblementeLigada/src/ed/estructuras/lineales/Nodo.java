package ed.estructuras.lineales;

class Nodo <E>{
    private Nodo<E> prev;
    private E datum;
    private Nodo<E> next;

    public Nodo(E datum){
        this(null,datum,null);
    }

    public Nodo(Nodo<E> prev,E datum){
        this(prev,datum,null);
    }

    public Nodo(E datum,Nodo<E> next){
        this(null,datum,next);
    }

    public Nodo(Nodo<E> prev,E datum,Nodo<E> next){
        this.prev = prev;
        this.datum = datum;
        this.next = next;
    }

    public void setPrevious(Nodo<E> prev){
        this.prev = prev;
    }

    public void setDatum(E datum){
        this.datum = datum;
    }

    public void setNext(Nodo<E> next){
        this.next = next;
    }

    public Nodo getPrevious(){
        return prev;
    }

    public E getDatum(){
        return datum;
    }

    public Nodo getNext(){
        return next;
    }
}