package it.unibs.IngSftwB.Model;

public class Applicazione implements XmlWrite,XmlRead {
    //classe contenitrice di dati utenti, configurazione, lista scambi
    //implementa interfaccia xml per caricare dai file
    //all'inizio si fa il boot dei dati salvati usando le interfaccie, poi si crea l'oggetto controller
    //che ha i dati caricati nell'oggetto applicazione e l'oggetto.
    private Configurazione configurazione;
    private DatiUtenti datiUtenti;
    private ListaScambi listaScambi;

    public Applicazione (Configurazione _configurazione, DatiUtenti _datiUtenti, ListaScambi _listaScambi){
        this.configurazione=_configurazione;
        this.datiUtenti=_datiUtenti;
        this.listaScambi=_listaScambi;
    }
}
