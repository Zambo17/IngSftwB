package it.unibs.IngSftwB.Model;

import it.unibs.IngSftwB.Controller.Messaggio;
import it.unibs.IngSftwB.Controller.MessaggioScambi;

import java.util.ArrayList;

public class ListaScambi {
    private ArrayList <Scambio> scambi=new ArrayList<>();

    public ListaScambi(ArrayList <Scambio> _scambi){
        scambi=_scambi;
    }

    public ListaScambi scambiOfferente(Utente f){
        ArrayList <Scambio> scambiOfferti=new ArrayList<>();
        for(Scambio s: this.scambi){
            if(s.getOfferente().getNomeFruitore().equals(f.getUsername())){
                scambiOfferti.add(s);
            }
        }
        return new ListaScambi(scambiOfferti);
    }
    public ListaScambi scambiRicevente(Utente f){
        ArrayList <Scambio> scambiRicevuti=new ArrayList<>();
        for(Scambio s: this.scambi){
            if(s.getRicevente().getNomeFruitore().equals(f.getUsername())){
                scambiRicevuti.add(s);
            }
        }
        return new ListaScambi(scambiRicevuti);
    }


    /**
     * Metodo per aggiornare la lista degli scambi in base a quelli scaduti
     * @param ps i parametri degli scambi
     * @param offerte le offerte del sistema
     */
    public void controllaValiditaScambi(ParametriScambi ps,Offerte offerte){
        ArrayList <Scambio> temp=new ArrayList<>();
        for(Scambio s:this.scambi){
            if(s.getOfferente().getStatoAttuale()==StatoOfferta.CHIUSA){
                temp.add(s);
            }
            else if(!s.scambioScaduto(ps,offerte)){
                temp.add(s);
            }

        }
        this.scambi= temp;
    }

    public void addScambio(Scambio scambio){
        this.scambi.add(scambio);
    }

    public ArrayList<Scambio> getScambi() {
        return scambi;
    }
    public void aggiornaScambio(Scambio sca, int index){
        this.scambi.remove(index);
        this.scambi.add(sca);
    }

    public Messaggio getScambiDefinition(){
        return new MessaggioScambi(scambi);
    }
}
