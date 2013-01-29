/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elio;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ElioTest {
    
    private Elio elio;
    
    public ElioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        elio = new Elio("suominimi", "latinanimi");
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void metodigetLattarinNeljaEkaaKirjaintaToimii() {
     
         assertEquals("lati", elio.getLattarinNeljaEkaaKirjainta());
     
     }
     
          @Test
     public void metodigetLattarinEkaKirjainToimii() {
     
         assertEquals("l", elio.getLattarinEkaKirjain());
     
     }
}
