package ed.ordenamientos;

import java.util.Arrays;

public class MergeSorter<C extends Comparable<C>> implements IOrdenador<C> {

	/**
	 * Crea un arreglo nuevo con los elementos ordenados.
	 * @param a El arreglo cuyos elementos se quieren ordenar.
	 * @return Un arreglo nuevo, del mismo tipo de <code>a</code> pero con los
	 *		 elementos en el orden dictado por <code>compareTo</code>.
	 */
	public C[] ordena(C[] a){
		return mergesort(a,0,a.length-1);
	}
	
	/**
	 * Devuelve un arreglo de enteros de tal manera que, si es ordenado con
	 * un objeto de esta clase, será el peor caso para la complejidad en tiempo.
	 * @param tam La longitud del arreglo a generar.
	 * @return Arreglo con el peor caso para el algoritmo de ordenamiento
	 *		 implementado.
	 */
	public int[] peorCaso(int tam){
		int[] a = new int[tam];
		int count = 1;
		for(int i=a.length-1;i>=0;i--) {
			a[i] = count++;
		}
		return a;
	}

	private C[] mergesort(C[] a, int l,int r) {
		if(r>l) {
			int middle = (r+l)/2;
			C[] left = mergesort(a,l,middle);
			C[] right = mergesort(a,middle+1,r);
			merge(a,l,middle,r);
		}
		return a;
	}

	private void merge(C[] a, int l, int m, int r) {
		int lengthLeft = m - l + 1;
		int lengthRight = r - m;

		C left[] = Arrays.copyOf(a,lengthLeft);
		C right[] = Arrays.copyOf(a,lengthRight);

		for (int i=0; i<lengthLeft; ++i)
			left[i] = a[l + i];
		for (int j=0; j<lengthRight; ++j)
			right[j] = a[m + 1 + j];

		int i = 0;
		int j = 0;
 
		int k = l;
		while (i < lengthLeft && j < lengthRight) {
			if (left[i].compareTo(right[j])<=0) {
				a[k] = left[i];
				i++;
			} else {
				a[k] = right[j];
				j++;
			}
			k++;
		}
 
		while (i < lengthLeft) {
			a[k] = left[i];
			i++;
			k++;
		}

		while (j < lengthRight) {
			a[k] = right[j];
			j++;
			k++;
		}
	}

}