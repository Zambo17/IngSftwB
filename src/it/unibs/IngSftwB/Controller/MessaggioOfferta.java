package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.mainClasses.CampoNativo;
import it.unibs.IngSftwB.mainClasses.StatoOfferta;

import java.util.ArrayList;
import java.util.HashMap;

public class MessaggioOfferta implements Messaggio{

    private String nomeCategoria;
    private String nomeRadice;
    private HashMap<CampoNativo, String> compliazioni=new HashMap<>();
    private StatoOfferta statoAttuale;
    private String nomeFruitore;

    public MessaggioOfferta(String _nomeCategoria, HashMap <CampoNativo, String> _compilazioni, StatoOfferta _statoAttuale, String _nomeFruitore, String _nomeRadice ){
        this.nomeCategoria=_nomeCategoria;
        this.compliazioni=_compilazioni;
        this.statoAttuale=_statoAttuale;
        this.nomeFruitore=_nomeFruitore;
        this.nomeRadice=_nomeRadice;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public String getNomeRadice() {
        return nomeRadice;
    }

    public HashMap<CampoNativo, String> getCompliazioni() {
        return compliazioni;
    }

    public StatoOfferta getStatoAttuale() {
        return statoAttuale;
    }

    public String getNomeFruitore() {
        return nomeFruitore;
    }
}
