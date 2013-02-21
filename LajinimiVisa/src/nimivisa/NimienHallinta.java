package nimivisa;

import elio.Elio;
import java.io.File;
import tiedostonKasittely.TiedostonLukija;
import java.util.*;
import kayttoliittyma.TekstiKayttoliittyma;
import tiedostonKasittely.TiedostonValinta;

/**
 * Hallinnoi TiedostonLukijalta saatavia nimilistoja, palauttaa kysyttävän lajin ja kolme vaihtoehtoa
 *
 * @author ekettu
 */
public class NimienHallinta {
    
    /**
     * Lukee tiedoston ja tekee siitä listoja, jos se on oikeassa muodossa
     */
    private TiedostonLukija lukija;
    
    /**
     * Eliö, jonka suomenkielinen nimi tulee käyttäjän nähtäväksi 
     */
    private Elio kysyttava;   
    
    /**
     * Sisältää aluksi kolmen muun arvottavan "väärän" lajin latinakieliset nimet
     */
    private List<String> arvottujenLajienLattarit;
    
    /**
     * Sisältää kysyttävän lajin ja kolmen muun lajin latinankieliset nimet satunnaisessa järjestyksessä
     */
    private List<String> vaihtoehdot;
    
    /**
     * Sisältää kaikkien tiedoston sisältämien lajien latinankieliset nimet
     */
    private List<String> kaikkiLajienLattarit;
    
    /**
     * Sisältää kysyttävän eliön kanssa samansukuiset lajit, jos niitä on
     */
    private List<String> samanSukuiset;
    
    /**
     * Sisältää kysyttävän eliön kanssa samalla kirjaimella alkavat lajit, jos niitä on
     */
    private List<String> samallaKirjaimellaAlkavat;
    
    /**
     * Arpoo kokonaisluvun halutulta väliltä
     */
    private Arpoja arvonta;
    
    /**
     * Lista, josta kysyttävä eliö poimitaan, alkaen ensimmäisestä
     */
    private List<Elio> kysyttavatEliot;
    
    /**
     * Kysyttyjen lajien lukumäärä
     */
    private int kysyttyjenLajienMaara;
    
    /**
     * Kertoo, onko tiedosto luettavissa
     */
    private boolean onkoTiedostoOikea;

    /**
     * Alustaa luokassa tarvittavat listat ja luo uudet Elion, Arpojan sekä
     * TiedostonLukijan, joka saa parametrinaan NimienHallinta-konstruktorista
     * saadun tiedostonNimi-nimisen uuden Tiedoston.
     *
     * @param tiedostonNimi Tiedosto, jonka sisältämiä nimiä halutaan
     * käsiteltävän
     * 
     * 
     * @see lukija.jaaRivitOsiin();
     */
    public NimienHallinta(String tiedostonNimi) {

        kysyttava = new Elio("", "");
        arvonta = new Arpoja();
        lukija = new TiedostonLukija(new File(tiedostonNimi));
        lukija.jaaRivitOsiin();
        kaikkiLajienLattarit = lukija.getLatinaNimet();
        samanSukuiset = new ArrayList<String>();
        arvottujenLajienLattarit = new ArrayList<String>();
        samallaKirjaimellaAlkavat = new ArrayList<String>();
        vaihtoehdot = new ArrayList<String>();
        kysyttavatEliot = lukija.getTiedostonEliot();
        sekoitaKysyttavatEliotLista();
        kysyttyjenLajienMaara = 0;
        
        onkoTiedostoOikea = true;

    }

    /**
     * Metodi, joka käynnistää nimien arvonnan kutsumalla muita luokan metodeja
     */
    public void kaynnistaNimienArvonta() {
        
        hommaaKysyttavaLaji();
        lisataanSamanSukuisetOmaanListaan(getKysyttavaLaji());
        lisataanSamallaKirjaimellaAlkavatSuvutListaan(getKysyttavaLaji());
        arvoMuutKolmeLajia();
        arvoLattarienJarjestys();

    }

    public boolean onkoTiedostoLuettavissa() {

        if (lukija.getOnkoTiedostonMuotoOikea()) {
            onkoTiedostoOikea = true;
        } else {
            onkoTiedostoOikea = false;
        }
        return onkoTiedostoOikea;
    }

    /**
     * Sekoittaa kysyttävien lajien listan
     */
    public void sekoitaKysyttavatEliotLista() {
        Collections.shuffle(kysyttavatEliot);

    }

    public List<Elio> getKysyttavatEliotLista() {
        return kysyttavatEliot;
    }

    /**
     * Määrittää, mikä on kysyttävä laji (aluksi sekoitetun listan ensimmäinen)
     * ja huolehtii kysyttyjen lajien määrän kasvusta joka kierroksella
     */
    public void hommaaKysyttavaLaji() {

        kysyttava = kysyttavatEliot.get(kysyttyjenLajienMaara);
        kysyttyjenLajienMaara++;
    }

    public int getKysyttyjenLajienMaara() {
        return kysyttyjenLajienMaara;
    }

    public Elio getKysyttavaLaji() {
        return kysyttava;
    }

    public String getKysyttavanLajinSuomiNimi() {
        return kysyttava.getSuominimi();
    }

    public String getKysyttavanLajinLattari() {
        return kysyttava.getLattari();
    }

    /**
     * Lisää samanSukuiset-listalle kysyttävän lajin kanssa samaa sukua olevien
     * eliöiden lattarit, jos niitä on
     *
     * @param kysyttavaElio parametri, jonka metodi saa kun sitä kutsutaan
     * kaynnistaNimienArvonta-metodissa
     *
     */
    public void lisataanSamanSukuisetOmaanListaan(Elio kysyttavaElio) {
        List<String> kaikkiLattarit = lukija.getLatinaNimet();
        kaikkiLattarit.remove(kysyttavaElio.getLattari());
        for (String samaSuku : kaikkiLattarit) {
            if (samaSuku.startsWith(kysyttavaElio.getLattarinNeljaEkaaKirjainta()) && !samanSukuiset.contains(samaSuku)) {
                samanSukuiset.add(samaSuku);
            }
        }

    }

    public List<String> getSamanSukuiset() {
        return samanSukuiset;
    }

    /**
     * Tarkistaa, onko samaa sukua olevia eliötä listalla samanSukuiset
     * vähintään kolme
     *
     * @return true, jos on vähintään kolme, false, jos vähemmän
     */
    public boolean onkoSamanSukuisiaVahintaanKolme() {

        if (samanSukuiset.size() >= 3) {
            return true;
        }
        return false;
    }

    /**
     * Lisää samallaKirjaimellaAlkavat listalle kysytyn lajin latinankielisen
     * nimen kanssa samalla alkukirjaimella alkavia lajeja, jos niitä on
     *
     * @param kysyttavaElio parametri, jonka metodi saa kun sitä kutsutaan
     * kaynnistaNimienArvonta-metodissa
     */
    public void lisataanSamallaKirjaimellaAlkavatSuvutListaan(Elio kysyttavaElio) {
        List<String> kaikkiLattarit = lukija.getLatinaNimet();
        kaikkiLattarit.remove(kysyttavaElio.getLattari());
        for (String samaKirjain : kaikkiLattarit) {
            if (samaKirjain.startsWith(kysyttavaElio.getLattarinEkaKirjain()) && !samallaKirjaimellaAlkavat.contains(samaKirjain)) {
                samallaKirjaimellaAlkavat.add(samaKirjain);
            }
        }

    }

    public List<String> getSamallaKirjaimellaAlkavat() {
        return samallaKirjaimellaAlkavat;
    }

    /**
     * Tarkistaa, onko kysyttävn lajin lattarin kanssa samalla kirjaimella
     * alkavia lajinimiä vähintään kolme
     *
     * @return true, jos on kolme tai enemmän, false jos ei
     */
    public boolean onkoSamallaKirjaimellaAlkaviaVahintaanKolme() {
        if (samallaKirjaimellaAlkavat.size() >= 3) {
            return true;
        }

        return false;
    }

    /**
     * Selvittää, mitä listaa käytetään sen mukaan, onko kysytyllä lajilla
     * samansukuisia tai samalla kirjaimella alkavia lajeja
     *
     * @return samanSukuiset, jos samansukuisia lajeja on vähintää kolme,
     * samallaKirjaimellaAlkavat, jos niitä on vähintään kolme tai jos ei ole
     * kumpaakaan, niin palautetaan kaikkien lajien latinankieliset nimet
     * sisältävä lista
     */
    public List<String> getListaJostaLattaritArvotaan() {
        if (onkoSamanSukuisiaVahintaanKolme()) {
            return samanSukuiset;
        } else if (onkoSamallaKirjaimellaAlkaviaVahintaanKolme()) {
            return samallaKirjaimellaAlkavat;
        } else {
            return kaikkiLajienLattarit;
        }
    }

    /**
     * Arpoo kolme muuta lajia, joiden lattarit käyttäjä tulee näkymään, se
     * mistä listasta lajit arvotaan, riippuu
     * getListaJostaLattaritArvotaan-metodin tuloksesta
     * arvottujenLajienLattarit-lista ei saa kasvaa yli kolmen mittaiseksi eikä
     * se saa sisältää kysyttävää lajia tai jotain toista lajia kahteen kertaan
     * Lopuksi lisätään kolme saatua lajia arvottujenLajienLattarit-listalle
     * 
     * @see arvonta.arvoLukuValilta(int x, int y)
     *
     */
    public void arvoMuutKolmeLajia() {
       
        while (arvottujenLajienLattarit.size() < 3) {
            int arvottava = arvonta.arvoLukuValilta(0, getListaJostaLattaritArvotaan().size());
            
            //   int arvottava = arpoja.nextInt(getListaJostaLattaritArvotaan().size());
            String arvottuLattari = getListaJostaLattaritArvotaan().get(arvottava);

            if (arvottuLattari.equals(this.kysyttava.getLattari()) || arvottujenLajienLattarit.contains(arvottuLattari)) {
                continue;
            } else {
                arvottujenLajienLattarit.add(arvottuLattari);
            }
        }

    }

    public int getLajienLattaritListanPituus() {
        return arvottujenLajienLattarit.size();
    }

    /**
     * Lisää kysytyn lajin arvottujenLajienLattarit listalle, jonka jälkeen
     * lisää kaikki neljä lajia vaihtoehdot-listalle. Lopuksi sekoitetaan
     * vaihtoehdot-lista, jolloin oikea laji päätyy satunnaiselle paikalle
     */
    public void arvoLattarienJarjestys() {
        arvottujenLajienLattarit.add(kysyttava.getLattari());

        int i = 0;
        for (String lattari : arvottujenLajienLattarit) {
            vaihtoehdot.add(lattari);
        }
        Collections.shuffle(vaihtoehdot);
    }

    /**
     * Luo uudelleen listat samanSukuiset, samallaKirjaimellaAlkavat,
     * arvottujenLajienLattarit sekä vaihtoehdot, jotteivat ne pääse kasvamaan
     * ylisuuriksi ja uusi kyselykierros toimii
     */
    public void tyhjennaLajiListat() {
        samanSukuiset = new ArrayList<String>();
        samallaKirjaimellaAlkavat = new ArrayList<String>();
        arvottujenLajienLattarit = new ArrayList<String>();
        vaihtoehdot = new ArrayList<String>();

    }

    public String getVaihtoehtoA() {
        return vaihtoehdot.get(0);
    }

    public String getVaihtoehtoB() {
        return vaihtoehdot.get(1);
    }

    public String getVaihtoehtoC() {
        return vaihtoehdot.get(2);
    }

    public String getVaihtoehtoD() {
        return vaihtoehdot.get(3);
    }

    public String getKaikkiVaihtoehdot() {
        return "A " + getVaihtoehtoA() + "\n" + "B " + getVaihtoehtoB() + "\n" + "C " + getVaihtoehtoC() + "\n" + "D " + getVaihtoehtoD();
    }

    public int getVaihtoehdotListanPituus() {
        return vaihtoehdot.size();
    }

    public List<String> getLajienLattarit() {
        return arvottujenLajienLattarit;
    }

    public List<String> getVaihtoehdot() {
        return vaihtoehdot;
    }
}
