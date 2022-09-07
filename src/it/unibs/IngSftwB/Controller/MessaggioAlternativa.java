package it.unibs.IngSftwB.Controller;

public enum MessaggioAlternativa implements MessaggioStampabile{

    DETTAGLIO_CATEGORIA("Inserisci 1 se vuoi visualizzare in dettaglio una categoria, 0 altrimenti:");

    private String message;

    MessaggioAlternativa(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
