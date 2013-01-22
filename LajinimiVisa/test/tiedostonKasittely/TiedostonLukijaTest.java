/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostonKasittely;

import elio.Elio;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TiedostonLukijaTest {
    
    TiedostonLukija lukija;
    Elio elio;
    File tiedosto;
  //  Scanner tiedostonLukija;
    
    public TiedostonLukijaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws FileNotFoundException {
        tiedosto = new File("sienet.txt");
     //   tiedostonLukija = new Scanner(tiedosto);
        lukija = new TiedostonLukija(tiedosto);
         elio = new Elio("", "");
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void tiedostonLukijaLuoElionJaSuomiNimiOikein() {
         
         lukija.jaaRivitOsiin();
         elio = lukija.getElioidenNimet().get(0);
         
     
         assertEquals(elio.getSuominimi(), lukija.getSuomiNimet().get(0));
     
     }
     
     
          @Test
     public void tiedostonLukijaLuoElionJaLattariOikein() {
         
         lukija.jaaRivitOsiin();
         elio = lukija.getElioidenNimet().get(0);  
         assertEquals(elio.getLattari(), lukija.getLatinaNimet().get(0));
     
     }
          
          
}
