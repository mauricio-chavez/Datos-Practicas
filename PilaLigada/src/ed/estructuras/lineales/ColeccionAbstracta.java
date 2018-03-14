package ed.estructuras.lineales;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Collection;

public abstract class ColeccionAbstracta<E> implements Collection<E>{
   
   	/**
	 * Método que regresará un booleano verdadero si la coleccion contiene el elemento especificado
	 * @param o - Elemento que se verificara que se encuentra en la coleccion
	 * @return boolean- True si el elemento se encunetra en la coleccion, false en otro caso.
	 */
   	@Override
	public boolean contains(Object o){

		if(o==null)
			for(Object d:this){
				if(d==null)
					return true;
			}

		else
			for(Object e:this){
				if(e.equals(o))
					return true;	
			}

		return false;
	}

	/**
	 * Método que regresa un arreglo conteniendo todos los elementos de la coleccion. Si la coleccion el orden de los elementos que se estan
	 * regresando por su iterador, este metodo debe devolver los elementos en el mismo orden.
	 * @return Un arreglo que contiene todos los elementos de la coleccion.
	 */
	@Override
	public Object[] toArray(){
		Object[] buffer = new Object[this.size()];
		int count = 0;

		for(E e:this){
			buffer[count++] = e;
		}

		return buffer;
	}

	/**
	 * Método que regresa un arreglo conteniendo todos los elementos en la coleccion, el tipo tipo de tiempo de ejecución del arreglo regresado
	 * es de ese arreglo en específico. Si la colección cabe en este arreglo específico, se va regresar este. En otro caso un nuevo arreglo
	 * se va  a colocar con el tipo de tiempo de ejecución con el arreglo específico y el tamaño de su colección.
	 * @param a - El arreglo del cual los elementos de esta coleccion seran guardados, si es lo suficientemente grande, en otro caso un nuevo 
	 * arreglo del mismo tiempo de ejecución sera colocado para su mismo proposito.
	 * @throws NullPointerException - Si el arreglo espeficado es null.
	 * @return Arreglo que contiene todos los elementos de esta coleccion
	 */
	@Override
	public <T> T[] toArray(T[] a){
		if(a.length==0)
			throw new NullPointerException("El arreglo del parametro no puede ser null");

		int size = size();
	  	if(a.length < size)
	  		return (T[]) Arrays.copyOf(this.toArray(),size,a.getClass());
	  	System.arraycopy(this.toArray(),0,a,0,size);
	  	if(a.length > size)
	  		a[size] = null;
	  	return a;
	}

	/**
	 * Método que verifica  si la coleccion contiene todos los elementos en la coleccion especifica
	 * @param c - Coleccion que se checara para contencion en esta coleccion
	 * @throws ClassCastException - Si el tipo del elemento especifico es imcompatible con la coleccion
	 * @return boolean - True si la coleccion contiene todos los elementos en la coleccion especifica
	 */
	@Override
	public boolean containsAll(Collection<?> c){
		for(Object t:c){
			if(!(this.contains(t)))
				return false;
		}

		return true;
	}

	/**
	 * Método que agrega todos los elementos en la coleccion especifica a esta coleccion. 
	 * @param c - Coleccion que contiene los elementos que se agregaran a esta coleccion.
	 * @throws ClassCastException - Si el tipo del elemento especifico es imcompatible con la coleccion
	 * @return modificado -  modificado es igual true si la coleccion cambio debido a la operacion, false en otro caso.
	 */
	@Override
	public  boolean addAll(Collection <? extends E> c){
		boolean changed = false;
	    for (E e : c) 
	    	if (add(e)) 
	    		changed = true; 
	    return changed;
	}

	/**
	 * Método que remueve una sola instancia del elemento especificado de esta coleccion.
	 * @param o - Elemento que sera removido de la coleccion, si esta presente
	 * @throws UnsupportedOperationException - Si la operacion addAll no esta soportada en esta coleccion
	 * @throws ClassCastException - Si el tipo del elemento especifico es imcompatible con la coleccion
	 * @return boolean - True si el elemento fue 	removido de esta coleccion, false en otro caso
	 */
	@Override
	public boolean remove (Object o){
		Iterator<E> it = iterator();
		if (o==null) {
			while (it.hasNext())
				if (it.next()==null){
					it.remove();
					return true;
				}
		} else {
			while (it.hasNext())
				if (o.equals(it.next())){
					it.remove();
					return true;
				}
		}
		return false;
	}

	/**
	 * Elimina todos los elementos de esta coleccion que estan igualmente contenidos en la coleccion especificada. 
	 * Despues de esta operacion, esta coleccion no contendrá elementos en común con la colección pasada como parámetro.
	 * @param c - coleccion que contiene a los elementos que serán removidos de esta coleccion
	 * @return true si esta coleccion cambio como resultado de llamar al metodo
	 * @throws UnsupportedOperationException - si el método removeAll no está soportado por esta coleccion
	 */
	@Override
	public boolean removeAll(Collection<?> c){
		boolean removed = false;
		for(Object o:c){
			if(this.remove(o))
				removed = true;
		}
		return removed;
	}

	/**
	 * Conserva únicamente los elementos de esta coleccion que estan igualmente contenidos en la coleccion especificada. 
	 * Es decir, quita de esta coleccion los elementos que no están contenidos en la coleccion especificada como parámetro
	 * @param c - coleccion que contiene a los elementos que serán conservador de esta coleccion
	 * @return true si esta coleccion cambio como resultado de llamar al metodo
	 * @throws UnsupportedOperationException - si el método retainAll no está soportado por esta coleccion
	 */
	@Override
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

	/**
	 * Elimina todos los elementos de esta coleccion.
	 * @throws UnsupportedOperationException - si el método clear no está soportado por esta coleccion
	 */
	@Override
	public void clear(){
		Iterator<E> it = iterator();
		while(it.hasNext()){
			it.next();
			it.remove();
		}
	}

	/**
	 * Regresa la coleccion como una cadena de texto, con fines de depuracion
	 * @return Una cadena de texto con los elementos de la coleccion
	 */
	@Override
	public String toString(){
		Iterator<E> it = iterator();
		String result = "{";
			while(it.hasNext())
				result += it.next() + " ";
		return result.trim() + "}";
	}
}