/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostonKasittely;

import java.io.File;
import nimivisa.NimienHallinta;

public class TiedostonValinta {

    private String valittu;

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

    public String getValittu() {
        return valittu;
    }
//    public String getSieniTiedosto() {
//        return "sienet.txt";
//    }
//
//    public String getElainTiedosto() {
//        return "linnut.txt";
//    }
//
//    public String getKasviTiedosto() {
//        return "kasvit.txt";
//    }
}
