package ed.estructuras.lineales;

/**
 * Arreglo de n-dimensiones
 * @author Mauricio Chavez Olea | Mario Navarrete Baltazar
 * @version 0.1
 */
public class Arreglo implements IArreglo{

	private int[] array;

	/**
	 * Crea un arreglo de n-dimensiones con el tamaño especificado en el arreglo pasado como parametro
	 * @param indices arreglo con los indices del arreglo multidimensional a crear.
	 */
	public Arreglo(int[] indices){
		

		for(int i=0;i<indices.length;i++)
			if(!(indices[i]>0))
				throw new IllegalArgumentException("Todas las dimensiones deben ser mayores a cero"); //Preguntar si esa se documenta
		
		int length = 0;

		for(int i=0;i<indices.length;i++)
			length += indices[i];

		array = new int[length];


		/*
			int pos = 0;
			int f;
			int n = indices.length-1;

		for(int i=0;i<=n;i++){
			if(i==n)
				f = 1;
			else
				for(int j=i+1;j<=n;j++)
					f *= indices[j];

			pos += f*indices[i];
		}

		*/
	}

	/**
	* Devuelve el elemento que se encuentra en la posicion <code>th</code> en el arreglo multidimensional.
	* @param indices arreglo con los indices del elemento a recuperar.
	* @return el elemento almacenado en la posicion <code>i</code>.
	*/
	public int obtenerElemento(int [] indices){
		
	}

	/**
	* Asigna un elemento en la posicion <code>th</code> del arreglo multidimensional.
	* @param indices arreglo con los indices donde se almacenara el elemento.
	* @param elem elemento a almacenar.
	*/
	public void almacenarElemento(int [] indices, int elem){

	}

	/**
	* Devuelve la posicion <code>i</code> del elemento en el arreglo de una dimension.
	* @param indices arreglo con los indices donde esta el elemento en el arreglo multidimensional.
        *                se debe cumplir que cada índice es positivo y menor que el tamaño de la dimensión correspondiente.
	* @return la posicion del elemento en el arreglo de una dimension.
	* @throws IndexOutOfBoundsException si alguno de los indices del arreglo no esta dentro del rango.
	*/
	public int obtenerIndice(int [] indices){

	}
}