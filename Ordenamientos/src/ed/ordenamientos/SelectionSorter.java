package ed.ordenamientos;

public class SelectionSorter<C extends Comparable<C>> implements IOrdenador<C> {

	/**
	 * Crea un arreglo nuevo con los elementos ordenados.
	 * @param a El arreglo cuyos elementos se quieren ordenar.
	 * @return Un arreglo nuevo, del mismo tipo de <code>a</code> pero con los
	 *         elementos en el orden dictado por <code>compareTo</code>.
	 */
	public C[] ordena(C[] a){
		C menor;
		for(int j=0;j<a.length-1;j++) {
			menor = a[j];
			for(int i=j+1;i<a.length;i++)
				if(a[i].compareTo(menor)<0)
					menor = a[i];
			a[j] = menor;
		}
		return a;
	}
	
	/**
	 * Devuelve un arreglo de enteros de tal manera que, si es ordenado con
	 * un objeto de esta clase, será el peor caso para la complejidad en tiempo.
	 * @param tam La longitud del arreglo a generar.
	 * @return Arreglo con el peor caso para el algoritmo de ordenamiento
	 *         implementado.
	 */
	public int[] peorCaso(int tam){
		int[] a = new int[tam];
		int count = 1;
		for(int i=a.length-1;i>=0;i--) {
			a[i] = count++;
		}
		return a;
	}

}