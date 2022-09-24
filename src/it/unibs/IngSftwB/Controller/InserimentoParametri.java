package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.Controller.AzioniConfiguratore.ModificaParametri;
import it.unibs.IngSftwB.Model.Giorno;
import it.unibs.IngSftwB.Model.Intervallo;
import it.unibs.IngSftwB.Model.ParametriScambi;
import it.unibs.IngSftwB.Model.Utilita;

import java.util.ArrayList;

public class InserimentoParametri {

    public static ParametriScambi inserimentoParametri(Controller controller){
        String piazza = controller.richiediStringaView(MessaggioGenerale.CITTA_SCAMBI);
        int scelta = 1;
        ArrayList<String> luoghi = new ArrayList<>();
        while (scelta != 0) {
            luoghi.add(ModificaParametri.addLuogo(controller,MessaggioGenerale.LUOGO_SCAMBI));
            scelta = controller.richiediInteroIntervalloView(MessaggioAlternativa.ALTRO_LUOGO, 0, 1);
        }
        ArrayList<Giorno> giorni = new ArrayList<>();
        scelta = 1;
        while (scelta != 0) {
            controller.getApp().getConfigurazione().getParametri().addGiorno(ModificaParametri.addGiorno(controller));
            scelta = controller.richiediInteroIntervalloView(MessaggioAlternativa.ALTRO_GIORNO, 0, 1);
        }

        ArrayList<Intervallo> intervalli = new ArrayList<>();
        scelta = 1;
        while (scelta != 0) {
            controller.getApp().getConfigurazione().getParametri().addIntervallo(ModificaParametri.addIntervallo(controller));
            scelta = controller.richiediInteroIntervalloView(MessaggioAlternativa.ALTRO_INTERVALLO,0,1);
        }

        controller.getApp().getConfigurazione().getParametri().setScadenza(ModificaParametri.addScadenza(controller));

        return new ParametriScambi(piazza,);
    }

}
