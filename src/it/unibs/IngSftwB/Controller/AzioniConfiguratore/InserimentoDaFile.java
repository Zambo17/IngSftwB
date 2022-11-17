package it.unibs.IngSftwB.Controller.AzioniConfiguratore;

import it.unibs.IngSftwB.Controller.*;
import it.unibs.IngSftwB.Model.Utente;
import it.unibs.IngSftwB.Model.XmlConfigurazione;

import javax.xml.stream.XMLStreamException;

public class InserimentoDaFile implements AzioneUtente {
    @Override
    public void eseguiAzione(Controller controller, Utente utente) {
        int sceltaXml= controller.richiediInteroIntervalloView(MessaggioAlternativa.SCELTA_FILE,0,2);
        boolean successo=true;
        try{
            switch(sceltaXml){
                case 1:
                    inserisciGerarchieDaFile(controller);
                    break;
                case 2:
                    inserisciParametriDaFile(controller);
                    break;
                case 0:
                    System.out.println(MessaggioGenerale.ANNULLA_FILE);
                    break;
            }
        } catch (Exception e){
            controller.comunicaAllaView(MessaggioErrore.ERRORE_FILE);
            successo=false;
        }
        if(successo)
            controller.comunicaAllaView(MessaggioGenerale.FILE_CORRETTO);

    }

    public void inserisciGerarchieDaFile(Controller controller) throws XMLStreamException {
        String nomefileSis= controller.richiediStringaView(MessaggioGenerale.PERCORSO_FILE);
        if(ControlloFile.fileExists(nomefileSis) && ControlloFile.isXmlFile(nomefileSis)){
                controller.getApp().getConfigurazione().setSis(XmlConfigurazione.readSis(nomefileSis));
        }
        else{
            controller.comunicaAllaView(MessaggioErrore.FILE_NON_ESISTENTE);
        }
        return;
    }

    public void inserisciParametriDaFile(Controller controller) throws XMLStreamException {
        String nomeFilePar= controller.richiediStringaView(MessaggioGenerale.PERCORSO_FILE);
        if(ControlloFile.fileExists(nomeFilePar) && ControlloFile.isXmlFile(nomeFilePar)){
            controller.getApp().getConfigurazione().setParametri(XmlConfigurazione.leggiParametri(nomeFilePar));


        }
        else{
            controller.comunicaAllaView(MessaggioErrore.FILE_NON_ESISTENTE);
        }
    }

    @Override
    public String getNomeAzione() {
        return "Inserire dati tramite file xml";
    }
}
