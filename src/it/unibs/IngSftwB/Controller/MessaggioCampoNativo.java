package it.unibs.IngSftwB.Controller;

public class MessaggioCampoNativo implements Messaggio{

    private String nomeCampo;
    private boolean obbligatoria;

    public MessaggioCampoNativo(String _nomeCampo, boolean _obbligatoria) {

        this.nomeCampo=_nomeCampo;
        this.obbligatoria=_obbligatoria;
    }

    public String getNomeCampo() {
        return nomeCampo;
    }

    public boolean isObbligatoria() {
        return obbligatoria;
    }
}
