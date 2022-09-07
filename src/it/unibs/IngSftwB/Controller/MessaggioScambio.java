package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.mainClasses.Offerta;

public class MessaggioScambio implements Messaggio{

    private Offerta offerente;
    private Offerta ricevente;

    public MessaggioScambio(Offerta offerente, Offerta ricevente) {
        this.offerente = offerente;
        this.ricevente = ricevente;
    }

    public Offerta getOfferente() {
        return offerente;
    }

    public Offerta getRicevente() {
        return ricevente;
    }
}
