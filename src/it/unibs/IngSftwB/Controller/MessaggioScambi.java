package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.Model.Scambio;

import java.util.ArrayList;

public class MessaggioScambi implements Messaggio{

    private ArrayList<Scambio> scambi=new ArrayList<>();

    public MessaggioScambi(ArrayList <Scambio> _scambi){
        scambi=_scambi;
    }

    public ArrayList<Scambio> getScambi() {
        return scambi;
    }
}
