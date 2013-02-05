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

        assertThat(lajienLattarit, not(vaihtoehdot)); //tässä pieni ongelma, välillä näet menee satunnaisesti sufflessa ihan samaan järjestykseen, mutta siis se kai ok?
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
    public void lisataanSamaaSukuaOlevatListaanToimiiJosEiSukunsaAinoa() {

        Elio kysyttyLaji = new Elio("seitikki sp", "Cortinarius mucosus");
        hallinta.lisataanSamanSukuisetOmaanListaan(kysyttyLaji);

        assertEquals(4, hallinta.getSamanSukuiset().size());
    }

    @Test
    public void eiLisataSamaaSukuaOleviaListaanJosSukunsaAinoa() {

        Elio kysyttyLaji = new Elio("isorusokas", "Entoloma sinuatum");
        hallinta.lisataanSamanSukuisetOmaanListaan(kysyttyLaji);

        assertEquals(0, hallinta.getSamanSukuiset().size());
    }

    @Test
    public void lisataanSamallaKirjaimellaAlkavatListaanToimiiJosEiSukunsaAinoa() {

        Elio kysyttyLaji = new Elio("seitikki sp", "Cortinarius mucosus");
        hallinta.lisataanSamallaKirjaimellaAlkavatSuvutListaan(kysyttyLaji);

        assertEquals(8, hallinta.getSamallaKirjaimellaAlkavat().size());
    }

    @Test
    public void eiLisataSamallaKirjaimellaAlkaviaListaanJosSukunsaAinoa() {

        Elio kysyttyLaji = new Elio("isorusokas", "Entoloma sinuatum");
        hallinta.lisataanSamallaKirjaimellaAlkavatSuvutListaan(kysyttyLaji);

        assertEquals(0, hallinta.getSamallaKirjaimellaAlkavat().size());
    }

    @Test
    public void onkoSamanSukuisiaVahintaanKolmePalauttaaTrueOikein() {
        Elio kysyttyLaji = new Elio("seitikki sp", "Cortinarius mucosus");
        hallinta.lisataanSamanSukuisetOmaanListaan(kysyttyLaji);

        assertEquals(true, hallinta.onkoSamanSukuisiaVahintaanKolme());
    }

    @Test
    public void onkoSamanSukuisiaVahintaanKolmePalauttaaFalseOikein() {
        Elio kysyttyLaji = new Elio("isorusokas", "Entoloma sinuatum");
        hallinta.lisataanSamanSukuisetOmaanListaan(kysyttyLaji);

        assertEquals(false, hallinta.onkoSamanSukuisiaVahintaanKolme());
    }

    @Test
    public void onkoSamallaKirjaimellaAlkaviaVahintaanKolmePalauttaaTrueOikein() {
        Elio kysyttyLaji = new Elio("seitikki sp", "Cortinarius mucosus");
        hallinta.lisataanSamallaKirjaimellaAlkavatSuvutListaan(kysyttyLaji);

        assertEquals(true, hallinta.onkoSamallaKirjaimellaAlkaviaVahintaanKolme());
    }
    
    
    
        @Test
    public void onkoSamallaKirjaimellaAlkaviaVahintaanKolmePalauttaaFalseOikein() {
        Elio kysyttyLaji = new Elio("isorusokas", "Entoloma sinuatum");
        hallinta.lisataanSamallaKirjaimellaAlkavatSuvutListaan(kysyttyLaji);
        assertEquals(false, hallinta.onkoSamallaKirjaimellaAlkaviaVahintaanKolme());
    }
        
                @Test
    public void tyhjennaListatMetodiToimiiVaihtoehdotTyhjenee() {
        hallinta.kaynnistaNimienArvonta();
        hallinta.tyhjennaLajiListat();
        
        assertEquals(0, hallinta.getVaihtoehdotListanPituus());
    }
        
//                              @Test
//    public void kysyttavatEliotListaSekoitetaanToimii() {
//        List<Elio> alkuperaisetEliotJarjestyksessa = hallinta.getKysyttavatEliotLista();
//        hallinta.sekoitaKysyttavatEliotLista();
//        
//        assertThat(hallinta.getKysyttavatEliotLista(), not(alkuperaisetEliotJarjestyksessa));
//    }
   
}

