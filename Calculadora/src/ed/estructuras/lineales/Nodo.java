package ed.estructuras.lineales;

class Nodo <E>{
    private Nodo<E> prev;
    private E datum;
    private Nodo<E> next;

    public Nodo(E datum){
        this(null,datum,null);
    }

    public Nodo(Nodo prev,E datum){
        this(prev,datum,null);
    }

    public Nodo(E datum,Nodo next){
        this(null,datum,next);
    }

    public Nodo(Nodo prev,E datum,Nodo next){
        this.prev = prev;
        this.datum = datum;
        this.next = next;
    }

    public void setPrev(Nodo prev){
        this.prev = prev;
    }

    public void setDatum(E datum){
        this.datum = datum;
    }

    public void setNext(Nodo next){
        this.next = next;
    }

    public Nodo getPrev(){
        return prev;
    }

    public E getDatum(){
        return datum;
    }

    public Nodo getNext(){
        return next;
    }
}