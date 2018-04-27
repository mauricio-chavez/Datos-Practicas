package ed.aplicaciones.calculadora;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculadoraTest {
	
	private static float points;
    private static float totalPoints;
    private float testValue;
	
	public CalculadoraTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
		totalPoints = points = 0;
	}
	
	@AfterClass
	public static void tearDownClass() {
		System.out.println("==============================");
    	System.out.println("Calificacion automatica: " + points * 10 /totalPoints);
        System.out.println("==============================");
	}
	
	/**
	 * Test de evaluaPrefija()
	 */
	@Test
	public void testEvaluaPrefija(){
		System.out.println("testPrefija()");
		testValue = 2.5f;
		totalPoints += testValue;
		
		String[] tokens = {"+","-4","-","8","2"};
		double result = Fija.evaluaPrefija(tokens);

		assertEquals(2.0,result);
		points += testValue;
	}
	
	/**
	 * Test de evaluaPostfija()
	 */
	@Test
	public void testEvaluaPostfija(){
		System.out.println("testPostfija()");
		testValue = 2.5f;
		totalPoints += testValue;
		
		String[] tokens = {"3","5","-7","*","3","-"};
		double result = Fija.evaluaPostfija(tokens);

		assertEquals(-38.0,result);
		points += testValue;
	}
	
	/**
	 * Test de testInfijaASufija()
	 */
	@Test
	public void testInfijaASufija(){
		System.out.println("testInfijaASufija()");
		testValue = 2.5f;
		totalPoints += testValue;
		
		String[] expected = {"4","5","+","2","5","*","-"};
		String[] result = {"4","+","5","-","(","2","*","5",")"};

		assertEquals(expected,Infija.infijaASufija(result));
		points += testValue;
	}
	
	
	/**
	 * Test de textEvaluaInfija()
	 */
	@Test
	public void textEvaluaInfija(){
		System.out.println("testEvaluaInfija()");
		testValue = 2.5f;
		totalPoints += testValue;
		
		String[] tokens = {"4","+","5","-","(","2","*","5",")"};

		assertEquals(-1.0,Infija.evaluaInfija(tokens));
		points += testValue;
	}
	
}