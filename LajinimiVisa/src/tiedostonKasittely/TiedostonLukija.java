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
 //   String [] osat;
    
    private Elio elio;
    
    private Map<Integer, Elio> elioidenNimet;
    
    private Map<String, String> nimetSuomiLatina;
    private Map<String, String> nimetLatinaSuomi;
    private List<String> suomiNimet;
    private List<String> latinaNimet;

    public TiedostonLukija(File nimiTiedosto) {
        this.nimiTiedosto = nimiTiedosto;
        
        elio = new Elio ("", "");
        
        elioidenNimet = new HashMap<Integer, Elio>();
        
        nimetSuomiLatina = new HashMap<String, String>();
        nimetLatinaSuomi = new HashMap<String, String>();
        suomiNimet = new ArrayList<String>();
        latinaNimet = new ArrayList<String>();
        rivimaara = 0;
    }

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
            
            elio = new Elio(osat[0], osat[1]);
            elioidenNimet.put(rivimaara, elio);
            
            nimetSuomiLatina.put(osat[0], osat[1]);
            nimetLatinaSuomi.put(osat[1], osat[0]);
            
            suomiNimet.add(osat[0]);
            latinaNimet.add(osat[1]);
            rivimaara++;
            
        }

    }
    
//    public String[] getTiedostonOsat() {
//        return osat;
//        
//    }
    
    public Map<Integer, Elio> getElioidenNimet() {
        return elioidenNimet;
    }

    public Map<String, String> getNimetSuomiLatina() {
        return nimetSuomiLatina;

    }

    public Map<String, String> getNimetLatinaSuomi() {
        return nimetLatinaSuomi;

    }

    public List<String> getSuomiNimet() {
        return suomiNimet;
    }

    public List<String> getLatinaNimet() {
        return latinaNimet;
    }

    public int getRiviLkm() {
        return rivimaara;
    }
    
}



















