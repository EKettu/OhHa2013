/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nimivisa;

import elio.Elio;
import tiedostonKasittely.TiedostonValinta;

/**
 * Kutsuu NimienHallintaa käynnistäen lajien arvonnan ja selvittää käyttäjän
 * vastauksen oikeellisuuden.
 *
 * @author ekettu
 */
public class NimiVisa {

    /**
     * Huolehtii kysyttävän lajin valinnasta ja vaihtoehdoista
     */
    private NimienHallinta hallinta;
    /**
     * Kysyttävä eliö, saadaan NimienHallinnalta
     */
    Elio kysytty;
    /**
     * Oikeiden vastauksien lukumäärä
     */
    private int oikeitaVastauksia;

    /**
     * Alustaa oliomuuttujat.
     *
     * @param hallinta käyttöliittymässä saatava NimienHallinta-olio
     */
    public NimiVisa(NimienHallinta hallinta) {

        this.hallinta = hallinta;
        oikeitaVastauksia = 0;


    }

    /**
     * Käynnistää NimienHallinnassa tapahtuvan kysytyn ja vaihtoehtoisten lajien arvonnan
     * ja selvittää kysytyn lajin.
     * @see hallinta.kaynnistaNimienArvonta();
     */
    public void arvoLajit() {

        hallinta.kaynnistaNimienArvonta();
        kysytty = hallinta.getKysyttavaLaji();

    }

    /**
     * Selvittää, onko käyttäjän tekstikäyttöliittymässä valitsema vaihtoehto
     * oikea ja kasvattaa oikeiden vastausten lukumäärää, jos vastaus on oikea.
     *
     * @param vaihtoehto parametri, joka saadaa käyttäjältä
     * @return true, jos on valittu tietty kirjain ja kyseisen kirjaimen takana
     * on lajin oikea latinankielinen nimi, false, jos ei ole oikea vaihtoehto
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

    /**
     * Selvittää, onko käyttäjän graafisessa käyttöliittymässä valitsema
     * vaihtoehto oikea ja kasvattaa oikeiden vastausten lukumäärää, jos vastaus
     * on oikea.
     *
     * @param vaihtoehto parametri, joka saadaa Kayttoliittyma-luokalta, eli
     * kayttajan valitsema vaihtoehto
     * @return true, jos valittu vaihtoehto vastaa kysyttävän lajin
     * latinankielistä nimeä, false jos ei vastaa
     */
    public boolean valittiinkoOikeaVaihtoehtoGraafinen(String vaihtoehto) {

        if (vaihtoehto.equals(hallinta.getKysyttavanLajinLattari())) {
            oikeitaVastauksia++;
            return true;
        } else {
            return false;
        }

    }
    
    /**
     * Tarkistetaan, onko tiedostosta otetussa nimilistassa enää kysyttäviä lajeja.
     * @return true, jos kysytty laji on KysyttavatEliotListan viimeinen, false jos ei ole
     */

    public boolean onkoKysyttyLajiTiedostonViimeinen() {
        if (kysytty == hallinta.getKysyttavatEliotLista().get(hallinta.getKysyttavatEliotLista().size()-1)) {
            return true;
        }
        return false;
    }

    public int getOikeidenVastaustenLkm() {
        return oikeitaVastauksia;
    }
}
