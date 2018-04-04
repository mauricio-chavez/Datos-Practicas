package ed.estructuras.lineales;

public class Reinas{

    private class PosicionReina{
        int fila;
        int columna;

	    PosicionReina(int fila, int columna){
            this.fila = fila;
            this.columna = columna;
	    }
	}

	PilaLigada<PosicionReina> stack = new PilaLigada<PosicionReina>();
	PosicionReina primerPosicion = new PosicionReina(1,1);
	stack.empuja(primerPosicion);

	public Reinas(int n){
		if(n<3)
			throw new IllegalArgumentException("No es posible resolver este problema para menos de tres casillas");

	}

    public static void main(String[] args) {
    }
}