package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.mainClasses.Orario;

public class MessaggioIncontro implements Messaggio {

    private String nomeFruitore;
    private String luogo;
    private Orario ora;
    private String data;
    private long tempo;

    public MessaggioIncontro(String _nomeFruitore, String _luogo, Orario _ora, String _data, long _tempo){
        nomeFruitore=_nomeFruitore;
        luogo=_luogo;
        ora=_ora;
        data=_data;
        tempo=_tempo;
    }

    public String getNomeFruitore() {
        return nomeFruitore;
    }

    public String getLuogo() {
        return luogo;
    }

    public Orario getOra() {
        return ora;
    }

    public String getData() {
        return data;
    }

    public long getTempo() {
        return tempo;
    }
}
