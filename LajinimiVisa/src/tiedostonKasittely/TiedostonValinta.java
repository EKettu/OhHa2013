/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostonKasittely;

import nimivisa.NimienHallinta;

public class TiedostonValinta {

    private String valittu;
    private boolean lopetustaEiValittu;

    public TiedostonValinta() {
        valittu = "";
        lopetustaEiValittu = true;
    }

    public void valinnanSelvitys(String valinta) {

        if (!valinta.equalsIgnoreCase("x")) {

            if (valinta.equals("e")) {
                valittu = getElainTiedosto();
            } else if (valinta.equals("k")) {
                valittu = getKasviTiedosto();

            } else if (valinta.equals("s")) {
                valittu = getSieniTiedosto();
            }
        } else {
           lopetustaEiValittu = false;        
        }

    }
    
    public boolean getLopetustaEiValittu() {
        return lopetustaEiValittu;
    }

    public String getValittu() {
        return valittu;
    }

    public String getSieniTiedosto() {
        return "sienet.txt";
    }

    public String getElainTiedosto() {
        return "linnut.txt";
    }

    public String getKasviTiedosto() {
        return "kasvit.txt";
    }
    
    public String getLopetus() {
        return "Lopetus";
    }
}
