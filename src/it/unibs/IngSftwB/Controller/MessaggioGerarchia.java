package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.Model.Categoria;

import java.util.HashMap;

public class MessaggioGerarchia implements Messaggio{

    private HashMap<Categoria, Categoria> ramo= new HashMap<Categoria,Categoria>();
    private Categoria radice;

    public MessaggioGerarchia(HashMap <Categoria, Categoria> _ramo, Categoria _radice){
        this.ramo=_ramo;
        this.radice=_radice;
    }

    public HashMap<Categoria, Categoria> getRamo() {
        return ramo;
    }

    public Categoria getRadice() {
        return radice;
    }

}
