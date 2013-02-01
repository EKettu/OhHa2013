/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nimivisa;

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
    public void metodiValittiinkoOikeaVaihtoehtoPalauttaaFalseJosVaaraKirjainSyote() { //heittää vällillä NullPointerExceptionin!!!!!
        visa.arvoLajit();
        String valinta = "Ö";
        assertEquals(false, visa.valittiinkoOikeaVaihtoehto(valinta));

    }

    @Test
    public void metodiValittiinkoOikeaVaihtoehtoPalauttaaTrueJosOikeaVaihtoehtoValittu() { //meneen välillä läpi ja välillä ei, syynä random?
        visa.arvoLajit();
        String valinta = "A";
        String aLajiNimi = "Pla";
        String kysyttyLaji = "Pla";

       if (valinta.equals("A") && aLajiNimi.equals(kysyttyLaji)) {
            assertEquals(true, visa.valittiinkoOikeaVaihtoehto(valinta));
       }

    }
}
