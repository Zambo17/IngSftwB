package it.unibs.IngSftwB.Model;

import it.unibs.IngSftwB.Controller.AzioneUtente;

import java.util.List;

/**
 * Classe per la gestione del fruitore
 * @author Jacopo Tedeschi, Enrico Zambelli
 */
public class Fruitore extends Utente{

    /**
     * Costruttore della classe utente
     * @param _username il nome del fruitore
     * @param _password la password del fruitore
     */
    public Fruitore(String _username, String _password) {
        super(_username, _password);
    }

    @Override
    public List<AzioneUtente> getMenuUtente() {
        return null;
    }
}

