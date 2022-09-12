package it.unibs.IngSftwB.Controller;

public enum MessaggioGenerale implements MessaggioStampabile {

    SELEZIONA_INDICE("\nSelezionare un indice."),
    INSERISCI_NOME("Inserisci il tuo nome: ");

    private String message;


    MessaggioGenerale(String message) {
        this.message = message;
    }


    public String getMessage() {
        return this.message;
    }
}
