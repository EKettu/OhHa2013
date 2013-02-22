/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import nimivisa.NimiVisa;
import nimivisa.NimienHallinta;

/**
 * Luo ohjelman ulkoasun sekä valikot ja painikkeet, joiden avulla ohjelma
 * kommunikoi käyttäjän kanssa
 *
 * @author ekettu
 */
public class Kayttoliittyma implements Runnable {

    /**
     * Kehys ohjelman nappuloille yms.
     */
    private JFrame frame;
    /**
     * Visan kaynnistyksestä huolehtiva olio
     */
    private NimiVisa visa;
    /**
     * Huolehtii eliöiden nimistä;
     */
    private NimienHallinta hallinta;
    /**
     * Valittava tiedostonimi
     */
    private String valinta;
    /**
     * Kertoo, mitä tehdä jos mitäkin nappia klikattu
     */
    private TapahtumanKuuntelija kuuntelija;
    /**
     * Kertoo, onko käyttäjältä saatu vastaus oikein vai väärin
     */
    private boolean vastausOikein;
    /**
     * Montako lajia kysytty yhteensä
     */
    private int vastausmaara;
    /**
     * Vastauksen oikeellisuuden tai vääryyden graafinen esitys
     */
    private JLabel vastaus;
    /**
     * Vastausten määrän graafinen esitys
     */
    private JLabel vastausTilasto;
    /**
     * eliön suominimi, jolle pitäisi löytää oikea lattari
     */
    private JLabel kysyttavaLaji;
    /**
     * Tähän tulee tekstiä, jos on käyty kaikki tiedoston eliöt läpi
     */
    private JLabel vikalajiteksti;
    /**
     * NimienHallinnasta saadun ensimmäisen lattarivaihtoehdon nappi
     */
    private JButton vaihtoehtoA;
    /**
     * NimienHallinnasta saadun toisen lattarivaihtoehdon nappi
     */
    private JButton vaihtoehtoB;
    /**
     * NimienHallinnasta saadun kolmannen lattarivaihtoehdon nappi
     */
    private JButton vaihtoehtoC;
    /**
     * NimienHallinnasta saadun neljännen lattarivaihtoehdon nappi
     */
    private JButton vaihtoehtoD;
    /**
     * Kuinka monta prosenttia vastauksista meni oikein.
     */
    private double onnistumisprosentti;

    @Override
    public void run() {
        listaaKansionTekstitiedostot();
        valinta = (String) JOptionPane.showInputDialog(null, "Valitse tiedosto", "LajiNimiVisa", JOptionPane.PLAIN_MESSAGE, null, listaaKansionTekstitiedostot(), listaaKansionTekstitiedostot()[0]);
        if (valinta == null) {
            JOptionPane.showMessageDialog(null, "Et valinnut tiedostoa, lopetetaan.");
            System.exit(0);
        }


        hallinta = new NimienHallinta(valinta);

        if (hallinta.onkoTiedostoLuettavissa()) {
            vastausmaara = 0;
            onnistumisprosentti = 0;
            visa = new NimiVisa(hallinta);
            visa.arvoLajit();

            frame = new JFrame("LajinimiVisa");
            frame.setPreferredSize(new Dimension(400, 700));
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            luoKomponentit(frame.getContentPane());

            paivita();

            frame.pack();
            frame.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Tiedosto ei ole luettavissa");
            System.exit(0);
        }



    }

    /**
     * Tekee listan niistä .txt tai .TXT -pääteisistä tiedostoista, jotka
     * sijaitsevat samassa kansiossa kuin ohjelmatiedosto. Lopuksi muuttaa
     * listan Object-taulukoksi
     *
     * @return Object-taulukoksi muutettu lista ohjelman kansiosta löytyvistä
     * tekstitiedostoista
     */
    public Object[] listaaKansionTekstitiedostot() {

        String tiedostopolku = ".";
        ArrayList<String> tiedostoNimet = new ArrayList();
        File kansio = new File(tiedostopolku);
        File[] tiedostolista = kansio.listFiles();

        for (int i = 0; i < tiedostolista.length; i++) {
            if (tiedostolista[i].getName().endsWith(".txt") || tiedostolista[i].getName().endsWith(".TXT")) {
                tiedostoNimet.add(tiedostolista[i].getName());
            }
        }
        if (tiedostoNimet.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ohjelman kansiossa ei valitettavasti ole sopivia tiedostoja.");
            System.exit(0);
        }
        Object[] tiedostoVaihtoehdot = tiedostoNimet.toArray();

        return tiedostoVaihtoehdot;
    }

    /**
     * Luo graafiset komponentit, eli nappulat ja tekstikentät
     *
     * @param container "säiliö", jonne erilaiset nappulat ja tekstialueet
     * sijoitetaan
     */
    private void luoKomponentit(Container container) {
        JLabel tyhjaTila = new JLabel("\n");
        kysyttavaLaji = new JLabel(hallinta.getKysyttavanLajinSuomiNimi());
        JLabel tyhjaTila2 = new JLabel("\n");
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);

        container.setLayout(layout);
        container.add(tyhjaTila);
        container.add(kysyttavaLaji);
        container.add(tyhjaTila2);

        JPanel paneeli = new JPanel(new GridLayout(9, 1));
        vaihtoehtoA = new JButton(hallinta.getVaihtoehtoA());
        vaihtoehtoB = new JButton(hallinta.getVaihtoehtoB());
        vaihtoehtoC = new JButton(hallinta.getVaihtoehtoC());
        vaihtoehtoD = new JButton(hallinta.getVaihtoehtoD());

        JButton lopetus = new JButton("Lopetus");
        kuuntelija = new TapahtumanKuuntelija(this, vaihtoehtoA, vaihtoehtoB, vaihtoehtoC, vaihtoehtoD, lopetus);

        vaihtoehtoA.addActionListener(kuuntelija);
        vaihtoehtoB.addActionListener(kuuntelija);
        vaihtoehtoC.addActionListener(kuuntelija);
        vaihtoehtoD.addActionListener(kuuntelija);
        lopetus.addActionListener(kuuntelija);

        vastaus = new JLabel(vastausTeksti());
        vastausTilasto = new JLabel(vastausMaaraTeksti());
        vikalajiteksti = new JLabel("");

        paneeli.add(vaihtoehtoA);
        paneeli.add(vaihtoehtoB);
        paneeli.add(vaihtoehtoC);
        paneeli.add(vaihtoehtoD);
        paneeli.add(lopetus);
        paneeli.add(vastaus);
        paneeli.add(vastausTilasto);
        paneeli.add(vikalajiteksti);

        container.add(paneeli);


    }

    /**
     * Selvittää NimiVisa-luokan kautta onko kysytty laji tiedoston viimeinen ja
     * onko valittu vaihtoehto oikea, kasvattaa vastausmäärää, sekä päivittää
     * vastaus- ja vastausTilasto-olioiden tekstit
     *
     * @param vaihtoehto käyttäjän valitsema vaihtoehto
     * @see visa.onkoKysyttyLajiTiedostonViimeinen()
     * @see visa.valittiinkoOikeaVaihtoehtoGraafinen(vaihtoehto)
     */
    public void vastauksenSelvitys(String vaihtoehto) {
        if (visa.onkoKysyttyLajiTiedostonViimeinen()) {
            vastausOikein = visa.valittiinkoOikeaVaihtoehtoGraafinen(vaihtoehto);
            vastausmaara++;
            vastaus.setText(vastausTeksti());
            vastausTilasto.setText(vastausMaaraTeksti());
            vikalajiteksti.setText("Valitsemassasi tiedostossa ei ole enempää kysyttäviä eliöitä.");
            paivitaJosLajiVika();
            ohjelmanLopetus();
        } else {
            vastausOikein = visa.valittiinkoOikeaVaihtoehtoGraafinen(vaihtoehto);
            vastausmaara++;
            vastaus.setText(vastausTeksti());
            vastausTilasto.setText(vastausMaaraTeksti());
            paivita();
        }
    }

    /**
     * Palauttaa käyttäjän nähtäväksi tiedon siitä, menikö vastaus oikein vai
     * ei.
     *
     * @return vastausTeksti, jonka muoto riippuu siitä, vastasiko käyttäjä
     * oikein vai väärin
     */
    public String vastausTeksti() {
        String vastausTeksti = "";

        if (vastausmaara == 0) {
            return vastausTeksti;
        }

        if (vastausOikein) {
            vastausTeksti = "     Oikein!";
        } else {
            vastausTeksti = "     Väärin! Oikea vastaus on: " + hallinta.getKysyttavanLajinLattari();
        }


        return vastausTeksti;
    }

    /**
     * Palauttaa sanallisen esityksen siitä, kuinka monta lajia on kysytty ja
     * kuinka monta mennyt oikein
     *
     * @return merkkijono, joka kertoo montako monestako lajista on oikein
     */
    public String vastausMaaraTeksti() {

        return "     Oikeita vastauksia: " + visa.getOikeidenVastaustenLkm() + "/" + vastausmaara;

    }

    /**
     * Kertoo onnistumisprosentin ja lopettaa ohjelman suorituksen, mikäli
     * käyttäjä on vastannut kysymyksiin. Jos käyttäjä painaa suoraan
     * lopetusnappia vastaamatta yhteenkään kysymykseen, ohjelma sulkeutuu.
     */
    public void ohjelmanLopetus() {
        if (vastausmaara != 0) {
            onnistumisprosentti = Math.ceil(((1.0 * visa.getOikeidenVastaustenLkm()) / (1.0 * vastausmaara)) * 100.0);

            JOptionPane.showMessageDialog(null, "Sait oikein " + onnistumisprosentti + " prosenttia vastauksista." + "\n" + annaTulosTeksti());
            System.exit(0);
        } else {
            System.exit(0);
        }
    }

    /**
     * Palauttaa tulostekstin, joka vaihtelee sen mukaan, miten hyvin käyttäjä
     * pärjäsi visassa
     */
    public String annaTulosTeksti() {
        String tulosteksti = "";

        if (onnistumisprosentti <= 10.0) {
            tulosteksti += "Tosi surkeaa...";
        } else if (onnistumisprosentti > 10.0 && onnistumisprosentti <= 25.0) {
            tulosteksti += "Melko säälittävää...";
        } else if (onnistumisprosentti >= 25.0 && onnistumisprosentti <= 50.0) {
            tulosteksti += "Ei mennyt ihan nappiin.";
        } else if (onnistumisprosentti >= 50.0 && onnistumisprosentti <= 65.0) {
            tulosteksti += "Ihan ok.";
        } else if (onnistumisprosentti >= 66.0 && onnistumisprosentti <= 85.0) {
            tulosteksti += "Hyvin meni!";
        } else if (onnistumisprosentti >= 85.0 && onnistumisprosentti < 100.0) {
            tulosteksti += "Loistavaa!";
        } else if (onnistumisprosentti == 100.0) {
            tulosteksti += "Täydellistä!";
        }

        return tulosteksti;
    }

    private double getOnnistumisprosentti() {
        return onnistumisprosentti;
    }

    /**
     * Tyhjentää lajilistat, arpoo uudet lajit, sekä kirjoittaa uuden kysyttävän
     * lajin ja neljä latinankielistä vaihtoehtoa
     *
     * @see hallinta.tyhjennaLajiListat();
     * @see visa.arvoLajit();
     */
    public void paivita() {
        hallinta.tyhjennaLajiListat();
        visa.arvoLajit();
        kysyttavaLaji.setText(hallinta.getKysyttavanLajinSuomiNimi());
        vaihtoehtoA.setText(hallinta.getVaihtoehtoA());
        vaihtoehtoB.setText(hallinta.getVaihtoehtoB());
        vaihtoehtoC.setText(hallinta.getVaihtoehtoC());
        vaihtoehtoD.setText(hallinta.getVaihtoehtoD());

    }

    /**
     * Muuttaa kysytyn lajinimen kohdalle lopetusilmoituksen ja tyhjentää
     * vaihtoehto-nappuloiden tekstit
     */
    public void paivitaJosLajiVika() {
        kysyttavaLaji.setText("Visa loppui!");
        vaihtoehtoA.setText("");
        vaihtoehtoB.setText("");
        vaihtoehtoC.setText("");
        vaihtoehtoD.setText("");
    }
}
