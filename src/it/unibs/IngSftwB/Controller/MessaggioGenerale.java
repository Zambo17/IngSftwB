package it.unibs.IngSftwB.Controller;

public enum MessaggioGenerale implements MessaggioStampabile {

    SELEZIONA_INDICE("\nSelezionare un indice."),
    NUOVO_USERNAME("Inserisci il nome con cui vuoi registrarti:"),
    NOME_NON_DISPONIBILE("Questo nome utente non è disponibile"),
    NUOVA_PASSWORD("Inserisci la tua nuova password:"),
    INSERISCI_PASSWORD("Inserisci la tua password: "),
    ACCESSO_CORRETTO("Accesso eseguito con successo"),
    CREDENZIALI_ERRATE("Le credenziali inserite non sono corrette, hai in totale tre tentativi"),
    ACCESSO_FALLITO("Accesso fallito chiudere il programma"),

    INSERISCI_NOME("Inserisci il tuo nome: ");


    private String message;


    MessaggioGenerale(String message) {
        this.message = message;
    }


    public String getMessage() {
        return this.message;
    }
}
