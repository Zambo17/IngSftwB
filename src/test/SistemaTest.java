package test;

import it.unibs.IngSftwB.Model.Categoria;
import it.unibs.IngSftwB.Model.Gerarchia;
import it.unibs.IngSftwB.Model.Sistema;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaTest {

    ArrayList<Gerarchia> lista =new ArrayList<>();
    Sistema sistema=new Sistema(lista);

    @Test
    void controlloRadiceNuova(){
        Categoria radice1=new Categoria("radice1","",null);
        Gerarchia gerarchia1=new Gerarchia(null,radice1);
        Categoria radice2=new Categoria("radice2","",null);
        Gerarchia gerarchia2=new Gerarchia(null,radice2);
        sistema.addGerarchia(gerarchia1);
        sistema.addGerarchia(gerarchia2);
        assertTrue(sistema.checkNomeNuovoRadice("radice3"));
    }

    @Test
    void controlloRadiceGiaPresente(){
        Categoria radice1=new Categoria("radice1","",null);
        Gerarchia gerarchia1=new Gerarchia(null,radice1);
        Categoria radice2=new Categoria("radice2","",null);
        Gerarchia gerarchia2=new Gerarchia(null,radice2);
        sistema.addGerarchia(gerarchia1);
        sistema.addGerarchia(gerarchia2);
        assertFalse(sistema.checkNomeNuovoRadice("radice2"));
    }

    @Test
    void controlloCategoriaFogliaPresente(){
        Categoria radice1=new Categoria("radice1","",null);
        HashMap <Categoria, Categoria> ramo= new HashMap<Categoria,Categoria>();
        Categoria figlio=new Categoria("figlio","",null);
        ramo.put(figlio,radice1);
        Gerarchia gerarchia1=new Gerarchia(ramo,radice1);
        sistema.addGerarchia(gerarchia1);

        assertTrue(sistema.isFoglia("figlio"));
    }

    @Test
    void controlloCategoriaFogliaNonPresente(){
        Categoria radice1=new Categoria("radice1","",null);
        HashMap <Categoria, Categoria> ramo= new HashMap<Categoria,Categoria>();
        Categoria figlio=new Categoria("figlio","",null);
        ramo.put(figlio,radice1);
        Gerarchia gerarchia1=new Gerarchia(ramo,radice1);
        sistema.addGerarchia(gerarchia1);

        assertFalse(sistema.isFoglia("nuovo"));
    }

    @Test
    void controlloCategoriaNonFogliaPresente() {
        Categoria radice1 = new Categoria("radice1", "", null);
        HashMap<Categoria, Categoria> ramo = new HashMap<Categoria, Categoria>();
        Categoria figlio = new Categoria("figlio", "", null);
        ramo.put(figlio, radice1);
        Categoria figlio2 = new Categoria("figlio2", "", null);
        ramo.put(figlio2, figlio);
        Gerarchia gerarchia1 = new Gerarchia(ramo, radice1);
        sistema.addGerarchia(gerarchia1);

        assertFalse(sistema.isFoglia("figlio"));
    }

}
