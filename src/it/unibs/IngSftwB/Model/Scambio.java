package it.unibs.IngSftwB.Model;

import it.unibs.IngSftwB.Controller.Messaggio;
import it.unibs.IngSftwB.Controller.MessaggioScambio;

import java.util.Calendar;

public class Scambio {
    private Offerta offerente;
    private Offerta ricevente;
    private PropostaIncontro ultimaProposta;
    private long tempo;

    public Scambio(Offerta _offerrente, Offerta _ricevente, PropostaIncontro _ultimaProposta, long _tempo){
        offerente=_offerrente;
        ricevente=_ricevente;
        ultimaProposta=_ultimaProposta;
        tempo=_tempo;
    }

    /**
     * metodo che restituisce un int in base allo stato dello scambio
     * @return 0 se non è neanche ora stata accettata l'offerta, 1 se è stato accettata e si stanno accordando sullo scambio
     * 2 se è scaduto il tempo e allora le offerte tornano aperte, 3 se i due fruitore si sono accordati sullo scambio
     */
    public int statoScambio(){
        int stato=0;
        if(offerente.getStatoAttuale()==StatoOfferta.ACCOPPIATA && ricevente.getStatoAttuale()==StatoOfferta.SELEZIONATA){
            stato=0;
        }
        else if(offerente.getStatoAttuale()==StatoOfferta.INSCAMBIO && ricevente.getStatoAttuale()==StatoOfferta.INSCAMBIO){
            stato=1;
        }
        else if(offerente.getStatoAttuale()==StatoOfferta.APERTA && ricevente.getStatoAttuale()==StatoOfferta.APERTA){
            stato=2;
        }
        else if(offerente.getStatoAttuale()==StatoOfferta.CHIUSA && ricevente.getStatoAttuale()==StatoOfferta.CHIUSA){
            stato=3;
        }
        return stato;
    }

    public void cambiaProposta(PropostaIncontro pi){
        this.ultimaProposta=pi;
    }

    /**
     * Metodo che controlla se uno scambio è scaduto e se lo è rimette le offerte nello stato aperta
     * @param ps parametri scambi in cui c'è la scadenza degli scambi
     * @param offerte le offerte del sistema
     * @return true se è scaduto, false altrimenti
     */
    public boolean scambioScaduto(ParametriScambi ps, Offerte offerte){
        boolean scaduto=false;
        long tempoOra=Calendar.getInstance().getTimeInMillis();
        long differenza=compareIstants(this.tempo,tempoOra);
        if(differenza>ps.getScadenza()){
            scaduto=true;
            this.ricevente.cambiaStato(StatoOfferta.APERTA);
            this.offerente.cambiaStato(StatoOfferta.APERTA);
            offerte.modificaOffertaEsistente(ricevente, StatoOfferta.APERTA);
            offerte.modificaOffertaEsistente(offerente, StatoOfferta.APERTA);
        }
        return scaduto;
    }

    public Offerta getOfferente() {
        return offerente;
    }

    public Offerta getRicevente() {
        return ricevente;
    }

    public PropostaIncontro getUltimaProposta() {
        return ultimaProposta;
    }

    public long getTempo() {
        return tempo;
    }

    public Messaggio getScambioDefinition(){
        return new MessaggioScambio(offerente,ricevente,ultimaProposta,tempo);
    }

    public void setTempo(long tempo) {
        this.tempo = tempo;
    }

    /**
     * metodo che restituisce la differenza tra due istanti in giorni
     * @param old istante vecchio
     * @param nuovo instante nuovo
     * @return nuovo-old
     */
    public static long compareIstants(long old, long nuovo){
        return  ((nuovo/(1000*60*60*24))-(old/(1000*60*60*24)));
    }
}
