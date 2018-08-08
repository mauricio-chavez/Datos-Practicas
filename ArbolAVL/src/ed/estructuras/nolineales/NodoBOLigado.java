package ed.estructuras.nolineales;

import ed.estructuras.lineales.ListaDoblementeLigada;
import java.util.List;
import java.util.Iterator;

public class NodoBOLigado<C extends Comparable<C>> implements NodoBinario<C>{

	private C dato;
	private NodoBOLigado<C> padre;
    private NodoBOLigado<C> hijoI;
    private NodoBOLigado<C> hijoD;
    private int altura;

    public NodoBOLigado(C dato){
    	this(dato,null,null,null);
    }

    public NodoBOLigado(C dato, NodoBOLigado<C> padre){
        this(dato,padre,null,null);
    }

    public NodoBOLigado(C dato, NodoBOLigado<C> padre, NodoBOLigado<C> hijoI, NodoBOLigado<C> hijoD){
    	this.dato = dato;
        this.padre = padre;
    	this.hijoI = hijoI;
    	this.hijoD = hijoD;
    	this.altura = getAltura();
    }

	public C getElemento(){
		return dato;
	}

    public void setElemento(C dato){
    	this.dato = dato;
    }

    public NodoBOLigado<C> getPadre(){
        return padre;
    }

    public void setPadre(Nodo<C> padre){
    	this.padre = (NodoBOLigado<C>) padre;
    }
	
	/**
	 * Indica si este nodo no tiene hijos o
	 * todos sus hijos son árboles vacíos.
	 */
    public boolean esHoja(){
        return hijoI==null && hijoD==null;
    }
	
    /** Devuelve el altura del subárbol que tiene este nodo por raíz. */
    public int getAltura(){
    	if(esHoja()) 
    		return 0;
        else if(hijoI != null && hijoD!=null){
        	return (hijoI.getAltura()>=hijoD.getAltura())? hijoI.getAltura() + 1 : hijoD.getAltura() + 1;
        }else if(hijoI == null){
            return hijoD.getAltura() + 1;
        }else{
            return hijoI.getAltura() + 1;
        }
    }
    
    /**
     * Devuelve al hijo en la posición <code>índice</code>.
     * @param índice
     * @return 
     */
    public NodoBinario<C> getHijo(int indice) throws IndexOutOfBoundsException{
    	if(indice!=0 && indice!=1){
            throw new IndexOutOfBoundsException();
        } else {
            if(indice==0)
                return hijoI;
            else
                return hijoD;
        }
    }
    
    /**
     * Devuelve al hijo que va después de <code>hijo</code>.
     * @param hijo
     * @throws IllegalArgumentException si <code>hijo</code> no es hijo de este
     * nodo.
     */
    public Nodo<C> getHermanoSiguiente(Nodo<C> hijo) throws IllegalArgumentException{
    	if(hijo==null)
            throw new IllegalArgumentException("El parametro no puede ser null");
        else if(hijo!=hijoI && hijo!=hijoD)
            throw new IllegalArgumentException("El parametro no es hijo de este nodo");
        else if(hijo==hijoI)
            return hijoI;
        else
            return hijoD;
    }
    
	/**
	 * Devuelve el número de hijos que tiene este nodo.
	 * @return 
	 */
    public int getGrado(){
    	if(hijoI==null && hijoD==null)
            return 0;
        else {
            if(hijoI==null || hijoD==null)
                return 1;
            else
                return 2;
        }
    }
    
    /**
     * Devuelve una lista con todos los hijos de este nodo.
     * Cualquier alteración a la lista devuelta no debe reflejar una alteración
     * a los hijos del nodo.
     * @return hijos de este nodo.
     */
    public List<Nodo<C>> getListaHijos(){
    	ListaDoblementeLigada<Nodo<C>> listaHijos = new ListaDoblementeLigada<>();
        boolean temp;
        if(hijoI!=null)
            temp = listaHijos.add(hijoI);
        if(hijoD!=null)
            temp = listaHijos.add(hijoD);
        return listaHijos;
    }
    
    /**
     * Remueve al hijo indicado.
     * @param hijo
     * @return Si el hijo estuvo presente y fue removido.
     */
    public boolean remueveHijo(Nodo<C> hijo){
        if(hijo==hijoD) {
            hijoD.remover();
            return true;
        } else if(hijo==hijoI) {
            hijoI.remover();
            return true;
        } else {
            return false;
        }
	}

	public NodoBOLigado<C> remover(){
		NodoBOLigado<C> temp = this;
        if(temp.esHoja()){
        	if(temp==temp.getPadre().getHijoD())
                temp.getPadre().setHijoD(null);
            else
                temp.getPadre().setHijoI(null);
            return temp;
        } else {
            temp = cambiarParaEliminar();
			return temp.remover();
        }
    }

    private NodoBOLigado<C> cambiarParaEliminar(){
    	NodoBOLigado<C> temp = this;
    	while(!temp.esHoja()) {
	    	if(hijoI!=null) {
	    		temp = hijoI;
	    		while(temp.getHijoD()!=null) {
	    			temp = temp.getHijoD();
	    		}
	    	} else if(hijoD!=null) {
	    		temp = hijoD;
	    		while(temp.getHijoI()!=null) {
	    			temp = temp.getHijoI();
	    		}
	    	}
	    }
	    intercambiarDatos(temp);
	    return temp;
    }

    public NodoBOLigado<C> buscaNodo(C o){
    	if(o==null)
    		throw new NullPointerException();
        if(o.compareTo(dato) == 0) 
        	return this;
        else if(o.compareTo(dato) > 0)
        	return (hijoD!=null)? hijoD.buscaNodo(o) : null;
        else 
            return (hijoI!=null)? hijoI.buscaNodo(o) : null;
    }

    public NodoBOLigado<C> getHijoI(){
    	return hijoI;
    }

    public void setHijoI(NodoBOLigado<C> hijoI){
    	this.hijoI = hijoI;
    }

    public NodoBOLigado<C> getHijoD(){
    	return hijoD;
    }

    public void setHijoD(NodoBOLigado<C> hijoD){
    	this.hijoD = hijoD;
    }

    public void intercambiarDatos(NodoBOLigado<C> nodo){
    	C temp = this.dato;
    	this.dato = nodo.getElemento();
    	nodo.setElemento(temp); 
    }

    public boolean esHijoI() {
    	return (this.getPadre().getHijoI() == this) ? true : false;
    }

    public boolean esHijoD() {
    	return (this.getPadre().getHijoD() == this) ? true : false;
    }

    public void actualizarAltura(){
        this.altura = getAltura();
    }
}