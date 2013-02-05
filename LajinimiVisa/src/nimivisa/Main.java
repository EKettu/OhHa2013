package nimivisa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import kayttoliittyma.Kayttoliittyma;
import kayttoliittyma.TekstiKayttoliittyma;
import tiedostonKasittely.TiedostonLukija;
import tiedostonKasittely.TiedostonValinta;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        TiedostonValinta valinta = new TiedostonValinta();
        
        TekstiKayttoliittyma liittyma = new TekstiKayttoliittyma(valinta);
        liittyma.kaynnista();
//        
//        Kayttoliittyma kayttis = new Kayttoliittyma();
//        kayttis.run();

    }
}
