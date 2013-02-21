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
 * Tekstipohjainen käyttöliittymä, joka huolehtii lajinimien kyselystä ja kommunikoi käyttäjän kanssa.
 *
 * @author ekettu
 */
public class TekstiKayttoliittyma {

    /**
     * Skanneri, jonka avulla saadaan käyttäjältä haluttu tiedosto ja valinta
     */
    private Scanner lukija;
    
    /**
     * Käynnistää NimienHallinnan ja kertoo käyttäjän arvauksen oikeellisuuden
     */
    private NimiVisa visa;
    
    /**
     * Huolehtii kysyttävän lajin valinnasta ja vaihtoehdoista
     */
    private NimienHallinta hallinta;
    
    /**
     * Tarkistaa, onko valittu tiedosto olemassa
     */
    private TiedostonValinta valinta;
    
    /**
     * Valitun tiedoston nimi
     */
    private String valittuTiedosto;
    
    /**
     * Käyttäjän arvaama lajinimi
     */
    private String valittuVaihtoehto;
    
    /**
     * Kertoo, onko käyttäjä päättänyt lopettaa ohjelman
     */
    private boolean onkoLopetusValittu;
    
    /**
     * Kertoo, jatkuuko visa
     */
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

    /**
     * Käynnistää ja ylläpitää visailua niin kauan kuin käyttäjä haluaa, mikäli 
     * tiedostoa valitessa ei ole valittu lopetusta tai tiedosto ei ole
     * luettavassa muodossa.
     * @see hallinta.onkoTiedostoLuettavissa();
     * @see visa.arvoLajit();
     * @see hallinta.tyhjennaLajiListat();
     */
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

    /**
     * Tulostaa kysyttävän lajin ja vaihtoehtoiset latinanimet
     * ja kutsuu valinnan kysymistä käyttäjältä.
     */
    public void uusiVisaKayntiin() {
        
        System.out.println(hallinta.getKysyttavanLajinSuomiNimi());
        System.out.println("");
        System.out.println(hallinta.getKaikkiVaihtoehdot());
        System.out.println("");
        kysyOikeanLajinValinta();

    }

    /**
     * Tulostaa ohjelman ensimmäisen rivin.
     */
    public void tulostaAloitus() {
        System.out.println("Kirjoita haluamasi tiedoston nimi. X lopettaa");

    }

    /**
     * Kysyy käyttäjältä halutun tiedoston tai lopetusmerkin,
     * selvittää onko tiedosto olemassa
     * @see valinta.valinnanSelvitys(valittu);
     * 
     */
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

    /**
     * Kysyy käyttäjältä, minkä vaihtoehdon tämä valitsee ja 
     * selvittää NimiVisan kautta, menikö vastaus oikein. Kysyy kirjainta niin kauan, 
     * kunnes se on joko a, b, c tai d, eli käyttäjä ei voi syöttää muuta kuin jonkun em. neljästä
     * kirjaimesta päästäkseen eteenpäin. 
     */
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

    /**
     * Vaihtoehdon valinnan ja oikean vastauksen selvityksen jälkeen kysyy, haluaako käyttäjä jatkaa
     * visaa, k-kirjain jatkaa, e-lopettaa. Jos valitaan lopetus, tulostaa tiedon kuinka monta vastausta meni
     * oikein. Jos yritetään syöttää joku muu kuin k tai e, kysyy uudestaan oikeaa syötettä. 
     */
    public void jatketaankoVisaa() {
        System.out.println("Haluatko jatkaa? k/e");
        String jatkuuko = lukija.nextLine();

        if (jatkuuko.equalsIgnoreCase("k")) {
            jatketaankoVisaa = true;

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
