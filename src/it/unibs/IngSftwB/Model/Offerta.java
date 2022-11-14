package it.unibs.IngSftwB.Model;

import it.unibs.IngSftwB.Controller.Messaggio;
import it.unibs.IngSftwB.Controller.MessaggioOfferta;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe per la gestione di un offerta
 */
public class Offerta {

    private String nomeCategoria;
    private String nomeRadice;
    private HashMap <CampoNativo, String> compliazioni=new HashMap<>();
    private StatoOfferta statoAttuale;
    private ArrayList <StatoOfferta> statiPassati=new ArrayList<>();
    private String nomeFruitore;

    /**
     * Costruttore della classe offerta
     * @param _nomeCategoria nome della categoria a cui appartiene l'offerta
     * @param _compilazioni le compilazioni dei campi nativi della categoria
     * @param _statoAttuale lo stato attuale dell'offerta
     * @param _nomeFruitore il nome utente del fruitore autore dell'offerta
     * @param _statiPassati gli stati passati dell'offerta
     * @param _nomeRadice il nome della categoria radice della gerarchia a cui appartiene la categoria
     */
    public Offerta(String _nomeCategoria, HashMap <CampoNativo, String> _compilazioni, StatoOfferta _statoAttuale, String _nomeFruitore, ArrayList<StatoOfferta> _statiPassati, String _nomeRadice ){
        this.nomeCategoria=_nomeCategoria;
        this.compliazioni=_compilazioni;
        this.statoAttuale=_statoAttuale;
        this.nomeFruitore=_nomeFruitore;
        this.statiPassati=_statiPassati;
        this.nomeRadice=_nomeRadice;
    }

    /**
     * Costruttore senza parametri della classe Offerta
     */
    public Offerta() {
    }


    /**
     * Metodo per comparare un offerta con questa offerta
     * @param toCompare
     * @return true se l'ooferta toCompare è uguale a questa offerta
     */
    public boolean stessaOfferta(Offerta toCompare){
        boolean idem=false;
        if (this.getNomeFruitore().equals(toCompare.getNomeFruitore())){
            if(this.nomeCategoria.equals(toCompare.nomeCategoria)){
                if(this.nomeRadice.equals(toCompare.nomeRadice)){
                    boolean compilazioniUguali=true;
                    for(CampoNativo cn: this.compliazioni.keySet()){
                        String s1=this.compliazioni.get(cn);
                        String s2="";
                        for(CampoNativo ct :toCompare.getCompliazioni().keySet()){
                            if(cn.getNomeCampo().equals(ct.getNomeCampo())){
                                s2=toCompare.getCompliazioni().get(ct);
                            }
                        }
                        if(!s1.equals(s2)){
                            compilazioniUguali=false;
                            break;
                        }
                    }
                    if(compilazioniUguali){
                        idem=true;
                    }
                }
            }
        }
        return idem;
    }

    /**
     * Metodo per effettuare il cambio di stato di un'offerta
     */
    public void cambiaStato(StatoOfferta so){
        this.statiPassati.add(this.statoAttuale);
        this.statoAttuale=so;
    }

    /**
     * Metodo get per il nome della radice
     * @return il nome della radice
     */
    public String getNomeRadice() {
        return nomeRadice;
    }


    /**
     * Metodo get per gli stati passati
     * @return gli stati passati dell'offerta
     */
    public ArrayList<StatoOfferta> getStatiPassati() {
        return statiPassati;
    }
    /**
     * Metodo get per il nome della categoria
     * @return il nome della categoria
     */
    public String getNomeCategoria() {
        return nomeCategoria;
    }
    /**
     * Metodo get per la HashMap delle compilazioni
     * @return la HashMap delle compilazioni
     */
    public HashMap<CampoNativo, String> getCompliazioni() {
        return compliazioni;
    }
    /**
     * Metodo get per lo stato attuale
     * @return lo stato attuale dell'offerta
     */
    public StatoOfferta getStatoAttuale() {
        return statoAttuale;
    }

    /**
     * Metodo get per il nome del fruitore
     * @return il nome del fruitore autore dell'offerta
     */
    public String getNomeFruitore() {
        return nomeFruitore;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public void setNomeRadice(String nomeRadice) {
        this.nomeRadice = nomeRadice;
    }

    public void setCompliazioni(HashMap<CampoNativo, String> compliazioni) {
        this.compliazioni = compliazioni;
    }

    public void setStatoAttuale(StatoOfferta statoAttuale) {
        this.statoAttuale = statoAttuale;
    }

    public void setStatiPassati(ArrayList<StatoOfferta> statiPassati) {
        this.statiPassati = statiPassati;
    }

    public void setNomeFruitore(String nomeFruitore) {
        this.nomeFruitore = nomeFruitore;
    }

    public Messaggio getOffertaDefinition(){
        return new MessaggioOfferta(this.nomeCategoria,this.compliazioni,this.statoAttuale,this.nomeFruitore,this.nomeRadice);
    }

}
