package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.Model.Utente;

public class Accesso {


    public void nuovoUtente(Controller controller,boolean conf){
        String username;
        String password;
        do {
            username = controller.richiediStringaView(MessaggioGenerale.NUOVO_USERNAME);
            if (controller.getApp().getDatiUtenti().checkName(username)) {
                controller.comunicaAllaView(MessaggioGenerale.NOME_NON_DISPONIBILE);
            }
        } while (controller.getApp().getDatiUtenti().checkName(username));

        password = controller.richiediStringaView(MessaggioGenerale.NUOVA_PASSWORD);
        controller.getApp().getDatiUtenti().addUtente(username, password, conf);
    }

    public boolean accessoStandard(Controller controller){
        String username=controller.richiediStringaView(MessaggioGenerale.INSERISCI_NOME);
        String password=controller.richiediStringaView(MessaggioGenerale.INSERISCI_PASSWORD);
        Utente temp=new Utente(username,password);
        if(controller.getApp().getDatiUtenti().checkConf(temp)){
            nuovoUtente(controller,true);
            return true;
        }

        for(Utente utente: controller.getApp().getDatiUtenti().getListaUtenti()){
            if(Utente.sameUtente(utente, temp)){
                controller.comunicaAllaView(MessaggioGenerale.ACCESSO_CORRETTO);
                return true;
            }
        }
        return false;
    }

    public void accessoTentativi(Controller controller){
        for (int i = 0; i < 3; i++) {
            if(this.accessoStandard(controller)){
                return;
            }
            else {
               controller.comunicaAllaView(MessaggioGenerale.CREDENZIALI_ERRATE);
            }
        }

        controller.comunicaAllaView(MessaggioGenerale.ACCESSO_FALLITO);
    }

    public void accessoCompleto(Controller controller){
        int scelta=controller.richiediInteroIntervalloView(MessaggioAlternativa.SCELTA_ACCESSO,0,1);
        if(scelta==1){
            this.nuovoUtente(controller,false);
        }
        else {
            this.accessoTentativi(controller);
        }
    }
}
