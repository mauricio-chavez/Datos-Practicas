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
    
    public NodoAVL(C dato){
        super(dato);
    }

    public NodoAVL(C dato, NodoAVL<C> padre){
        super(dato,padre);
    }

    public NodoAVL(C dato, NodoAVL<C> padre, NodoAVL<C> hijoI, NodoAVL<C> hijoD){
    	super(dato,padre,hijoI,hijoD);
        factorBalanceo = calcularFB();
    }

    public int calcularFB(){
        int hI = (hijoI==null)? 0 : 1 + hijoI.getAltura();
        int hD = (hijoD==null)? 0 : 1 + hijoD.getAltura();
        return hI-hD;
    }

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

    private void actualizarFactorBalanceo(){
        factorBalanceo = calcularFB();
    }

    public int getFactorBalanceo(){
        return factorBalanceo;
    }
}