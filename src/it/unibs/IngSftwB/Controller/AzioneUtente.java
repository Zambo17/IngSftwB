package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.Model.Utente;

public interface AzioneUtente {

    void eseguiAzione(Controller controller, Utente utente);

    String getNomeAzione();
}
