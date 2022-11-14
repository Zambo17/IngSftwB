package it.unibs.IngSftwB.Model;

import it.unibs.IngSftwB.Controller.Messaggio;
import it.unibs.IngSftwB.Controller.MessaggioOfferte;

import java.util.ArrayList;

/**
 * classe per la gestione di tutte le offerte
 * @author Enrico Zambelli, Jacopo Tedeschi
 */
public class Offerte {
    private ArrayList<Offerta> listaOfferte= new ArrayList<Offerta>();

    /**
     * costruttore della classe
     * @param _offerteAccoppiate arraylist delle offerte
     */
    public Offerte(ArrayList<Offerta> _offerteAccoppiate) {
        this.listaOfferte=_offerteAccoppiate;
    }

    /**
     * metodo che aggiunge un offerta alla liste delle offerte
     * @param o offerta da aggiungere
     */
    public void addOffertaAunFruitore( Offerta o){
        this.listaOfferte.add(o);
    }

    /**
     * metodo che restituisce la lista delle offerte
     * @return arraylist delle offerte
     */
    public ArrayList<Offerta> getListaOfferte() {
        return listaOfferte;
    }

    /**
     * metodo che restituisce le offerte appartenenti ad un fruitore
     * @param nomeFruiotore nome del fruitore di cui si vogliono le offerte
     * @return arrayList di offerte che appartengono al fruitore dato
     */
    public ArrayList<Offerta> getOfferteFromFruitore(String nomeFruiotore){
        ArrayList <Offerta> toReturn=new ArrayList<>();
        for(Offerta o: this.listaOfferte){
            if(o.getNomeFruitore().equals(nomeFruiotore)){
                toReturn.add(o);
            }
        }
        return toReturn;
    }

    /**
     * metodo per cambiare lo stato di una offerta esistente
     * @param toChange offerta di cui si vuole cambiare lo stato
     */
    public void modificaOffertaEsistente(Offerta toChange, StatoOfferta so){
        int indice=0;
        for(Offerta of:this.listaOfferte){
            if(of.stessaOfferta(toChange)){
                indice=this.listaOfferte.indexOf(of);
                break;
            }
        }
        Offerta temp=this.listaOfferte.get(indice);
        temp.cambiaStato(so);
        this.listaOfferte.remove(indice);
        this.listaOfferte.add(temp);
    }

    /**
     * metodo che elimina le offerte nell statoOfferta RITIRATA
     */
    public void togliRitirate(){
        ArrayList <Offerta> temp=new ArrayList<>();
        for(Offerta o: this.listaOfferte){
            if(o.getStatoAttuale()==StatoOfferta.RITIRATA){
                temp.add(o);
            }
        }
        this.listaOfferte.removeAll(temp);
    }

    /**
     * metodo per ottenere tutte le offerte realtive ad una categoria foglia
     * @param nomeFoglia nome della categoria foglia di cui si vogliono sapere le offerte
     * @param nomeRadice nome della radice della gerarchia
     * @return Offerte con tutte le offerte relative alla categoria
     */
    public Offerte offerteFoglia(String nomeFoglia, String nomeRadice){
        ArrayList <Offerta> toRet=new ArrayList<>();
        for(Offerta o:this.listaOfferte){
            if(o.getNomeCategoria().equals(nomeFoglia) && o.getNomeRadice().equals(nomeRadice)){
                toRet.add(o);
            }
        }
        Offerte ret=new Offerte(toRet);
        if(!toRet.isEmpty()){
            ret.togliRitirate();
        }
        return ret;
    }
    /**
     * Metodo che restituisce solo le offerte in scambio o chiuse
     * @return la lista delle offerte in scambio o chiuse
     */
    public void offerteScambiate(){
        ArrayList <Offerta> temp=new ArrayList<>();
        for(Offerta o:this.listaOfferte){
            if(o.getStatoAttuale()==StatoOfferta.CHIUSA || o.getStatoAttuale()==StatoOfferta.INSCAMBIO ){
                temp.add(o);
            }
        }
        this.listaOfferte.clear();
        this.listaOfferte.addAll(temp);
    }

    public void offerteAperte(){
        ArrayList <Offerta> temp=new ArrayList<>();
        for(Offerta o:this.listaOfferte){
            if(o.getStatoAttuale()==StatoOfferta.APERTA){
                temp.add(o);
            }
        }
        this.listaOfferte.clear();
        this.listaOfferte.addAll(temp);
    }

    /**
     * metodo che restituisce le offerte con l'offerta daScambiare può proporre uno scambio
     * @param daScambiare offerta di cui si vogliono le possibili offerte con cui scambiarla
     * @return le offerte con cui si può fare lo scambio
     */
    public Offerte offerteScambiabili(Offerta daScambiare){
        ArrayList <Offerta> offerteScambiabili=new ArrayList<Offerta>();
        String nomef= daScambiare.getNomeFruitore();
        offerteScambiabili.addAll(this.offerteFoglia(daScambiare.getNomeCategoria(),daScambiare.getNomeRadice())
                .getListaOfferte());
        Offerte of=new Offerte(offerteScambiabili);
        of.offerteAperte();
        ArrayList <Offerta> papabili=new ArrayList<>();
        for(Offerta offerta: of.getListaOfferte()){
            if(!offerta.getNomeFruitore().equals(daScambiare.getNomeFruitore())){
                papabili.add(offerta);
            }
        }
        return new Offerte(papabili);
    }

    /**
     * metodo che rimuove un offerta
     * @param o offerta da rimuovere
     */
    public void togliOfferta(Offerta o){
        this.listaOfferte.remove(o);
    }

    public Messaggio getOfferteDefinition(){
        return new MessaggioOfferte(this.listaOfferte);
    }
}

