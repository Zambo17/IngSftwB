package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.View.LettoreIntero;
import it.unibs.IngSftwB.View.LettoreStringa;
import it.unibs.IngSftwB.View.View;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

public class Controller {

    private View view;
    /**
     * private Applicazione app;
     */


    public View getView() {
        return view;
    }

    public void comunicaAllaView(Messaggio messaggio){
        this.view.notifica(messaggio);
    }

    public <T> void comunicaListaAllaView(List<T> toPrint, Function<T, Messaggio> toApply) {
        this.view.stampaLista(toPrint, toApply);
    }

    public String richiediStringaView(MessaggioStampabile messaggio){
        return (new LettoreStringa()).leggiStringaNonVuota(messaggio);
    }

    public int richiediInteroView(MessaggioStampabile messaggio){
        return (new LettoreIntero().leggiIntero(messaggio));
    }

    public int richiediInteroIntervalloView(MessaggioStampabile messaggio,int minimo, int massimo){
        return (new LettoreIntero().leggiIntero(messaggio,minimo,massimo));
    }


}
