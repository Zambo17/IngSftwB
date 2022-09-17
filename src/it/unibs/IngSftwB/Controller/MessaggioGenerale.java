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
    NOME_CAMPO("Inserisci nome campo: "),
    CAMPO_GIA_PRESENTE("Il nome inserito non è valido, inserire un nuovo nome:"),
    DESCRIZIONE_CATEGORIA("Inserisci la descrizione della categoria:"),
    NOME_RADICE("Inserisci il nome della radice della gerarchia:"),
    NOME_CATEGORIA_PRESENTE("Questo nome è già presente"),
    INSERISCI_NOME("Inserisci il tuo nome: ");


    private String message;


    MessaggioGenerale(String message) {
        this.message = message;
    }


    public String getMessage() {
        return this.message;
    }
}
