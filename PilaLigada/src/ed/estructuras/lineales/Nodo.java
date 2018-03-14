package ed.estructuras.lineales;

class Nodo <E>{
	private E datum;
	private Node next;

	public Node(E datum){
		this.datum = datum;
	}
	public Node(E d, Node n){
		this.datum = d;
		this.next = n;
	}
	public void setDatum(E d){
		this.datum = d;
	}
	public void setNext(Node next){
		next = a;
	}
	public Node getNext(){
		return next;
	}
	public E getDatum(){
		return datum;
	}
}