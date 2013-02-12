/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;
import nimivisa.NimiVisa;
import nimivisa.NimienHallinta;
import tiedostonKasittely.TiedostonLukija;
import tiedostonKasittely.TiedostonValinta;

/**
 * Käyttöliittymä, josta ohjelmaa käytetään, kysyy halutun tiedoston
 * käyttäjältä, tarkistaa että se on kunnossa, kysyy mikä vaihtoehdoista
 * valitaan ja jatketaanko visaa.
 *
 * @author ekettu
 */
public class TekstiKayttoliittyma {

    private Scanner lukija;
    private NimiVisa visa;
    private NimienHallinta hallinta;
    private TiedostonValinta valinta;
    private String valittuTiedosto;
    private String valittuVaihtoehto;
    private boolean onkoLopetusValittu;
    private boolean jatketaankoVisaa;

    /**
     * Luo uuden lukija-skannerin, alustaa boolean-muuttujat ja
     * Tiedostonvalinta-olion.
     *
     * @param valinta konstruktorin parametrina saama TiedostonValinta-olio
     */
    public TekstiKayttoliittyma(TiedostonValinta valinta) {
        this.valinta = valinta;
        jatketaankoVisaa = true;
        lukija = new Scanner(System.in);
        onkoLopetusValittu = false;
    }

    public void kaynnista() {


        tulostaAloitus();
        tiedostonValinta();

        if (onkoLopetusValittu == true) {
            System.out.println("Lopetus");

        } else {
            hallinta = new NimienHallinta(valinta.getValittu());

         if (hallinta.onkoTiedostoLuettavissa()) {

                visa = new NimiVisa(hallinta);
                System.out.println("Visa alkaa!" + "\n");
                while (jatketaankoVisaa) {
                   
                    visa.arvoLajit();
                    uusiVisaKayntiin();
                    jatketaankoVisaa();
                    
                    hallinta.tyhjennaLajiListat();
                }
            } else {
                System.out.println("Tiedosto ei oikeassa muodossa.");
            }
        }

    }

    public void uusiVisaKayntiin() {
        
        System.out.println(hallinta.getKysyttavanLajinSuomiNimi());
        System.out.println("");
        System.out.println(hallinta.getKaikkiVaihtoehdot());
        System.out.println("");
        kysyOikeanLajinValinta();

    }

    public void tulostaAloitus() {
        System.out.println("Kirjoita haluamasi tiedoston nimi. X lopettaa");

    }

    public void tiedostonValinta() {
        String valittu = lukija.nextLine();


        if (valittu.equalsIgnoreCase("X")) {
            onkoLopetusValittu = true;

        } else {
            if (!valinta.valinnanSelvitys(valittu)) {
                System.out.println("Virheellinen syöte! Anna oikea tiedostonimi.");
                tiedostonValinta();
            }
        }
    }

    public void kysyOikeanLajinValinta() {
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
            kysyOikeanLajinValinta();
        }
    }

    public void jatketaankoVisaa() {
        System.out.println("Haluatko jatkaa? k/e");
        String jatkuuko = lukija.nextLine();

        if (jatkuuko.equalsIgnoreCase("k")) {
            jatketaankoVisaa = true;
       //     visa.onkoVisaKaynnissa(jatketaankoVisaa);

        } else if (jatkuuko.equalsIgnoreCase("e")) {
            jatketaankoVisaa = false;
            System.out.println("Näkemiin!");
            System.out.println("Oikeita vastauksia: " + visa.getOikeidenVastaustenLkm() + "/" + hallinta.getKysyttyjenLajienMaara()); //+hallinta.getKysytytNimetLista().size()

        } else {
            System.out.println("Virheellinen syöte!");
            System.out.println("Haluatko jatkaa? k/e");
            jatkuuko = lukija.nextLine();
        }
    }
}
