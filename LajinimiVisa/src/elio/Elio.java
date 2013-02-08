/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elio;

/**
 * Tehdään Eliö, jolla on suomi- ja latinanimi, sekä selvitetään luodun Eliön
 * latinanimen ensimmäinen sekä neljä ensimmäistä kiorjainta
 * @author ekettu
 */

public class Elio {

    private String suominimi;
    private String lattari;
    
    /**
     * Luodaan Eliö jolla on sekä suomenkielinen että latinankielinen nimi
     * @param suominimi merkkijono, saadaan konstruktorin parametrina kun luodaan Elio-olio
     * @param lattari merkkijono, saadaan konstruktorin parametrina kun luodaan Elio-olio
     */

    public Elio(String suominimi, String lattari) {
        this.suominimi = suominimi;
        this.lattari = lattari;
    }
    
    public String getSuominimi() {
        return suominimi;
    }
  
    public String getLattari() {
        return lattari;
    }

    /**
     * Selvittää latinankielisen nimen ensimmäisen kirjaimen
     * @return latinankielisen nimen alkukirjain
     */
    public String getLattarinEkaKirjain() {
        String ekaKirjain = "" + lattari.charAt(0);
        return ekaKirjain;
    }
    
    /**
     * Luo merkkijonon, jossa on eliön latinankielisen nimen neljä ensimmäistä kirjainta
     * @return palauttaa nelikirjaimisen merkkijonon
     */

    public String getLattarinNeljaEkaaKirjainta() {
        String lattarinAlkuosa = "";
        for (int i = 0; i < 4; i++) {
            lattarinAlkuosa = lattarinAlkuosa + lattari.charAt(i);
        }
        return lattarinAlkuosa;
    }
}
