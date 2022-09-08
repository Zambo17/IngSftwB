package it.unibs.IngSftwB.Model;
import it.unibs.IngSftwB.xmlUtilities.*;

import javax.xml.stream.XMLStreamException;

/**
 * Classe per la gestione dei menu
 *  @author Jacopo Tedeschi, Enrico Zambelli
 */
public class Menu {


    final private static String CORNICE = "--------------------------------";
    final private static String VOCE_USCITA = "0\tEsci";
    final private static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata : ";
    final private static String[] VOCI_Configuratore = new String[]{"Inserimento nuova gerarchia","Visualizzazione delle gerarchie","Modifica dei parametri","Visualizza le offerte di una categoria","Visualizzare le offerte in scambio o chiuse di una categoria","Inserire dati tramite xml file"};
    public static final String[] VOCI_Fruitore = new String[]{"Visualizza le radici e i parametri di sistema","Pubblicazione prodotto","Modificare una offerta già esistente","visualizza le tue offerte","Visualizza le offerte di una categoria","Proporre uno scambio","Controllare gli scambi"};
    public static final int ZERO = 0;
    public static final int UNO = 1;

    private String titolo;
    private String[] voci;


    /**
     * Costruttore della classe menu
     * @param titolo titolo del menu
     * @param voci le voci del menu
     */
    public Menu(String titolo, String[] voci) {
        this.titolo = titolo;
        this.voci = voci;
    }

    /**
     * Metodo per la scelta di un'opzione del menu
     * @return il numero della scelta
     */
    public int scegli() {
        stampaMenu();
        return Utilita.leggiIntero(RICHIESTA_INSERIMENTO, 0, voci.length);
    }

    /**
     * Metodo per la stampa a video di un menu
     */
    public void stampaMenu() {
        System.out.println(CORNICE);
        System.out.println(titolo);
        System.out.println(CORNICE);
        for (int i = 0; i < voci.length; i++) {
            System.out.println((i + 1) + "\t" + voci[i]);
        }
        System.out.println();
        System.out.println(VOCE_USCITA);
        System.out.println();
    }

    /**
     * Metodo per la gestione del menu del configuratore
     * @param conf la configurazione su cui opera il configuratore
     */
    public void MenuConfiguratore(Configurazione conf, Offerte offerte) throws XMLStreamException {
        int risposta=1;
        this.setVoci(VOCI_Configuratore);
        do {
            if(conf.getSis().getListaGerarchie().size()==0){

                int firstTime=Utilita.leggiIntero("Non è presente alcune gerarchia\n premere 1 per inserirla tramite l'applicazione\n premere 2 per accedere alle sezioni di inserimento di file xml",1,2);
                if(firstTime==1)
                    risposta=1;
                else
                    risposta=6;
            }
            else
                risposta = this.scegli();
            switch (risposta){
                case 1 :
                    String nomeRadice;
                    boolean nomeRadiceNuovo=false;
                    do{
                        nomeRadice=Utilita.leggiStringaNonVuota("Inserisci il nome della radice della gerarchia:");
                        if(conf.getSis().checkNomeNuovoRadice(nomeRadice)){
                            nomeRadiceNuovo=true;
                        }
                        else
                            System.out.println("Questo nome è già presente");
                    }while(!nomeRadiceNuovo);
                    Gerarchia creata=Gerarchia.creaRamo(nomeRadice);
                    //conf.getSis().addGerarchia(creata);
                    conf.getSis().getListaGerarchie().add(creata);
                    break;
                case 2 :
                    System.out.println(conf.getSis().toStringSistema());
                    if(conf.getSis().getListaGerarchie().size()==0){
                        break;
                    }
                    int sceltaCat=0;
                    do{
                        sceltaCat=Utilita.leggiIntero("Inserisci 1 se vuoi visualizzare in dettaglio una categoria, 0 altrimenti:", ZERO, UNO);
                        if(sceltaCat==1){
                            Categoria toSee=Utilita.leggiCategoria(conf.getSis());
                            if(toSee!=null){
                                System.out.println(toSee.toStringCategoria());
                            }
                        }


                    }while(sceltaCat==1);
                    break;
                case 3:
                    conf.getParametri().modificaParametri();
                    break;
                case 4:
                    offerte.stampaOfferteFoglia(conf);
                    break;
                case 5:
                    Categoria [] foglia=conf.getSis().scegliFoglia();
                    Offerte daVedere=offerte.offerteFoglia(foglia[0].getNome(),foglia[1].getNome());
                    daVedere.offerteScambiate();
                    System.out.println(daVedere.toStringOfferte());
                    break;
                case 6:
                    Menu.menuXml(conf);
                    break;
                default:
                    break;

            }
        }while(risposta!=0);

    }

    /**
     * Metodo per la gestione del menu del fruitore
     * @param conf la configurazione su cui opera il fruitore
     */
    public void MenuFruitore(Configurazione conf,Fruitore f,Offerte offerte,ListaScambi listascambi){
        int rispostaFruitore;
        this.setVoci(VOCI_Fruitore);
        do {
            rispostaFruitore = this.scegli();
            switch (rispostaFruitore){
                case 1 :
                    System.out.println(conf.getSis().visualizzaRadici());
                    if(conf.getParametri()==null){
                        System.out.println("I parametri di configurazione non sono ancora stati settati");
                    }
                    else{
                        System.out.println(conf.getParametri().toStringParametri());
                    }
                    break;
                case 2:
                    if(conf.getParametri()!=null){
                        if(conf.getSis().getListaGerarchie().size()>0){
                            Offerta off=new Offerta();
                            boolean notNull=off.creaOfferta(conf.getSis(), f.getUsername());
                            if(notNull)
                                offerte.addOffertaAunFruitore(off);
                        }
                    }
                    else{
                        System.out.println("L'applicazione non è stata settata dal configuratore quindi non puoi pubblicare offerte per ora");
                    }
                    break;
                case 3:
                    Offerte offerteFruitore= new Offerte(offerte.getOfferteFromFruitore(f.getUsername()));
                    offerteFruitore.togliRitirate();
                    if(offerteFruitore.getListaOfferte().size()>0){
                        Offerta toChange=offerteFruitore.scegliOfferta();
                        int sceltaSicura=Utilita.leggiIntero("Premi 1 se sei sicuro di cancellare tale offerta altrimenti 0",0,1);
                        if(sceltaSicura==1){
                            offerte.modificaOffertaEsistente(toChange, StatoOfferta.RITIRATA);
                            System.out.println("Offerta ritirata correttamente");
                        }
                    }
                    else{
                        System.out.println("Non ci sono offerte ritirabili");
                    }

                    break;
                case 4:
                    Offerte offerteFruitoreToSee=new Offerte(offerte.getOfferteFromFruitore(f.getUsername()));
                    System.out.println(offerteFruitoreToSee.toStringOfferte());
                    break;
                case 5:
                    offerte.stampaOfferteFoglia(conf);
                    break;
                case 6:
                    Scambio scambio=Scambio.creaScambio(conf,offerte, f);
                    if(scambio!=null){
                        listascambi.addScambio(scambio);
                    }
                    break;
                case 7:
                    int sceltaFattiRicevuti=Utilita.leggiIntero("Inserisci 1 se vuoi vedere gli scambi che hai proposto\n0 se vuoi vedere quelli che ti sono stati proposti: ");
                    if(sceltaFattiRicevuti==1){
                        ListaScambi fatti=listascambi.scambiOfferente(f);
                        if(fatti.getScambi().size()>0){
                            Scambio scambioScelto=fatti.scegliScambio();
                            int index=listascambi.getScambi().indexOf(scambioScelto);
                            if(scambioScelto!=null){
                                scambioScelto.gestisciScambio(f,conf.getParametri());
                                listascambi.aggiornaScambio(scambioScelto,index);
                            }
                            else
                                break;
                        }
                        else{
                            System.out.println("Non hai proposto scambi");
                        }

                    }
                    else{ //if per quando non ce ne sono
                        ListaScambi ricevuti=listascambi.scambiRicevente(f);
                        if(ricevuti.getScambi().size()>0){
                            Scambio scambioScelto=ricevuti.scegliScambio();
                            int index=listascambi.getScambi().indexOf(scambioScelto);
                            if(scambioScelto!=null){
                                scambioScelto.gestisciScambio(f,conf.getParametri());
                                listascambi.aggiornaScambio(scambioScelto,index);
                            }
                            else
                                break;
                        }
                        else{
                            System.out.println("Non ti sono stati proposti scambi");
                        }

                    }
                    break;
                default:
                    break;

            }
        }while(rispostaFruitore !=0);
    }

    public static void  menuXml(Configurazione c) throws XMLStreamException {
        int sceltaXml=Utilita.leggiIntero("Attenzione inserire i dati da file xml può portare a errori consigliamo massima attenzione nella scrittura del file\n premere 1 per inserire le gerarchie\n premere 2 per inserire parametri di configurazione\n premere 0 per non inserire nessun file");
        switch(sceltaXml){
            case 1:
                String nomefileSis=Utilita.leggiStringaNonVuota("Inserire il percorso del file per esempio: C:\\Users\\apote\\Desktop\\testxml\\testing.xml\nInserisci il nome del file: ");
                if(Utilita.fileExists(nomefileSis) && Utilita.isXmlFile(nomefileSis)){
                    c.setSis(XmlReader.readSis(nomefileSis));
                }
                else{
                    System.out.println("File non esistente o di un formato sbagliato");
                }
                break;
            case 2:
                String nomefilePar=Utilita.leggiStringaNonVuota("Inserire il percorso del file per esempio: C:\\Users\\apote\\Desktop\\testxml\\testing.xml\nInserisci il nome del file: ");
                if(Utilita.fileExists(nomefilePar) && Utilita.isXmlFile(nomefilePar)){
                    c.setParametri(XmlReader.leggiParametri(nomefilePar));
                }
                else{
                    System.out.println("File non esistente o di un formato sbagliato");
                }
                break;
            case 0:
                System.out.println("Caricamento da file annullato");
                break;
        }
    }



    /**
     * Metodo get per le voci del menu
     * @return le voci del menu
     */
    public String[] getVoci() {
        return voci;
    }

    /**
     * Metodo set per le voci del menu
     * @param voci le voci da settare
     */
    public void setVoci(String[] voci) {
        this.voci = voci;
    }

}
