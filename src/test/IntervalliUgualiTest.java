package test;

import it.unibs.IngSftwB.Model.Intervallo;
import it.unibs.IngSftwB.Model.Orario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntervalliUgualiTest {

    Orario orario1= new Orario(10,00);
    Orario orario4= new Orario(8,30);
    Orario orario5= new Orario(8,00);


    @Test
    void intervalloOttoDieciUguale(){
        Intervallo intervallo1=new Intervallo(new Orario[]{orario5, orario1});
        Intervallo intervallo2=new Intervallo(new Orario[]{orario5, orario1});
        assertTrue(intervallo1.equals(intervallo2));
    }

    @Test
    void intervalloOttoDieciSingoloUguale(){
        Intervallo intervallo1=new Intervallo(new Orario[]{orario5, orario1});
        assertTrue(intervallo1.equals(intervallo1));
    }

    @Test
    void intervalloOttoDieciOttoTrentaDieciDiverso(){
        Intervallo intervallo1=new Intervallo(new Orario[]{orario5, orario1});
        Intervallo intervallo2=new Intervallo(new Orario[]{orario4, orario1});
        assertFalse(intervallo1.equals(intervallo2));
    }

}
