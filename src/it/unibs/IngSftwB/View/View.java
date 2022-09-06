package it.unibs.IngSftwB.View;

import it.unibs.IngSftwB.Controller.*;
import it.unibs.IngSftwB.mainClasses.CampoNativo;
import it.unibs.IngSftwB.mainClasses.Categoria;
import it.unibs.IngSftwB.mainClasses.Gerarchia;
import it.unibs.IngSftwB.mainClasses.Intervallo;

import java.util.ArrayList;

public class View {

    private void printText(String text) {
        System.out.println(text);
    }

    public void notify(String text) {
        this.printText(text);
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
            if(msg.getRamo().get(x).getNome().equals(msg.getRadice().getNome())){
                figliAlti.add(x);
            }
        }
        /*
        if(this.numFigli(this.radice)==0){
            s.append("Non ha sottocategorie");
        }
        else{
            s.append("Le sottocategorie di "+this.radice.getNome()+ " sono: ");
        }

         */
        for(Categoria x: figliAlti){
            s.append(x.getNome()+"   ");
        }

        do{
            s.append("\n");
            ArrayList<Categoria> figliBassi=new ArrayList<Categoria>();
            for(Categoria x: figliAlti){
                s.append("\n");
                ArrayList <Categoria> figlietti=new ArrayList<Categoria>();//aggiungi un invio qui
                for(Categoria y: msg.getRamo().keySet()){
                    if(msg.getRamo().get(y).getNome().equals(x.getNome())){
                        figlietti.add(y);
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

    public String getSistemaDescription(MessaggioSistema msg){
        StringBuffer stb=new StringBuffer();
        if(msg.getListaGerarchie().isEmpty()){
            stb.append("Il sistema non ha alcuna gerarchia");
            return stb.toString();
        }
        int i=1;
        for(Gerarchia g : msg.getListaGerarchie()){
            stb.append("Gerarchia " + i +":\n");
            stb.append(g.vediRamo()+"\n");
            stb.append("\n");
            i++;
        }
        return stb.toString();
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

    public String getGiornoDescription(MessaggioGiorno msg){
       return msg.getNomeGiorno();
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




}
