/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nimivisa;

import elio.Elio;
import tiedostonKasittely.TiedostonValinta;

/**
 * Kutsuu NimienHallintaa ja käynnistää nimien arvonnan, tarkistaa onko käyttäjältä saatu vastaus
 * oikea sekä pitää lukua oikeista vastauksista. Tarkistaa myös, onko visa käynnissä. 
 * @author elinakettunen
 */

public class NimiVisa {

  
    private NimienHallinta hallinta;
    Elio kysytty;
    private boolean totta;
    private int oikeitaVastauksia;

    /**
     * Alustaa oliomuuttujat.
     * @param hallinta käyttöliittymässä saatava NimienHallinta-olio
     */
    public NimiVisa(NimienHallinta hallinta) {

        this.hallinta = hallinta;
        totta = true;
        oikeitaVastauksia = 0;


    }

    /*'
     * Käynnistää NimienHallinnassa tapahtuvan kysytyn ja vaihtoehtoisten lajien arvonnan
     * ja selvittää kysytyn lajin.
     */
    public void arvoLajit() {
        hallinta.kaynnistaNimienArvonta();
        kysytty = hallinta.getKysyttavaLaji();

    //    System.out.println(kysytty.getSuominimi());

    }
    
  /**
   * Selvittää, onko käyttäjän valitsema vaihtoehto oikea ja kasvattaa oikeiden vastausten lukumäärää,
   * jos vastaus on oikea.
   * @param vaihtoehto parametri, joka saadaa käyttäjältä
   * @return true, jos on valittu tietty kirjain ja kyseisen kirjaimen takana on lajin oikea latinankielinen
   * nimi, false, jos ei ole oikea vaihtoehto
   */

    public boolean valittiinkoOikeaVaihtoehto(String vaihtoehto) {

        if (vaihtoehto.equalsIgnoreCase("A") && kysytty.getLattari().equals(hallinta.getVaihtoehtoA())) {
            oikeitaVastauksia++;
            return true;
        } else if (vaihtoehto.equalsIgnoreCase("B") && kysytty.getLattari().equals(hallinta.getVaihtoehtoB())) {
            oikeitaVastauksia++;
            return true;
        } else if (vaihtoehto.equalsIgnoreCase("C") && kysytty.getLattari().equals(hallinta.getVaihtoehtoC())) {
            oikeitaVastauksia++;
            return true;
        } else if (vaihtoehto.equalsIgnoreCase("D") && kysytty.getLattari().equals(hallinta.getVaihtoehtoD())) {
            oikeitaVastauksia++;
            return true;
        } else {
            return false;
        }

    }
    
    public int getOikeidenVastaustenLkm() {
        return oikeitaVastauksia;
    }

    /**
     * Tarkistaa, onko NimiVisa käynnissä
     * @param totta parametri, joka kertoo visan olevan käynnissä
     * @return true, jos totta on true eli visa on käynnissä, false, jos ei ole
     */
    public boolean onkoVisaKaynnissa(boolean totta) {
        this.totta = totta;
        if (totta) {
            return true;
        }
        return false;
    }
}
