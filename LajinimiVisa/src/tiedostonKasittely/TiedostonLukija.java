package tiedostonKasittely;

import elio.Elio;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Lukee annetun tiedoston, pilkkoo sen osiin ja lisää erilaisille listoille
 * tiedostosta saadut lajinimet.
 *
 * @author ekettu
 */
public class TiedostonLukija {

    /**
     * Tiedosto, jota luetaan
     */
    private File nimiTiedosto;
    /**
     * Elio, johon tiedostosta saadut nimet talletetaan
     */
    private Elio elio;
    
    /**
     * Sisältää kaikki tiedoston eliöt
     */
    private List<Elio> tiedostonEliot;
    
    /**
     * Sisältää tiedoston eliöiden suomenkieliset nimet
     */
    private List<String> suomiNimet;
    
    /**
     * Sisältää tiedoston eliöiden latinankieliset nimet
     */
    private ArrayList<String> latinaNimet;
    
    /**
     * Kertoo, onko tiedoston muoto oikea
     */
    private boolean onkoTiedostonMuotoOikea;

    /**
     * Alustaa tiedoston, Elio-olion sekä käytettävän Mapin sekä Listat.
     *
     * @param nimiTiedosto konstruktorin parametrina saatava tiedosto
     */
    public TiedostonLukija(File nimiTiedosto) {
        this.nimiTiedosto = nimiTiedosto;
        elio = new Elio("", "");
        tiedostonEliot = new ArrayList<Elio>();
        suomiNimet = new ArrayList<String>();
        latinaNimet = new ArrayList<String>();
        onkoTiedostonMuotoOikea = true;

    }

    /**
     * Lukee tiedoston, pilkkoo sen rivin String-taulukoksi ja tarkistaa, onko
     * saadun taulukon muoto oikea. Jos taulukon (ja sitä kautta tiedoston)
     * muoto on oikein, asetetaan parametrin onkoTiedostonMuotoOikein arvoksi
     * true ja lisätään String-taulukosta saadut merkkijonot Elio-olennollle
     * nimiksi sekä eri listoille
     *
     * @return onkoTiedostonMuotoOikea (true, jos muoto on oikein, false jos ei
     * ole)
     */
    public void jaaRivitOsiin() {
        Scanner tiedostonLukija = null;
        try {
            tiedostonLukija = new Scanner(nimiTiedosto);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TiedostonLukija.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (tiedostonLukija.hasNextLine()) {

            String rivi = tiedostonLukija.nextLine();
            String[] osat = rivi.split(":");
            
            if (osat.length != 2) {
                onkoTiedostonMuotoOikea = false;
                

            } else {
                onkoTiedostonMuotoOikea = true;
                elio = new Elio(osat[0], osat[1]);
                tiedostonEliot.add(elio);
                suomiNimet.add(osat[0]);
                latinaNimet.add(osat[1]);
                
            }
        }

 
    }
    
    public boolean getOnkoTiedostonMuotoOikea (){
        return onkoTiedostonMuotoOikea;
    }

    public List<Elio> getTiedostonEliot() {
        return tiedostonEliot;
    }


    public List<String> getSuomiNimet() {
        return suomiNimet;
    }

    public List<String> getLatinaNimet() {
        return (List<String>) latinaNimet.clone();
    }

}
