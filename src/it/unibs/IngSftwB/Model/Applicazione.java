package it.unibs.IngSftwB.Model;

// temporanea per implementare accesso
public class Applicazione {

    private Offerte offerte;
    private ListaScambi listaScambi;
    private Configurazione configurazione;
    private DatiUtenti datiUtenti;

    public Applicazione(DatiUtenti datiUtenti) {
        this.datiUtenti = datiUtenti;
    }

    public DatiUtenti getDatiUtenti() {
        return datiUtenti;
    }
}
