package it.unibs.IngSftwB.Model;

import it.unibs.IngSftwB.Controller.ControlloFile;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

// temporanea per implementare accesso
public class Applicazione {

    private Offerte offerte;
    private ListaScambi listaScambi;
    private Configurazione configurazione;
    private DatiUtenti datiUtenti;

    public Applicazione (){};

    public Applicazione(Offerte offerte, ListaScambi listaScambi, Configurazione configurazione, DatiUtenti datiUtenti) {
        this.offerte = offerte;
        this.listaScambi = listaScambi;
        this.configurazione = configurazione;
        this.datiUtenti = datiUtenti;
    }
    public void caricaDati(String fileScambi, String fileOfferte, String fileUtenti, String fileSistema, String fileParametri) throws XMLStreamException {
        if(ControlloFile.fileExists(fileScambi)){
            this.listaScambi=XmlScambi.leggiScambi(fileScambi);
        }
        if(ControlloFile.fileExists(fileUtenti)){
            this.datiUtenti=XmlDatiUtenti.leggiUtenti(fileUtenti);
        }
        if(ControlloFile.fileExists(fileParametri)){
            this.configurazione.setParametri(XmlConfigurazione.leggiParametri(fileParametri));
        }
        if(ControlloFile.fileExists(fileSistema)){
            this.configurazione.setSis(XmlConfigurazione.readSis(fileSistema));
        }
        if(ControlloFile.fileExists(fileOfferte)){
            this.offerte=XmlOfferte.leggiOfferte(fileOfferte);
        }
    }

    public void salvaDati() throws ParserConfigurationException {
        XmlOfferte.salvaOfferte(this.offerte, "offerte.xml");
        XmlConfigurazione.salvaParametri(this.configurazione.getParametri(), "parametriScambio.xml");
        XmlConfigurazione.salvaSistema(this.configurazione.getSis(), "sistema.xml");
        XmlDatiUtenti.utentiWrite(this.datiUtenti, "utenti.xml");
        XmlScambi.scriviScambi(this.listaScambi, "scambi.xml");
    }

    public DatiUtenti getDatiUtenti() {
        return datiUtenti;
    }

    public Offerte getOfferte() {
        return offerte;
    }

    public ListaScambi getListaScambi() {
        return listaScambi;
    }

    public Configurazione getConfigurazione() {
        return configurazione;
    }
}
