package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.Model.Gerarchia;

import java.util.ArrayList;

public class MessaggioSistema implements Messaggio {

    private ArrayList<Gerarchia> listaGerarchie=new ArrayList<Gerarchia>();

    public MessaggioSistema(ArrayList<Gerarchia>_listaGerarchie){
        this.listaGerarchie=_listaGerarchie;
    }

    public ArrayList<Gerarchia> getListaGerarchie() {
        return listaGerarchie;
    }


}
