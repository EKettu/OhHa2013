/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elio;

public class Elio {

    private String suominimi;
    private String lattari;

    public Elio(String suominimi, String lattari) {
        this.suominimi = suominimi;
        this.lattari = lattari;
    }
    
    /**
     * 
     * @return eliön suomenkielinen nimi
     */

    public String getSuominimi() {
        return suominimi;
    }

    /**
     * 
     * @return eliön latinankielinen nimi
     */
    
    public String getLattari() {
        return lattari;
    }

    public String getLattarinEkaKirjain() {
        String ekaKirjain = "" + lattari.charAt(0);
        return ekaKirjain;
    }

    public String getLattarinNeljaEkaaKirjainta() {
        String lattarinAlkuosa = "";
        for (int i = 0; i < 4; i++) {
            lattarinAlkuosa = lattarinAlkuosa + lattari.charAt(i);
        }
        return lattarinAlkuosa;
    }
}
