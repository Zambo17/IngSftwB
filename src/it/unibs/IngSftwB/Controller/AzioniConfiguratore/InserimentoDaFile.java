package it.unibs.IngSftwB.Controller.AzioniConfiguratore;

import it.unibs.IngSftwB.Controller.*;
import it.unibs.IngSftwB.Model.Utente;
import it.unibs.IngSftwB.xmlUtilities.XmlReader;

import javax.xml.stream.XMLStreamException;

public class InserimentoDaFile implements AzioneUtente {
    @Override
    public void eseguiAzione(Controller controller, Utente utente) {
        int sceltaXml= controller.richiediInteroIntervalloView(MessaggioAlternativa.SCELTA_FILE,0,2);
        try{
            switch(sceltaXml){
                case 1:
                    String nomeFileSis=controller.richiediStringaView(MessaggioGenerale.PERCORSO_FILE);
                    if(ControlloFile.fileExists(nomeFileSis) && ControlloFile.isXmlFile(nomeFileSis)){
                        controller.getApp().getConfigurazione().setSis(XmlReader.readSis(nomeFileSis));
                    }
                    else{
                        controller.comunicaAllaView(MessaggioErrore.FILE_NON_ESISTENTE);
                    }
                    break;
                case 2:
                    String nomefilePar=controller.richiediStringaView(MessaggioGenerale.PERCORSO_FILE);
                    if(ControlloFile.fileExists(nomefilePar) && ControlloFile.isXmlFile(nomefilePar)){
                            controller.getApp().getConfigurazione().setParametri(XmlReader.leggiParametri(nomefilePar));
                    }
                    else{
                        controller.comunicaAllaView(MessaggioErrore.FILE_NON_ESISTENTE);
                    }
                    break;
                case 0:
                    System.out.println(MessaggioGenerale.ANNULLA_FILE);
                    break;
            }
        } catch (XMLStreamException e){
            e.printStackTrace();
        }

    }

    @Override
    public String getNomeAzione() {
        return "Inserire dati tramite file xml";
    }
}
