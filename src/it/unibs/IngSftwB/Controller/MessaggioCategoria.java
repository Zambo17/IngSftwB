package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.mainClasses.CampoNativo;

import java.util.ArrayList;

public class MessaggioCategoria implements Messaggio {

    private MessaggioCampoNativo msg;
    private String nome;
    private String descrizione;
    private ArrayList<CampoNativo> campiNativi= new ArrayList <CampoNativo>();

    public MessaggioCategoria(String _nome, String _descrizione, ArrayList <CampoNativo> _campiNativi, MessaggioCampoNativo msg) {
        this.msg=msg;
        this.nome=_nome;
        this.descrizione=_descrizione;
        this.campiNativi=_campiNativi;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public ArrayList<CampoNativo> getCampiNativi() {
        return campiNativi;
    }

    public MessaggioCampoNativo getMsg() {
        return msg;
    }

}
