package it.unibs.IngSftwB.Model;
//v44444
import it.unibs.IngSftwB.Controller.Messaggio;
import it.unibs.IngSftwB.Controller.MessaggioCampoNativo;

import java.util.ArrayList;

/**
 * Classe per la gestione di un campo nativo.
 * @author Jacopo Tedeschi, Enrico Zambelli
 */
public class CampoNativo {
    private String nomeCampo;
    private boolean obbligatoria;

    /**
     * Costruttore di campo nativo
     * @param _nomeCampo nome del campo nativo
     * @param _obbligatoria boolean che è true se la compilazione del campo è obbligatoria false altrimenti
     */
    public CampoNativo(String _nomeCampo, boolean _obbligatoria) {

        this.nomeCampo=_nomeCampo;
        this.obbligatoria=_obbligatoria;
    }


    /**
     * Metodo per ottenere il nome del campo
     * @return nome del campo
     */
    public String getNomeCampo() {
        return nomeCampo;
    }

    /**
     * Metodo per ottenere un boolean che è true se è obbligatorio false altrimenti
     * @return boolean riguardo l'obbligo del campo
     */
    public boolean isObbligatoria() {
        return obbligatoria;
    }

    public Messaggio getCampoNativoDefinition(){
        return new MessaggioCampoNativo(this.nomeCampo, this.obbligatoria);
    }



}
