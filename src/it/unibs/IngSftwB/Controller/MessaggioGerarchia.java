package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.mainClasses.Categoria;

import java.util.HashMap;

public class MessaggioGerarchia implements Messaggio{

    private HashMap<Categoria, Categoria> ramo= new HashMap<Categoria,Categoria>();
    private Categoria radice;
    private MessaggioCategoria msg;

    public MessaggioGerarchia(HashMap <Categoria, Categoria> _ramo, Categoria _radice, MessaggioCategoria msg){
        this.ramo=_ramo;
        this.radice=_radice;
        this.msg=msg;
    }

    public HashMap<Categoria, Categoria> getRamo() {
        return ramo;
    }

    public Categoria getRadice() {
        return radice;
    }

    public MessaggioCategoria getMsg() {
        return msg;
    }
}
