package ed.estructuras.lineales;

/**
 * Arreglo de n-dimensiones
 * @author Mauricio Chavez Olea | Mario Navarrete Baltazar
 * @version 0.1
 */
public class Arreglo implements IArreglo{

	private int[] array;
	private int[] lengthArray;

	/**
	 * Crea un arreglo de n-dimensiones con el tamaño especificado en el arreglo pasado como parametro
	 * @param tamanios arreglo con los tamaños de los arreglos para crear el arreglo multidimensional.
	 */
	public Arreglo(int[] tamanios){
		
		for(int i=0;i<tamanios.length;i++)
			if(!(tamanios[i]>0))
				throw new IllegalArgumentException("Todas las dimensiones deben ser mayores a cero");
		
		this.lengthArray = tamanios;

		int length = 1;

		for(int i=0;i<tamanios.length;i++)
			length *= tamanios[i];

		array = new int[length];
	}

	/**
	* Devuelve el elemento que se encuentra en la posicion <code>th</code> en el arreglo multidimensional.
	* @param indices arreglo con los indices del elemento a recuperar.
	* @return el elemento almacenado en la posicion <code>i</code>
	* @throws IndexOutOfBoundsException si alguno de los indices del arreglo no esta dentro del rango.
	*/
	public int obtenerElemento(int [] indices){

		for(int i=0;i<lengthArray.length;i++)
			if(!(indices[i]<lengthArray[i]))
				throw new IndexOutOfBoundsException("Alguno de los indices es mayor al tamaño de su arreglo correspondiente");

		return array[obtenerIndice(indices)];
	}

	/**
	* Asigna un elemento en la posicion <code>th</code> del arreglo multidimensional.
	* @param indices arreglo con los indices donde se almacenara el elemento.
	* @param elem elemento a almacenar.
	* @throws IndexOutOfBoundsException si alguno de los indices del arreglo no esta dentro del rango.
	*/
	public void almacenarElemento(int [] indices, int elem){

		for(int i=0;i<lengthArray.length;i++)
			if(!(indices[i]<lengthArray[i]))
				throw new IndexOutOfBoundsException("Alguno de los indices es mayor al tamaño de su arreglo correspondiente");

		array[obtenerIndice(indices)] = elem;

	}

	/**
	* Devuelve la posicion <code>i</code> del elemento en el arreglo de una dimension.
	* @param indices arreglo con los indices donde esta el elemento en el arreglo multidimensional.
    *                se debe cumplir que cada índice es positivo y menor que el tamaño de la dimensión correspondiente.
	* @return la posicion del elemento en el arreglo de una dimension.
	* @throws IndexOutOfBoundsException si alguno de los indices del arreglo no esta dentro del rango.
	*/
	public int obtenerIndice(int [] indices){

		for(int i=0;i<lengthArray.length;i++)
			if(!(indices[i]<lengthArray[i]))
				throw new IndexOutOfBoundsException("Alguno de los indices es mayor al tamaño de su arreglo correspondiente");

		int index = 0;
		int n = indices.length-1;

		for(int i=0;i<=n;i++){

			int f = 1;

			for(int j=i+1;j<=n;j++)
				f *= lengthArray[j];

			index += f*indices[i];
		}

		return index;
	}
}