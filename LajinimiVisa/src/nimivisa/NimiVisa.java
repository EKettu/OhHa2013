/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nimivisa;

import elio.Elio;
import tiedostonKasittely.TiedostonValinta;

public class NimiVisa {

    private TiedostonValinta valinta;
    private NimienHallinta hallinta;
    Elio kysytty;

    public NimiVisa(NimienHallinta hallinta) {
        valinta = new TiedostonValinta();
        this.hallinta = hallinta;


    }

    public void kaynnistaVisa() {
        hallinta.kaynnistaNimienArvonta();
        kysytty = hallinta.getKysyttavaLaji();
       

    }

    public boolean valittiinkoOikeaVaihtoehto(String vaihtoehto) {

        if (vaihtoehto.equalsIgnoreCase("A") && kysytty.getLattari().equals(hallinta.getVaihtoehtoA())) {

            return true;
        } else if (vaihtoehto.equalsIgnoreCase("B") && kysytty.getLattari().equals(hallinta.getVaihtoehtoB())) {

            return true;
        } else if (vaihtoehto.equalsIgnoreCase("C") && kysytty.getLattari().equals(hallinta.getVaihtoehtoC())) {

            return true;
        } else if (vaihtoehto.equalsIgnoreCase("D") && kysytty.getLattari().equals(hallinta.getVaihtoehtoD())) {

            return true;
        } else {
            return false;
        }

    }
}
