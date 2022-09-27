package it.unibs.IngSftwB.Controller.AzioniFruitore;

import it.unibs.IngSftwB.Controller.AzioneUtente;
import it.unibs.IngSftwB.Controller.Controller;
import it.unibs.IngSftwB.Controller.MessaggioErrore;
import it.unibs.IngSftwB.Controller.MessaggioGenerale;
import it.unibs.IngSftwB.Model.*;

public class ModificaOfferta implements AzioneUtente {
    @Override
    public void eseguiAzione(Controller controller, Utente utente) {
        Applicazione app =controller.getApp();

        Offerte offerteFruitore= new Offerte(app.getOfferte().getOfferteFromFruitore(utente.getUsername()));
        offerteFruitore.togliRitirate();
        if(offerteFruitore.getListaOfferte().size()>0){
            Offerta toChange=controller.scegliOfferta(offerteFruitore);
            int sceltaSicura= controller.richiediInteroIntervalloView(MessaggioGenerale.ELIMINA_OFFERTA,0,1);
            if(sceltaSicura==1){
                app.getOfferte().modificaOffertaEsistente(toChange, StatoOfferta.RITIRATA);
                controller.comunicaAllaView(MessaggioGenerale.OFFERTA_ELIMINATA);
            }
        }
        else{
            controller.comunicaAllaView(MessaggioErrore.NO_RITIRABILI);
        }

    }

    @Override
    public String getNomeAzione() {
        return "Modifica una offerta già esistente";
    }
}
