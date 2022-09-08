package it.unibs.IngSftwB.View;

import it.unibs.IngSftwB.Controller.MessaggioErrore;
import it.unibs.IngSftwB.Controller.MessaggioStampabile;

import java.util.Scanner;

public class LettoreStringa {

    public String leggiStringaNonVuota(MessaggioStampabile messaggio) {
        boolean finito = false;
        String lettura = null;
        Scanner lettore = new Scanner(System.in);
        do {
            lettura = lettore.nextLine().trim();
            //lettura = lettura.trim();
            if (lettura.length() > 0)
                finito = true;
            else
                StampaMessaggio.printText(MessaggioErrore.ERRORE_STRINGA_VUOTA);
        } while (!finito);

        return lettura;
    }
}
