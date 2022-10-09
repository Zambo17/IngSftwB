package it.unibs.IngSftwB.Model;


import it.unibs.IngSftwB.Controller.AzioneUtente;
import it.unibs.IngSftwB.Controller.AzioniConfiguratore.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe per la gestione del configuratore
 * @author Enrico Zambelli, Jacopo Tedeschi
 */
public class Configuratore extends Utente {
    /**
     * Costruttore di Configuratore
     * @param _username username scelto dal configuratore
     * @param _password password scelta dal configuratore
     */
    public Configuratore(String _username, String _password) {
        super(_username, _password);

    }

    @Override
    public List<AzioneUtente> getMenuUtente() {
        List<AzioneUtente> menu = new LinkedList<>();
        menu.add(new InserisciGerarchia());
        menu.add(new VisualizzazioneGerarchie());
        menu.add(new ModificaParametri());
        menu.add(new StampaOfferteFoglia());
        menu.add(new StampaOfferteChiuseInScambio());
        menu.add(new InserimentoDaFile());
        menu.add(new Esci());
        return menu;
    }

}
