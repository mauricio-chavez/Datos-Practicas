/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo tal cual a estudiantes actuales o potenciales.
 */
package ed.aplicaciones.calculadora;

import ed.estructuras.lineales.PilaLigada;
import ed.estructuras.lineales.ColaArreglo;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

/**
 * Clase para evaluar expresiones en notación infija.
 *
 * @author blackzafiro
 */
public class Infija {

	/**
	 * Devuelve la precedencia de cada operador. Entre mayor es la precedencia,
	 * más pronto deberá ejecutarse la operación.
	 *
	 * @operador Símbolo que representa a las operaciones: +,-,*,/ y '('.
	 * @throws UnsupportedOperationException para cualquier otro símbolo.
	 */
	private static int precedencia(char operador) {
		switch(operador){
			case '(':
				return 0;
			case '+':
				return 1;
			case '-':
				return 1;
			case '*':
				return 2;
			case '/':
				return 2;
			case '%':
				return 2;
			default:
				throw new UnsupportedOperationException();
		}
	}

	/**
	 * Pasa las operaciones indicadas en notación infija a notación sufija o
	 * postfija.
	 *
	 * @param tokens Arreglo con símbolos de operaciones (incluyendo paréntesis)
	 * y números (según la definición aceptada por
	 * <code>Double.parseDouble(token)</code> en orden infijo.
	 * @return Arreglo con símbolos de operaciones (sin incluir paréntesis) y
	 * números en orden postfijo.
	 */
	public static String[] infijaASufija(String[] tokens) {
		PilaLigada<String> pila = new PilaLigada<>();
		ColaArreglo<String> cola = new ColaArreglo<>(new String[0]);

		for(String token:tokens){
			if(esUnNumero(token))
				cola.forma(token);
			else {
				if(token.charAt(0)==')') {
					while(pila.mira().charAt(0)!='('){
						cola.forma(pila.expulsa());
					}
					String parentesis = pila.expulsa();
				} else {
					if(!pila.isEmpty()){
						int prec = precedencia(token.charAt(0));
						while(precedencia(pila.mira().charAt(0))>=prec && token.charAt(0) != '(') {
							cola.forma(pila.expulsa());
							if(pila.isEmpty())
								break;
						}
					}	
					pila.empuja(token);
				}
			}
		}

		while(!pila.isEmpty()){
			cola.forma(pila.expulsa());
		}

		String[] colaArreglo = new String[cola.size()];
		for(int i=0;i<colaArreglo.length;i++)
			colaArreglo[i] = cola.atiende();
		return colaArreglo;
	}

	/**
	 * Recibe la secuencia de símbolos de una expresión matemática en notación
	 * infija y calcula el resultado de evaluarla.
	 *
	 * @param tokens Lista de símbolos: operadores, paréntesis y números.
	 * @return resultado de la operación.
	 */
	public static double evaluaInfija(String[] tokens) {
		String[] suf = infijaASufija(tokens);
		System.out.println("Sufija: " + Arrays.toString(suf));
		return Fija.evaluaPostfija(suf);
	}

	/**
	 * Interfaz de texto para la calculadora.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String sentence;
		String method = "infija";
		String delims = "\\s+|(?<=\\()|(?=\\))";
		String[] tokens;

		System.out.println("Calculadora en modo notación " + method);
		while (true) {
			sentence = scanner.nextLine();
			switch (sentence) {
				case "exit":
					return;
				case "infija":
				case "prefija":
				case "postfija":
					System.out.println("Cambiando a notación " + sentence);
					method = sentence;
					continue;
				default:
					break;
			}
			tokens = sentence.split(delims);
			System.out.println("Tokens: " + Arrays.toString(tokens));
			double resultado;
			switch (method) {
				case "infija":
					resultado = evaluaInfija(tokens);
					break;
				case "prefija":
					resultado = Fija.evaluaPrefija(tokens);
					break;
				case "postfija":
					resultado = Fija.evaluaPostfija(tokens);
					break;
				default:
					System.out.println("Método inválido <" + method
							+ "> seleccione alguno de:\n"
							+ "\tinfija\n"
							+ "\tprefija\n"
							+ "\tpostfija\n");
					continue;
			}
			System.out.println("= " + resultado);
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
