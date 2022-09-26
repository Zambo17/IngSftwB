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
    ORARIO_NON_VALIDO("L'orario inserito non è valido"),
    INTERVALLO_NON_VALIDO("L'intervallo inserito non è valido o è già presente"),
    NESSUN_ORARIO("Ci deve essere almeno un intervallo, quindi aggiungine uno:"),
    INTERVALLO_NON_PRESENTE("L'intervallo inserito non è presente"),
    GIORNO_NON_PRESENTE("Il giorno inserito è inesistente"),
    GIORNO_GIA_PRESENTE("Questo giorno è già stato inserito"),
    GIORNO_ERRATO("Il giorno inserito non è presente o il dato inserito non è corretto"),
    NESSUN_GIORNO("Deve esserci almeno un giorno quindi inserisci un giorno"),
    LUOGO_NON_PRESENTE("Questo luogo non è tra i luoghi presenti"),
    NO_OFFERTE_APERTE("Non ci sono offerte aperte relative a questa categoria"),
    VISUALIZZAZIONE_OFFERTE_FALLITA("Visualizzazione offerte fallita"),
    ANNULLA_SELEZIONE("Selezione categoria annullata"),
    FILE_NON_ESISTENTE("File non esistente o di un formato sbagliato"),
    PARAMETRI_NON_SETTATI("I parametri di configurazione non sono ancora stati settati"),
    APP_NON_SETTATA("L'applicazione non è stata settata dal configuratore quindi non puoi pubblicare offerte per ora"),
    ERRORE_STRINGA_VUOTA("La stringa inserita non può essere vuota");

    private String message;

    MessaggioErrore(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
