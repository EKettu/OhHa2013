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
    
    public int arvoLukuValilta(int alku, int loppu) {
        int arvottuluku = alku + arpoja.nextInt(loppu);
        return arvottuluku;
    }
}
