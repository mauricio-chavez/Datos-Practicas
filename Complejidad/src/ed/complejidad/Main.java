package ed.complejidad;

import java.util.Scanner;

public class Main{

	 /**
     * Metodo main
     * @param args los parametros de ejecucion
     */
	public static void main(String[] args){
		int i;
		int j;
		int n;
		Complejidad comp = new Complejidad();
		Scanner io = new Scanner(System.in);
		System.out.println("Triangulo de Pascal recursivo:\n");
		System.out.print("\tIntroduce el numero de fila: ");
		i = io.nextInt();
		System.out.print("\tIntroduce el numero de columna: ");
		j = io.nextInt();
		System.out.println("\tEl resultado es: " + comp.tPascalRec(i,j));
		System.out.println("\tNumero de operaciones = " + comp.leeContador());
		comp.reiniciaContador();
		System.out.println("\nTriangulo de Pascal iterativo:\n");
		System.out.print("\tIntroduce el numero de fila: ");
		i = io.nextInt();
		System.out.print("\tIntroduce el numero de columna: ");
		j = io.nextInt();
		System.out.println("\tEl resultado es: " + comp.tPascalIt(i,j));
		System.out.println("\tNumero de operaciones = " + comp.leeContador());
		comp.reiniciaContador();
		System.out.println("\nSucecion de Fibonacci recursiva:\n");
		System.out.print("\tIntroduce el indice del elemento que deseas calcular: ");
		n = io.nextInt();
		System.out.println("\tEl resultado es : " + comp.fibonacciRec(n));
		System.out.println("\tNumero de operaciones = " + comp.leeContador());
		comp.reiniciaContador();
		System.out.println("\nSucecion de Fibonacci iterativa:\n");
		System.out.print("\tIntroduce el indice del elemento que deseas calcular: ");
		n = io.nextInt();
		System.out.println("\tEl resultado es : " + comp.fibonacciIt(n));
		System.out.println("\tNumero de operaciones = " + comp.leeContador());
		comp.reiniciaContador();
	}
}