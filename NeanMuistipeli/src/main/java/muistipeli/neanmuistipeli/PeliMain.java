package muistipeli.neanmuistipeli;

import muistipeli.neanmuistipeli.peli.Peli;

public class PeliMain {

    /**
     * @param args the command line arguments
     */
    //Muistipeli toimii, mutta käyttöliittymää en ole vielä luonut. 
    //Itseäni vain helpottaa toteuttaa projekti ensin näin, jotta toiminta hahmottuu.
    public static void main(String[] args) {

        Peli peli = new Peli(2); //Korttien määrän voisi myös kysyä pelin alussa. 
        peli.pelaa();

    }

}
