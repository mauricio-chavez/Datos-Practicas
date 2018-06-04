package ed.ordenamientos;

public class QuickSorter<C extends Comparable<C>> implements IOrdenador<C> {

	/**
	 * Crea un arreglo nuevo con los elementos ordenados.
	 * @param a El arreglo cuyos elementos se quieren ordenar.
	 * @return Un arreglo nuevo, del mismo tipo de <code>a</code> pero con los
	 *         elementos en el orden dictado por <code>compareTo</code>.
	 */
	public C[] ordena(C[] a){
		return quicksort(a,0,a.length-1);
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

		for(int i=0;i<a.length;i++)
			a[i] = i;
		
		return a;
	}

	private int partition(C[] arr, int first, int last) {
        C pivot = arr[first]; 
        int low = (first+1);
        int high = last;

	    while (high > low) {

	    	while (low <= high && arr[low].compareTo(pivot)<=0)
	        	low++;

	    	while (low <= high && arr[high].compareTo(pivot)>0)
	    		high--;

	    	if (high > low)
	    		swap(arr,low,high);
	    }

	    while (high > first && arr[high].compareTo(pivot)>=0)
	    	high--;

	    if (pivot.compareTo(arr[high])>0) {
	    	arr[first] = arr[high];
	    	arr[high] = pivot;
	    	return high;
	    }
	    else {
	    	return first;
	    }
    }
 
    private C[] quicksort(C[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);

            quicksort(arr, low, pivot-1);
            quicksort(arr, pivot+1, high);
        }
        return arr;
    }

}