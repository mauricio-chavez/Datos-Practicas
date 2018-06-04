package ed.estructuras.nolineales;

import java.util.Iterator;
import java.util.NoSuchElementException;
import ed.estructuras.lineales.ListaDoblementeLigada;
import java.util.Stack;
import java.util.EmptyStackException;
import ed.estructuras.ColeccionAbstracta;

public class ÁrbolAVL <C extends Comparable<C>> extends ÁrbolBOLigado<C>{

	private NodoAVL<C> raiz;
	private int size;

	public ÁrbolAVL(){
		super();
	}

	public boolean add(C c){
		NodoAVL<C> temp = (NodoAVL<C>) addNode(c);
		while(temp!=null) {
			temp.actualizarAltura();
			int fb = temp.calcularFB();
			if(fb==2 || fb==-2){
				NodoAVL<C> raizLocal = temp.balancear();
				if(temp==raiz)
					raiz = temp = raizLocal;
			}
			temp = (NodoAVL<C>) temp.getPadre();
		}
		return true;
	}

	/*protected NodoAVL<C> addNode(C e){
		if(e==null)
			throw new NullPointerException();
		if(raiz==null) {
			raiz = creaNodo(e,null,null,null);
			return raiz;
		} else {
			NodoAVL<C> posicion = raiz;
			do {
				if(e.compareTo(posicion.getElemento())>=0) {
					if(posicion.getHijoD()==null) {
						posicion.setHijoD(creaNodo(e,posicion,null,null));
						NodoAVL<C> temp = posicion;
						while(temp!=null) {
							temp.actualizarAltura();
							int fb = temp.calcularFB();
							if(fb==2 || fb==-2){
								NodoAVL<C> raizLocal = temp.balancear();
								if(temp==raiz)
									raiz = temp = raizLocal;
							}
							temp = (NodoAVL<C>) temp.getPadre();
						}
						size++;
						return (NodoAVL<C>) posicion.getHijoD();
					}
					else
						posicion = (NodoAVL<C>) posicion.getHijoD();
				} else {
					if(posicion.getHijoI()==null) {
						posicion.setHijoI(creaNodo(e,posicion,null,null));
						NodoAVL<C> temp = posicion;
						while(temp!=null) {
							temp.actualizarAltura();
							int fb = temp.calcularFB();
							if(fb==2 || fb==-2){
								NodoAVL<C> raizLocal = temp.balancear();
								if(temp==raiz)
									raiz = temp = raizLocal;
							}
							temp = (NodoAVL<C>) temp.getPadre();
						}
						size++;
						return (NodoAVL<C>) posicion.getHijoI();
					}
					else
						posicion = (NodoAVL<C>) posicion.getHijoI();
				}
			} while(posicion!=null);
		}
		return null;
	}*/

	/**
	 * Remueve el objeto comparable <code>o</code>.
	 * La complejidad de este método es log(n) en promedio.
	 * @param o el objeto a remover
	 * @return si el objeto estuvo presente y lo removió
	 * @throws NullPointerException si <code>o</code> es <code>null</code>.
	 */
	public boolean remove(C o) throws NullPointerException{
		/*if(o==null)
			throw new NullPointerException();
		if(this.isEmpty())
			return false;
		else {
			NodoBOLigado<C> temp = raiz.buscaNodo(o);
			if(temp==raiz && temp.esHoja()) {
				raiz = null;
				return true;
			}
			else if(temp!=null) {
				temp = temp.remover();
				return true;
			} else {
				return false;
			}
		}*/
		return super.remove(o);
	}

	@Override
	protected NodoAVL<C> creaNodo(C dato, NodoBOLigado<C> padre, NodoBOLigado<C> hijoI, NodoBOLigado<C> hijoD){
		return new NodoAVL(dato,(NodoAVL<C>)padre,(NodoAVL<C>)hijoI,(NodoAVL<C>)hijoD);
	}
}