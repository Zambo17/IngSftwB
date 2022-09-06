package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.mainClasses.Gerarchia;

import java.util.ArrayList;

public class MessaggioSistema implements Messaggio {

    private ArrayList<Gerarchia> listaGerarchie=new ArrayList<Gerarchia>();
    private MessaggioGerarchia msg;

    public MessaggioSistema(ArrayList<Gerarchia>_listaGerarchie, MessaggioGerarchia msg){
        this.listaGerarchie=_listaGerarchie;
        this.msg=msg;
    }

    public ArrayList<Gerarchia> getListaGerarchie() {
        return listaGerarchie;
    }

    public MessaggioGerarchia getMsg() {
        return msg;
    }
}
