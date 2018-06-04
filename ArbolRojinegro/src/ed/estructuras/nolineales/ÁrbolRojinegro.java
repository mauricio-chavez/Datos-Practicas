package ed.estructuras.nolineales;

public class ÁrbolRojinegro<C extends Comparable<C>> extends ÁrbolBOLigado<C> {

	private NodoRojinegro<C> z;
	/**
	 *Constructor de ÁrbolRojiNegro
	 */
	public ÁrbolRojinegro() {
		this.z = null;
	}
	/**
	 *Método booleano que verifica que un elemento fue agregado
	 *@param c - Elemento a ser agregado
	 */
	public boolean add(C c) {
		z = (NodoRojinegro<C>) addNode(c);
		if(z!=this.getRaíz()) {
			NodoRojinegro<C> y;
			while(z.getPadre().getColor()==NodoRojinegro.Color.ROJO) {
				if(z.getPadre().esHijoI()) {
					y = z.getPadre().getPadre().getHijoD(); //ES EL TIO
					if(tieneTioRojo(z)) {
						z.getPadre().setColor(NodoRojinegro.Color.NEGRO);
						y.setColor(NodoRojinegro.Color.NEGRO);
						z.getPadre().getPadre().setColor(NodoRojinegro.Color.ROJO);
						z = z.getPadre().getPadre();
						if(z==this.getRaíz())
							break;
					} else {
						if(z.esHijoD()) {
							z = z.getPadre();
						z.rotacionIzquierda();
						}
						z.getPadre().setColor(NodoRojinegro.Color.NEGRO);
						z.getPadre().getPadre().setColor(NodoRojinegro.Color.ROJO);
						z.getPadre().getPadre().rotacionDerecha();
					}
				} else {
					y = z.getPadre().getPadre().getHijoI();
					if(tieneTioRojo(z)) {
						z.getPadre().setColor(NodoRojinegro.Color.NEGRO);
						y.setColor(NodoRojinegro.Color.NEGRO);
						z.getPadre().getPadre().setColor(NodoRojinegro.Color.ROJO);
						z = z.getPadre().getPadre();
						if(z==this.getRaíz())
							break;
					} else {
						if(z.esHijoI()) {
							z = z.getPadre();
							z.rotacionDerecha();
						}
						z.getPadre().setColor(NodoRojinegro.Color.NEGRO);
						z.getPadre().getPadre().setColor(NodoRojinegro.Color.ROJO);
						z.getPadre().getPadre().rotacionIzquierda();
					}
				}
			}	
		}
		if(z!=null) {
			while (z.getPadre()!=null) {
				z = z.getPadre(); 
			}
			setRaíz(z);
		} 
		getRaíz().setColor(NodoRojinegro.Color.NEGRO);
		return true;
	}
	/**
	 *Método booleano que verifica que un elemento fue removido
	 *@param c - elemento que fue removido
	 */
	public boolean remove(C c) {
		if(c==null) 
			throw new NullPointerException();
		if(!this.contains(c))
			return false;
		else {
			NodoRojinegro<C> removido;
			NodoRojinegro<C> x = removido = (NodoRojinegro<C>) this.getRaíz().buscaNodo(c);
			/*if(temp==this.getRaíz()){
				raiz = null;
				return true;
			}*/
			//NodoRojinegro<C> x = (NodoRojinegro<C>) removeNode(c);
			/*if(x==null)
				return false;*/
			NodoRojinegro<C> p = (NodoRojinegro<C>) x.getPadre();
			NodoRojinegro<C> w;

			while(x!=getRaíz() && esNodoNegro(x)) {
				if(x==p.getHijoI()) {
					w = p.getHijoD();
					if(w.getColor()==NodoRojinegro.Color.ROJO) {
						w.setColor(NodoRojinegro.Color.NEGRO);
						p.setColor(NodoRojinegro.Color.ROJO);
						p.rotacionIzquierda();
						if(p==this.getRaíz())
							setRaíz(p.getPadre());
						w = p.getHijoD();
					}
					if(esNodoNegro(w.getHijoI()) && esNodoNegro(w.getHijoD())) {
						w.setColor(NodoRojinegro.Color.ROJO);
						x = p;
						p = x.getPadre();
					} else {
						if(esNodoNegro(w.getHijoD())) {
							w.getHijoI().setColor(NodoRojinegro.Color.NEGRO);
							w.setColor(NodoRojinegro.Color.ROJO);
							w.rotacionDerecha();
							w = p.getHijoD();
						}
						w.setColor(p.getColor());
						p.setColor(NodoRojinegro.Color.NEGRO);
						w.getHijoD().setColor(NodoRojinegro.Color.NEGRO);
						p.rotacionIzquierda();
						if(p==this.getRaíz())
							setRaíz(p.getPadre());
						break;
					}
				} else {
					//SIMETRICO
					w = p.getHijoI();
					if(w.getColor()==NodoRojinegro.Color.ROJO) {
						w.setColor(NodoRojinegro.Color.NEGRO);
						p.setColor(NodoRojinegro.Color.ROJO);
						p.rotacionDerecha();
						if(p==this.getRaíz())
							setRaíz(p.getPadre());
						w = p.getHijoI();
					}
					if(esNodoNegro(w.getHijoI()) && esNodoNegro(w.getHijoD())) {
						w.setColor(NodoRojinegro.Color.ROJO);
						x = p;
						p = x.getPadre();
					} else {
						if(esNodoNegro(w.getHijoI())) {
							w.getHijoD().setColor(NodoRojinegro.Color.NEGRO);
							w.setColor(NodoRojinegro.Color.ROJO);
							w.rotacionIzquierda();
							w = p.getHijoI();
						}
						w.setColor(p.getColor());
						p.setColor(NodoRojinegro.Color.NEGRO);
						w.getHijoI().setColor(NodoRojinegro.Color.NEGRO);
						p.rotacionDerecha();
						if(p==this.getRaíz())
							setRaíz(p.getPadre());
						break;
					}
				}
			}

			if(x!=null)
				x.setColor(NodoRojinegro.Color.NEGRO);
			removido = (NodoRojinegro<C>) removeNode(removido.getElemento());
			return true;
		}
	}
	/**
	 *Método para obtener la raíz
	 *@return metodo para obtener la raíz obtiendo la raíz del árbol
	 */
	public NodoRojinegro<C> getRaíz() {
		return (NodoRojinegro<C>) super.getRaíz();
	}
	/**
	 *Método que crea un nodo
	 *@return un nuevo nodo
	 */
	@Override
	protected NodoRojinegro<C> creaNodo(C dato, NodoBOLigado<C> padre, NodoBOLigado<C> hijoI, NodoBOLigado<C> hijoD){
		return new NodoRojinegro(dato,(NodoRojinegro<C>)padre,(NodoRojinegro<C>)hijoI,(NodoRojinegro<C>)hijoD);
	}
	/*
	 *Método privado para verificar que tiene un tioRojo
	 *@return true si tiene tio rojo, false en otro caso
	 */
	private boolean tieneTioRojo(NodoRojinegro<C> nodo) {
		if(nodo.getTio()==null)
			return false;
		else 
			if(nodo.getTio().getColor()==NodoRojinegro.Color.ROJO)
				return true;
		return false;
	}
	/*
	 *Método privado booleano para verififcar que sea nodo negro
	 *@return true si lo tiene, false en otro caso
	 */
	private boolean esNodoNegro(NodoRojinegro<C> nodo) {
		if(nodo==null)
			return true;
		else if(nodo.getColor()==NodoRojinegro.Color.NEGRO)
			return true;
		return false;
	}
}
