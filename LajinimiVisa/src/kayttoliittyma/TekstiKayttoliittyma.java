/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.io.File;
import java.util.Scanner;
import nimivisa.NimiVisa;
import tiedostonKasittely.TiedostonLukija;
import tiedostonKasittely.TiedostonValinta;

public class TekstiKayttoliittyma {

    Scanner lukija;
    NimiVisa visa;
    TiedostonValinta valinta;


    public TekstiKayttoliittyma(TiedostonValinta valinta) {       
        this.valinta = valinta;
                 
    }
    

    public void tulostaAloitus() {
        System.out.println("Valitse eliöryhmä");
        System.out.println("Eläimet: e");
        System.out.println("Kasvit: k");
        System.out.println("Sienet: s");
        System.out.println("Lopetus: x");
    }

    public void kaynnista() {
        tulostaAloitus();
        valinta.valinnanSelvitys();        
        visa = new NimiVisa(valinta.getValittu());      
        visa.kaynnistaVisa();
        System.out.println(visa.getKysyttavanLajinNimi());
        System.out.println(visa.getKaikkiVaihtoehdot());
        
              
    }


}
