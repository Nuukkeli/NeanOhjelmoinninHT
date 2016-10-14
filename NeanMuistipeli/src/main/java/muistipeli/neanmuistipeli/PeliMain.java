package muistipeli.neanmuistipeli;

import muistipeli.neanmuistipeli.kortti.*;
import muistipeli.neanmuistipeli.peli.*;

/**
 * Main luokka, joka käynnistää pelin.
 */
public class PeliMain {

    /**
     * Main luokka. Käynnistää pelin luomalla kysymysalustan. 
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Kysymysalusta peli = new Kysymysalusta(false);
        peli.aloita();
    }

}
