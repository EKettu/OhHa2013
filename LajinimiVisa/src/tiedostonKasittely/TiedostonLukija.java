package tiedostonKasittely;

import elio.Elio;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TiedostonLukija {

    private File nimiTiedosto;
    private int rivimaara;    
    private Elio elio;    
    private Map<Integer, Elio> elioidenNimet;
    private List<Elio> tiedostonEliot;
    private List<String> suomiNimet;
    private ArrayList<String> latinaNimet;
    private boolean onkoTiedostonMuotoOikea;
    
    

    public TiedostonLukija(File nimiTiedosto) {
        this.nimiTiedosto = nimiTiedosto;        
        elio = new Elio ("", "");       
        elioidenNimet = new HashMap<Integer, Elio>();
        tiedostonEliot = new ArrayList<Elio>();
        suomiNimet = new ArrayList<String>();
        latinaNimet = new ArrayList<String>();
        rivimaara = 0;
        
    }

    /**
     * Lukee tiedoston, pilkkoo sen rivin String-taulukoksi ja tarkistaa, onko saadun taulukon muoto oikea.
     * Jos taulukon (ja sitä kautta tiedoston) muoto on oikein, asetetaan parametrin onkoTiedostonMuotoOikein arvoksi 
     * true ja lisätään String-taulukosta saadut merkkijonot Elio-olennollle nimiksi sekä eri listoille
     * @return onkoTiedostonMuotoOikea (true, jos muoto on oikein, false jos ei ole)
     */
    public boolean jaaRivitOsiin() {
        Scanner tiedostonLukija = null;
        try {
            tiedostonLukija = new Scanner(nimiTiedosto);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TiedostonLukija.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (tiedostonLukija.hasNextLine()) {
           
            String rivi = tiedostonLukija.nextLine();
            String[] osat = rivi.split(":"); 
            
            if (osat.length==2) {
            onkoTiedostonMuotoOikea = true;
            elio = new Elio(osat[0], osat[1]);
            tiedostonEliot.add(elio);
            elioidenNimet.put(rivimaara, elio);
            suomiNimet.add(osat[0]);
            latinaNimet.add(osat[1]);
            rivimaara++;
            }
            
            else  {
                onkoTiedostonMuotoOikea = false;
            }         
        }
        
        return onkoTiedostonMuotoOikea;

    }
    
    public List<Elio> getTiedostonEliot() {
        return tiedostonEliot;
    }
    
    public Map<Integer, Elio> getElioidenNimet() {
        return elioidenNimet;
    }

    public List<String> getSuomiNimet() {
        return suomiNimet;
    }

    public List<String> getLatinaNimet() {
        return (List<String>) latinaNimet.clone();
    }

    public int getRiviLkm() {
        return rivimaara;
    }
    
}



















