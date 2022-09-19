package it.unibs.IngSftwB.Controller.AzioniConfiguratore;

import it.unibs.IngSftwB.Controller.*;
import it.unibs.IngSftwB.Model.Applicazione;
import it.unibs.IngSftwB.Model.ParametriScambi;
import it.unibs.IngSftwB.Model.Utente;
import it.unibs.IngSftwB.Model.Utilita;

import java.util.function.Function;

public class ModificaParametri implements AzioneUtente {
    @Override
    public void eseguiAzione(Controller controller, Utente utente) {
        Applicazione app=controller.getApp();

        controller.comunicaAllaView(MessaggioGenerale.PARAMETRI);
        controller.getView().stampaParametriDescription((MessaggioParametri) app.getConfigurazione().getParametri().getParametriDefinition());
        int choice= controller.richiediInteroIntervalloView(MessaggioAlternativa.SCELTA_PARAMETRO,1,4);
        int choiceP=controller.richiediInteroIntervalloView(MessaggioAlternativa.AGGIUNTA_RIMOZIONE,1,2);
        switch(choice){
            case 1:
                if(choiceP==1)
                    app.getConfigurazione().getParametri().addLuogo();
                else
                    app.getConfigurazione().getParametri().togliLuogo();
                break;
            case 2:
                if(choiceP==1)
                    app.getConfigurazione().getParametri().addIntervalloNuovo();
                else
                    app.getConfigurazione().getParametri().togliIntervallo();
                break;
            case 3:
                if(choiceP==1)
                    app.getConfigurazione().getParametri().addGiorno();
                else
                    app.getConfigurazione().getParametri().togliGiorno();
                break;
            case 4:
                app.getConfigurazione().getParametri().addScadenza();
                break;
        }
    }


    @Override
    public String getNomeAzione() {
        return "Modifica dei parametri";
    }
}
