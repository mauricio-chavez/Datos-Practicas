package ed.estructuras.nolineales;

import ed.estructuras.lineales.ListaDoblementeLigada;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.List;

public class NodoAVL <C extends Comparable <C>> extends NodoBOLigado<C> implements NodoBinario<C>{
	private C dato;
	private int altura;
	private int factorBalanceo;
	private NodoAVL<C> padre;
	private NodoAVL<C> hijoI;
	private NodoAVL<C> hijoD;
	/**
	 *Constructor de un nodo de árbol AVL
	 *@param dato - dato
	 */
	public NodoAVL(C dato){
		super(dato);
	}
	/**
	 *Constructor de un nodo árbol AVL
	 *@param dato - dato
	 *@param padre - Nodo padre
	 */
	public NodoAVL(C dato, NodoAVL<C> padre){
		super(dato,padre);
	}
	/**
	 *Constructor de un nodo árbol AVL
	 *@param dato  - dato
	 *@param hijoI - Nodo hijo izquierdo
	 *@param hijoD - Nodo hijo derecho
	 */
	public NodoAVL(C dato, NodoAVL<C> padre, NodoAVL<C> hijoI, NodoAVL<C> hijoD){
		super(dato,padre,hijoI,hijoD);
		factorBalanceo = calcularFB();
	}
	/**
	 *Método para calcular el factor de balanceo
	 *@return hI-hD- Factor de balanceo
	 */
	public int calcularFB(){
		int hI = (hijoI==null)? 0 : 1 + hijoI.getAltura();
		int hD = (hijoD==null)? 0 : 1 + hijoD.getAltura();
		return hI-hD;
	}
	/**
	 *Método para realizar una rotación a la derecha
	 *@return n - nuevo nodo
	 */
	public NodoAVL<C> rotarDerecha(){
		if(padre != null){
			if(padre.getHijoI() == this){
				padre.setHijoI(hijoI);
			}else{
				padre.setHijoD(hijoI);
			}
		}
		hijoI.setPadre(padre);
		NodoAVL<C> n = hijoI;
		hijoI = (NodoAVL<C>) n.getHijoD();
		if(n.getHijoD() != null)
			n.getHijoD().setPadre(this);
		n.setHijoD(this);
		padre = n;
		return n;
	}
	/**
	 *Método para realizar una rotación a la izquierda
	 *@return n - nuevo nodo 
	 */
	public NodoAVL<C> rotarIzquierda(){
		if(padre != null){
			if(padre.getHijoI() == this){
				padre.setHijoI(hijoD);
			}else{
				padre.setHijoD(hijoD);
			}
		}
		hijoD.setPadre(padre);
		NodoAVL<C> n = hijoD;
		hijoD = (NodoAVL<C>) n.getHijoI();
		if(n.getHijoI() != null)
			n.getHijoI().setPadre(this);
		n.setHijoI(this);
		padre = n;
		return n;
	}
	/**
	 *Método para balancear el árbol
	 *@return una rotación a la derecha o la izquierda o en caso de no haber rotaciones necesario regresa un null
	 */
	public NodoAVL<C> balancear(){
		actualizarFactorBalanceo();
		int fb = factorBalanceo;
		if(fb==2) {
			if(hijoI.calcularFB()==1)
				return rotarDerecha();
			else {
				NodoAVL<C> temp = (NodoAVL<C>) hijoI.rotarIzquierda().getPadre();
				return temp.rotarDerecha();
			}
		} else if(fb==-2) {
			if(hijoI.calcularFB()==-1)
				return rotarIzquierda();
			else {
				NodoAVL<C> temp = (NodoAVL<C>) hijoD.rotarDerecha().getPadre();
				return temp.rotarIzquierda();
			}
		}
		return null;
	}
	/*
	 *Método privado para actualizar el factor de balanceo
	 */
	private void actualizarFactorBalanceo(){
		factorBalanceo = calcularFB();
	}
	/**
	 *Método para obtener el factor de balanceo
	 *@return factorBalanceo--Factor de Balanceo
	 */
	public int getFactorBalanceo(){
		return factorBalanceo;
	}
}
