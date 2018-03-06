package ed.estructuras;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Collection;

public abstract class ColeccionAbstracta<E> implements Collection<E>{
   
   private Object[] buffer;
   private int size;

	/**
	*Método que verifica si un elemento especifico de la coleccion se encuentra
	*@param o - Elemento que se verificara que se encuentra en la coleccion
	*@throws ClassCastException - Si el tipo del elemento especifico es imcompatible con la coleccion
	*@throws NullPointerException - Si el elemento espeficado es null y la coleccion no permite elementos null.
	*@return boolean- True si el elemento se encunetra en la coleccion, false en otro caso.
	*/
	public boolean contains(Object o){
		private Iterator<E> e = iterator();
		if (o==null) {
		while (e.hasNext())
		if (e.next()==null)
		return true;
		} else {
		while (e.hasNext())
		if (o.equals(e.next()))
		return true;
		}
		return false;
		

	}
	/**
	*Método que regresa un arreglo conteniendo todos los elementos de la coleccion. Si la coleccion el orden de los elementos que se estan
	*regresando por su iterador, este metodo debe devolver los elementos en el mismo orden.
	*@return Array- Un arreglo que contiene todos los elementos.
	*/
	public Object[] toArray(){
		Object[] array = new Object[10];

			return array;
		
	}/**
	*Método que regresa un arreglo conteniendo todos los elementos en la coleccion, el tipo tipo de tiempo de ejecución del arreglo regresedo
	* es de ese arreglo en específico. Si la colección cabe en este arreglo específico, se va regresar este. En otro caso un nuevo arreglo
	* se va  a colocar con el tipo de tiempo de ejecución con el arreglo específico y el tamaño de su colección.
	*@param a - El arreglo del cual los elementos de esta coleccion seran guardados, si es lo suficientemente grande, en otro caso un nuevo 
	*arreglo del mismo tiempo de ejecución sera colocado para su mismo proposito.
	*@throws ArrayStoreException - Si el tiempo de ejecución del arreglo especificado no es un supertipo del tiempo de ejecución de cada 
	*elemento de esta coleccion.
	*@throws NullPointerException - Si el arreglo espeficado es null.
	*@return r - Arreglo que contiene todos los elementos de esta coleccion
	*/
	public <T> T[] toArray(T[] a){
		int size = size();
		    T[] r = a.length >= size 
		        ? a 
		        : (T[]) Arrays.newInstance(a.getClass().getComponentType(), size);
		    Iterator<E> it = iterator();

		    for (int i = 0; i < r.length; i++) {
		        if (!it.hasNext()) {
		            if (a != r)
		                return Arrays.copyOf(r, i);
		            r[i] = null;
		            return r;
		        }
		        r[i] = (T) it.next();
		    }
		    return it.hasNext() ? finishToArray(r, it) : r;
		}
		/**
		*Método que verifica  si la coleccion contiene todos los elementos en la coleccion especifica
		*@param c - Coleccion que se checara para contencion en esta coleccion
		*@throws ClassCastException - Si el tipo del elemento especifico es imcompatible con la coleccion
		*@throws NullPointerException - Si el elemento espeficado es null y la coleccion no permite elementos null.
		*@return boolean - True si la coleccion contiene todos los elementos en la coleccion especifica
		*/
	public boolean containsAll(Collection<?> c){
		Iterator<?> e = c.iterator();
		while (e.hasNext())
		if(!contains(e.next()))
		return false;
		return true;
		

	}
		/**
		*Método que agrega todos los elementos en la coleccion especifica a esta coleccion. 
		*@param c - Coleccion que contiene los elementos que se agregaran a esta coleccion.
		*@throws UnsupportedOperationException - Si la operacion addAll no esta soportada en esta coleccion
		*@throws ClassCastException - Si el tipo del elemento especifico es imcompatible con la coleccion
		*@throws NullPointerException - Si el elemento espeficado es null y la coleccion no permite elementos null.
		*@throws IllegalArgumentException - Si alguna propiedad del elemento de la coleccion especificada previene que sea agregado 
		*a esta coleccion.
		*@return modificado -  modificado es igual true si la coleccion cambio debido a la operacion, false en otro caso.
		*/
	public  boolean addAll(Collection <? extends E> c){
		  boolean modificado= false;
    for (E e : c) if (add(e)) modificado = true; 
    return modificado;
		}
		/**
		*Método que remueve una sola instancia del elemento especificado de esta coleccion.
		*@param o - Elemento que sera removido de la coleccion, si esta presente
		*@throws UnsupportedOperationException - Si la operacion addAll no esta soportada en esta coleccion
		*@throws ClassCastException - Si el tipo del elemento especifico es imcompatible con la coleccion
		*@throws NullPointerException - Si el elemento espeficado es null y la coleccion no permite elementos null.
		*@return boolean - True si el elemento fue 	removido de esta coleccion, false en otro caso
		*/
	public boolean remove (Object o){
		    if(remove(o)){
		        if( maxElement == x ){ 
		            updateMax();
		        }
		        return true;
		    }
		    return false;
		}

	
	public boolean removeAll(Collection<?> c){
		return false;//NO ES ESO
	}
	public boolean retainAll(Collection<?> c){
		return false;//NO ES ESO
	}
	public void clear(){

	}

	public String toString(){
		return null;//NO ES ESO
	}
}