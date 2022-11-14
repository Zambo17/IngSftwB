package it.unibs.IngSftwB.Model;

import it.unibs.IngSftwB.Controller.Messaggio;
import it.unibs.IngSftwB.Controller.MessaggioProposta;

import java.util.ArrayList;
import java.util.Calendar;

public class PropostaIncontro {
    private String nomeFruitore;
    private String luogo;
    private Orario ora;
    private String data;
    private long tempo;

    public PropostaIncontro(String _nomeFruitore, String _luogo, Orario _ora, String _data, long _tempo){
        nomeFruitore=_nomeFruitore;
        luogo=_luogo;
        ora=_ora;
        data=_data;
        tempo=_tempo;
    }

    public long getTempo() {
        return tempo;
    }

    public String getLuogo() {
        return luogo;
    }

    public String getNomeFruitore() {
        return nomeFruitore;
    }

    public Orario getOra() {
        return ora;
    }

    public String getData() {
        return data;
    }

    public Messaggio getPropostaDefinition(){
        return new MessaggioProposta(nomeFruitore,luogo,ora,data,tempo);
    }
}
