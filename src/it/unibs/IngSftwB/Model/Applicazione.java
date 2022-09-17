package it.unibs.IngSftwB.Model;

// temporanea per implementare accesso
public class Applicazione {

    private Offerte offerte;
    private ListaScambi listaScambi;
    private Configurazione configurazione;
    private DatiUtenti datiUtenti;

    public Applicazione(Offerte offerte, ListaScambi listaScambi, Configurazione configurazione, DatiUtenti datiUtenti) {
        this.offerte = offerte;
        this.listaScambi = listaScambi;
        this.configurazione = configurazione;
        this.datiUtenti = datiUtenti;
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
