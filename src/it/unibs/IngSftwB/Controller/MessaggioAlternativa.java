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
            "4 per cambiare la scadenza: "),
    DETTAGLIO_CATEGORIA("Inserisci 1 se vuoi visualizzare in dettaglio una categoria, 0 altrimenti:");

    private String message;

    MessaggioAlternativa(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
