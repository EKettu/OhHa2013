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
    private boolean totta;
    private int oikeitaVastauksia;

    public NimiVisa(NimienHallinta hallinta) {
        valinta = new TiedostonValinta();
        this.hallinta = hallinta;
        totta = true;
        oikeitaVastauksia = 0;


    }

    public void arvoLajit() {
//      while(true) {
        //       if (onkoVisaKaynnissa(totta)) {
        hallinta.kaynnistaNimienArvonta();
        kysytty = hallinta.getKysyttavaLaji();

        System.out.println(kysytty.getSuominimi());
//      }
//          else {
//              break;
//          }
//    }
    }

    public boolean valittiinkoOikeaVaihtoehto(String vaihtoehto) {

        if (vaihtoehto.equalsIgnoreCase("A") && kysytty.getLattari().equals(hallinta.getVaihtoehtoA())) {
            oikeitaVastauksia++;
            return true;
        } else if (vaihtoehto.equalsIgnoreCase("B") && kysytty.getLattari().equals(hallinta.getVaihtoehtoB())) {
            oikeitaVastauksia++;
            return true;
        } else if (vaihtoehto.equalsIgnoreCase("C") && kysytty.getLattari().equals(hallinta.getVaihtoehtoC())) {
            oikeitaVastauksia++;
            return true;
        } else if (vaihtoehto.equalsIgnoreCase("D") && kysytty.getLattari().equals(hallinta.getVaihtoehtoD())) {
            oikeitaVastauksia++;
            return true;
        } else {
            return false;
        }

    }
    
    public int getOikeidenVastaustenLkm() {
        return oikeitaVastauksia;
    }

    public boolean onkoVisaKaynnissa(boolean totta) {
        this.totta = totta;
        if (totta) {
            return true;
        }
        return false;
    }
}
