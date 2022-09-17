package it.unibs.IngSftwB.Controller.AzioniConfiguratore;

import it.unibs.IngSftwB.Controller.AzioneUtente;
import it.unibs.IngSftwB.Controller.Controller;
import it.unibs.IngSftwB.Controller.MessaggioAlternativa;
import it.unibs.IngSftwB.Controller.MessaggioGenerale;
import it.unibs.IngSftwB.Model.*;

import java.util.ArrayList;

public class InserisciGerarchia implements AzioneUtente {

    public void eseguiAzione(Controller controller, Utente utente){
        /*
        Applicazione app=controller.getApp();

        String nomeRadice;
        boolean nomeRadiceNuovo=false;
        do{
            nomeRadice=controller.richiediStringaView(MessaggioGenerale.NOME_RADICE);
            if(app.getConfigurazione().getSis().checkNomeNuovoRadice(nomeRadice)){
                nomeRadiceNuovo=true;
            }
            else
                controller.comunicaAllaView(MessaggioGenerale.NOME_CATEGORIA_PRESENTE);
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
                nomePadre=Utilita.leggiStringaNonVuota("Inserisci il nome del padre:");
                if(finale.checkPadreNome(nomePadre)){
                    nomePadreValido=true;
                    padre=finale.findPadre(nomePadre);
                }
                else {
                    System.out.println("Tale padre non esiste, scegli uno dei possibili padri:");
                    System.out.println(finale.vediPadri());
                }
            }while(nomePadreValido==false);
            boolean nomeNuovo=false;
            String nomeCatgoria;
            do{
                nomeCatgoria=Utilita.leggiStringaNonVuota("Inserisci il nome della categoria:");
                if(finale.checkNomeNuovo(nomeCatgoria)){
                    nomeNuovo=true;
                }
                else{
                    System.out.println("Nome non valido");
                }
            }while(!nomeNuovo);

            int figli=finale.numFigli(padre);
            if(figli==0){
                System.out.println("Si devono inserire almeno 2 sottocategorie perchè il padre non ne ha nessuna per ora");
                finale.ramo.put(Categoria.creaCategoria(finale.findPadre(nomePadre).getCampiNativi(),nomeCatgoria),finale.findPadre(nomePadre));
                //System.out.println("inserire la seconda sottocategoria di: "+nomePadre);
                String nomeCat2=Utilita.leggiStringaNonVuota("Inserire il nome della seconda sottocategoria di "+ nomePadre+": ");
                finale.ramo.put(Categoria.creaCategoria(finale.findPadre(nomePadre).getCampiNativi(),nomeCat2),finale.findPadre(nomePadre));
            }
            else{
                finale.ramo.put(Categoria.creaCategoria(finale.findPadre(nomePadre).getCampiNativi(),nomeCatgoria),finale.findPadre(nomePadre));
            }



            choiceContinue=Utilita.leggiStringaNonVuota("Inserisci 1 se vuoi inserire un'altra sottocategoria, 0 altrimenti:");
        }

        return finale;
        }


         */
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
