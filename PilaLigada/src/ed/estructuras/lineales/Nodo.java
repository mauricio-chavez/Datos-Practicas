package ed.estructuras.lineales;

class Nodo <E>{
    private E datum;
    private Nodo next;

    public Nodo(E datum){
        this(datum,null);
    }
    public Nodo(E datum, Nodo next){
        this.datum = datum;
        this.next = next;
    }
    public void setDatum(E datum){
        this.datum = datum;
    }
    public void setNext(Nodo next){
        this.next = next;
    }
    public Nodo getNext(){
        return next;
    }
    public E getDatum(){
        return datum;
    }
}