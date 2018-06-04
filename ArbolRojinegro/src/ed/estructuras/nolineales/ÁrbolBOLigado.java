package ed.estructuras.nolineales;

import ed.estructuras.ColeccionAbstracta;
import ed.estructuras.lineales.ListaDoblementeLigada;
import java.util.Stack;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.EmptyStackException;
import java.util.Iterator;

public class ÁrbolBOLigado<C extends Comparable <C>> extends ListaDoblementeLigada<C> implements ÁrbolBinarioOrdenado<C>{

	private NodoBOLigado<C> raiz;
	private int size;

	public ÁrbolBOLigado(){
		this(null,0);
	}

	private ÁrbolBOLigado(NodoBOLigado<C> raiz, int size){
		this.raiz = raiz;
		this.size = size;
	}

	public NodoBinario<C> getRaíz(){
		return raiz;
	}
	public Iterator<C> getIteradorPreorden(){
		return new IteradorPreorden();
	}
	public Iterator<C> getIteradorPostorden(){
		return new IteradorPostorden();
	}
	public Iterator<C> getIteradorInorden(){
		return new IteradorInorden();
	}
	
	/**
	 * Devuelve una lista con los datos en este árbol, según son visitados en un
	 * recorrido postorden.
	 * @return 
	 */
	public List<C> recorridoPostorden(){
		ListaDoblementeLigada<C> lista = new ListaDoblementeLigada<>();
		Iterator<C> iterador = getIteradorPostorden();
		while(iterador.hasNext()) {
			lista.add(iterador.next());
		}
		return lista;
	}
	
	/**
	 * Devuelve el altura del árbol.  Si el árbol es vacío devuelve -1.
	 * @return altura del árbol.
	 */
	public int getAltura() {
		return (raiz==null)? -1 : raiz.getAltura();
	}

	public boolean add(C c){
		return (addNode(c)!=null) ? true : false;
	}

	public Iterator<C> iterator(){
		return new IteradorInorden();
	}

	public boolean isEmpty(){
		return raiz==null;
	}

	public int size(){
		return size;
	}

	/**
	 * Indica si el objeto comparable <code>o</code> se encuentra en este árbol.
	 * La complejidad de este método es log(n) en promedio.
	 * @param o
	 * @return si el objeto se encuentra en el árbol.
	 * @throws NullPointerException si <code>o</code> es <code>null</code>.
	 */
	public boolean contains(C o) throws NullPointerException{
		if(o==null)
			throw new NullPointerException();
		NodoBOLigado<C> posicion = raiz;
		if(posicion==null) return false;
		while(!posicion.esHoja()) { 
			if(o.compareTo(posicion.getElemento())>0)
				posicion = (NodoBOLigado<C>) posicion.getHijoD();
			else if(o.compareTo(posicion.getElemento())<0)
				posicion = (NodoBOLigado<C>) posicion.getHijoI();
			else
				return true;
		}
		return o.compareTo(posicion.getElemento())==0;
	}

	/**
	 * Remueve el objeto comparable <code>o</code>.
	 * La complejidad de este método es log(n) en promedio.
	 * @param o el objeto a remover
	 * @return si el objeto estuvo presente y lo removió
	 * @throws NullPointerException si <code>o</code> es <code>null</code>.
	 */
	public boolean remove(C o) throws NullPointerException{
		return (removeNode(o)!=null) ? true : false;
	}

	protected NodoBOLigado<C> removeNode(C o) throws NullPointerException{
		if(o==null)
			throw new NullPointerException();
		if(this.isEmpty())
			return null;
		else {
			NodoBOLigado<C> temp = raiz.buscaNodo(o);
			if(temp==raiz && temp.esHoja()) {
				raiz = null;
				return temp;
			}
			else if(temp!=null) {
				temp = temp.remover();
				return temp;
			} else {
				return null;
			}
		}
	}

	public void setRaíz(NodoBOLigado<C> raiz) {
		this.raiz = raiz;
	}

	protected NodoBOLigado<C> addNode(C e){
		if(e==null)
			throw new NullPointerException();
		if(raiz==null) {
			raiz = creaNodo(e,null,null,null);
			return raiz;
		} else {
			NodoBOLigado<C> posicion = raiz;
			do {
				if(e.compareTo(posicion.getElemento())>=0) {
					if(posicion.getHijoD()==null) {
						posicion.setHijoD(creaNodo(e,posicion,null,null));
						size++;
						return posicion.getHijoD();
					}
					else
						posicion = posicion.getHijoD();
				} else {
					if(posicion.getHijoI()==null) {
						posicion.setHijoI(creaNodo(e,posicion,null,null));
						size++;
						return posicion.getHijoI();
					}
					else
						posicion = posicion.getHijoI();
				}
			} while(posicion!=null);
		}
		return null;
	}

	protected NodoBOLigado<C> creaNodo(C dato, NodoBOLigado<C> padre, NodoBOLigado<C> hijoI, NodoBOLigado<C> hijoD){
		return new NodoBOLigado(dato,padre,hijoI,hijoD);
	}

	private class IteradorPostorden implements Iterator<C>{
		private NodoBOLigado<C> posicion;
		private Stack<NodoBOLigado<C>> pila;

		public IteradorPostorden(){
			pila = new Stack<>();
			posicion = obtenerHojaIzqFinal(raiz);
		}

		public C next(){
			if(!hasNext())
				throw new NoSuchElementException();
			NodoBOLigado<C> temp = posicion;
			if(posicion==raiz)
				posicion = null;
			else if(!pila.isEmpty()) {
				if(posicion.getPadre().getHijoD()!=posicion)
					posicion = obtenerHojaIzqFinal((NodoBOLigado<C>) posicion.getPadre().getHijoD());
				else
					posicion = pila.pop();
			}
			return temp.getElemento();
		}

		public boolean hasNext(){
			return posicion!=null;
		}

		private NodoBOLigado<C> obtenerHojaIzqFinal(NodoBOLigado<C> temp){
		  while(!temp.esHoja()){
			pila.push(temp);
			if(temp.getHijoI()!=null)
				temp = (NodoBOLigado<C>) temp.getHijoI();
			else
				temp = (NodoBOLigado<C>) temp.getHijoD();
		  }
		  return temp;
		}
	}

	private class IteradorPreorden implements Iterator<C> {
		private NodoBOLigado<C> posicion;
		private Stack<NodoBOLigado<C>> pila;

		public IteradorPreorden(){
			posicion = raiz;
			pila = new Stack<>();
		}

		public C next(){
			if(!hasNext())
				throw new NoSuchElementException();
			NodoBOLigado<C> temp = posicion;
			if(!posicion.esHoja()) {
				pila.push(posicion);
				if(posicion.getHijoI()!=null)
					posicion = (NodoBOLigado<C>) posicion.getHijoI();
				else
					posicion = (NodoBOLigado<C>) posicion.getHijoD();
			} else {
				if(posicion.getPadre()==null)
					posicion = null;
				else if(posicion.getPadre().getHijoD()!=posicion)
					posicion = (NodoBOLigado<C>) posicion.getPadre().getHijoD();
				else {
					NodoBOLigado<C> nodoCompletado = pila.pop();
					if(!pila.isEmpty())
						posicion = (NodoBOLigado<C>) pila.pop().getHijoD();
					else
						posicion = null;
				}
			}
			
			return temp.getElemento();
		}

		public boolean hasNext(){
			return posicion!=null;
		}
	}

	private class IteradorInorden implements Iterator<C>{
		private NodoBOLigado<C> posicion;
		private Stack<NodoBOLigado<C>> pila;

		public IteradorInorden(){
			posicion = obtenerHojaIzqFinal(raiz);
			pila = new Stack<>();
			
		}

		public C next(){
			if(!hasNext())
				throw new NoSuchElementException();
			NodoBOLigado<C> temp = posicion;
			if(posicion.getPadre()==null)
				posicion = null;
			else if(posicion!=peekWrapper(pila))
				posicion = peekWrapper(pila);
			else {
				posicion = obtenerHojaIzqFinal((NodoBOLigado<C>) pila.pop().getHijoD());
			}
			return temp.getElemento();
		}

		public boolean hasNext(){
			return posicion != null;
		}

		private NodoBOLigado<C> obtenerHojaIzqFinal(NodoBOLigado<C> posicion){
			while(posicion.getHijoI()!=null) {
				pila.push(posicion);
				posicion = (NodoBOLigado<C>) posicion.getHijoI();
			}
			return posicion;
		}

		private NodoBOLigado<C> peekWrapper(Stack<NodoBOLigado<C>> pila) {
			try {
				return pila.peek();
			} catch(EmptyStackException ex) {
				return null;
			}
		}
	}
}