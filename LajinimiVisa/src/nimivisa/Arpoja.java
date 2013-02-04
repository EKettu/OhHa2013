/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nimivisa;

import java.util.Random;


public class Arpoja {
    
    private Random arpoja;
    
    public Arpoja () {
        arpoja = new Random();
    }
    
    /**
     * Arvotaan luku, joka on kahden annetun luvun välissä
     * @param alku arvottavan luvun alaraja
     * @param loppu arvottavan luvun yläraja
     * @return arvotun luvun tietyltä väliltä
     */
    
    public int arvoLukuValilta(int alku, int loppu) {
        int arvottuluku = alku + arpoja.nextInt(loppu);
        return arvottuluku;
    }
}
