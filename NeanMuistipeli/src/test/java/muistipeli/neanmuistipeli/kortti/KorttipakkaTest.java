/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.neanmuistipeli.kortti;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author euro
 */
public class KorttipakkaTest {

    Korttipakka pakka;

    public KorttipakkaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        pakka = new Korttipakka(4);
    }

    @After
    public void tearDown() {
    }

    //Kaikkia metodeja ei testattu. Listan testaaminen vähän hakusessa. 
    //Testeille on tehty kyllä otsikoita, mutta sisältö puuttuu.
    @Test
    public void korttipakkaanEiVoiTullaLiikaaKortteja() {
        Korttipakka kp = new Korttipakka(32);

        assertEquals(8, kp.parienMaara());
    }

    @Test
    public void korttiPakkaanEiVoiTullaLiianVahanKortteja() {
        Korttipakka kp = new Korttipakka(0);

        assertEquals(2, kp.parienMaara());
    }

    @Test
    public void kaantyvatkoKaikkiKortitPiiloonKunYhtakaanEiOleLoydetty() {
        boolean jokinKuvaNakyy = false;

        pakka.kortit().get(0).kuvaNakyviin();
        pakka.kortit.get(pakka.parienMaara()).kuvaNakyviin();
        pakka.kortit.get(pakka.parienMaara() - 1).kuvaNakyviin();

        pakka.kaannaKortit();

        for (Kortti k : pakka.kortit()) {
            if (k.nakyykoKuva()) {
                jokinKuvaNakyy = true;
                break;
            }
        }

        assertEquals(false, jokinKuvaNakyy);
    }

    @Test
    public void kaantyvatkoVainLoytamattomatKortitPiiloon() {
        boolean loytamattomiaNakyy = false;

        pakka.kortit().get(0).loydettiin();
        pakka.kortit.get(pakka.parienMaara()).kuvaNakyviin();
        pakka.kortit.get(pakka.parienMaara() - 1).kuvaNakyviin();

        pakka.kaannaKortit();

        for (Kortti k : pakka.kortit()) {
            if (k.nakyykoKuva() && !k.onkoLoydetty()) {
                loytamattomiaNakyy = true;
                break;
            }
        }

        assertEquals(false, loytamattomiaNakyy);
    }

    @Test
    public void ovatkoPariTunnistaaJosKortitOvatPari() {
        Kortti eka = new Kortti(1);
        Kortti toka = new Kortti(1);

        assertEquals(true, pakka.ovatkoPari(eka, toka));
    }

    @Test
    public void ovatkoPariTunnistaaJosKortitEivatOlePari() {
        Kortti eka = new Kortti(10);
        Kortti toka = new Kortti(11);

        assertEquals(false, pakka.ovatkoPari(eka, toka));
    }

    @Test
    public void onkoKaikkiLoytynytPalauttaaTrueJosKaikkiOnLoytynyt() {

        for (Kortti k : pakka.kortit()) {
            k.loydettiin();
        }

        assertEquals(true, pakka.onkoKaikkiLoytynyt());
    }
    
    @Test
    public void ovatkoKaikkiLoytynytPalauttaaFalseJosKaikkiEiOleLoytynyt(){
      
        pakka.kortit.get(0).loydettiin();
        pakka.kortit.get(pakka.parienMaara() - 1).loydettiin();
        
        assertFalse(pakka.onkoKaikkiLoytynyt());
    }

    @Test
    public void kortinSijaintiPalauttaaKortinSijainnin() {
        Kortti k = pakka.kortit().get(pakka.parienMaara() * 2 - 2);
        int sijainti = pakka.kortinSijainti(k);

        assertEquals(pakka.parienMaara() * 2 - 1, sijainti);
    }
    
    @Test
    public void korttiSijainnillaPalauttaaOikeanKortin(){
        Kortti k = pakka.kortit.get(1);
        Kortti kortti = pakka.korttiSijainnilla(2);
        
        assertEquals(k.arvo(), kortti.arvo());
    }
    

    @Test 
    public void korttipakkaSisaltaaKaikkiaArvojaKaksiKpl() {
        ArrayList<Kortti> kortit = pakka.kortit;

        for (int i = 1; i < 5; i++) {
            int n = 0;
            for (Kortti kortti : kortit) {
                if (kortti.arvo() == i) {
                    n++;
                }
            }
            assertEquals(n, 2);
        }
    }
    
    @Test
    public void ovatkoKaannetytPariPalauttaaTrueJosKaannetytOvatPari(){
        Kortti eka = pakka.korttiSijainnilla(1);
        Kortti toka = pakka.korttiSijainnilla(2);

        for (int i = 1; i < pakka.parienMaara() * 2 - 1; i++) {

            if (eka.arvo() != toka.arvo()) {
                toka = pakka.korttiSijainnilla(2 + i);
            }
        }

        eka.kuvaNakyviin();
        toka.kuvaNakyviin();
        
        assertTrue(pakka.ovatkoKaannetytPariJaAsetaLoytyneeksiJosOvat());
        assertTrue(toka.onkoLoydetty());
        assertTrue(eka.onkoLoydetty());
    }
    
    @Test
    public void ovatkoKaannetytPariPalauttaaFalseJosKaannetytEivatOlePari(){
        Kortti eka = pakka.korttiSijainnilla(1);
        Kortti toka = pakka.korttiSijainnilla(2);
        
        if(eka.arvo() == toka.arvo()){
            toka = pakka.korttiSijainnilla(3);
        }
        
        eka.kuvaNakyviin();
        toka.kuvaNakyviin();
        
        assertFalse(pakka.ovatkoKaannetytPariJaAsetaLoytyneeksiJosOvat());
        assertFalse(toka.onkoLoydetty());
        assertFalse(eka.onkoLoydetty());
    }

}
