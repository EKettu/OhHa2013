package nimivisa;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import elio.Elio;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import nimivisa.NimienHallinta;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import tiedostonKasittely.TiedostonLukija;

public class NimienHallintaTest {

    private NimienHallinta hallinta;
    private TiedostonLukija lukija;

    public NimienHallintaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        hallinta = new NimienHallinta("testiSienet.txt");
        File tiedosto = new File("testiSienet.txt");
        lukija = new TiedostonLukija(tiedosto);
        
    }

    @After
    public void tearDown() {
    }


    @Test
    public void arvotaanTasanKolmeMuutaLajia() {
        hallinta.arvoMuutKolmeLajia();
        assertEquals(3, hallinta.getLajienLattaritListanPituus());

    }

    @Test
    public void lajienLattaritListaanLisataanKysyttyLajiJaPituusOikein() {
        hallinta.arvoMuutKolmeLajia();
        hallinta.arvoLattarienJarjestys();
        assertEquals(4, hallinta.getLajienLattaritListanPituus());


    }

    @Test
    public void kaikkiNeljaVaihtoehtoaListanPituusOikein() {
        hallinta.arvoMuutKolmeLajia();
        hallinta.arvoLattarienJarjestys();
        assertEquals(4, hallinta.getVaihtoehdotListanPituus());
    }

    @Test
    public void lajienLattaritListaPysyyErillisenaVaihtoehdotListasta() {
        List<String> lajienLattarit = hallinta.getLajienLattarit();
        hallinta.arvoMuutKolmeLajia();
        hallinta.arvoLattarienJarjestys();


        assertEquals(lajienLattarit, hallinta.getLajienLattarit());
    }

    @Test
    public void vaihtoehdotListallaLajitEriJarjestyksessaKuinLajienLattaritListalla() {
        List<String> lajienLattarit = hallinta.getLajienLattarit();
        hallinta.arvoMuutKolmeLajia();
        hallinta.arvoLattarienJarjestys();
        List<String> vaihtoehdot = hallinta.getVaihtoehdot();

        assertThat(lajienLattarit, not(vaihtoehdot));
    }

    @Test
    public void kysyttavaLajiEiOleKolmenMuunLajinListalla() {
        List<String> lajienLattarit = hallinta.getLajienLattarit();
        hallinta.arvoMuutKolmeLajia();

        String kysytynLattari = hallinta.getKysyttavaLaji().getLattari();

        assertFalse(lajienLattarit.contains(kysytynLattari));

    }

    @Test
    public void kysyttavaLajiOnVaihtoehdotListalla() {
        List<String> vaihtoehdot = hallinta.getVaihtoehdot();
        hallinta.arvoMuutKolmeLajia();
        hallinta.arvoLattarienJarjestys();

        String kysytynLattari = hallinta.getKysyttavaLaji().getLattari();

        assertTrue(vaihtoehdot.contains(kysytynLattari));

    }
    
        @Test
    public void lisataanSamallaKirjaimellaAlkavatSuvutListaanToimiiJosEiSukunsaAinoa() {
            lukija.jaaRivitOsiin();
            List<String> samallaKirjaimellaAlkavat = new ArrayList<String>();
            Elio kysyttyLaji = new Elio ("seitikki sp", "Cortinarius mucosus");
        List <String> kaikkiLattarit = lukija.getLatinaNimet();
        kaikkiLattarit.remove(kysyttyLaji.getLattari());
        for (String samaKirjain : kaikkiLattarit) {
            if (samaKirjain.startsWith(kysyttyLaji.getLattarinEkaKirjain())) {
                samallaKirjaimellaAlkavat.add(samaKirjain);
            }
        }
        
        assertEquals(8, samallaKirjaimellaAlkavat.size());
    }
        
                @Test
    public void eiLisataSamallaKirjaimellaAlkavienSukujenListaanJosSukunsaAinoa() {
            lukija.jaaRivitOsiin();
            List<String> samallaKirjaimellaAlkavat = new ArrayList<String>();
            Elio kysyttyLaji = new Elio ("isorusokas", "Entoloma sinuatum");
        List <String> kaikkiLattarit = lukija.getLatinaNimet();
        kaikkiLattarit.remove(kysyttyLaji.getLattari());
        for (String samaKirjain : kaikkiLattarit) {
            if (samaKirjain.startsWith(kysyttyLaji.getLattarinEkaKirjain())) {
                samallaKirjaimellaAlkavat.add(samaKirjain);
            }
        }        
        assertEquals(0, samallaKirjaimellaAlkavat.size());
    }
       
        
                        @Test
    public void lisataanSamaaSukuaOlevatListaanToimiiJosEiSukunsaAinoa() {
            lukija.jaaRivitOsiin();
            List<String> samanSukuiset = new ArrayList<String>();
            Elio kysyttyLaji = new Elio ("seitikki sp", "Cortinarius mucosus");
        List <String> kaikkiLattarit = lukija.getLatinaNimet();
        kaikkiLattarit.remove(kysyttyLaji.getLattari());
        for (String samaaSukua : kaikkiLattarit) {
            if (samaaSukua.startsWith(kysyttyLaji.getLattarinNeljaEkaaKirjainta())) {
                samanSukuiset.add(samaaSukua);
            }
        }       
        assertEquals(4, samanSukuiset.size());
    }
                        
                                
                        @Test
    public void eiLisataSamaaSukuaOleviaListaanJosSukunsaAinoa() {
            lukija.jaaRivitOsiin();
            List<String> samanSukuiset = new ArrayList<String>();
            Elio kysyttyLaji = new Elio ("isorusokas", "Entoloma sinuatum");
        List <String> kaikkiLattarit = lukija.getLatinaNimet();
        kaikkiLattarit.remove(kysyttyLaji.getLattari());
        for (String samaaSukua : kaikkiLattarit) {
            if (samaaSukua.startsWith(kysyttyLaji.getLattarinNeljaEkaaKirjainta())) {
                samanSukuiset.add(samaaSukua);
            }
        }       
        assertEquals(0, samanSukuiset.size());
    }

}
