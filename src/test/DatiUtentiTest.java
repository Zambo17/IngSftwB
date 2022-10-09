package test;

import it.unibs.IngSftwB.Model.Configuratore;
import it.unibs.IngSftwB.Model.DatiUtenti;
import it.unibs.IngSftwB.Model.Utente;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DatiUtentiTest {

    ArrayList<Utente> lista=new ArrayList<>();
    DatiUtenti datiUtenti=new DatiUtenti(lista);

    @Test
    void controlloCredenzialiUtentePresente(){
        datiUtenti.addUtente("mariorossi","1234",false);
        assertTrue(datiUtenti.checkCredenziali("mariorossi","1234"));
    }

    @Test
    void controlloCredenzialiUtenteAssente(){
        datiUtenti.addUtente("mariorossi","1234",false);
        assertFalse(datiUtenti.checkCredenziali("paoloverdi","5678"));
    }

    @Test
    void controlloCredenzialiPredefinite(){
        assertTrue(datiUtenti.checkConf("admin","ezjt9917"));
    }

    @Test
    void controlloCredenzialiPredefiniteErrate(){
        assertFalse(datiUtenti.checkConf("mariorossi","1234"));
    }

    @Test
    void controlloNomePresente(){
        datiUtenti.addUtente("mariorossi","1234",false);
        assertTrue(datiUtenti.checkName("mariorossi"));
    }

    @Test
    void controlloNomeNonPresente(){
        datiUtenti.addUtente("mariorossi","1234",false);
        assertFalse(datiUtenti.checkName("paoloverdi"));
    }


}
