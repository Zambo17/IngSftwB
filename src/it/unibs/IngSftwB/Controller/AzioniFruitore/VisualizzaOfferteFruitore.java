package it.unibs.IngSftwB.Controller.AzioniFruitore;

import it.unibs.IngSftwB.Controller.AzioneUtente;
import it.unibs.IngSftwB.Controller.Controller;
import it.unibs.IngSftwB.Controller.MessaggioOfferte;
import it.unibs.IngSftwB.Model.Offerte;
import it.unibs.IngSftwB.Model.Utente;

public class VisualizzaOfferteFruitore implements AzioneUtente {
    @Override
    public void eseguiAzione(Controller controller, Utente utente) {
        Offerte offerteFruitoreToSee=new Offerte(controller.getApp().getOfferte().getOfferteFromFruitore(utente.getUsername()));
        controller.getView().stampaOfferteDescription((MessaggioOfferte) offerteFruitoreToSee.getOfferteDefinition());
    }

    @Override
    public String getNomeAzione() {
        return "Visualizza le tue offerte";
    }
}
