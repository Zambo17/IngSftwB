package it.unibs.IngSftwB.Controller.AzioniConfiguratore;

import it.unibs.IngSftwB.Controller.*;
import it.unibs.IngSftwB.Model.Applicazione;
import it.unibs.IngSftwB.Model.Categoria;
import it.unibs.IngSftwB.Model.Offerte;
import it.unibs.IngSftwB.Model.Utente;

public class StampaOfferteFoglia implements AzioneUtente {
    @Override
    public void eseguiAzione(Controller controller, Utente utente) {
        Applicazione app=controller.getApp();

        Categoria[] categoriaFoglia= controller.scegliFoglia();
        if(categoriaFoglia[0] != null){
            Offerte tosee=app.getOfferte().offerteFoglia(categoriaFoglia[0].getNome(), categoriaFoglia[1].getNome());
            if(tosee.getListaOfferte().size()!=0){
                controller.comunicaAllaView(MessaggioGenerale.OFFERTE_CATEGORIA);
                controller.getView().stampaOfferteDescription((MessaggioOfferte) app.getOfferte().getOfferteDefinition());
            }
            else{
                controller.comunicaAllaView(MessaggioErrore.NO_OFFERTE_APERTE);
            }
        }
        else{
            controller.comunicaAllaView(MessaggioErrore.VISUALIZZAZIONE_OFFERTE_FALLITA);
        }
    }

    @Override
    public String getNomeAzione() {
        return "Visualizza le offerte di una categoria";
    }
}
