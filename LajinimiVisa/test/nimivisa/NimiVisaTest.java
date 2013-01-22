package nimivisa;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import nimivisa.NimiVisa;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class NimiVisaTest {
    
   private NimiVisa visa;
    
    public NimiVisaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        visa = new NimiVisa("sienet.txt");
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void arvotaanTasanKolmeMuutaLajia() {
         visa.arvoMuutKolmeLajia();        
         assertEquals(3, visa.getLajienLattaritListanPituus());
                  
}
     
          @Test
     public void lajienLattaritListaanLisataanKysyttyLajiJaPituusOikein() {
         visa.arvoMuutKolmeLajia();
         visa.arvoLattarienJarjestys();
         assertEquals(4, visa.getLajienLattaritListanPituus());
         
         
}
          @Test
          public void kaikkiNeljaVaihtoehtoaListanPituusOikein() {
              visa.arvoMuutKolmeLajia();
              visa.arvoLattarienJarjestys();
              assertEquals(4, visa.getVaihtoehdotListanPituus());
          }
          
                    @Test
          public void lajienLattaritListaPysyyErillisenaVaihtoehdotListasta() {
              List<String> lajienLattarit = visa.getLajienLattarit();        
              visa.arvoMuutKolmeLajia();
              visa.arvoLattarienJarjestys();
             
              
              assertEquals(lajienLattarit, visa.getLajienLattarit());
          }
     
                                        @Test
         public void vaihtoehdotListallaLajitEriJarjestyksessaKuinLajienLattaritListalla() {
              List<String> lajienLattarit = visa.getLajienLattarit();        
              visa.arvoMuutKolmeLajia();
              visa.arvoLattarienJarjestys();
              List<String> vaihtoehdot = visa.getVaihtoehdot();
              
              assertThat(lajienLattarit, not(vaihtoehdot));
         }
                                        
                     @Test
         public void kysyttavaLajiEiOleKolmenMuunLajinListalla() {
              List<String> lajienLattarit = visa.getLajienLattarit();        
              visa.arvoMuutKolmeLajia();
              
              String kysytynLattari = visa.getKysyttavaLaji().getLattari();
             
              assertFalse(lajienLattarit.contains(kysytynLattari));
          
         }   
                     
                     @Test
         public void kysyttavaLajiOnVaihtoehdotListalla() {
              List<String> vaihtoehdot = visa.getVaihtoehdot();       
              visa.arvoMuutKolmeLajia();
              visa.arvoLattarienJarjestys();
              
              String kysytynLattari = visa.getKysyttavaLaji().getLattari();
             
              assertTrue(vaihtoehdot.contains(kysytynLattari));
          
         }  
                                        
     
}
