package it.unibs.IngSftwB.Model;

import it.unibs.IngSftwB.Controller.AzioneUtente;
import it.unibs.IngSftwB.Controller.AzioniConfiguratore.*;
import it.unibs.IngSftwB.Controller.AzioniFruitore.VisualizzaParametriRadici;

import java.util.LinkedList;
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
        List<AzioneUtente> menu = new LinkedList<>();
        menu.add(new VisualizzaParametriRadici());
        menu.add(new Esci());
        return menu;
    }
}

