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
	/**
	 * Constructor por omisión
	 */
	public Complejidad(){
		this.operaciones = 0;
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

    	if(j==i || j==0){
            return 1;
        }
    	else{
    		this.operaciones++;
    		return tPascalRec(i-1,j-1) + tPascalRec(i-1,j);
    	}

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

        if(iFact==0)
            iFact=1;
        else
            for(int k=i-1;k>1;k--){
                iFact*= k;
                this.operaciones++;
            }
        

        if(jFact==0)
            jFact=1;
        else
            for(int k=j-1;k>1;k--){
                jFact*= k;
                this.operaciones++;
            }
        

        if(ijFact==0)
            ijFact=1;
        else
            for(int k=ijFact-1;k>1;k--){
                ijFact*= k;
                this.operaciones++;
            }
        

    	return iFact/(jFact*ijFact);
    }
	
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
}