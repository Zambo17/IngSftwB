package it.unibs.IngSftwB.Controller;

public enum MessaggioErrore implements MessaggioStampabile {
    NOME_NON_DISPONIBILE("Questo nome utente non è disponibile"),
    ERRORE_FORMATO("Attenzione il dato inserito non e' nel formato corretto"),
    ERRORE_MINIMO("Attenzione: e' richiesto un valore maggiore o uguale a "),
    ERRORE_MASSIMO("Attenzione: e' richiesto un valore minore o uguale a "),
    ERRORE_STRINGA_VUOTA("La stringa inserita non può essere vuota");

    private String message;

    MessaggioErrore(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
