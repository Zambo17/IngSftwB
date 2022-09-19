package it.unibs.IngSftwB.Controller.AzioniConfiguratore;

import it.unibs.IngSftwB.Controller.*;
import it.unibs.IngSftwB.Model.*;

import java.util.ArrayList;

public class InserisciGerarchia implements AzioneUtente {

    public void eseguiAzione(Controller controller, Utente utente){

        Applicazione app=controller.getApp();

        String nomeRadice;
        boolean nomeRadiceNuovo=false;
        do{
            nomeRadice=controller.richiediStringaView(MessaggioGenerale.NOME_RADICE);
            if(app.getConfigurazione().getSis().checkNomeNuovoRadice(nomeRadice)){
                nomeRadiceNuovo=true;
            }
            else
                controller.comunicaAllaView(MessaggioErrore.NOME_CATEGORIA_PRESENTE);
        }while(!nomeRadiceNuovo);

        Gerarchia finale= new Gerarchia();
        Categoria r=Categoria.creaCategoria(Categoria.generaCampiIniziali(),nomeRadice);
        finale.getRamo().put(r,null); //controllare che funzioni senza problemi
        finale.setRadice(r);

        int choiceContinue=controller.richiediInteroIntervalloView(MessaggioAlternativa.NUOVA_SOTTOCATEGORIA,0,1);
        while(choiceContinue==1){
            controller.comunicaListaAllaView(finale.listaNomi(),null);

            boolean nomePadreValido=false;
            String nomePadre;
            Categoria padre = new Categoria("","",null);
            do{
                nomePadre=controller.richiediStringaView(MessaggioGenerale.NOME_PADRE);
                if(finale.checkPadreNome(nomePadre)){
                    nomePadreValido=true;
                    padre=finale.findPadre(nomePadre);
                }
                else {
                    controller.comunicaAllaView(MessaggioErrore.PADRE_NON_ESISTE);
                    controller.comunicaListaAllaView(finale.listaNomi(),null);
                }
            }while(!nomePadreValido);
            boolean nomeNuovo=false;
            String nomeCategoria;
            do{
                nomeCategoria=controller.richiediStringaView(MessaggioGenerale.INSERISCI_CATEGORIA);
                if(finale.checkNomeNuovo(nomeCategoria)){
                    nomeNuovo=true;
                }
                else{
                    controller.comunicaAllaView(MessaggioErrore.NOME_CATEGORIA_PRESENTE);
                }
            }while(!nomeNuovo);

            int figli=finale.numFigli(padre);
            if(figli==0){
                controller.comunicaAllaView(MessaggioGenerale.NUMERO_FIGLI);
                finale.getRamo().put(this.creaCategoria(finale.findPadre(nomePadre).getCampiNativi(),nomeCategoria,controller),finale.findPadre(nomePadre));
                //System.out.println("inserire la seconda sottocategoria di: "+nomePadre);
                String nomeCat2=controller.richiediStringaView(MessaggioGenerale.SECONDO_FIGLIO);
                finale.getRamo().put(this.creaCategoria(finale.findPadre(nomePadre).getCampiNativi(),nomeCat2,controller),finale.findPadre(nomePadre));
            }
            else{
                finale.getRamo().put(this.creaCategoria(finale.findPadre(nomePadre).getCampiNativi(),nomeCategoria,controller),finale.findPadre(nomePadre));
            }

            choiceContinue=controller.richiediInteroIntervalloView(MessaggioAlternativa.NUOVA_SOTTOCATEGORIA,0,1);
        }

        app.getConfigurazione().getSis().getListaGerarchie().add(finale);

    }




    public String getNomeAzione(){
        return "Inserimento nuova gerarchia";
    }

    public CampoNativo creaCampoNativo(ArrayList <String> nameToCompare,Controller controller){
        String nome= controller.richiediStringaView(MessaggioGenerale.NOME_CAMPO);
        boolean diverso=false;
        while(diverso==false){

            if(!nameToCompare.contains(nome)){
                diverso=true;
            }
            if(diverso==false){
                nome=controller.richiediStringaView(MessaggioGenerale.CAMPO_GIA_PRESENTE);
            }
        }
        boolean obbligo=false;
        int choice=controller.richiediInteroIntervalloView(MessaggioAlternativa.CAMPO_OBBLIGATORIO,0,1);
        if(choice==1){
            obbligo=true;
        }
        if(choice==0){
            obbligo=false;
        }
        CampoNativo c=new CampoNativo(nome, obbligo);
        return c;
    }

    public Categoria creaCategoria(ArrayList <CampoNativo> campi, String nomeCat,Controller controller){//devo ritornare una categoria

        ArrayList <CampoNativo> nuoviCampi=new ArrayList<>();
        nuoviCampi.addAll(campi);
        int choice;
        String descrizione=controller.richiediStringaView(MessaggioGenerale.DESCRIZIONE_CATEGORIA);
        Categoria creata;
        do {
            choice=controller.richiediInteroIntervalloView(MessaggioAlternativa.NUOVO_CAMPO,0,1);

            if(choice==1) {
                ArrayList<String> nomi = new ArrayList<String>();
                for (CampoNativo x : nuoviCampi) {
                    nomi.add(x.getNomeCampo());
                }
                nuoviCampi.add(this.creaCampoNativo(nomi,controller));
            }
            else
                System.out.println("Questa categoria ha " + nuoviCampi.size() + " campi");

        } while(choice==1);
        creata=new Categoria(nomeCat,descrizione, nuoviCampi);

        return creata;

    }





}
