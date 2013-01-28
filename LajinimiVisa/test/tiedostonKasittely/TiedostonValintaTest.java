/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostonKasittely;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class TiedostonValintaTest {

    private TiedostonValinta valinta;

    public TiedostonValintaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        valinta = new TiedostonValinta();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void josTiedostoaEiLoydyAntaaOikeanMerkkijonon() {
        String valittu = "x";
        valinta.valinnanSelvitys(valittu);
        assertEquals("Tiedostoa ei löydy", valinta.getValittu());
    }

    @Test
    public void josTiedostoLoytyyAntaaOikeanTiedoston() {
        String valittu = "s";
        valinta.valinnanSelvitys(valittu);
        assertEquals("sienet.txt", valinta.getValittu());
    }
}
