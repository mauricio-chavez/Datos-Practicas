package ed.complejidad;

/**
 * Clase que implementa la interfaz <code>IComplejidad</code>, implementando todos sus metodos, 
 * asi como implementa un metodo <code>main</code> para probar cada metodo con valores distintos
 * @author Chavez Olea Mauricio
 * @author Navarrete Baltazar Mario
 * @see IComplejidad
 * @version 15/02/18
 */
public class Complejidad implements IComplejidad{
	private static int operaciones;
	private String archivo;
	/**
	*Constructor por omisión
	*/
	public Complejidad(){
		this.operaciones = 0;
		this.archivo = null;
	}
	/**
	*Constructor que asigna las operaciones y los archivos
	*/
	public Complejidad(int ops, String arch){
		asignarOperaciones(ops);
		asignarArchivo(arch);
	}

	/**
	*Método para asignar operaciones
	*@param ops- operaciones a asignar
	*/
	public void asignarOperaciones(int ops){
		operaciones = ops;
	}
	/**
	*Metodo para asignar archivo
	*@param arch - archivo a asignar
	*/
	public void asignarArchivo(String arch){
		archivo = arch;
	}
	/**
	*Método para obtener operaciones
	*@return operaciones - operaciones
	*/
	public  int obtenerOperaciones(){
		return operaciones;
	}
	/**
	*Método para obtener archivo
	*@return archivo - archivo
	*/
	public  String obtenerArchivo(){
		return archivo;
	}
	
	/**
     * Pone en cero el contador de operaciones realizadas.
     */
	public void reiniciaContador(){
		operaciones = 0;

	}

	/**
     * Devuelve el número de operaciones acumuladas desde la última vez
     * que se reinició el contador.
     * @return El numero de operaciones como entero
     */
	public int leeContador(){
		return this.operaciones;
	}

	/**
     * Método para calcular, de forma recursiva, el elemento en la fila
     * <code>i</code>, en la columna <code>j</code> del triangulo de Pascal
     * @param i el numero de fila
     * @param j el numero de columna  Si es <code>null</code> no se realizará
     *                este cálculo.
     * @return El elemento en la <code>i</code>-esima fila y la
     * <code>j</code>-esima columna del triangulo de Pascal.
     * @throws IndexOutOfBoundsException Si los indices <code>i</code> o
     * <code>j</code> son inválidos
     */
    public int tPascalRec(int i, int j) {
    	if(i<0 || j<0)
    		throw new IndexOutOfBoundsException("Los indices no pueden ser menores o iguales a cero");
    	else if(j>i)
    		throw new IndexOutOfBoundsException("El indice j no puede ser mayor al indice i");

    	if(j==i || j==0)
    		return 1;
    	else{
    		this.operaciones++;
    		return tPascalRec(i-1,j-1) + tPascalRec(i-1,j);
    	}

		/* if (i < 0 || j < 0 || j > i)
			throw new IllegalArgumentException("No puede ser menor a 0");
		if ( j == 0 || j == 1)
			return 1;
		else 
			return tPascalRec(i - 1, j+1) + tPascalRec(i - 1);
			*/

	}

	/**
     * Metodo para calcular, iterativamente, el elemento en la fila
     * <code>i</code> y la columna <code>j</code> del triangulo de Pascal
     * @param i el numero de fila
     * @param j el numero de columna
     * @return El elemento en la i-esima fila y la j-esima columna del triangulo
     * de Pascal.
     * @throws IndexOutOfBoundsException Si los indices <code>i</code> o
     * <code>j</code> son inválidos
     */
    public int tPascalIt(int i,int j){

    	if(i<0 || j<0)
    		throw new IndexOutOfBoundsException("Los indices no pueden ser menores o iguales a cero");
    	else if(j>i)
    		throw new IndexOutOfBoundsException("El indice j no puede ser mayor al indice i");

    	int iFact = i;
    	int jFact = j;
    	int ijFact = i-j;

    	for(int k=i-1;k>1;k--){
    		iFact*= k;
    		this.operaciones++;
    	}

    	for(int k=j-1;k>1;k--){
    		jFact*= k;
    		this.operaciones++;
    	}

    	for(int k=ijFact-1;k>1;k--){
    		ijFact*= k;
    		this.operaciones++;
    	}

    	return iFact/(jFact*ijFact);
    }

    /*if (j < 0 || j > i) {
        return 0;
    }

    if (j == 0 || j == i) {
        return 1;
    }

    int[] filaAnterior = {1, 1};

    for (int n = 2; n < i; n++) {
        int[] filaActual = new int[n+1];
        for (int m = 0; m <= i; m++) {
            if (m == 0 || m == i) {
                filaActual[m] = 1;
                continue;
            }

            filaAnterior[m] = filaAnterior[m-1] + filaAnterior[m];
        }
        filaAnterior = filaActual;
    }

    return filaAnterior[j - 1] + filaAnterior[j];
}*/

	
	/**
     * Devuelve el n-esimo elemento, calculado de forma recursiva,
     * de la sucesion de Fibonacci
     * @param n el indice del elemento que se desea calcular
     * @return el n-esimo elemento de la sucesion de Fibonacci
     * @throws IndexOutOfBoundsException Si el valor de <code>n</code>es inválido
     */
    public int fibonacciRec(int n){
    	if(n<0)
    		throw new IndexOutOfBoundsException("El valor de n es invalido");

		if(n==0)
			return 0;
		else if(n==1)
		    return 1;
		else{    
			this.operaciones++;                         
		    return fibonacciRec(n-1) + fibonacciRec(n-2);
		}
	}
	
	/**
     * Devuelve el n-esimo elemento, calculado de forma iterativa,
     * de la sucesion de Fibonacci
     * @param n el indice del elemento que se desea calcular
     * @return el n-esimo elemento de la sucesion de Fibonacci
     * @throws IndexOutOfBoundsException Si el valor de <code>n</code>es inválido
     */
    public int fibonacciIt(int n){
    	if(n<0)
    		throw new IndexOutOfBoundsException("EL valor de n es invalido");

    	if(n==0)
    		return 0;
    	else if(n==1)
    		return 1;
    	else{
    		int nMenos1 = 0;
	    	int nMenos2 = 1;
	    	int fibN = -1;

    		for(int i=2;i<=n;i++){
    			fibN = nMenos2 + nMenos1;
    			nMenos2 = nMenos1;
    			nMenos1 = fibN;
    			this.operaciones++;
    		}

    		return nMenos1 + nMenos2;
    	}
    }
		/*int x, y, z;

    if(n == 0){
        return 0;
    }
    else{
        x = 1;
        y = 1;

        for(int i = 3; i <=n; i++){
            z = x+y;       
            x = y;      
            y = z;      
            this.operaciones++;        
        }
    }
}
    return y;*/  	
}