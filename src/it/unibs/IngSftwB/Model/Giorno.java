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
            case "Lunedi":
                return LUNEDI;
            case "Martedi":
                return MARTEDI;

            case "Mercoledi":
                return MERCOLEDI;

            case "Giovedi":
                return GIOVEDI;

            case "Venerdi":
                return VENERDI;

            case "Sabato":
                return SABATO;

            case "Domenica":
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

