package it.unibs.IngSftwB.Controller;

public enum MessaggioErrore implements MessaggioStampabile {
    NOME_NON_DISPONIBILE("Questo nome utente non è disponibile"),
    ERRORE_FORMATO("Attenzione il dato inserito non e' nel formato corretto"),
    ERRORE_MINIMO("Attenzione: e' richiesto un valore maggiore o uguale a "),
    ERRORE_MASSIMO("Attenzione: e' richiesto un valore minore o uguale a "),
    NO_GERARCHIE("Il sistema non ha alcuna gerarchia"),
    NO_OFFERTE("Non ci sono offerte"),
    CREDENZIALI_ERRATE("Le credenziali inserite non sono corrette, hai in totale tre tentativi"),
    ACCESSO_FALLITO("Accesso fallito chiudere il programma"),
    NOME_CATEGORIA_PRESENTE("Questo nome è già presente"),
    PADRE_NON_ESISTE("Tale padre non esiste, scegli uno dei possibili padri:"),
    CATEGORIA_NON_PRESENTE("Categoria non presente"),
    ERRORE_STRINGA_VUOTA("La stringa inserita non può essere vuota");

    private String message;

    MessaggioErrore(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
