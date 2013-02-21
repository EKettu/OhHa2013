/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nimivisa;

import elio.Elio;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NimiVisaTest {

    private NimiVisa visa;
    private NimienHallinta hallinta;

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
        hallinta = new NimienHallinta("testiSienet.txt");
        visa = new NimiVisa(hallinta);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void metodiValittiinkoOikeaVaihtoehtoPalauttaaFalseJosVaaraKirjainSyote() { 
        visa.arvoLajit();
        String valinta = "Ö";
        assertEquals(false, visa.valittiinkoOikeaVaihtoehto(valinta));

    }

//    @Test
//    public void metodiValittiinkoOikeaVaihtoehtoPalauttaaTrueJosOikeaVaihtoehtoValittu() { 
//       visa.arvoLajit();
//        String valinta = "A";
//        String aLajiNimi = "Pla";
//        String kysyttyLaji = "Pla";
//
//       if (valinta.equals("A") && aLajiNimi.equals(kysyttyLaji)) {
//            assertEquals(true, visa.valittiinkoOikeaVaihtoehto(valinta)); //menee välillä läpi ja välillä ei...
//       }
//
//    }
    
    @Test
    public void metodiOnkoKysyttyLajiTiedostonViimeinenPalauttaaTrueJosOn() {
        
        Elio kysytty = hallinta.getKysyttavaLaji();  
        if (kysytty.equals(hallinta.getKysyttavatEliotLista().get(hallinta.getKysyttavatEliotLista().size()-1))) {   
        assertEquals(true, visa.onkoKysyttyLajiTiedostonViimeinen());
        }
        
    }


    @Test
    public void metodiOnkoKysyttyLajiTiedostonViimeinenPalauttaaFalseJosEi() {
        
        Elio kysytty = hallinta.getKysyttavaLaji();  
        if (!kysytty.equals(hallinta.getKysyttavatEliotLista().get(hallinta.getKysyttavatEliotLista().size()-1))) {       
        assertEquals(false, visa.onkoKysyttyLajiTiedostonViimeinen());
        }
        
    }
    
    
        @Test
    public void metodiValittiinkoOikeaVaihtoehtoGraafinenPalauttaaTrueOikein() {
        
       String vaihtoehto = "Cortinarius mucosus";
       
       if (vaihtoehto.equals(hallinta.getKysyttavanLajinLattari())) {
           assertEquals(true, visa.valittiinkoOikeaVaihtoehto(vaihtoehto));
       }
        
    }
        
                @Test
    public void metodiValittiinkoOikeaVaihtoehtoGraafinenPalauttaaFalseOikein() {
        
       String vaihtoehto = "Cortinarius mucosus";
       
       if (!vaihtoehto.equals(hallinta.getKysyttavanLajinLattari())) {
           assertEquals(false, visa.valittiinkoOikeaVaihtoehto(vaihtoehto));
       }
        
    }
    
 
}


