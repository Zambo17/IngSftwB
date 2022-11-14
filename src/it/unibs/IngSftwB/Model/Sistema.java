package it.unibs.IngSftwB.Model;


import it.unibs.IngSftwB.Controller.Messaggio;
import it.unibs.IngSftwB.Controller.MessaggioCategoria;
import it.unibs.IngSftwB.Controller.MessaggioSistema;

import java.util.ArrayList;

/**
 * Classe per la gestione del sistema
 * @author Jacopo Tedeschi, Enrico Zambelli
 */
public class Sistema {


    private ArrayList <Gerarchia> listaGerarchie=new ArrayList<Gerarchia>();

    /**
     * Costruttore della classe Sistema
     * @param _listaGerarchie la lista delle gerarchie del sistema
     */
    public Sistema(ArrayList<Gerarchia>_listaGerarchie){
        this.listaGerarchie=_listaGerarchie;
    }

    /**
     * Metodo per l'aggiunta di una gerarchia al sistema
     * @param g la gerarchia da aggiungere
     */
    public void addGerarchia(Gerarchia g){
        this.listaGerarchie.add(g);
    }

    /**
     * Metodo gt per la lista delle gerarchie
     * @return la lista delle gerarchie del sistema
     */
    public ArrayList<Gerarchia> getListaGerarchie() {
        return listaGerarchie;
    }

    /**
     * Metodo per la ricerca di una categoria nel sistema in base al nome passato in ingresso
     * @param nome il nome della categoria da cercare
     * @return la categoria cercata se presente, null altrimenti
     */
    public Categoria findCategoria(String nome, int numGer){
        for(Categoria x: this.listaGerarchie.get(numGer-1).getRamo().keySet()){
            if(x.getNome().equalsIgnoreCase(nome)){
                return x;
            }
        }
        return null;
    }

    /**
     * Metodo per il controllo del nome di una radice nel sistema
     * @param s il nome della radice da controllare
     * @return true se il nome della radice non è presente nel sistema, false altrimenti
     */
    public boolean checkNomeNuovoRadice(String s){
        boolean valido=true;
        for(Gerarchia x:this.listaGerarchie){
            if(x.getRadice().getNome().equalsIgnoreCase(s)){
                valido=false;
            }
        }
        return valido;
    }

    public boolean isFoglia(String nomeCat){
        ArrayList <Categoria> foglie=new ArrayList<>();
        boolean isFoglia=false;
        for(Gerarchia g:this.listaGerarchie){
            foglie.addAll(g.listaFoglie());
        }
        for(Categoria c:foglie){
            if(c.getNome().equals(nomeCat))
                isFoglia=true;
        }
        return isFoglia;
    }

    public Messaggio getSistemaDefinition(){
        return new MessaggioSistema(this.listaGerarchie);
    }

}

