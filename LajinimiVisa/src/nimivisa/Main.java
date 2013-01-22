package nimivisa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import kayttoliittyma.TekstiKayttoliittyma;
import tiedostonKasittely.TiedostonLukija;
import tiedostonKasittely.TiedostonValinta;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        
      //s
       // NimiVisa visa = new NimiVisa();
        TiedostonValinta valinta = new TiedostonValinta();
        
        TekstiKayttoliittyma liittyma = new TekstiKayttoliittyma(valinta);
        liittyma.kaynnista();

//        Scanner skanneri = new Scanner(System.in);
//
//        TekstiKayttoliittyma kayttis = new TekstiKayttoliittyma(skanneri);
//        kayttis.kaynnista();
//
//        TiedostonLukija lukija = new TiedostonLukija(new File(kayttis.getValittu()));
//ss
//        lukija.jaaRivitOsiin();

 //     System.out.println(lukija.getRiviLkm());

    }
}
