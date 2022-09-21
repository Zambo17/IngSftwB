package it.unibs.IngSftwB.Controller.AzioniConfiguratore;

import it.unibs.IngSftwB.Controller.AzioneUtente;
import it.unibs.IngSftwB.Controller.Controller;
import it.unibs.IngSftwB.Controller.MessaggioOfferte;
import it.unibs.IngSftwB.Model.Categoria;
import it.unibs.IngSftwB.Model.Offerte;
import it.unibs.IngSftwB.Model.Utente;

public class StampaOfferteChiuseInScambio implements AzioneUtente {
    @Override
    public void eseguiAzione(Controller controller, Utente utente) {
        Categoria[] foglia=controller.scegliFoglia();
        Offerte daVedere=controller.getApp().getOfferte().offerteFoglia(foglia[0].getNome(),foglia[1].getNome());
        daVedere.offerteScambiate();
        controller.getView().stampaOfferteDescription((MessaggioOfferte) daVedere.getOfferteDefinition());
    }

    @Override
    public String getNomeAzione() {
        return "Visualizzare le offerte in scambio o chiuse di una categoria";
    }
}
