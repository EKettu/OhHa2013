/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostonKasittely;

import java.io.File;
import nimivisa.NimienHallinta;

/**
 * Tarkistetaan, onko valittua tiedostoa olemassa ja palautetaan se.
 * @author elinakettunen
 */

public class TiedostonValinta {

    private String valittu;
/**
 * Alustetaan valitun tiedoston nimeä kuvaava merkkijonomuuttuja. 
 */
    public TiedostonValinta() {
        valittu = "";

    }

    /**
     * Tarkistaa, etta syötetty tiedosto löytyy.
     *
     * @param valinta luettavan tekstitiedoston nimi
     * @return true, jos tiedosto löytyy, false jos ei löydy
     */
    public boolean valinnanSelvitys(String valinta) {
        File tiedosto = new File(valinta);
        valittu = valinta;

        return tiedosto.exists();
    }
    
    /**
     * 
     * @return valitun tiedoston
     */

    public String getValittu() {
        return valittu;
    }

}
