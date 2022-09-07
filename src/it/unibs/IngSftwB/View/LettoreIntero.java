package it.unibs.IngSftwB.View;

import it.unibs.IngSftwB.Controller.MessaggioErrore;
import it.unibs.IngSftwB.Controller.MessaggioStampabile;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LettoreIntero {

    public int leggiIntero(MessaggioStampabile messaggio) {
        boolean finito = false;
        int valoreLetto = 0;
        Scanner lettore=new Scanner(System.in);
        do {
            StampaMessaggio.printText(messaggio);
            try {
                valoreLetto = lettore.nextInt();
                lettore.nextLine();
                finito = true;
            } catch (InputMismatchException e) {
                StampaMessaggio.printText(MessaggioErrore.ERRORE_FORMATO);
                String daButtare = lettore.next();
            }
        } while (!finito);
        return valoreLetto;
    }

    public int leggiIntero(MessaggioStampabile messaggio, int minimo, int massimo) {
        boolean finito = false;
        int valoreLetto = 0;
        do {
            valoreLetto = this.leggiIntero(messaggio);
            if (valoreLetto >= minimo && valoreLetto <= massimo)
                finito = true;
            else if (valoreLetto < minimo)
               StampaMessaggio.printText(MessaggioErrore.ERRORE_MINIMO,minimo);
            else
                StampaMessaggio.printText(MessaggioErrore.ERRORE_MASSIMO,massimo);
        } while (!finito);

        return valoreLetto;
    }
}
