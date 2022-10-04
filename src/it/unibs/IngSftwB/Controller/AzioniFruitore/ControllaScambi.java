package it.unibs.IngSftwB.Controller.AzioniFruitore;

import it.unibs.IngSftwB.Controller.*;
import it.unibs.IngSftwB.Model.*;

import java.util.ArrayList;
import java.util.Calendar;

public class ControllaScambi implements AzioneUtente {
    @Override
    public void eseguiAzione(Controller controller, Utente utente) {
        Applicazione app= controller.getApp();
        int sceltaFattiRicevuti= controller.richiediInteroIntervalloView(MessaggioAlternativa.SCELTA_SCAMBI,0,1);
        if(sceltaFattiRicevuti==1){
            ListaScambi fatti=app.getListaScambi().scambiOfferente(utente);
            if(fatti.getScambi().size()>0){
                Scambio scambioScelto=this.scegliScambio(controller);
                int index=app.getListaScambi().getScambi().indexOf(scambioScelto);
                if(scambioScelto!=null){
                    this.gestisciScambio(utente,controller,scambioScelto);
                    app.getListaScambi().aggiornaScambio(scambioScelto,index);
                }
                else
                    return;
            }
            else{
                controller.comunicaAllaView(MessaggioErrore.NO_PROPOSTI);
            }

        }
        else{ //if per quando non ce ne sono
            ListaScambi ricevuti=app.getListaScambi().scambiRicevente(utente);
            if(ricevuti.getScambi().size()>0){
                Scambio scambioScelto=this.scegliScambio(controller);
                int index=app.getListaScambi().getScambi().indexOf(scambioScelto);
                if(scambioScelto!=null){
                    this.gestisciScambio(utente,controller,scambioScelto);
                    app.getListaScambi().aggiornaScambio(scambioScelto,index);
                }
                else
                    return;
            }
            else{
                controller.comunicaAllaView(MessaggioErrore.NO_RICEVUTI);
            }

        }
    }

    @Override
    public String getNomeAzione() {
        return "Controllare gli scambi";
    }

    public Scambio scegliScambio(Controller controller){
        controller.getView().stampaScambiDescription((MessaggioScambi) controller.getApp().getListaScambi().getScambiDefinition());
        Scambio s=null;
        if(controller.getApp().getListaScambi().getScambi().size()>0){
            int indice=controller.richiediInteroIntervalloView(MessaggioGenerale.SCELTA_SCAMBIO,0,controller.getApp().getListaScambi().getScambi().size());

            if(indice!=0){
                s=controller.getApp().getListaScambi().getScambi().get(indice-1);
            }
        }
        return s;
    }

    public void gestisciScambio(Utente utente,Controller controller,Scambio scambio){
        switch (scambio.statoScambio()){
            case 0:
                if(utente.getUsername().equals(scambio.getOfferente().getNomeFruitore())){
                    controller.comunicaAllaView(MessaggioErrore.NO_RISPOSTA);
                }
                else{
                    controller.comunicaAllaView(MessaggioGenerale.PROPOSTA);
                    controller.getView().stampaOffertaDescription((MessaggioOfferta) scambio.getOfferente().getOffertaDefinition());
                    controller.comunicaAllaView(MessaggioGenerale.TUA_OFFERTA);
                    controller.getView().stampaOffertaDescription((MessaggioOfferta) scambio.getRicevente().getOffertaDefinition());
                    int rispondi=controller.richiediInteroIntervalloView(MessaggioAlternativa.SCELTA_RISPOSTA,0,1);
                    if(rispondi==1){
                        PropostaIncontro nuovaPropposta=this.creaProposta(utente.getUsername(),controller);
                        scambio.cambiaProposta(nuovaPropposta);
                        scambio.getRicevente().cambiaStato(StatoOfferta.INSCAMBIO);
                        scambio.getOfferente().cambiaStato(StatoOfferta.INSCAMBIO);
                        scambio.setTempo(nuovaPropposta.getTempo());
                    }
                }
                break;
            case 1:
                if(scambio.getUltimaProposta().getNomeFruitore().equals(utente.getUsername())){
                    controller.comunicaAllaView(MessaggioErrore.NO_RISPOSTA);
                }
                else{
                    controller.comunicaAllaView(MessaggioGenerale.PROPOSTA_INCONTRO);
                    controller.getView().stampaPropostaDescription((MessaggioProposta) scambio.getUltimaProposta().getPropostaDefinition());
                    int scelta=controller.richiediInteroIntervalloView(MessaggioAlternativa.SCELTA_PROPOSTA,0,2);
                    if(scelta!=0){
                        if(scelta==1){
                            controller.comunicaAllaView(MessaggioGenerale.SCAMBIO_ESEGUITO_CORRETTO);
                            controller.getView().stampaPropostaDescription((MessaggioProposta) scambio.getUltimaProposta().getPropostaDefinition());
                            scambio.getRicevente().cambiaStato(StatoOfferta.CHIUSA);
                            scambio.getOfferente().cambiaStato(StatoOfferta.CHIUSA);
                        }
                        else{
                            if(scelta==2){
                                scambio.cambiaProposta(this.creaProposta(utente.getUsername(),controller));
                            }
                        }
                    }
                }
                break;
            case 3:
                controller.comunicaAllaView(MessaggioGenerale.SCAMBIO_CHIUSO);
                break;
        }
    }

    public PropostaIncontro creaProposta(String fruitoreNome,Controller controller){
        controller.comunicaAllaView(MessaggioGenerale.CREA_PROPOSTA);
        String luogoScelto=this.scegliLuogo(controller);
        boolean giornoCorretto=false;
        String dataTemp="";
        while(!giornoCorretto){
            ArrayList<Giorno> giorniTemP=new ArrayList();
            controller.comunicaAllaView(MessaggioGenerale.GIORNI_SCAMBIO);
            for(Giorno g:controller.getApp().getConfigurazione().getParametri().getGiorni()) {
                if (!giorniTemP.contains(g)) {
                    giorniTemP.add(g);
                    controller.getView().notifica(g.getNomeGiorno() + "\t");
                }
            }
            controller.comunicaAllaView(MessaggioGenerale.A_CAPO);
            dataTemp=InserimentoDate.inserisciData(controller);
            if(InserimentoDate.checkGiornoSettimana(dataTemp,controller.getApp().getConfigurazione().getParametri().giorniInInteri())){
                giornoCorretto=true;
            }
            if(!giornoCorretto){
                controller.comunicaAllaView(MessaggioErrore.GIORNO_ERRATO_NUOVI);
                for(Giorno gg: giorniTemP){
                    controller.getView().notifica(gg.getNomeGiorno() + "\t");
                }

            }
        }
        boolean compreso=false;
        Orario inizio=null;
        controller.comunicaAllaView(MessaggioGenerale.ORARIO);
        do{
            controller.getView().stampaIntervalliDescription((MessaggioParametri) controller.getApp().getConfigurazione().getParametri().getParametriDefinition());
            boolean finito = false;
            do {
                int ora = controller.richiediInteroView(MessaggioGenerale.ORA_APPUNTAMENTO);
                int minuti = controller.richiediInteroView(MessaggioGenerale.MINUTI_APPUNTAMENTO);
                inizio = new Orario(ora, minuti);
                if (inizio.orarioValido()) {
                    finito = true;
                } else {
                    controller.comunicaAllaView(MessaggioErrore.ORARIO_NON_VALIDO);
                }
            } while (!finito);
            for(Intervallo i:controller.getApp().getConfigurazione().getParametri().getIntervalli()){
                if(inizio.isInsideIntervallo(i.getOre()[0], i.getOre()[1]))
                    compreso=true;
                else
                    controller.comunicaAllaView(MessaggioErrore.ORARIO_NON_PRESENTE);
            }
        }while(!compreso);
        long timeNow= Calendar.getInstance().getTimeInMillis();
        PropostaIncontro proposta=new PropostaIncontro(fruitoreNome, luogoScelto, inizio, dataTemp,timeNow);
        controller.comunicaAllaView(MessaggioGenerale.PROPORSTA_CORRETTA);
        return proposta;
    }

    public String scegliLuogo(Controller controller){
        int count=1;
        for(String s:controller.getApp().getConfigurazione().getParametri().getLuoghi()){
            controller.getView().notifica(count+") "+s);
            count++;
        }
        int indexLuogo=controller.richiediInteroIntervalloView(MessaggioGenerale.SCELTA_LUOGO,1,controller.getApp().getConfigurazione().getParametri().getLuoghi().size());
        return controller.getApp().getConfigurazione().getParametri().getLuoghi().get(indexLuogo-1);
    }


}
