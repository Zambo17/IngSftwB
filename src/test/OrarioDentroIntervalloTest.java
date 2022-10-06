package test;

import it.unibs.IngSftwB.Model.Orario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrarioDentroIntervalloTest {


    //test relativi al fatto se un orario appartiene ad un dato intervallo

    Orario orario15 =new Orario(12,00);
    Orario orario16 =new Orario(16,00);

    Orario orario17 =new Orario(11,30);
    Orario orario18 =new Orario(12,30);
    Orario orario19 =new Orario(15,30);
    Orario orario20 =new Orario(16,30);

    @Test
    void oreUndiciTrentaFuoriIntervallo(){
        assertFalse(orario17.isInsideIntervallo(orario15,orario16));
    }

    @Test
    void oreDodiciDentroIntervallo(){
        assertTrue(orario15.isInsideIntervallo(orario15,orario16));
    }

    @Test
    void oreDodiciTrentaDentroIntervallo(){
        assertTrue(orario18.isInsideIntervallo(orario15,orario16));
    }

    @Test
    void oreQuindiciTrentaDentroIntervallo(){
        assertTrue(orario19.isInsideIntervallo(orario15,orario16));
    }

    @Test
    void oreSediciDentroIntervallo(){
        assertTrue(orario16.isInsideIntervallo(orario15,orario16));
    }

    @Test
    void oreSediciTrentaFuoriIntervallo(){
        assertFalse(orario20.isInsideIntervallo(orario15,orario16));
    }



}
