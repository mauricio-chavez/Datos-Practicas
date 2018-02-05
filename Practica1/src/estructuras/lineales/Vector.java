/*/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo resuelto a estudiantes actuales o potenciales.
 */

package estructuras.lineales;

/**
 * Arreglo redimensionable.
 * @author veronica
 */
public class Vector<T> {
    public static final int INC = 10;
    private Object[] buffer;
    
    /**
     * Constructor que crea un <code>Vector</code> con capacidad inicial INC.
     */
    public Vector() {
        buffer = new Object[INC];
        for(int i=0;i<INC;i++)
            buffer[i] = null;
    }
    
    /// MÉTODOS DE ACCESO
    
    /**
     * Devuelve el elemento almacenado en la posición <code>i</code>.
     * @param i el índice del objeto a recuperar.
     * @return el elemento almacenado en la posición <code>i</code>.
     * @throws IndexOutOfBoundsException si
     *         <code>!(0 &lt;= i &lt; this.leeCapacidad()) </code>.
     */
    public T lee(int i) {
        T e = null;
        if(i<0 || i>=this.leeCapacidad())
                throw new IndexOutOfBoundsException("No existe este indice en el Vector");
        try{
            e = (T) (buffer[i]);
        }catch(ClassCastException exc){
            System.out.println("El objeto no es del tipo especificado");
        }
        return e;
    }
    
    /**
     * Devuelve la capacidad actual de este <code>Vector</code>.
     * @return la capacidad actual del <code>Vector</code>.
     */
    public int leeCapacidad() {
        return buffer.length;
    }
    
    /// MÉTODOS DE MANIPULACIÓN
    
    /**
     * Almacena el elemento <code>e</code> en la posición <code>i</code>.
     * @param i el índice en el cual <code>e</code> será almacenado. 
     *          Debe cumplirse <code>0 &lt;= i &lt; this.leeCapacidad() </code>.
     * @param e el elemento a almacenar.
     * @throws IndexOutOfBoundsException si
     *         <code>!(0 &lt;= i &lt; this.leeCapacidad()) </code>.
     */
    public void asigna(int i, T e) {
        if(i<0 || i>=this.leeCapacidad())
            throw new IndexOutOfBoundsException("No existe este indice en el Vector");
        buffer[i] = e;
    }
    
    /**
     * Asigna la capacidad del <code>Vector</code>.
     * Si <code>n &lt; this.leeCapacidad()</code> los elementos de
     * <code>n</code> en adelante son descartados.
     * Si <code>n &gt; this.leeCapacidad()</code> se agregan <code>null</code>
     * en los espacios agregados.
     * @param n la nueva capacidad del <code>Vector</code>, debe ser mayor que
     *          cero.
     * @throws IllegalSizeException si <code>n &lt; 1</code>.
     */
    public void asignaCapacidad(int n) throws IllegalSizeException {
        if(n<1)
            throw new IllegalSizeException("La capacidad debe ser mayor a cero");
        Object temp[] = new Object[n];
        if(n<this.leeCapacidad())
            for(int i=0;i<n;i++)
                temp[i] = this.buffer[i];

        else if(n>this.leeCapacidad()){
            for(int i=0;i<this.leeCapacidad();i++)
                temp[i] = this.buffer[i];
            for(int i=this.leeCapacidad();i<n;i++)
                temp[i] = null;
        }         
            
        buffer = temp;
    }
    
    /**
     * Garantiza que el <code>Vector</code> cuente al menos con capacidad para
     * almacenar <code>n</code> elementos.
     * Si <code>n &gt; this.leeCapacidad()</code> el tamaño del
     * <code>Vector</code> es incrementado de tal modo que el requerimiento
     * sea satisfecho con cierta holgura.
     * @param n capacidad mínima que debe tener el <code>Vector</code>,
     *          no puede ser menor a cero.
     */
    public void aseguraCapacidad(int n) {
        if(n>this.leeCapacidad()){
            try{
                this.asignaCapacidad(n+5);
            }catch(IndexOutOfBoundsException e){
                System.out.println("n no puede ser menor a cero");//ME FALTAN COSAS
            }
        }
    }
}
