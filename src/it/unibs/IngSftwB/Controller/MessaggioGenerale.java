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
    INSERISCI_ORA_INIZIO("Inserisci l'ora dell'inizio dell'intervallo(compresa tra 0 e 24):"),
    INSERISCI_ORA_FINE("Inserisci l'ora della fine dell'intervallo(compresa tra 0 e 24):"),
    INSERISCI_MINUTI_INIZIO("Inserisci i minuti dell'inizio dell'intervallo(0 oppure 30):"),
    INSERISCI_MINUTI_FINE("Inserisci i minuti della fine dell'intervallo(0 oppure 30):"),
    INSERISCI_GIORNO("Inserisci il nome del giorno in cui vengono effettuati gli scambi:"),
    RIMUOVI_GIORNO("Inserisci il nome del giorno da rimuovere: "),
    NUOVO_LUOGO("Inserisci il nuovo luogo: "),
    RIMUOVI_LUOGO("Inserisci il nome del luogo da togliere"),
    NESSUN_LUOGO("Deve esserci sempre almeno un luogo, quindi aggiungine uno: "),
    NUOVA_SCADENZA("Inserisci la nuova scadenza: "),
    OFFERTE_CATEGORIA("Le offerte di questa categoria sono: "),
    SCELTA_CATEGORIA("Scegli il numero rispettivo alla categoria radice da cui vuoi partire a cercare la categoria voluta: "),
    NUMERO_CATEGORIA("Inserisci il numero della categoria, se nessuna ti va bene premi 0 e si annulla l'operazione corrente: "),
    PERCORSO_FILE("Inserire il percorso del file per esempio: C:\\Users\\apote\\Desktop\\testxml\\testing.xml\nInserisci il nome del file: "),
    ANNULLA_FILE("Caricamento da file annullato"),
    FINE_PROGRAMMA("FINE PROGRAMMA"),
    INSERISCI_NOME("Inserisci il tuo nome: ");


    private String message;


    MessaggioGenerale(String message) {
        this.message = message;
    }


    public String getMessage() {
        return this.message;
    }
}
