package ed.estructuras.nolineales;

public class NodoRojinegro<C extends Comparable<C>> extends NodoBOLigado<C>{

	private Color color;
	/**
	 *Constructor de un nodoRojiNegro
	 *@param dato - nodo del árbol RojiNegro
	 */
	public NodoRojinegro(C dato){
		super(dato,null,null,null);
		this.color = Color.ROJO;
	}
	/**
	 *Constructor de un nodo padre de un árbol RojiNegro
	 *@param padre - nodo padre del árbol RojiNegro
	 *@param dato - nodo
	 */
	public NodoRojinegro(C dato, NodoRojinegro<C> padre){
		super(dato,padre,null,null);
		this.color = Color.ROJO;
	}
	/**
	 *Constructor de un nodo padre, hijo izquierdo e hijo derecho de un árbol RojiNegro
	 *@param padre - nodo padre del árbol Rojinegro
	 *@param dato - nodo
	 *@param hijoI - hijoIzquierdo
	 *@param hijoD - hijoDerecho
	 */
	public NodoRojinegro(C dato, NodoRojinegro<C> padre, NodoRojinegro<C> hijoI, NodoRojinegro<C> hijoD){
		super(dato,padre,hijoI,hijoD);	
		this.color = Color.ROJO;
	}
	 /**
	 *Constructor de un nodo padre, hijo izquierdo,hijo derecho y un color  de un árbol RojiNegro
	 *@param padre - nodo padre del árbol Rojinegro
	 *@param dato - nodo
	 *@param hijoI - hijoIzquierdo
	 *@param hijoD - hijoDerecho
	 *@param color - color
	 */
	public NodoRojinegro(C dato, NodoRojinegro<C> padre, NodoRojinegro<C> hijoI, NodoRojinegro<C> hijoD, Color color){
		super(dato,padre,hijoI,hijoD);
		this.color = color;
	}
	/**
	 *Método para obtener el color de un nodo
	 *@return color - color del nodo
	 */
	public Color getColor() {
		return this.color;
	}
	/**
	 *Método para asignar el color de un nodo
	 *@param c - color del nodo
	 */
	public void setColor(Color c) {
		this.color = c;
	}
	/**
	 *Método para realizar una rotación a la izquierda en un árbol RojiNegro
	 */
	public void rotacionIzquierda() {		
		if(getPadre()!=null){
			if(esHijoI()){
				getPadre().setHijoI(getHijoD());
			} else {
				getPadre().setHijoD(getHijoD());
			}
		}
		this.getHijoD().setPadre(this.getPadre());
		NodoRojinegro<C> nuevoPadre = this.getHijoD();
		setHijoD(nuevoPadre.getHijoI());
		if(nuevoPadre.getHijoI() != null)
			nuevoPadre.getHijoI().setPadre(this);
		nuevoPadre.setHijoI(this);
		setPadre(nuevoPadre);
	}
	/**
	 *Método para realizar una rotación a la derecha en un árbol RojiNegro
	 */
	public void rotacionDerecha() {
		if(getPadre()!=null){
			if(esHijoI()){
				getPadre().setHijoI(getHijoI());
			} else {
				getPadre().setHijoD(getHijoI());
			}
		}
		this.getHijoI().setPadre(this.getPadre());
		NodoRojinegro<C> nuevoPadre = this.getHijoI();
		setHijoI(nuevoPadre.getHijoD());
		if(nuevoPadre.getHijoD() != null)
			nuevoPadre.getHijoD().setPadre(this);
		nuevoPadre.setHijoD(this);
		setPadre(nuevoPadre);
	}
	/**
	 *Método para obtener el nodo tio
	 *@return el nodo tio
	 */
	public NodoRojinegro<C> getTio() {
		if(this.getPadre().getPadre().getHijoI()==this.getPadre())
			return (NodoRojinegro<C>) this.getPadre().getPadre().getHijoD();
		else
			return (NodoRojinegro<C>) this.getPadre().getPadre().getHijoI();
	}
	/**
	 *Método para obtener el nodo padre
	 *@return el nodo padre
	 */
	public NodoRojinegro<C> getPadre() {
		return (NodoRojinegro<C>) super.getPadre();
	}
	/**
	 *Método para obtener el hijo Izquierdo
	 *@return nodo Hijo Izquierdo 
	 */
	public NodoRojinegro<C> getHijoI() {
		return (NodoRojinegro<C>) super.getHijoI();
	}
	/**
	 *Método para obtener el nodo hijo Derecho del árbol RojiNegro
	 */
	public NodoRojinegro<C> getHijoD() {
		return (NodoRojinegro<C>) super.getHijoD();
	}
	/**
	 *Método para crear objetos constante que representan el color rojo  y negro.
	 */
	public enum Color {
		ROJO,NEGRO
	}
}
