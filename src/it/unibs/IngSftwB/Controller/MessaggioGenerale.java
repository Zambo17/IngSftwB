package it.unibs.IngSftwB.Controller;

public enum MessaggioGenerale implements MessaggioStampabile {

    SELEZIONA_INDICE("\nSelezionare un indice."),
    NUOVO_USERNAME("Inserisci il nome con cui vuoi registrarti:"),
    NUOVA_PASSWORD("Inserisci la tua nuova password:"),
    INSERISCI_PASSWORD("Inserisci la tua password: "),
    ACCESSO_CORRETTO("Accesso eseguito con successo"),
    NOME_CAMPO("Inserisci nome campo: "),
    CAMPO_GIA_PRESENTE("Il nome inserito non è valido, inserire un nuovo nome:"),
    DESCRIZIONE_CATEGORIA("Inserisci la descrizione della categoria:"),
    NOME_RADICE("Inserisci il nome della radice della gerarchia:"),
    NOME_PADRE("Inserisci il nome del padre:"),
    INSERISCI_CATEGORIA("Inserisci il nome della categoria:"),
    NUMERO_FIGLI("Si devono inserire almeno 2 sottocategorie perchè il padre non ne ha nessuna per ora"),
    SECONDO_FIGLIO("Inserire il nome della seconda sottocategoria della categoria corrente: "),
    NUMERO_GERARCHIA("Inserisci il numero della gerarchia alla quale appartiene la categoria che vuoi vedere nel dettaglio: "),
    VISUALIZZA_CATEGRORIA("Inserisci il nome della categoria da visualizzare: "),
    PARAMETRI("Questi sono i parametri:\n"),
    INSERISCI_NOME("Inserisci il tuo nome: ");


    private String message;


    MessaggioGenerale(String message) {
        this.message = message;
    }


    public String getMessage() {
        return this.message;
    }
}
