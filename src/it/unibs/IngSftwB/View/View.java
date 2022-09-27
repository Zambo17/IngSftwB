package it.unibs.IngSftwB.View;

import it.unibs.IngSftwB.Controller.*;
import it.unibs.IngSftwB.Model.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class View {

    private Map<Class<? extends Messaggio>, Function<Messaggio, String>> conversioneMap = new HashMap<>();

    public View() {
        conversioneMap.put(MessaggioCampoNativo.class, (e) -> this.getCampoDescription((MessaggioCampoNativo) e));
        conversioneMap.put(MessaggioCategoria.class, (e) -> this.getCategoriaDescription((MessaggioCategoria) e));
        conversioneMap.put(MessaggioGerarchia.class, (e) -> this.getGerarchiaDescription((MessaggioGerarchia) e));
        conversioneMap.put(MessaggioSistema.class, (e) -> this.getSistemaDescription((MessaggioSistema) e));
        conversioneMap.put(MessaggioIntervallo.class, (e) -> this.getIntervalloDescription((MessaggioIntervallo) e));
        conversioneMap.put(MessaggioOrario.class, (e) -> this.getOrarioDescription((MessaggioOrario) e));
        conversioneMap.put(MessaggioParametri.class, (e) -> this.getParametriDescription((MessaggioParametri) e));
        conversioneMap.put(MessaggioOfferta.class, (e) -> this.getOffertaDescription((MessaggioOfferta) e));
        conversioneMap.put(MessaggioOfferte.class, (e) -> this.getOfferteDescription((MessaggioOfferte) e));
        conversioneMap.put(MessaggioIncontro.class, (e) -> this.getIncontroDescription((MessaggioIncontro) e));
        conversioneMap.put(MessaggioScambio.class, (e) -> this.getScambioDescription((MessaggioScambio) e));
        conversioneMap.put(MessaggioErrore.class, (e) -> ((MessaggioErrore) e).getMessage());
        conversioneMap.put(MessaggioGenerale.class, (e) -> ((MessaggioGenerale) e).getMessage());
        conversioneMap.put(MessaggioAlternativa.class, (e) -> ((MessaggioAlternativa) e).getMessage());
        conversioneMap.put(MessaggioCustom.class, (e) -> ((MessaggioCustom) e).getMessage());
        //conversioneMap.put(CustomMessage.class, (e) -> ((CustomMessage) e).getMessage());
    }



    private void stampaTesto(String text) {
        System.out.println(text);
    }

    public void notifica(String text) {
        this.stampaTesto(text);
    }

    public void notifica(@NotNull Messaggio m) {
        this.notifica(this.conversioneMap.get(m.getClass()).apply(m));
    }

    public <T> void stampaLista (@NotNull List<T> lista, Function<T, Messaggio> function){
        lista.forEach(e -> this.notifica(function == null ? new MessaggioCustom(e.toString()) : function.apply(e)));
    }



    public <T> T scegli(Messaggio messaggio, @NotNull List<T> opzioni, Function<T, String> funzione) {
        this.notifica(messaggio);
        return this.scegli(opzioni, funzione);
    }

    public <T> int scegliIntero(Messaggio messaggio, @NotNull List<T> opzioni, Function<T, String> funzione) {
        this.notifica(messaggio);
        return this.scegliIntero(opzioni, funzione);
    }

    public <T> T scegli(@NotNull List<T> opzioni, Function<T, String> funzione) {
        int i = 0;
        for (T o : opzioni)
            this.notifica(i++ + ") " + (funzione == null ? o.toString() : funzione.apply(o)));

        int input = -1;
        LettoreIntero lettoreIntero = new LettoreIntero();
        input = lettoreIntero.leggiIntero(MessaggioGenerale.SELEZIONA_INDICE,0,opzioni.size());
        return opzioni.get(input);
    }

    public <T> int scegliIntero(@NotNull List<T> opzioni, Function<T, String> funzione) {
        int i = 1;
        for (T o : opzioni)
            this.notifica(i++ + ") " + (funzione == null ? o.toString() : funzione.apply(o)));

        int input = -1;
        LettoreIntero lettoreIntero = new LettoreIntero();
        input = lettoreIntero.leggiIntero(MessaggioGenerale.SELEZIONA_INDICE,1,opzioni.size()+1);
        return input;
    }

    public String getCampoDescription(MessaggioCampoNativo msg){
        StringBuffer str = new StringBuffer();
        str.append(msg.getNomeCampo());
        if(msg.isObbligatoria()){
            str.append(" compilazione obbligatoria\n");
        }
        else
            str.append(" compilazione facoltativa\n");
        return str.toString();
    }

    public String getCategoriaDescription(MessaggioCategoria msg) {
        StringBuffer str = new StringBuffer();
        str.append("Il nome è: "+msg.getNome() + " e la sua descrizione è: " + msg.getDescrizione() + "\n");

        if (!msg.getCampiNativi().isEmpty()) {
            str.append("Campi Nativi : \n");
            for (CampoNativo cn : msg.getCampiNativi()) {
                str.append(this.getCampoDescription((MessaggioCampoNativo) cn.getCampoNativoDefinition()));
            }

        }
        return str.toString();
    }

    public String getGerarchiaDescription(MessaggioGerarchia msg){
        ArrayList<Categoria> nonVisti=new ArrayList<Categoria>();
        StringBuffer s=new StringBuffer();
        s.append("La radice è "+msg.getRadice().getNome()+". ");
        for(Categoria x: msg.getRamo().keySet()){
            nonVisti.add(x);
        }
        nonVisti.remove(msg.getRadice());
        ArrayList <Categoria> figliAlti=new ArrayList<Categoria>();
        for(Categoria x:msg.getRamo().keySet()){
            if(!x.getNome().equalsIgnoreCase(msg.getRadice().getNome())) {
                if (msg.getRamo().get(x).getNome().equals(msg.getRadice().getNome())) {
                    figliAlti.add(x);
                }
            }
        }

        if(figliAlti.size()==0){
            s.append("Non ha sottocategorie");
        }
        else{
            s.append("Le sottocategorie di "+msg.getRadice().getNome()+ " sono: ");
        }

        for(Categoria x: figliAlti){
            s.append(x.getNome()+"   ");
        }

        do{
            s.append("\n");
            ArrayList<Categoria> figliBassi=new ArrayList<Categoria>();
            for(Categoria x: figliAlti){
                s.append("\n");
                ArrayList <Categoria> figlietti=new ArrayList<Categoria>();//aggiungi un invio qui
                for(Categoria y: msg.getRamo().keySet()) {
                    if (!y.getNome().equalsIgnoreCase(msg.getRadice().getNome())) {
                        if (msg.getRamo().get(y).getNome().equals(x.getNome())) {
                            figlietti.add(y);
                        }
                    }
                }
                if(!figlietti.isEmpty()){
                    s.append("Le sottocategorie di "+x.getNome()+" sono: ");
                    for(Categoria j:figlietti){
                        s.append(j.getNome()+"    ");
                    }
                    figliBassi.addAll(figlietti);
                }
                else{
                    s.append(x.getNome()+" non ha sottocategorie");
                }

            }
            figliAlti.clear();
            figliAlti.addAll(figliBassi);

        }while(!figliAlti.isEmpty());
        return s.toString();
    }

    public void stampaCategoriaDescription(MessaggioCategoria msg){
        this.notifica(this.getCategoriaDescription(msg));
    }
    //da sistemare
    public String getSistemaDescription(MessaggioSistema msg){
        StringBuffer stb=new StringBuffer();
        if(msg.getListaGerarchie().isEmpty()){
            stb.append(MessaggioErrore.NO_GERARCHIE.getMessage());
            return stb.toString();
        }
        int i=1;
        for(Gerarchia g : msg.getListaGerarchie()){
            stb.append("Gerarchia " + i +":\n");
            stb.append(this.getGerarchiaDescription(((MessaggioGerarchia) g.getGerarchiaDefinition()))+"\n");
            stb.append("\n");
            i++;
        }
        return stb.toString();
    }

    public void stampaSistemaDescription(MessaggioSistema msg){
        this.notifica(this.getSistemaDescription(msg));
    }

    public String getRadiciDescription(MessaggioSistema msg){
        StringBuffer str=new StringBuffer();
        if(msg.getListaGerarchie().size()==0){
            str.append("Siamo spiacenti ma il configuratore non ha settato alcuna gerarchia per ora");
        }
        else{
            if(msg.getListaGerarchie().size()==1){
                str.append("Esiste una sola gerarchia e questa è la sua radice: \n"+msg.getListaGerarchie().get(0).getRadice().toStringCategoria());
            }
            else{
                str.append("Le radici di ogni gerarchia sono:\n ");
                int count=0;
                for(Gerarchia x:msg.getListaGerarchie()){
                    str.append("1. "+x.getRadice().toStringCategoria()+"\n");
                }
            }
        }
        return str.toString();
    }

    public void stampaRadiciDescription(MessaggioSistema msg){
        this.notifica(this.getRadiciDescription(msg));
    }

    public String getOrarioDescription(MessaggioOrario msg){
        StringBuilder str = new StringBuilder();
        if(msg.getOra() <10){
            str.append("0"+msg.getOra());
        }
        else{
            str.append(msg.getOra());
        }

        if(msg.getMinuti()==0){
            str.append(":00");
        }
        else{
            str.append(":30");
        }

        return str.toString();
    }

    public String getIntervalloDescription(MessaggioIntervallo msg){
        StringBuffer sb=new StringBuffer();
        sb.append(this.getOrarioDescription((MessaggioOrario) msg.getInizio().getOrarioDefinition()));
        sb.append("-");
        sb.append(this.getOrarioDescription((MessaggioOrario) msg.getFine().getOrarioDefinition()));
        return sb.toString();
    }

    public String getIntervalliDescription(MessaggioParametri msg){
        StringBuffer sb=new StringBuffer();
        int count=0;
        if(msg.getIntervalli().size()==1){
            sb.append("L'intervallo è: \n");
        }
        else
            sb.append("Gli intervalli sono: \n");
        for(Intervallo i: msg.getIntervalli()){
            sb.append(this.getIntervalloDescription((MessaggioIntervallo) i.getIntervalloDefinition())+"\t");
            count++;
            if(count%6==0);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void stampaIntervalliDescription(MessaggioParametri msg){
        this.notifica(this.getIntervalliDescription(msg));
    }


    public String getParametriDescription(MessaggioParametri msg){
        StringBuffer stb = new StringBuffer();
        stb.append("Piazza: " + msg.getPiazza());
        stb.append("\nLuoghi: ");
        for (int i = 0; i < msg.getLuoghi().size(); i++) {
            stb.append(msg.getLuoghi().get(i));
            if (i != msg.getLuoghi().size() - 1) {
                stb.append(",");
            }
        }
        stb.append(".");
        stb.append("\nGiorni: ");
        for (int i = 0; i < msg.getGiorni().size(); i++) {
            stb.append(msg.getGiorni().get(i).getNomeGiorno());
            if (i != msg.getGiorni().size() - 1) {
                stb.append(",");
            }
        }
        stb.append(".");
        for (Intervallo x : msg.getIntervalli()) {
            stb.append("\nOrario: " + this.getIntervalloDescription((MessaggioIntervallo) x.getIntervalloDefinition()));
        }

        stb.append("\nScadenza: " + msg.getScadenza());
        return stb.toString();
    }

    public void stampaParametriDescription(MessaggioParametri msg){
        this.notifica(this.getParametriDescription(msg));
    }

    public String getOffertaDescription(MessaggioOfferta msg){
        StringBuffer stb = new StringBuffer();
        stb.append(" Categoria: " + msg.getNomeCategoria() +"\n");
        for(CampoNativo c: msg.getCompliazioni().keySet()){
            stb.append("\t"+c.getNomeCampo() + ": " + msg.getCompliazioni().get(c) + "\n");
        }
        stb.append("\tStato dell'offerta: " + msg.getStatoAttuale().getState());

        return stb.toString();
    }

    public String getOffertaAutoreDescription (MessaggioOfferta msg){
        StringBuffer stb=new StringBuffer();
        stb.append(this.getOffertaDescription(msg));
        stb.append("\n\tAutore offerta: " + msg.getNomeFruitore()+"\n");
        return stb.toString();
    }

    public void stampaOffertaDescription(MessaggioOfferta msg){
        this.notifica(this.getOffertaDescription((msg)));
    }

    public String getOfferteDescription(MessaggioOfferte msg){
        StringBuffer s=new StringBuffer();
        int count=0;
        if(msg.getListaOfferte().size()==0){
            s.append(MessaggioErrore.NO_OFFERTE.getMessage());
        }
        else{
            for(Offerta o:msg.getListaOfferte()){
                s.append("\n"+count +") " );
                s.append(this.getOffertaDescription((MessaggioOfferta) o.getOffertaDefinition()));
                count++;
            }
        }

        return s.toString();
    }

    public void stampaOfferteDescription(MessaggioOfferte msg){
        this.notifica(this.getOfferteDescription(msg));
    }

    public String getIncontroDescription(MessaggioIncontro msg){
            StringBuffer sb=new StringBuffer();
            sb.append("\nIl luogo proposto è: "+ msg.getLuogo()+ " alle ore: "+this.getOrarioDescription((MessaggioOrario) msg.getOra().getOrarioDefinition())+"\nIn data: "+ msg.getData());
            return sb.toString();
    }

    public String getScambioDescription(MessaggioScambio msg){
        StringBuffer sb=new StringBuffer();
        sb.append("Offerta offerente: \n");
        sb.append(this.getOffertaAutoreDescription((MessaggioOfferta) msg.getOfferente().getOffertaDefinition()));
        sb.append("\n\tOfferta con cui si vorrebbe effettuare lo scambio:\n");
        sb.append(this.getOffertaAutoreDescription((MessaggioOfferta) msg.getRicevente().getOffertaDefinition()));
        return sb.toString();
    }


    public void stampaScambioDescription(MessaggioScambio msg){
        this.notifica(this.getScambioDescription(msg));
    }

    public String getScambiDescription(MessaggioScambi msg){
        StringBuffer sb=new StringBuffer();
        int count=1;
        sb.append("Questi sono gli scambi che ti sono stati proposti:\n");
        for(Scambio s: msg.getScambi()){
            sb.append("\t");
            sb.append(count+") ");
            sb.append(this.getScambioDescription((MessaggioScambio) s.getScambioDefinition())+"\n");
            count++;
        }
        return sb.toString();
    }

    public void stampaScambiDescription(MessaggioScambi msg){
        this.notifica(this.getScambiDescription(msg));
    }

    public String getPropostaDescription(MessaggioProposta msg){
        StringBuffer sb=new StringBuffer();
        sb.append("\nIl luogo proposto è: "+ msg.getLuogo()+ " alle ore: "+this.getOrarioDescription((MessaggioOrario) msg.getOra().getOrarioDefinition())+"\nIn data: "+ msg.getData());
        return sb.toString();
    }

    public void stampaPropostaDescription(MessaggioProposta msg){
        this.notifica(getPropostaDescription(msg));
    }





}
