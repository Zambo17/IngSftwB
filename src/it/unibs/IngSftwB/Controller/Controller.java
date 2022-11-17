package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.Controller.AzioniConfiguratore.Esci;
import it.unibs.IngSftwB.Controller.AzioniConfiguratore.InserimentoDaFile;
import it.unibs.IngSftwB.Controller.AzioniConfiguratore.InserisciGerarchia;
import it.unibs.IngSftwB.Model.*;
import it.unibs.IngSftwB.View.LettoreIntero;
import it.unibs.IngSftwB.View.LettoreStringa;
import it.unibs.IngSftwB.View.View;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
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

    public Controller(View view, Applicazione app) {
        this.view = view;
        this.app = app;
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

    public void run() throws IOException, XMLStreamException, ParserConfigurationException {
        //caricare dati da file
        try{
            this.app.caricaDati("scambi.xml","offerte.xml","utenti.xml","sistema.xml","parametriScambio.xml");
        }catch(Exception e){
            this.comunicaAllaView(MessaggioErrore.ERRORE_FILE);
        }

        Utente acceduto = this.accessoCompleto();
        //controllare se i file sono vuoti e agire di conseguenza
        if(acceduto==null){
            return;
        }
        this.controlliIniziali(acceduto);

        this.comunicaAllaView(MessaggioGenerale.BENVENUTO);
        this.eseguiMenuAzioni(acceduto.getMenuUtente(),acceduto);
        this.app.salvaDati();
    }

    public void eseguiMenuAzioni(List<AzioneUtente> menuUtente, Utente u) throws IOException {
        AzioneUtente chosen;
        do {
            this.comunicaAllaView(MessaggioGenerale.INIZIO_MENU);
            chosen = this.view.scegli(menuUtente, AzioneUtente::getNomeAzione);
            chosen.eseguiAzione(this, u);
        } while (!(chosen instanceof Esci));
    }

    private void controlliIniziali(Utente acceduto) throws XMLStreamException {
        if(this.getApp().getConfigurazione().getParametri()==null && acceduto instanceof Configuratore){
            this.primoAccessoConfiguratoreParametri();
        }

        if(this.getApp().getConfigurazione().getSis().getListaGerarchie().size()==0 && acceduto instanceof Configuratore){
            this.primoAccessoConfiguratoreGerarchia();
        }

        if(this.getApp().getListaScambi()!=null && this.getApp().getOfferte().getListaOfferte().size()!=0){
            if(this.getApp().getListaScambi().getScambi().size()>0)
                this.getApp().getListaScambi().controllaValiditaScambi(this.getApp().getConfigurazione().getParametri(), this.getApp().getOfferte());
        }
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
        //Utente temp=new Utente(username,password);
        if(this.getApp().getDatiUtenti().checkConf(username,password)){
            return nuovoUtente(true);
        }

        for(Utente utente: this.getApp().getDatiUtenti().getListaUtenti()){
            if(utente.sameUtente(username, password)){
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
        int sceltaGer =this.getView().scegliIntero(MessaggioGenerale.SCELTA_CATEGORIA,this.getApp().getConfigurazione().getSis().getListaGerarchie(),e -> this.view.getCategoriaDescription((MessaggioCategoria) e.getRadice().getCategoriaDefinition()),1);
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
        int sceltaFoglia =this.getView().scegliIntero(MessaggioGenerale.NUMERO_CATEGORIA,foglie,e -> this.view.getCategoriaDescription((MessaggioCategoria) e.getCategoriaDefinition()),0);

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


    public void primoAccessoConfiguratoreParametri() throws XMLStreamException {
        int sceltaPar=this.richiediInteroIntervalloView(MessaggioAlternativa.PARAMETRI_VUOTI,1,2);
        if(sceltaPar==2){
            String nomefilePar=this.richiediStringaView(MessaggioGenerale.PERCORSO_FILE);
            boolean fileCorretto=true;
            if(ControlloFile.fileExists(nomefilePar) && ControlloFile.isXmlFile(nomefilePar)){
                try{
                    this.getApp().getConfigurazione().setParametri(XmlConfigurazione.leggiParametri(nomefilePar));
                }catch(Exception e) {
                    fileCorretto = false;
                }
            }
            else{
                fileCorretto=false;
            }
            if(!fileCorretto){
                this.comunicaAllaView(MessaggioErrore.FILE_NON_ESISTENTE);
                app.getConfigurazione().setParametri(InserimentoParametri.inserimentoParametri(this));
            }
            else
                this.comunicaAllaView(MessaggioGenerale.FILE_CORRETTO);
        }
        else{
            app.getConfigurazione().setParametri(InserimentoParametri.inserimentoParametri(this));
        }
    }

    private void primoAccessoConfiguratoreGerarchia() throws XMLStreamException {
        int scelta=this.richiediInteroIntervalloView(MessaggioAlternativa.ZERO_GERARCHIE,1,2);
        if(scelta==2){
            String nomeFileGer=this.richiediStringaView(MessaggioGenerale.PERCORSO_FILE);
            boolean fileCorretto=true;
            if(ControlloFile.fileExists(nomeFileGer) && ControlloFile.isXmlFile(nomeFileGer)){
                try{
                    this.getApp().getConfigurazione().setSis(XmlConfigurazione.readSis(nomeFileGer));
                }catch(Exception e){
                    fileCorretto=false;
                }
            }
            else{
                fileCorretto=false;

            }
            if(!fileCorretto){
                this.comunicaAllaView(MessaggioErrore.FILE_NON_ESISTENTE);
                InserisciGerarchia i=new InserisciGerarchia();
                i.eseguiAzione(this,null);
            }
            else{
                this.comunicaAllaView(MessaggioGenerale.FILE_CORRETTO);
            }
        }
        else{
            InserisciGerarchia i=new InserisciGerarchia();
            i.eseguiAzione(this,null);
        }
    }

    public Offerta scegliOfferta(Offerte offerte){
       this.comunicaAllaView(MessaggioGenerale.OFFERTE);
       this.getView().stampaOfferteDescription((MessaggioOfferte) offerte.getOfferteDefinition());
        int scelta=this.richiediInteroIntervalloView(MessaggioGenerale.SCELTA_OFFERTA,0, offerte.getListaOfferte().size());
        return offerte.getListaOfferte().get(scelta);
    }

}

