package it.unibs.IngSftwB.Controller.AzioniConfiguratore;

import it.unibs.IngSftwB.Controller.AzioneUtente;
import it.unibs.IngSftwB.Controller.Controller;
import it.unibs.IngSftwB.Controller.MessaggioGenerale;
import it.unibs.IngSftwB.Model.Utente;

public class Esci implements AzioneUtente {
    @Override
    public void eseguiAzione(Controller controller, Utente utente) {
        controller.comunicaAllaView(MessaggioGenerale.FINE_PROGRAMMA);
    }

    @Override
    public String getNomeAzione() {
        return "Esci";
    }
}