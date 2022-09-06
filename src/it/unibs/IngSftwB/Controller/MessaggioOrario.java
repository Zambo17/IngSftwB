package it.unibs.IngSftwB.Controller;

public class MessaggioOrario implements Messaggio{

    private int ora;
    private int minuti;

    public MessaggioOrario(int _ora, int _minuti){
        this.minuti=_minuti;
        this.ora =_ora;
    }

    public int getOra() {
        return ora;
    }

    public int getMinuti() {
        return minuti;
    }
}
