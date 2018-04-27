/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo tal cual a estudiantes actuales o potenciales.
 */
package ed.aplicaciones.calculadora;

import ed.estructuras.lineales.PilaLigada;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Clase para evaluar expresiones en notaciones prefija y postfija.
 *
 * @author blackzafiro
 */
public class Fija {

	/**
	 * Evalúa la operación indicada por <code>operador</code>.
	 */
	private static double evalua(char operador, double operando1, double operando2) {
		switch(operador){
			case '+':
				return operando1 + operando2;
			case '-':
				return operando1 - operando2;
			case '*':
				return operando1 * operando2;
			case '/':
				return operando1 / operando2;
			case '%':
				return operando1 % operando2;
			default:
				return 0;
		}
	}

	/**
	 * Recibe la secuencia de símbolos de una expresión matemática en notación
	 * prefija y calcula el resultado de evaluarla.
	 *
	 * @param tokens Lista de símbolos: operadores y números.
	 * @return resultado de la operación.
	 */
	public static double evaluaPrefija(String[] tokens) {
		PilaLigada<String> pila = new PilaLigada<>();
		for(int i=tokens.length-1;i>=0;i--){
			String s = tokens[i];
			if(esUnNumero(s))
				pila.empuja(s);
			else {
				double operando1 = Double.valueOf(pila.expulsa());
				double operando2 = Double.valueOf(pila.expulsa());
				pila.empuja(String.valueOf(evalua(s.charAt(0),operando1,operando2)));
			}
		}
		return Double.valueOf(pila.expulsa());
	}

	/**
	 * Recibe la secuencia de símbolos de una expresión matemática en notación
	 * postfija y calcula el resultado de evaluarla.
	 *
	 * @param tokens Lista de símbolos: operadores y números.
	 * @return resultado de la operación.
	 */
	public static double evaluaPostfija(String[] tokens) {
		PilaLigada<String> pila = new PilaLigada<>();
		for(String s:tokens){
			if(esUnNumero(s))
				pila.empuja(s);
			else {
				double operando2 = Double.valueOf(pila.expulsa());
				double operando1 = Double.valueOf(pila.expulsa());
				pila.empuja(String.valueOf(evalua(s.charAt(0),operando1,operando2)));
			}
		}
		return Double.valueOf(pila.expulsa());
	}

	/**
	 * Interfaz de texto para la calculadora.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String sentence;
		String method = "prefija";
		String delims = "\\s+|(?<=\\()|(?=\\))";
		String[] tokens;

		System.out.println("Calculadora en modo notación " + method);
		System.out.println("La notación prefija utiliza los operadores a la izquierda, el orden de ejecución de las operaciones depende de la posición que ocupan los elementos, los operandos deberan ser evualados antes de proceder");
		System.out.println("Si desea cambiar de notacion" + method + " escriba postfija, para salir del programa escriba exit");
		while (true) {
			sentence = scanner.nextLine();
			switch (sentence) {
				case "exit":
					return;
				case "prefija":
				case "postfija":
					System.out.println("Cambiando a notación " + sentence);
					method = sentence;
					continue;
				default:
					break;
			}
			tokens = sentence.split(delims);
			System.out.println(Arrays.toString(tokens));
			if (method.equals("postfija")) {
				System.out.println("= " + evaluaPostfija(tokens));
			} else {
				System.out.println("= " + evaluaPrefija(tokens));
			}

		}
	}

	private static boolean esUnNumero(String token){
		try{
			Double newDouble = Double.valueOf(token);
			return true;
		}catch(NumberFormatException ex){
			return false;
		}
	} 
}
