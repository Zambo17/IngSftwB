package it.unibs.IngSftwB.Model;

/**
 * Classe enum per la gestione dei giorni
 *  @author Jacopo Tedeschi,Enrico Zambelli
 */
public enum Giorno {
    LUNEDI("Lunedi"),
    MARTEDI("Martedi"),
    MERCOLEDI("Mercoledi"),
    GIOVEDI("Giovedi"),
    VENERDI("Venerdi"),
    SABATO("Sabato"),
    DOMENICA("Domenica");

    private String nomeGiorno;

    Giorno(String nomeGiorno) {
        this.nomeGiorno=nomeGiorno;
    }

    public String getNomeGiorno() {
        return this.nomeGiorno;
    }


    /**
     * Metodo che restituisce il giorno corrispondente alla stringa in ingresso
     * @param gg la stringa di cui restituire il giorno corrispondente
     * @return il giorno corrispondete alla stringa in ingresso se presente, null altrimenti
     */
    public static Giorno getGiornoFromString(String gg){

        switch(gg){
            case "lunedi":
                return LUNEDI;
            case "martedi":
                return MARTEDI;

            case "mercoledi":
                return MERCOLEDI;

            case "giovedi":
                return GIOVEDI;

            case "venerdi":
                return VENERDI;

            case "sabato":
                return SABATO;

            case "domenica":
                return DOMENICA;

        }
        return null;
    }

    public int getNumFromDay(){
        int numOfTheWeek=0;
        switch(this){
            case LUNEDI :
                numOfTheWeek=5;
                break;
            case MARTEDI:
                numOfTheWeek=6;
                break;
            case MERCOLEDI:
                numOfTheWeek=7;
                break;
            case GIOVEDI:
                numOfTheWeek=1;
                break;
            case VENERDI:
                numOfTheWeek=2;
                break;
            case SABATO:
                numOfTheWeek=3;
                break;
            case DOMENICA:
                numOfTheWeek=4;
                break;

        }
        return numOfTheWeek;
    }
}

