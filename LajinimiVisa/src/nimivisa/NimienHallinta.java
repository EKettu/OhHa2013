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
    private List<Elio> kysyttavatEliot;
    private int kysyttyjenLajienMaara;
    
/**
 * 
 * @param tiedostonNimi 
 */
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
        kysyttavatEliot = lukija.getTiedostonEliot();
       sekoitaKysyttavatLajitLista();
        kysyttyjenLajienMaara=0;

    }

    public void kaynnistaNimienArvonta() {
     hommaaKysyttavaLaji();
        System.out.println(kysyttava.getSuominimi());
    //   arvoKysyttavaLaji();
        lisataanSamanSukuisetOmaanListaan(getKysyttavaLaji());
        lisataanSamallaKirjaimellaAlkavatSuvutListaan(getKysyttavaLaji());
        arvoMuutKolmeLajia();
        arvoLattarienJarjestys();
       // vaihtoehdot = new ArrayList<String>();
      //  System.out.println(vaihtoehdot);

    }

    public void sekoitaKysyttavatLajitLista() {
        Collections.shuffle(kysyttavatEliot);
        
    }
    
    public void hommaaKysyttavaLaji() {
      
        kysyttava = kysyttavatEliot.get(kysyttyjenLajienMaara);
        kysyttyjenLajienMaara++;
    }
    
    public int getKysyttyjenLajienMaara() {
        return kysyttyjenLajienMaara;
    }
            
            
//    public void arvoKysyttavaLaji() {
//        int arvottava = arvonta.arvoLukuValilta(1, lajienNimet.size());
//        if (!kysytytNimet.contains(kysyttava.getSuominimi())) {
//            kysyttava = lajienNimet.get(arvottava);
//        }
//        kysytytNimet.add(kysyttava.getSuominimi());
//       System.out.println(kysytytNimet);
//    }
//
//    public List<String> getKysytytNimetLista() {
//        return kysytytNimet;
//    }

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
        kaikkiLattarit.remove(kysyttavaElio.getLattari());                  //Tähän pitäs varmaan tehdä jotain, että ei poistettaisi sitä kysyttyä lattaria miltään listalta
        for (String samaSuku : kaikkiLattarit) {                            //Muuten näet jo kysytyt lajit eivät ainakaan päädy vaihtoehdot-listalle
            if (samaSuku.startsWith(kysyttavaElio.getLattarinNeljaEkaaKirjainta())) {
                samanSukuiset.add(samaSuku);
            }
        }
   //     kaikkiLattarit = lukija.getLatinaNimet();
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
      //  kaikkiLattarit = lukija.getLatinaNimet();
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
//    public boolean onkoSamallaKirjaimellaAlkaviaSukuja() {
//        List<String> kaikkiLattarit = lukija.getLatinaNimet();
//        kaikkiLattarit.remove(kysyttava.getLattari());
//
//        for (String lattari : kaikkiLattarit) {
//            if (!lattari.startsWith(kysyttava.getLattarinEkaKirjain())) {
//                return false;
//            }
//        }
//        kaikkiLattarit = lukija.getLatinaNimet();
//        return true;
//    }

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
        System.out.println(vaihtoehdot);
    }
    
    
    public void tyhjennaLajiListat() {
        samanSukuiset = new ArrayList<String>();
        samallaKirjaimellaAlkavat = new ArrayList<String>();
        arvottujenLajienLattarit = new ArrayList<String>();
        vaihtoehdot= new ArrayList<String>();
        
        
       
    }
//    public void tyhjennaVaihtoehdot() {
//        vaihtoehdot= new ArrayList<String>();
//    }
//    
//    public void tyhjennaArvottujenLajienLattarit() {
//        arvottujenLajienLattarit = new ArrayList<String>();
//    }
//    
//    public void tyhjennaSamanSukuiset() {
//        samanSukuiset = new ArrayList<String>();
//    }
//    
//    public void tyhjennaSamallaKirjaimellaAlkavat() {
//        samallaKirjaimellaAlkavat = new ArrayList<String>();
//    }

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
