package it.unibs.IngSftwB.Controller;

public class MessaggioCustom implements MessaggioStampabile{

    private String message;

    public MessaggioCustom(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public String toString() {
        return this.message;
    }
}
