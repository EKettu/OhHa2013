/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nimivisa;

import java.util.Random;

/**
 * Tehdään Arpoja, joka voi arpoa luvun tietyltä väliltä.
 * @author elinakettunen
 */

public class Arpoja {
    
    private Random arpoja;
    
    /**
     * Alustetaan Random arpoja -olio.
     */
    public Arpoja () {
        arpoja = new Random();
    }
    
    /**
     * Arvotaan luku, joka on kahden annetun luvun välissä.
     * @param alku arvottavan luvun alaraja
     * @param loppu arvottavan luvun yläraja
     * @return arvotun luvun tietyltä väliltä
     */
    
    public int arvoLukuValilta(int alku, int loppu) {
        int arvottuluku = alku + arpoja.nextInt(loppu);
        return arvottuluku;
    }
}
