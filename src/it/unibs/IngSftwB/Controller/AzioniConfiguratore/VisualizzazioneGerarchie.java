package it.unibs.IngSftwB.Controller.AzioniConfiguratore;

import it.unibs.IngSftwB.Controller.*;
import it.unibs.IngSftwB.Model.*;

public class VisualizzazioneGerarchie implements AzioneUtente {

    public void eseguiAzione(Controller controller, Utente utente) {
        Applicazione app = controller.getApp();
        controller.getView().stampaSistemaDescription((MessaggioSistema) app.getConfigurazione().getSis().getSistemaDefinition());
        if(app.getConfigurazione().getSis().getListaGerarchie().size()==0){
            return;
        }
        int sceltaCat=0;
        do{
            sceltaCat= controller.richiediInteroIntervalloView(MessaggioAlternativa.VEDI_CATEGORIA, 0, 1);
            if(sceltaCat==1){
                Categoria toSee=this.leggiCategoria(controller);
                if(toSee!=null){
                    controller.getView().stampaCategoriaDescription((MessaggioCategoria) toSee.getCategoriaDefinition());
                }
            }


        }while(sceltaCat==1);
    }


    public String getNomeAzione() {
        return "Visualizzazione delle gerarchie";
    }

    public Categoria leggiCategoria(Controller controller){
        Categoria trovata=null;
        int numCat=controller.richiediInteroIntervalloView(MessaggioGenerale.NUMERO_GERARCHIA, 1, controller.getApp().getConfigurazione().getSis().getListaGerarchie().size());
        String nome = controller.richiediStringaView(MessaggioGenerale.VISUALIZZA_CATEGRORIA);
        trovata = controller.getApp().getConfigurazione().getSis().findCategoria(nome, numCat);
        if (trovata == null) {
            controller.comunicaAllaView(MessaggioErrore.CATEGORIA_NON_PRESENTE);
        }
        return trovata;
    }
}
