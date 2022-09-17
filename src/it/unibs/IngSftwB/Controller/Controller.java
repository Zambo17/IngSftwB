package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.Model.Applicazione;
import it.unibs.IngSftwB.Model.Utente;
import it.unibs.IngSftwB.View.LettoreIntero;
import it.unibs.IngSftwB.View.LettoreStringa;
import it.unibs.IngSftwB.View.View;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

public class Controller {

    private View view;

    private Applicazione app;

    public Applicazione getApp() {
        return app;
    }

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

    public Utente nuovoUtente(boolean conf){
        String username;
        String password;
        do {
            username = this.richiediStringaView(MessaggioGenerale.NUOVO_USERNAME);
            if (this.getApp().getDatiUtenti().checkName(username)) {
                this.comunicaAllaView(MessaggioGenerale.NOME_NON_DISPONIBILE);
            }
        } while (this.getApp().getDatiUtenti().checkName(username));

        password = this.richiediStringaView(MessaggioGenerale.NUOVA_PASSWORD);
        this.getApp().getDatiUtenti().addUtente(username, password, conf);
        return this.getApp().getDatiUtenti().getUtenteDaCredenziali(username,password);
    }

    public Utente accessoStandard(){
        String username=this.richiediStringaView(MessaggioGenerale.INSERISCI_NOME);
        String password=this.richiediStringaView(MessaggioGenerale.INSERISCI_PASSWORD);
        Utente temp=new Utente(username,password);
        if(this.getApp().getDatiUtenti().checkConf(temp)){
            return nuovoUtente(true);
        }

        for(Utente utente: this.getApp().getDatiUtenti().getListaUtenti()){
            if(Utente.sameUtente(utente, temp)){
                this.comunicaAllaView(MessaggioGenerale.ACCESSO_CORRETTO);
                return this.getApp().getDatiUtenti().getUtenteDaCredenziali(username,password);
            }
        }
        return null;
    }

    public Utente accessoTentativi(Controller controller){
        Utente temp;
        for (int i = 0; i < 3; i++) {
            temp = this.accessoStandard();
            if(temp!=null){
                return temp;
            }
            else {
                controller.comunicaAllaView(MessaggioGenerale.CREDENZIALI_ERRATE);
            }
        }

        controller.comunicaAllaView(MessaggioGenerale.ACCESSO_FALLITO);
        return null;
    }

    public Utente accessoCompleto(){
        int scelta=this.richiediInteroIntervalloView(MessaggioAlternativa.SCELTA_ACCESSO,0,1);
        if(scelta==1){
            return this.nuovoUtente(false);
        }
        else {
            return this.accessoTentativi(this);
        }
    }


}
