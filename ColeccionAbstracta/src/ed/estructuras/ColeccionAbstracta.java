package ed.estructuras;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Collection;
import java.lang.reflect.Array;

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
		Iterator<E> it = iterator();
		if (o==null) {
			while (it.hasNext())
				if (it.next()==null)
					return true;
		} else {
			while (it.hasNext())
				if (o.equals(it.next()))
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
		Iterator<E> it = iterator();
		Object[] buffer = new Object[this.size()];
		int count = 0;

		while(it.hasNext())
			buffer[count++] = it.next();

		return buffer;
	}
	/**
	*Método que regresa un arreglo conteniendo todos los elementos en la coleccion, el tipo tipo de tiempo de ejecución del arreglo regresedo
	* es de ese arreglo en específico. Si la colección cabe en este arreglo específico, se va regresar este. En otro caso un nuevo arreglo
	* se va  a colocar con el tipo de tiempo de ejecución con el arreglo específico y el tamaño de su colección.
	*@param a - El arreglo del cual los elementos de esta coleccion seran guardados, si es lo suficientemente grande, en otro caso un nuevo 
	*arreglo del mismo tiempo de ejecución sera colocado para su mismo proposito.
	*@throws ArrayStoreException - Si el tiempo de ejecución del arreglo especificado no es un supertipo del tiempo de ejecución de cada 
	*elemento de esta coleccion.
	*@throws NullPointerException - Si el arreglo espeficado es null.
	*@throws IllegalArgumentException - Si la longitud del arreglo es diferente de cero
	*@return r - Arreglo que contiene todos los elementos de esta coleccion
	*/
	public <T> T[] toArray(T[] a){
		int size = size();
	    T[] r = (a.length >= size) ? a : (T[]) Array.newInstance(a.getClass().getComponentType(), size);

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
	    return (it.hasNext()) ? AToArray(r, it) : r;
	}

	/**
	*Método que verifica  si la coleccion contiene todos los elementos en la coleccion especifica
	*@param c - Coleccion que se checara para contencion en esta coleccion
	*@throws ClassCastException - Si el tipo del elemento especifico es imcompatible con la coleccion
	*@throws NullPointerException - Si el elemento espeficado es null y la coleccion no permite elementos null.
	*@return boolean - True si la coleccion contiene todos los elementos en la coleccion especifica
	*/
	public boolean containsAll(Collection<?> c){
		Iterator<?> it = c.iterator();
		while (it.hasNext())
			if(!(this.contains(it.next())))
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
		boolean changed = false;
	    for (E e : c) 
	    	if (add(e)) 
	    		changed = true; 
	    return changed;
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
		Iterator<E> e = iterator();
		if (o==null) {
			while (e.hasNext())
				if (e.next()==null){
					e.remove();
					return true;
				}
		} else {
			while (e.hasNext())
				if (o.equals(e.next())){
					e.remove();
					return true;
				}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> c){
		Iterator<?> it = c.iterator();
		boolean removed = false;
		while (it.hasNext())
			if(!(this.remove(it.next())))
				removed = true;
		return removed;
	}

	public boolean retainAll(Collection<?> c){
		Iterator<E> it = iterator();
		boolean changed = false;
		while (it.hasNext())
			if(!(c.contains(it.next()))){
				it.remove();
				changed = true;
			}
		return changed;
	}

	public void clear(){
		Iterator<E> it = iterator();
		while(it.hasNext()){
			it.next();
			it.remove();
		}
	}

	public String toString(){
		Iterator<E> it = iterator();
		String result = "{";
			while(it.hasNext())
				result += it.next().toString() + " ";
		return result.trim() + "}";
	}

	private static <T> T[] AToArray(T[] a, Iterator<?> it){
		int i = a.length;
		while(it.hasNext()){
			int cap = a.length;
			if(i == cap){
				int newCap = ((cap/2))*3;
				if(newCap<=cap){
					if(cap == Integer.MAX_VALUE)
						throw new OutOfMemoryError();
					newCap = Integer.MAX_VALUE;
				}
				a = Arrays.copyOf(a,newCap);
			}
			a[i++] = (T) it.next();
		}
		return (i==a.length) ? a : Arrays.copyOf(a,i);
	}
}