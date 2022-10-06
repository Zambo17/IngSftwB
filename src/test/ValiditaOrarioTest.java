package test;

import it.unibs.IngSftwB.Model.Orario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValiditaOrarioTest {

    //casi di test sui minuti, considerando un'ora valida
    Orario orario1 = new Orario(10, 00);
    Orario orario2 = new Orario(10, 01);
    Orario orario3 = new Orario(9, 59);
    Orario orario4 = new Orario(24, 00);
    Orario orario5 = new Orario(00, 00);
    Orario orario6 = new Orario(10, -1);
    Orario orario7 = new Orario(10, 60);
    Orario orario8 = new Orario(10, 30);
    Orario orario9 = new Orario(10, 31);
    Orario orario10 = new Orario(10, 29);


    @Test
    void oreDieciOrarioValido() {
        assertTrue(orario1.orarioValido());
    }

    @Test
    void oreDieciUnoOrarioNonValido() {
        assertFalse(orario2.orarioValido());
    }

    @Test
    void oreNoveCinquantanoveOrarioNonValido() {
        assertFalse(orario3.orarioValido());
    }

    @Test
    void oreVentiQuattroOrarioValido() {
        assertTrue(orario4.orarioValido());
    }

    @Test
    void oreZeroZeroOrarioValido() {
        assertTrue(orario5.orarioValido());
    }

    @Test
    void oreDieciMinutiNegativiOrarioValido() {
        assertFalse(orario6.orarioValido());
    }

    @Test
    void oreDieciMinutiSessantaOrarioNonValido() {
        assertFalse(orario7.orarioValido());
    }

    @Test
    void oreDieciTrentaOrarioValido() {
        assertTrue(orario8.orarioValido());
    }

    @Test
    void oreDieciVentinoveOrarioNonValido() {
        assertFalse(orario9.orarioValido());
    }

    @Test
    void oreDieciTrentunoOrarioNonValido() {
        assertFalse(orario10.orarioValido());
    }

    //casi di test delle ore con minuti corretti

    Orario orario11 = new Orario(1, 00);
    Orario orario12 = new Orario(-1, 00);
    Orario orario13 = new Orario(23, 00);
    Orario orario14 = new Orario(25, 00);

    @Test
    void oreUnaOrarioValido() {
        assertTrue(orario11.orarioValido());
    }

    @Test
    void oreMenuUnaOrarioNonValido() {
        assertFalse(orario12.orarioValido());
    }

    @Test
    void oreVentitreOrarioValido() {
        assertTrue(orario13.orarioValido());
    }

    @Test
    void oreVenticinqueOrarioNonValido() {
        assertFalse(orario14.orarioValido());
    }


    //casi di test di orari con minuti e ore non validi

    Orario orario21 = new Orario(-1, -1);
    Orario orario22 = new Orario(-1, 60);
    Orario orario23 = new Orario(25, -1);
    Orario orario24 = new Orario(25, 60);

    @Test
    void oreMenuUnaMinutiMenoUnoOrarioNonValido() {
        assertFalse(orario21.orarioValido());
    }

    @Test
    void oreMenuUnaMinutiSessantaOrarioNonValido() {
        assertFalse(orario22.orarioValido());
    }

    @Test
    void oreVenticinqueMinutiMenoUnoOrarioNonValido() {
        assertFalse(orario23.orarioValido());
    }

    @Test
    void oreVenticinqueMinutiSessantaOrarioNonValido() {
        assertFalse(orario21.orarioValido());
    }
}
