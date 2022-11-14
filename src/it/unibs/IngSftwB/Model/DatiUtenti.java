package it.unibs.IngSftwB.Model;


import java.util.ArrayList;

/**
 * Classe per la gestione degli utenti del sistema
 * @author Jacopo Tedeschi, Enrico Zambelli
 */
public class DatiUtenti {

    public static final Utente CREDENZIALI_PREDEFINITE = new Configuratore("admin", "ezjt9917");
    private ArrayList<Utente> listaUtenti = new ArrayList<Utente>();


    /**
     * Costruttore della classe DatiUtenti
     *
     * @param _listaUtenti lista degli utenti del programma
     */
    public DatiUtenti(ArrayList<Utente> _listaUtenti) {
        this.listaUtenti = _listaUtenti;

    }

    /**
     * Metodo per il controllo delle credenziali di un utente
     *
     * @param userName username dell'utente
     * @param password password dell'utente
     * @return true se le credenziali sono corrette, false altrimenti
     */
    public boolean checkCredenziali(String userName, String password) {
        boolean presente = false;

        //Utente temp = new Utente(userName, password);
        for (Utente x : this.listaUtenti) {
            if (x.sameUtente(userName, password))
                presente = true;
        }

        return presente;
    }

    /**
     * Metodo per il controllo delle credenziali predefinite
     *
     * @param u utente del quale si vuole verificare l'inserimento delle credenziali predefinite
     * @return true se le credenziali predefinite sono corrette,false altrimenti
     */
    public boolean checkConf(Utente u) {
        boolean corretto = false;
        if (Utente.sameUtente(u, CREDENZIALI_PREDEFINITE))
            corretto = true;
        return corretto;
    }

    public  boolean checkConf(String username,String password) {
        boolean corretto = false;
        if (CREDENZIALI_PREDEFINITE.sameUtente(username,password)) {
            corretto = true;
        }
        return corretto;
    }

    /**
     * Metodo per verificare se uno username è già usato da un altro utente
     *
     * @param name lo username di cui si vuole verificare la presenza
     * @return true se è già presente false altrimenti
     */
    public boolean checkName(String name) {
        boolean presente = false;
        if (CREDENZIALI_PREDEFINITE.getUsername().equals(name)) {
            presente = true;
        }
        for (Utente x : this.listaUtenti) {
            if (x.getUsername().equals(name)) {
                presente = true;
            }

        }
        return presente;
    }

    /**
     * Metodo per l'aggiunta di un configuratore alla lista degli utenti
     *
     * @param name     username dell'utente
     * @param password password dell'utente
     * @param conf     boolean che indica se l'utente è un configuratore o meno
     * @return
     */
    public boolean addUtente(String name, String password, boolean conf) {
        boolean successo = false;
        if (conf == true) {
            Configuratore c = new Configuratore(name, password);
            this.listaUtenti.add(c);
        } else {
            Fruitore f = new Fruitore(name, password);
            this.listaUtenti.add(f);
        }
        return successo;
    }

    /**
     * Metodo get per la lista degli utenti
     *
     * @return la lista degli utenti
     */
    public ArrayList<Utente> getListaUtenti() {
        return listaUtenti;
    }

    /**
     * Metodo set per la lista degli utenti
     *
     * @param listaUtenti la lista da settare
     */
    public void setListaUtenti(ArrayList<Utente> listaUtenti) {
        this.listaUtenti = listaUtenti;
    }

    /**
     * Metodo per ottenere un utente dalle sue credenziali
     *
     * @param name     lo username dell'utente da cercare
     * @param password la password dell'utente da cercare
     * @return l'utente che ha per credenziali quelle inserite
     */
    public Utente getUtenteDaCredenziali(String name, String password) {
        for (Utente x : this.listaUtenti) {
            if (x.getUsername().equals(name) && x.getPassword().equals(password)) {
                return x;
            }
        }
        return null;
    }
}


