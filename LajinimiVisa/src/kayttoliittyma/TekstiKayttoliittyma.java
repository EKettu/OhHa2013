/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.io.File;
import java.util.Scanner;
import nimivisa.NimiVisa;
import nimivisa.NimienHallinta;
import tiedostonKasittely.TiedostonLukija;
import tiedostonKasittely.TiedostonValinta;

public class TekstiKayttoliittyma {

    private Scanner lukija;
    private NimiVisa visa;
    private NimienHallinta hallinta;
    private TiedostonValinta valinta;
    private String valittuTiedosto;
    private String valittuVaihtoehto;
    private boolean onkoLopetusValittu;
    

    public TekstiKayttoliittyma(TiedostonValinta valinta) {
        this.valinta = valinta;

        lukija = new Scanner(System.in);
        onkoLopetusValittu = false;

    }

    public void kaynnista() {
        tulostaAloitus();
        tiedostonValinta();
        if (onkoLopetusValittu==true) {
            System.out.println("Lopetus");
          //  valinta.valinnanSelvitys("x");
        }
        else {
        System.out.println("Visa alkaa!" + "\n");
        uusiVisaKayntiin();
        jatketaankoVisaa();
        }

    }

    public void uusiVisaKayntiin() {
        hallinta = new NimienHallinta(valinta.getValittu());
        visa = new NimiVisa(hallinta);

        visa.kaynnistaVisa();
        System.out.println(hallinta.getKysyttavanLajinSuomiNimi());
        System.out.println("");
        System.out.println(hallinta.getKaikkiVaihtoehdot());
        System.out.println("");
        kysyValinta();


    }

    public void tulostaAloitus() {
        System.out.println("Valitse eliöryhmä");
        System.out.println("Eläimet: e");
        System.out.println("Kasvit: k");
        System.out.println("Sienet: s");
        System.out.println("Lopetus: x");
    }

    public void tiedostonValinta() {
        String valittu = lukija.nextLine();

        if (valittu.equals("e")) {
            valinta.valinnanSelvitys("e");
        } else if (valittu.equals("k")) {
            valinta.valinnanSelvitys("k");
        } else if (valittu.equals("s")) {
            valinta.valinnanSelvitys("s");
        } else if (valittu.equals("x")) {
           onkoLopetusValittu = true;
        }
        else {
            System.out.println("Virheellinen syöte! Anna oikea kirjain.");
            tiedostonValinta();
        }

    }

    public void kysyValinta() {
        System.out.println("Kirjoita oikean vaihtoehdon kirjain");
        valittuVaihtoehto = lukija.nextLine();

        if (valittuVaihtoehto.matches("A|a|B|b|C|c|D|d")) {
            if (visa.valittiinkoOikeaVaihtoehto(valittuVaihtoehto)) {
                System.out.println("Oikein!");
                System.out.println("");
            } else {
                System.out.println("Väärin! Oikea vaihtoehto on: " + hallinta.getKysyttavanLajinLattari());
                System.out.println("");
            }
        } else {
            System.out.println("Virheellinen kirjainvalinta!");
            kysyValinta();
        }
    }

    public void jatketaankoVisaa() {
        System.out.println("Haluatko jatkaa? k/e");
        String jatkuuko = lukija.nextLine();

 
        if (jatkuuko.equalsIgnoreCase("k")) {
            uusiVisaKayntiin();
            jatketaankoVisaa();
        } else if (jatkuuko.equalsIgnoreCase("e")) {
            System.out.println("Näkemiin!");
        
        } else {
            System.out.println("Virheellinen syöte!");
            jatketaankoVisaa();
        }
    }
}
