package ed.ordenamientos;

public class BubbleSorter<C extends Comparable<C>> implements IOrdenador<C> {

	/**
	 * Crea un arreglo nuevo con los elementos ordenados.
	 * @param a El arreglo cuyos elementos se quieren ordenar.
	 * @return Un arreglo nuevo, del mismo tipo de <code>a</code> pero con los
	 *         elementos en el orden dictado por <code>compareTo</code>.
	 */
	public C[] ordena(C[] a){
		boolean cambio;

		for(int k=a.length-2;k>=0;k--) {
			cambio = false;
			for(int i=0,j=1;i<=k;i++,j++) {
				if(a[i].compareTo(a[j])>0) {
					swap(a,i,j);
					cambio = true;
				}
			}
			if(cambio==false) break;
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