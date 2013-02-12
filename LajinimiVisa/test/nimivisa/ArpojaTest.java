/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nimivisa;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author elinakettunen
 */
public class ArpojaTest {
    
    private Arpoja arpoja;
    
    public ArpojaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        arpoja = new Arpoja();
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void arvoLukuValiltaEiPalautaAlarajaaPienempaaLukua() {
                 
         assertNotSame(2, arpoja.arvoLukuValilta(4, 10));
     
     }
     
          @Test
     public void arvoLukuValiltaEiPalautaYlarajaaSuurempaaLukua() {
                 
         assertNotSame(11, arpoja.arvoLukuValilta(4, 10));
     
     }
           
}
