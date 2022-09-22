package it.unibs.IngSftwB.Controller.AzioniConfiguratore;

import it.unibs.IngSftwB.Controller.*;
import it.unibs.IngSftwB.Model.*;

public class ModificaParametri implements AzioneUtente {
    @Override
    public void eseguiAzione(Controller controller, Utente utente) {
        Applicazione app=controller.getApp();

        controller.comunicaAllaView(MessaggioGenerale.PARAMETRI);
        controller.getView().stampaParametriDescription((MessaggioParametri) app.getConfigurazione().getParametri().getParametriDefinition());
        int choice= controller.richiediInteroIntervalloView(MessaggioAlternativa.SCELTA_PARAMETRO,1,4);
        int choiceP;
        switch(choice){
            case 1:
                choiceP=controller.richiediInteroIntervalloView(MessaggioAlternativa.AGGIUNTA_RIMOZIONE,1,2);
                if(choiceP==1)
                    this.addLuogo(controller);
                else
                    this.togliLuogo(controller);
                break;
            case 2:
                choiceP=controller.richiediInteroIntervalloView(MessaggioAlternativa.AGGIUNTA_RIMOZIONE,1,2);
                if(choiceP==1)
                    app.getConfigurazione().getParametri().getIntervalli().add(this.addIntervallo(controller));
                else
                    this.togliIntervallo(controller);
                break;
            case 3:
                choiceP=controller.richiediInteroIntervalloView(MessaggioAlternativa.AGGIUNTA_RIMOZIONE,1,2);
                if(choiceP==1)
                    this.addGiorno(controller);
                else
                    this.togliGiorno(controller);
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

    public  Intervallo addIntervallo(Controller controller) {
        boolean intervalloValido = false;
        Orario inizio;
        Orario fine;
        Intervallo intervallo = null;
        do {
            Orario[] orari = new Orario[2];
            boolean finito = false;
            do {
                int ora = controller.richiediInteroView(MessaggioGenerale.INSERISCI_ORA_INIZIO);
                int minuti = controller.richiediInteroView(MessaggioGenerale.INSERISCI_MINUTI_INIZIO);
                inizio = new Orario(ora, minuti);
                if (inizio.orarioValido()) {
                    finito = true;
                } else {
                    controller.comunicaAllaView(MessaggioErrore.ORARIO_NON_VALIDO);
                }
            } while (!finito);
            finito = false;
            do {
                int ora = controller.richiediInteroView(MessaggioGenerale.INSERISCI_ORA_FINE);
                int minuti = controller.richiediInteroView(MessaggioGenerale.INSERISCI_MINUTI_FINE);
                fine = new Orario(ora, minuti);
                if (fine.orarioValido()) {
                    finito = true;
                } else {
                    controller.comunicaAllaView(MessaggioErrore.ORARIO_NON_VALIDO);
                }
            } while (!finito);
            orari[0] = inizio;
            orari[1] = fine;
            intervallo = new Intervallo(orari);
            if (!intervallo.intervalloValido()) {
                System.out.println(MessaggioErrore.INTERVALLO_NON_VALIDO);

            } else {
                intervalloValido = true;
            }
        } while (!intervalloValido);
        return intervallo;

    }

    public void togliIntervallo(Controller controller){
        Intervallo toRemove=this.addIntervallo(controller);
        boolean presente=false;
        int countI=0;
        for(Intervallo x: controller.getApp().getConfigurazione().getParametri().getIntervalli()){
            if(!presente){
                countI++;
            }
            if(x.compareIntervallo(toRemove)){
                presente=true;
            }

        }
        if (presente) {
            controller.getApp().getConfigurazione().getParametri().getIntervalli().remove(countI-1);
            if(controller.getApp().getConfigurazione().getParametri().getIntervalli().size()==0){
                controller.comunicaAllaView(MessaggioErrore.NESSUN_ORARIO);
                controller.getApp().getConfigurazione().getParametri().getIntervalli().add(this.addIntervallo(controller));
            }
        }
        else
            controller.comunicaAllaView(MessaggioErrore.INTERVALLO_NON_PRESENTE);
    }

    public void addGiorno(Controller controller) {
        boolean giornoCorretto = true;
        Giorno g = null;
        do {
            String giorno = controller.richiediStringaView(MessaggioGenerale.INSERISCI_GIORNO);
            g = Giorno.getGiornoFromString(giorno);
            if (g == null) {
                controller.comunicaAllaView(MessaggioErrore.GIORNO_NON_PRESENTE);
                giornoCorretto = false;
            }
            if (controller.getApp().getConfigurazione().getParametri().getGiorni().contains(g)) {
                controller.comunicaAllaView(MessaggioErrore.GIORNO_GIA_PRESENTE);
                giornoCorretto = false;
            }
            if (g != null && !controller.getApp().getConfigurazione().getParametri().getGiorni().contains(g))
                giornoCorretto = true;
        } while (!giornoCorretto);
        controller.getApp().getConfigurazione().getParametri().getGiorni().add(g);
    }

    public void togliGiorno(Controller controller){
        String nomeGiorno=controller.richiediStringaView(MessaggioGenerale.RIMUOVI_GIORNO);
        Giorno toRemove=Giorno.getGiornoFromString(nomeGiorno);
        boolean giornoEsiste=false;
        if(toRemove!=null ){
            if(controller.getApp().getConfigurazione().getParametri().getGiorni().contains(toRemove)){
                controller.getApp().getConfigurazione().getParametri().getGiorni().remove(toRemove);
                giornoEsiste=true;
            }
        }
        if(!giornoEsiste)
            controller.comunicaAllaView(MessaggioErrore.GIORNO_ERRATO);
        if(controller.getApp().getConfigurazione().getParametri().getGiorni().size()==0){
            controller.comunicaAllaView(MessaggioErrore.NESSUN_GIORNO);
            this.addGiorno(controller);
        }
    }

    public void addLuogo(Controller controller){
        String luogo=controller.richiediStringaView(MessaggioGenerale.NUOVO_LUOGO);
        controller.getApp().getConfigurazione().getParametri().getLuoghi().add(luogo);
    }

    public void togliLuogo(Controller controller){
        String luogoToRemove=controller.richiediStringaView(MessaggioGenerale.RIMUOVI_LUOGO);
        if(controller.getApp().getConfigurazione().getParametri().getLuoghi().contains(luogoToRemove)){
            controller.getApp().getConfigurazione().getParametri().getLuoghi().remove(luogoToRemove);
        }
        else{
            controller.comunicaAllaView(MessaggioErrore.LUOGO_NON_PRESENTE);
        }
        if(controller.getApp().getConfigurazione().getParametri().getLuoghi().size()==0){
            String luogoMinimo=controller.richiediStringaView(MessaggioGenerale.NESSUN_LUOGO);
            controller.getApp().getConfigurazione().getParametri().getLuoghi().add(luogoMinimo);
        }
    }

    public void addScadenza(Controller controller){
        int scad=controller.richiediInteroIntervalloView(MessaggioGenerale.NUOVA_SCADENZA,1,999999);
        controller.getApp().getConfigurazione().getParametri().setScadenza(scad);
    }
}