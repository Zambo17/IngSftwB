package it.unibs.IngSftwB.Controller;

public class MessaggioGiorno implements Messaggio{

    private String messaggio;

    public MessaggioGiorno(String nomeGiorno){
        this.messaggio=nomeGiorno;
    }

    public String getNomeGiorno() {
        return messaggio;
    }
}
