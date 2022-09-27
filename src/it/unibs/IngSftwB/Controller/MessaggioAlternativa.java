package it.unibs.IngSftwB.Controller;

public enum MessaggioAlternativa implements MessaggioStampabile{

    SCELTA_ACCESSO("Inserisci 1 se sei un nuovo fruitore e vuoi registrarti, 0 altrimenti: "),
    CAMPO_OBBLIGATORIO("Inserisci 1 se il campo è obbligatorio, 0 altrimenti:"),
    NUOVO_CAMPO("Inserisci 1 se vuoi aggiungere un nuovo campo, 0 altrimenti: "),
    NUOVA_SOTTOCATEGORIA("Inserisci 1 se vuoi inserire una sottocategoria, 0 altrimenti:"),
    VEDI_CATEGORIA("Inserisci 1 se vuoi visualizzare in dettaglio una categoria, 0 altrimenti:"),
    AGGIUNTA_RIMOZIONE("Inserisci 1 se vuoi aggiungere 2 se vuoi togliere: "),
    SCELTA_PARAMETRO("Inserisci\n1 per modificare i luoghi\n" +
            "2 per modificare gli intervalli\n" +
            "3 per modificare i giorni\n" +
            "4 per cambiare la scadenza \n" +
            "5 per annullare la modifica dei parametri :"),
    ZERO_GERARCHIE("Non è presente alcune gerarchia\n premere 1 per inserirla tramite l'applicazione\n premere 2 per inserirla tramite file xml"),
    PARAMETRI_VUOTI("I parametri di sistema non sono ancora stati configurati\n premere 1 per inserirli tramite l'applicazione\n premere 2 per inserirli tramite file xml"),
    ALTRO_LUOGO("Inserisci 1 per aggiungere un altro luogo, 0 altrimenti:"),
    ALTRO_GIORNO("Inserisci 1 per aggiungere un altro giorno, 0 altrimenti:"),
    ALTRO_INTERVALLO("Inserisci 1 per introdurre un nuovo intervallo, 0 altrimenti:"),
    RIPROVA_OFFERTA("La categoria inserita non esiste o non è una foglia, se vuoi riprovare premi 1 altrimenti 0:"),
    SCELTA_SCAMBI("Inserisci 1 se vuoi vedere gli scambi che hai proposto\n          0 se vuoi vedere quelli che ti sono stati proposti: "),
    SCELTA_RISPOSTA("Inserisci 1 se vuoi rispondere a questa offerta, 0 altrimenti"),
    SCELTA_PROPOSTA("Se vuoi accettare questo incontro premi 1, se vuoi proporne un altro premi 2, se vuoi non rispondere premi 0"),
    SCELTA_FILE("Attenzione inserire i dati da file xml può portare a errori consigliamo massima attenzione nella scrittura del file\n premere 1 per inserire le gerarchie\n premere 2 per inserire parametri di configurazione\n premere 0 per non inserire nessun file"),
    DETTAGLIO_CATEGORIA("Inserisci 1 se vuoi visualizzare in dettaglio una categoria, 0 altrimenti:");

    private String message;

    MessaggioAlternativa(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
