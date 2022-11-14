package it.unibs.IngSftwB.Model;


import it.unibs.IngSftwB.Controller.Messaggio;
import it.unibs.IngSftwB.Controller.MessaggioCategoria;

import java.util.ArrayList;

/**
 * Classe per la gestione di una categoria.
 * @author Jacopo Tedeschi, Enrico Zambelli
 */
public class Categoria {
    private String nome;
    private String descrizione;
    private ArrayList <CampoNativo> campiNativi= new ArrayList <CampoNativo>();

    /**
     * Costruttore della classe Categoria
     * @param _nome il nome della categoria
     * @param _descrizione la descrizione della categoria
     * @param _campiNativi la lista dei campi nativi della categoria
     */
    public Categoria(String _nome, String _descrizione, ArrayList <CampoNativo> _campiNativi ) {

        this.nome=_nome;
        this.descrizione=_descrizione;
        this.campiNativi=_campiNativi;
    }

    /**
     * Metodo get per la lista dei campi nativi
     * @return la lista dei campi nativi della categoria
     */
    public ArrayList<CampoNativo> getCampiNativi() {
        return campiNativi;
    }

    /**
     * Metodo get per il nome della categoria
     * @return il nome della categoria
     */
    public String getNome() {
        return nome;
    }
    /**
     * Metodo get per la descrizione della categoria
     * @return la descrizione della categoria
     */
    public String getDescrizione() {
        return descrizione;
    }

    public static ArrayList<CampoNativo> generaCampiIniziali(){
        CampoNativo stato_di_conservazione=new CampoNativo("Stato di conservazione",true);
        CampoNativo descrizione_libera=new CampoNativo("Descrizione libera",false);
        ArrayList <CampoNativo> campiIniziali=new ArrayList<>();
        campiIniziali.add(stato_di_conservazione);
        campiIniziali.add(descrizione_libera);

        return campiIniziali;
    }

    public Messaggio getCategoriaDefinition(){
        return new MessaggioCategoria(this.nome,this.descrizione,this.campiNativi);
    }
}


