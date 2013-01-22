package nimivisa;

import elio.Elio;
import java.io.File;
import tiedostonKasittely.TiedostonLukija;
import java.util.*;
import kayttoliittyma.TekstiKayttoliittyma;
import tiedostonKasittely.TiedostonValinta;

public class NimiVisa {

    private TiedostonValinta valinta;
    private TiedostonLukija lukija;
    private Elio kysyttava;
    private Map<Integer, Elio> lajienNimet;
    private Random arpoja;
    private List<String> lajienLattarit;
    private List<String> vaihtoehdot;

    public NimiVisa(String tiedostonNimi) {

        kysyttava = new Elio("", "");
        arpoja = new Random();
        valinta = new TiedostonValinta();
        lukija = new TiedostonLukija(new File(tiedostonNimi));
        lukija.jaaRivitOsiin();
        lajienNimet = lukija.getElioidenNimet();
        lajienLattarit = new ArrayList<String>();
        vaihtoehdot = new ArrayList<String>();

    }

    public void kaynnistaVisa() {

        arvoKysyttavaLaji();
        arvoMuutKolmeLajia();
        arvoLattarienJarjestys();


    }

    public void arvoKysyttavaLaji() {
        int arvottava = arpoja.nextInt(lajienNimet.size());
        kysyttava = lajienNimet.get(arvottava);
    }

    public Elio getKysyttavaLaji() {
        return kysyttava;
    }

    public String getKysyttavanLajinNimi() {
        return kysyttava.getSuominimi();
    }

    public void arvoMuutKolmeLajia() {

        while (lajienLattarit.size() < 3) {
            int arvottava = arpoja.nextInt(lajienNimet.size());
            Elio elio = lajienNimet.get(arvottava);

            if (elio.getLattari().equals(kysyttava.getLattari()) || lajienLattarit.contains(elio.getLattari())) {
                continue;
            } else {
                lajienLattarit.add(elio.getLattari());
            }
        }
        //    System.out.println(lajienLattarit);
    }

    private boolean onkoLajinLattariJoListalla() {


        return true;
    }

    public int getLajienLattaritListanPituus() {
        return lajienLattarit.size();
    }

    public void arvoLattarienJarjestys() {
        lajienLattarit.add(kysyttava.getLattari());

        int i = 0;
        for (String lattari : lajienLattarit) {
            vaihtoehdot.add(lattari);
        }

        Collections.shuffle(vaihtoehdot);
        //    System.out.println(lajienLattarit);


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
        return lajienLattarit;
    }

    public List<String> getVaihtoehdot() {
        return vaihtoehdot;
    }
}
