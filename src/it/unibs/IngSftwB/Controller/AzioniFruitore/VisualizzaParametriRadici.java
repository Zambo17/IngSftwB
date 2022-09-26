package it.unibs.IngSftwB.Controller.AzioniFruitore;

import it.unibs.IngSftwB.Controller.*;
import it.unibs.IngSftwB.Model.Applicazione;
import it.unibs.IngSftwB.Model.Utente;

public class VisualizzaParametriRadici implements AzioneUtente {
    @Override
    public void eseguiAzione(Controller controller, Utente utente) {
        Applicazione app =controller.getApp();
        controller.getView().stampaRadiciDescription((MessaggioSistema) app.getConfigurazione().getSis().getSistemaDefinition());
        if(app.getConfigurazione().getParametri()==null){
            controller.comunicaAllaView(MessaggioErrore.PARAMETRI_NON_SETTATI);
        }
        else{
            controller.getView().stampaParametriDescription((MessaggioParametri) app.getConfigurazione().getParametri().getParametriDefinition());
        }
    }

    @Override
    public String getNomeAzione() {
        return "Visualizza le radici e i parametri di sistema";
    }
}
