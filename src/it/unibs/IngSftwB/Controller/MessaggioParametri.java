package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.Model.Giorno;
import it.unibs.IngSftwB.Model.Intervallo;

import java.util.ArrayList;

public class MessaggioParametri implements Messaggio{

    private String piazza;
    private ArrayList<String> luoghi = new ArrayList<>();
    private ArrayList<Giorno> giorni = new ArrayList<>();
    private ArrayList<Intervallo> intervalli = new ArrayList<>();
    private int scadenza;

    public MessaggioParametri(String _piazza, ArrayList<String> _luoghi, ArrayList<Giorno> _giorni, ArrayList<Intervallo> _intervalli, int _scadenza) {
        this.giorni = _giorni;
        this.intervalli = _intervalli;
        this.piazza = _piazza;
        this.luoghi = _luoghi;
        this.scadenza = _scadenza;
    }

    public String getPiazza() {
        return piazza;
    }

    public ArrayList<String> getLuoghi() {
        return luoghi;
    }

    public ArrayList<Giorno> getGiorni() {
        return giorni;
    }

    public ArrayList<Intervallo> getIntervalli() {
        return intervalli;
    }

    public int getScadenza() {
        return scadenza;
    }
}
