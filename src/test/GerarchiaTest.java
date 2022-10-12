package test;

import it.unibs.IngSftwB.Model.Categoria;
import it.unibs.IngSftwB.Model.Gerarchia;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class GerarchiaTest {

    HashMap <Categoria, Categoria> ramo= new HashMap<Categoria,Categoria>();
    Categoria radice=new Categoria("radice","",null);
    Gerarchia gerarchia=new Gerarchia(ramo,radice);

    @Test
    void controlloNomeGiaPresente(){
        Categoria figlio=new Categoria("figlio","",null);
        ramo.put(figlio,radice);
        assertFalse(gerarchia.checkNomeNuovo("figlio"));
    }

    @Test
    void controlloNomeNonPresente(){
        Categoria figlio=new Categoria("figlio","",null);
        ramo.put(figlio,radice);
        assertTrue(gerarchia.checkNomeNuovo("nuova"));
    }

    @Test
    void controlloNomePadrePresente(){
        Categoria figlio=new Categoria("figlio","",null);
        ramo.put(figlio,radice);
        assertTrue(gerarchia.checkPadreNome("figlio"));
    }

    @Test
    void controlloNomePadreNonPresente(){
        Categoria figlio=new Categoria("figlio","",null);
        ramo.put(figlio,radice);
        assertFalse(gerarchia.checkPadreNome("nuova"));
    }

    @Test
    void controlloDueFigli(){
        Categoria figlio1=new Categoria("figlio1","",null);
        Categoria figlio2=new Categoria("figlio2","",null);
        ramo.put(figlio1,radice);
        ramo.put(figlio2,radice);
        assertEquals(gerarchia.numFigli(radice),2);
    }

    @Test
    void controlloZeroFigli(){
        assertEquals(gerarchia.numFigli(radice),0);
    }

    @Test
    void controlloUnFiglioDiFiglio(){
        Categoria figlio1=new Categoria("figlio1","",null);
        Categoria figlio2=new Categoria("figlio2","",null);
        ramo.put(figlio1,radice);
        ramo.put(figlio2,figlio1);
        assertEquals(gerarchia.numFigli(radice),1);
    }

    @Test
    void controlloGerarchiaConTreFoglie(){
        Categoria figlio1=new Categoria("figlio1","",null);
        Categoria figlio2=new Categoria("figlio2","",null);
        ramo.put(figlio1,radice);
        ramo.put(figlio2,figlio1);
        Categoria figlio3=new Categoria("figlio3","",null);
        Categoria figlio4=new Categoria("figlio4","",null);
        ramo.put(figlio4,radice);
        ramo.put(figlio3,radice);
        assertEquals(gerarchia.listaFoglie().size(),3);
    }

    @Test
    void controlloGerarchiaSoloRadice(){
        assertEquals(gerarchia.listaFoglie().size(),0);
    }

}
