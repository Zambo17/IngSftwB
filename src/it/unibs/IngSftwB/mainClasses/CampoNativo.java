package it.unibs.IngSftwB.mainClasses;
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
     * Metodo per la creazione del campo da parte del configuratore
     * @param nameToCompare ArrayList contenente i nome dei campi nativi già presenti che non possono essere ripetuti
     * @return il campo nativo creato
     */
    public static CampoNativo creaCampo(ArrayList <String> nameToCompare){
        String nome=Utilita.leggiStringaNonVuota("Inserisci nome campo: ");
        boolean diverso=false;
        while(diverso==false){

            if(!nameToCompare.contains(nome)){
                diverso=true;
            }
            if(diverso==false){
                nome=Utilita.leggiStringaNonVuota("Il nome inserito non è valido, inserire un nuovo nome:");
            }
        }
        boolean obbligo=false;
        String choice=Utilita.leggiStringaNonVuota("Inserisci 1 se il campo è obbligatorio, 0 altrimenti:");
        if(choice.equals("1")){
            obbligo=true;
        }
        if(choice.equals("0")){
            obbligo=false;
        }
        CampoNativo c=new CampoNativo(nome, obbligo);
        return c;
    }

    /**
     * Metodo per ottenere il nome del campo
     * @return nome del campo
     */
    public String getNomeCampo() {
        return nomeCampo;
    }

    /**
     * Metodo per ottenere una stringa per la visualizzazione del campo
     * @return stringa contenente le informazione del campo
     */
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append(nomeCampo);
        if(obbligatoria){
            str.append(" compilazione obbligatoria\n");
        }
        else
            str.append(" compilazione facoltativa\n");
        return str.toString();
    }
    //public ArrayList<String> nomiCampi(CampoNativo c)//metodo per ottenre solo la lista di nomi dei campi

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
