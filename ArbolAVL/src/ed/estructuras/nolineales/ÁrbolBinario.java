package ed.estructuras.nolineales;

import java.util.Iterator;

public interface ÁrbolBinario<E> extends Árbol<E>{
	NodoBinario<E> getRaíz();
	Iterator<E> getIteradorInorden();
}