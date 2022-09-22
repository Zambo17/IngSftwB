package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.Controller.AzioniConfiguratore.Esci;
import it.unibs.IngSftwB.Model.*;
import it.unibs.IngSftwB.View.LettoreIntero;
import it.unibs.IngSftwB.View.LettoreStringa;
import it.unibs.IngSftwB.View.View;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
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

    public void run() throws IOException {
        //caricare dati da file
        Utente acceduto = this.accessoTentativi();
        //controllare se i file sono vuoti e agire di conseguenza
        if(acceduto==null){
            return;
        }

        this.eseguiMenuAzioni(acceduto.getMenuUtente(),acceduto);
    }

    public void eseguiMenuAzioni(List<AzioneUtente> menuUtente, Utente u) throws IOException {
        AzioneUtente chosen;
        do {
            chosen = this.view.scegli(menuUtente, AzioneUtente::getNomeAzione);
            chosen.eseguiAzione(this, u);
        } while (!(chosen instanceof Esci));
    }

    public Utente nuovoUtente(boolean conf){
        String username;
        String password;
        do {
            username = this.richiediStringaView(MessaggioGenerale.NUOVO_USERNAME);
            if (this.getApp().getDatiUtenti().checkName(username)) {
                this.comunicaAllaView(MessaggioErrore.NOME_NON_DISPONIBILE);
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

    public Utente accessoTentativi(){
        Utente temp;
        for (int i = 0; i < 3; i++) {
            temp = this.accessoStandard();
            if(temp!=null){
                return temp;
            }
            else {
                this.comunicaAllaView(MessaggioErrore.CREDENZIALI_ERRATE);
            }
        }

        this.comunicaAllaView(MessaggioErrore.ACCESSO_FALLITO);
        return null;
    }

    public Utente accessoCompleto(){
        int scelta=this.richiediInteroIntervalloView(MessaggioAlternativa.SCELTA_ACCESSO,0,1);
        if(scelta==1){
            return this.nuovoUtente(false);
        }
        else {
            return this.accessoTentativi();
        }
    }


    public Categoria[] scegliFoglia() {

        Categoria[] questaRadice = new Categoria[2];
        /*
        int count = 1;
        for (Gerarchia g : this.getApp().getConfigurazione().getSis().getListaGerarchie()) {
            this.comunicaAllaView("\n" + count+")  ");
            System.out.println(g.getRadice().toStringCategoria());
            count++;
        }

        int sceltaGer = Utilita.leggiIntero("Scegli il numero rispettivo alla categoria radice da cui vuoi partire a cercare la categoria voluta: ", 0, this.listaGerarchie.size());

         */
        int sceltaGer =this.getView().scegliIntero(MessaggioGenerale.SCELTA_CATEGORIA,this.getApp().getConfigurazione().getSis().getListaGerarchie(),e -> this.view.getCategoriaDescription((MessaggioCategoria) e.getRadice().getCategoriaDefinition()));
        boolean fineScelta = false;
        ArrayList<Categoria> foglie=this.getApp().getConfigurazione().getSis().getListaGerarchie().get(sceltaGer-1).listaFoglie();
        /*
        int countF=1;
        for(Categoria f:foglie){
            System.out.print(countF+")  ");
            System.out.println(f.toStringCategoria());
            countF++;
        }
        int sceltaFoglia = Utilita.leggiIntero("Inserisci il numero della categoria, se nessuna ti va bene premi 0 e si annulla l'operazione corrente: ", 0, foglie.size());
        */
        int sceltaFoglia =this.getView().scegliIntero(MessaggioGenerale.NUMERO_CATEGORIA,foglie,e -> this.view.getCategoriaDescription((MessaggioCategoria) e.getCategoriaDefinition()));

        if(sceltaFoglia!=0){
            questaRadice[0]=foglie.get(sceltaFoglia-1);
            questaRadice[1]=this.getApp().getConfigurazione().getSis().getListaGerarchie().get(sceltaGer-1).getRadice();
        }
        else{
            comunicaAllaView(MessaggioErrore.ANNULLA_SELEZIONE);
            questaRadice[0]=null;
            questaRadice[1]=null;
        }
        return questaRadice;
    }

}

