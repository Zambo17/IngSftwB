package it.unibs.IngSftwB.Model;


import it.unibs.IngSftwB.Controller.Messaggio;
import it.unibs.IngSftwB.Controller.MessaggioParametri;

import java.util.ArrayList;

/**
 * Classe per la gestione dei parametri degli scambi
 * @author Jacopo Tedeschi,Enrico Zambelli
 */
public class ParametriScambi {
    private String piazza;
    private ArrayList<String> luoghi = new ArrayList<>();
    private ArrayList<Giorno> giorni = new ArrayList<>();
    private ArrayList<Intervallo> intervalli = new ArrayList<>();
    private int scadenza;

    /**
     * Costruttore della classe ParametriScambi
     * @param _piazza la piazza in cui avvengono gli scambi
     * @param _luoghi i luoghi della piazza in cui avvengono gli scambi
     * @param _giorni i giorni in cui avvengono gli scambi
     * @param _intervalli gli intervalli di tempo in cui avvengono gli scambi
     * @param _scadenza il numero massimo di giorni entro cui un fruitore può accettare una proposta di scambio
     */
    public ParametriScambi(String _piazza, ArrayList<String> _luoghi, ArrayList<Giorno> _giorni, ArrayList<Intervallo> _intervalli, int _scadenza) {
        this.giorni = _giorni;
        this.intervalli = _intervalli;
        this.piazza = _piazza;
        this.luoghi = _luoghi;
        this.scadenza = _scadenza;
    }
    /**
     * Metodo get per i luoghi di scambio
     * @return i luoghi di scambio
     */
    public ArrayList<String> getLuoghi() {
        return luoghi;
    }

    /**
     * Metodo get per la piazza di scambio
     * @return la piazza di scambio
     */
    public String getPiazza() {
        return piazza;
    }

    /**
     * Metodo get per i giorni di scambio
     * @return i giorni di scambio
     */
    public ArrayList<Giorno> getGiorni() {
        return giorni;
    }

    /**
     * Metodo get per gli intervalli di scambio
     * @return gli intervalli di scambio
     */
    public ArrayList<Intervallo> getIntervalli() {
        return intervalli;
    }

    /**
     * Metodo get per la scadenza di accettazione
     * @return la scadenza di accettazione
     */
    public int getScadenza() {
        return scadenza;
    }


    /**
     * metodo che restituisce i giorni della settimana sotto forma di numero partendo da giovedì
     * @return lista dei numeri rispettivi ai giorni
     */
    public ArrayList<Integer> giorniInInteri(){
        ArrayList <Integer> giorniInNum=new ArrayList<>();
        for(Giorno g: this.giorni){
           giorniInNum.add(g.getNumFromDay());
        }
        return giorniInNum;
    }

    /**
     * Metodo per l'aggiunta di un luogo
     * @param s il luogo da aggiungere
     */
    public void addLuogo(String s) {
        this.luoghi.add(s);
    }


    public void addIntervallo(Intervallo i) {
        this.intervalli.add(i);
    }


    public void addGiorno(Giorno g) {
        this.giorni.add(g);
    }


    public void setScadenza(int scadenza) {
        this.scadenza = scadenza;
    }

    public Messaggio getParametriDefinition(){
        return new MessaggioParametri(this.piazza,this.luoghi,this.giorni,this.intervalli,this.scadenza);
    }




}

