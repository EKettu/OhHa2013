package nimivisa;

import elio.Elio;
import java.io.File;
import tiedostonKasittely.TiedostonLukija;
import java.util.*;
import kayttoliittyma.TekstiKayttoliittyma;
import tiedostonKasittely.TiedostonValinta;

public class NimienHallinta {

    private TiedostonLukija lukija;
    private Elio kysyttava;
    private Map<Integer, Elio> lajienNimet;
    private List<String> arvottujenLajienLattarit;
    private List<String> vaihtoehdot;
    private List<String> kaikkiLajienLattarit;
    private List<String> samanSukuiset;
    private List<String> samallaKirjaimellaAlkavat;
    private List<String> kysytytNimet;
    private Arpoja arvonta;

    public NimienHallinta(String tiedostonNimi) {

        kysyttava = new Elio("", "");
        arvonta = new Arpoja();

        lukija = new TiedostonLukija(new File(tiedostonNimi));
        lukija.jaaRivitOsiin();
        lajienNimet = lukija.getElioidenNimet();
        kaikkiLajienLattarit = lukija.getLatinaNimet();

        samanSukuiset = new ArrayList<String>();
        arvottujenLajienLattarit = new ArrayList<String>();
        samallaKirjaimellaAlkavat = new ArrayList<String>();
        vaihtoehdot = new ArrayList<String>();
        kysytytNimet = new ArrayList<String>();

    }

    public void kaynnistaNimienArvonta() {

        arvoKysyttavaLaji();
        lisataanSamanSukuisetOmaanListaan(getKysyttavaLaji());
        lisataanSamallaKirjaimellaAlkavatSuvutListaan(getKysyttavaLaji());
        arvoMuutKolmeLajia();
        arvoLattarienJarjestys();

    }

    public void arvoKysyttavaLaji() {
        int arvottava = arvonta.arvoLukuValilta(1, lajienNimet.size());
        if (!kysytytNimet.contains(kysyttava.getSuominimi())) {
            kysyttava = lajienNimet.get(arvottava);
        }
        kysytytNimet.add(kysyttava.getSuominimi());
      //  System.out.println(kysytytNimet.size());
    }

    public List<String> getKysytytNimetLista() {
        return kysytytNimet;
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

    public void lisataanSamanSukuisetOmaanListaan(Elio kysyttavaElio) {
        List<String> kaikkiLattarit = lukija.getLatinaNimet();
        kaikkiLattarit.remove(kysyttavaElio.getLattari());
        for (String samaSuku : kaikkiLattarit) {
            if (samaSuku.startsWith(kysyttavaElio.getLattarinNeljaEkaaKirjainta())) {
                samanSukuiset.add(samaSuku);
            }
        }
    }

    public List<String> getSamanSukuiset() {
        return samanSukuiset;
    }

    public boolean onkoSamanSukuisiaVahintaanKolme() {

        if (samanSukuiset.size() >= 3) {
            return true;
        }
        return false;
    }

    public void lisataanSamallaKirjaimellaAlkavatSuvutListaan(Elio kysyttavaElio) {
        List<String> kaikkiLattarit = lukija.getLatinaNimet();
        kaikkiLattarit.remove(kysyttavaElio.getLattari());
        for (String samaKirjain : kaikkiLattarit) {
            if (samaKirjain.startsWith(kysyttavaElio.getLattarinEkaKirjain())) {
                samallaKirjaimellaAlkavat.add(samaKirjain);
            }
        }
    }

    public List<String> getSamallaKirjaimellaAlkavat() {
        return samallaKirjaimellaAlkavat;
    }

    public boolean onkoSamallaKirjaimellaAlkaviaVahintaanKolme() {
        if (samallaKirjaimellaAlkavat.size() >= 3) {
            return true;
        }

        return false;
    }

//    public boolean onkoSukunsaAinoa() {
//        List<String> kaikkiLattarit = lukija.getLatinaNimet();
//        kaikkiLattarit.remove(kysyttava.getLattari());
//
//        for (String lattari : kaikkiLattarit) {
//            if (lattari.startsWith(kysyttava.getLattarinNeljaEkaaKirjainta())) {
//                return false;
//            }
//        }
//        return true;
//    }
    public boolean onkoSamallaKirjaimellaAlkaviaSukuja() {
        List<String> kaikkiLattarit = lukija.getLatinaNimet();
        kaikkiLattarit.remove(kysyttava.getLattari());

        for (String lattari : kaikkiLattarit) {
            if (!lattari.startsWith(kysyttava.getLattarinEkaKirjain())) {
                return false;
            }
        }

        return true;
    }

    public List<String> getListaJostaLattaritArvotaan() {
        if (onkoSamanSukuisiaVahintaanKolme()) {
            return samanSukuiset;
        } else if (onkoSamallaKirjaimellaAlkaviaVahintaanKolme()) {
            return samallaKirjaimellaAlkavat;
        } else {
            return kaikkiLajienLattarit;
        }
    }

    public void arvoMuutKolmeLajia() {

        while (arvottujenLajienLattarit.size() < 3) {
            int arvottava = arvonta.arvoLukuValilta(0, getListaJostaLattaritArvotaan().size());
            //   int arvottava = arpoja.nextInt(getListaJostaLattaritArvotaan().size());
            String arvottuLattari = getListaJostaLattaritArvotaan().get(arvottava);

            if (arvottuLattari.equals(kysyttava.getLattari()) || arvottujenLajienLattarit.contains(arvottuLattari)) {
                continue;
            } else {
                arvottujenLajienLattarit.add(arvottuLattari);
            }
        }

    }

    public int getLajienLattaritListanPituus() {
        return arvottujenLajienLattarit.size();
    }

    public void arvoLattarienJarjestys() {
        arvottujenLajienLattarit.add(kysyttava.getLattari());

        int i = 0;
        for (String lattari : arvottujenLajienLattarit) {
            vaihtoehdot.add(lattari);
        }
        Collections.shuffle(vaihtoehdot);

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
