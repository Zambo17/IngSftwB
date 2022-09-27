package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.Model.Offerta;
import it.unibs.IngSftwB.Model.PropostaIncontro;

public class MessaggioScambio implements Messaggio{

    private Offerta offerente;
    private Offerta ricevente;
    private PropostaIncontro ultimaProposta;
    private long tempo;

    public MessaggioScambio(Offerta offerente, Offerta ricevente, PropostaIncontro ultimaProposta, long tempo) {
        this.offerente = offerente;
        this.ricevente = ricevente;
        this.ultimaProposta = ultimaProposta;
        this.tempo = tempo;
    }

    public Offerta getOfferente() {
        return offerente;
    }

    public Offerta getRicevente() {
        return ricevente;
    }
}
