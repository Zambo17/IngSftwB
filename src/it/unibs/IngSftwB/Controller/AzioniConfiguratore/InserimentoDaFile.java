package it.unibs.IngSftwB.Controller.AzioniConfiguratore;

import it.unibs.IngSftwB.Controller.*;
import it.unibs.IngSftwB.Model.Utente;
import it.unibs.IngSftwB.Model.XmlConfigurazione;
import it.unibs.IngSftwB.xmlUtilities.XmlReader;

import javax.xml.stream.XMLStreamException;

public class InserimentoDaFile implements AzioneUtente {
    @Override
    public void eseguiAzione(Controller controller, Utente utente) {
        int sceltaXml= controller.richiediInteroIntervalloView(MessaggioAlternativa.SCELTA_FILE,0,2);
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
        } catch (XMLStreamException e){
            e.printStackTrace();
        }

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
            controller.comunicaAllaView(MessaggioGenerale.FILE_CORRETTO);

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
