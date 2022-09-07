package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.mainClasses.Offerta;

import java.util.ArrayList;

public class MessaggioOfferte implements Messaggio{

    private ArrayList<Offerta> listaOfferte= new ArrayList<Offerta>();

    public MessaggioOfferte(ArrayList<Offerta> _offerteAccoppiate) {
        this.listaOfferte=_offerteAccoppiate;
    }

    public ArrayList<Offerta> getListaOfferte() {
        return listaOfferte;
    }
}
