/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostonKasittely;

import java.util.Scanner;
import nimivisa.NimiVisa;


public class TiedostonValinta {
    
    private Scanner lukija;  
    private String valittu;

    public TiedostonValinta() {
     
        this.lukija = new Scanner(System.in);
        valittu = "";
    }

    public void valinnanSelvitys() {
        String valinta = lukija.nextLine();

        if (valinta.equals("e")||valinta.equals("k")) {
            System.out.println("Tiedostoa ei löydy");
        } else if (valinta.equals("s")) {        
            valittu = "sienet.txt";
            
        } else {
            System.out.println("Lopetus");
        }
        
    }
    
    public String getValittu() {
        return valittu;
    }
}
    

