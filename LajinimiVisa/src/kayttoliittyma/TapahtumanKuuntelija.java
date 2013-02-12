/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import nimivisa.NimiVisa;
import nimivisa.NimienHallinta;

/**
 * Määrittää, mitä tapahtuu kun käyttäjä klikkailee eri nappuloita.
 *
 * @author ekettu
 */
public class TapahtumanKuuntelija implements ActionListener {

    private NimienHallinta hallinta;
    private String valittuTiedosto;
    private JLabel kysyttyLajinimi;
    private JButton vaihtoehtoA;
    private JButton vaihtoehtoB;
    private JButton vaihtoehtoC;
    private JButton vaihtoehtoD;
    private JButton lopetus;
    private Kayttoliittyma kayttis;
    
    /**
     * Alustaa tarvittavat parametrit
     * @param kayttis graafinen käyttöliittymä
     * @param hallinta NimienHallinta-olio, jota tämä luokka ja Kayttoliittyma käyttävä
     * @param valittuTiedosto tiedosto, josta lajeja kysellään
     * @param vaihtoehtoA hallinnasta saatavan ensimmäisen vaihtoehdon nappi
     * @param vaihtoehtoB hallinnasta saatavan toisen vaihtoehdon nappi
     * @param vaihtoehtoC hallinnasta saatavan kolmannen vaihtoehdon nappi
     * @param vaihtoehtoD hallinnasta saatavan neljännen vaihtoehdon nappi
     * @param lopetus ohjelman lopetusnappi
     */

    TapahtumanKuuntelija(Kayttoliittyma kayttis, NimienHallinta hallinta, String valittuTiedosto, JButton vaihtoehtoA, JButton vaihtoehtoB, JButton vaihtoehtoC, JButton vaihtoehtoD, JButton lopetus) {
        this.kayttis = kayttis;
        this.valittuTiedosto = valittuTiedosto;
        this.vaihtoehtoA = vaihtoehtoA;
        this.vaihtoehtoB = vaihtoehtoB;
        this.vaihtoehtoC = vaihtoehtoC;
        this.vaihtoehtoD = vaihtoehtoD;
        this.lopetus = lopetus;

        this.hallinta = hallinta;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == lopetus) {
            kayttis.ohjelmanLopetus();
        } else if (ae.getSource() == vaihtoehtoA) {
            kayttis.vastauksenSelvitys(vaihtoehtoA.getText());
        } else if (ae.getSource() == vaihtoehtoB) {
            kayttis.vastauksenSelvitys(vaihtoehtoB.getText());
        } else if (ae.getSource() == vaihtoehtoC) {
            kayttis.vastauksenSelvitys(vaihtoehtoC.getText());
        } else if (ae.getSource() == vaihtoehtoD) {
            kayttis.vastauksenSelvitys(vaihtoehtoD.getText());
        }


    }


}
