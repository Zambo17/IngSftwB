package it.unibs.IngSftwB.Controller.AzioniFruitore;

import it.unibs.IngSftwB.Controller.AzioneUtente;
import it.unibs.IngSftwB.Controller.Controller;
import it.unibs.IngSftwB.Controller.MessaggioErrore;
import it.unibs.IngSftwB.Controller.MessaggioGenerale;
import it.unibs.IngSftwB.Model.*;

import java.util.Calendar;

public class ProponiScambio implements AzioneUtente {
    @Override
    public void eseguiAzione(Controller controller, Utente utente) {
        Scambio scambio=this.creaScambio(controller, utente);
        if(scambio!=null){
            controller.getApp().getListaScambi().addScambio(scambio);
        }
    }

    @Override
    public String getNomeAzione() {
        return "Proporre uno scambio";
    }

    public static Scambio creaScambio(Controller controller, Utente f){

        Offerte offerteFruitore=new Offerte(controller.getApp().getOfferte().getOfferteFromFruitore(f.getUsername()));
        offerteFruitore.togliRitirate();
        Offerta daScambiare=null;
        Offerta vorrei=null;
        Scambio toRet=null;
        if(offerteFruitore.getListaOfferte().size()>0){
            controller.comunicaAllaView(MessaggioGenerale.CREA_SCAMBIO);
            controller.comunicaAllaView(MessaggioGenerale.SCELTA_OFFERTA_PROPOSTA);
            daScambiare=controller.scegliOfferta(offerteFruitore);
            Offerte possibiliScambi=controller.getApp().getOfferte().offerteScambiabili(daScambiare);
            if(possibiliScambi.getListaOfferte().size()>0){
                controller.comunicaAllaView(MessaggioGenerale.SCAMBIO_OFFERTA);
                vorrei=controller.scegliOfferta(possibiliScambi);
                controller.getApp().getOfferte().modificaOffertaEsistente(vorrei, StatoOfferta.SELEZIONATA);
                vorrei.cambiaStato(StatoOfferta.SELEZIONATA);
                controller.getApp().getOfferte().modificaOffertaEsistente(daScambiare, StatoOfferta.ACCOPPIATA);
                daScambiare.cambiaStato(StatoOfferta.ACCOPPIATA);
                PropostaIncontro p=null;
                long timeNow= Calendar.getInstance().getTimeInMillis();
                toRet=new Scambio(daScambiare,vorrei,p,timeNow);
                controller.comunicaAllaView(MessaggioGenerale.SCAMBIO_ESEGUITO_CORRETTO);
            }
            else{
                controller.comunicaAllaView(MessaggioErrore.NO_OFFERTE_SCAMBIABILI);
            }
        }
        else{
            controller.comunicaAllaView(MessaggioErrore.NO_OFFERTE);
        }
        return toRet;
    }
}
