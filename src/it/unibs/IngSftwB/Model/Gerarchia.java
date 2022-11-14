package it.unibs.IngSftwB.Model;


import it.unibs.IngSftwB.Controller.Messaggio;
import it.unibs.IngSftwB.Controller.MessaggioGerarchia;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe per la costruzione e la gestione della gerarchia
 * @author Enrico Zambelli, Jacopo Tedeschi
 */
public class Gerarchia {

    private HashMap <Categoria, Categoria> ramo= new HashMap<Categoria,Categoria>();
    private Categoria radice;

    /**
     * Costruttore della classe gerarchia
     * @param _ramo hashMap formata da una categoria e dalla sua categoria padre
     * @param _radice radice della gerarchia
     */
    public Gerarchia(HashMap <Categoria, Categoria> _ramo, Categoria _radice){
        this.ramo=_ramo;
        this.radice=_radice;
    }

    /**
     * Costruttore della classe gerarchia
     */
    public Gerarchia(){

    }

    /**
     * Metodo get per il ramo di una gerarchia
     * @return i ramo della categoria
     */
    public HashMap<Categoria, Categoria> getRamo() {
        return ramo;
    }

    /**
     * Metodo get per la radice di una gerarchia
     * @return la radice della gerarchia
     */
    public Categoria getRadice() {
        return radice;
    }


    /**
     * Metodo che controlla se un nome è già stato preso all'interno della gerarchia
     * @param s nome da controllare
     * @return nuovo boolean che è true se non è già presente false altrimenti
     */
    public boolean checkNomeNuovo(String s){
        boolean nuovo=true;
        for(Categoria x: this.ramo.keySet()){
            if(x.getNome().equals(s)){
                nuovo=false;
            }

        }
        return nuovo;
    }

    /**
     * Metodo che controlla quante sottocategorie ha una categoria
     * @param padre nome categoria da controllare
     * @return figli int che indica il numero di sottocategorie presenti
     */
    public int numFigli(Categoria padre){
        int figli=0;
        for(Categoria x:this.ramo.keySet()) {
            if (!x.getNome().equalsIgnoreCase(radice.getNome())) {
                if (this.ramo.get(x).getNome().equals(padre.getNome())) {
                    figli++;
                }
            }
        }
        return figli;

    }


    /**
     * Metodo che restituisce la categoria padre inserendone il nome
     * @param nomePadre nome del padre
     * @return x la categoria padre
     */
    public Categoria findPadre(String nomePadre){
        for(Categoria x: this.ramo.keySet()){
            if(x.getNome().equalsIgnoreCase(nomePadre)){
                return x;
            }

        }
        return null;
    }


    /**
     * Metodo per impostare il valore della radice
     * @param radice categoria che si vuole impostare come radice
     */
    public void setRadice(Categoria radice) {
        this.radice = radice;
    }


    /**
     * Metodo che controlla che il nome inserito come categoria padre sia valido
     * @param nome nome della categoria padre di cui si vuole creare 1 o più sottocategorie
     * @return esiste boolean che è true se valido false altrimenti
     */
    public boolean checkPadreNome (String nome){
        boolean esiste=false;
        for(Categoria x: this.ramo.keySet()){
            if(x.getNome().equals(nome))
                esiste=true;
        }
        return esiste;
    }

    public ArrayList<Categoria> listaFoglie(){
        ArrayList <Categoria> foglie=new ArrayList<>();
        ArrayList <Categoria> dads=new ArrayList<>();
        dads=this.listaPadri();
        for(Categoria c: this.ramo.keySet()){
            if(!dads.contains(c)){
                foglie.add(c);
            }
        }
        return foglie;
    }

    public ArrayList<Categoria> listaPadri(){
        ArrayList <Categoria> padri=new ArrayList<>();
        for(Categoria c: this.ramo.keySet()){
            if(!padri.contains(this.ramo.get(c))){
                padri.add(this.ramo.get(c));
            }
        }
        return padri;
    }

    public ArrayList<String> listaNomi(){
        ArrayList<String> s=new ArrayList<>();
        for(Categoria x: this.ramo.keySet()){
            s.add(x.getNome());
        }
        return s;
    }


    public Messaggio getGerarchiaDefinition(){
        return new MessaggioGerarchia(this.ramo,this.radice);
    }
}
