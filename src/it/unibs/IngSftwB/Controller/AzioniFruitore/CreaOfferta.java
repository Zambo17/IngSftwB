package it.unibs.IngSftwB.Controller.AzioniFruitore;

import it.unibs.IngSftwB.Controller.*;
import it.unibs.IngSftwB.Model.*;
import it.unibs.IngSftwB.View.StampaMessaggio;

import java.util.ArrayList;

public class CreaOfferta implements AzioneUtente {
    @Override
    public void eseguiAzione(Controller controller, Utente utente) {
        Applicazione app=controller.getApp();
        if(app.getConfigurazione().getParametri()!=null){
            if(app.getConfigurazione().getSis().getListaGerarchie().size()>0){
                Offerta off=creaOfferta(app.getConfigurazione().getSis(), utente.getUsername(),controller);
                if(!(off ==null))
                    app.getOfferte().addOffertaAunFruitore(off);
            }
        }
        else{
            controller.comunicaAllaView(MessaggioErrore.APP_NON_SETTATA);
        }
    }

    @Override
    public String getNomeAzione() {
        return "Pubblicazione prodotto";
    }

    public Offerta creaOfferta(Sistema s, String nome,Controller controller){
        controller.getView().stampaSistemaDescription((MessaggioSistema) s.getSistemaDefinition());
        boolean continua=true;
        Offerta offerta=new Offerta();
        do{
            int ger= controller.richiediInteroIntervalloView(MessaggioGenerale.NUM_GER,1,s.getListaGerarchie().size());
            String n=controller.richiediStringaView(MessaggioGenerale.NOME_FOGLIA);
            Categoria c=s.findCategoria(n,ger);

            if(c!=null && s.isFoglia(c.getNome())){
                offerta.setNomeRadice(s.getListaGerarchie().get(ger-1).getRadice().getNome());
                offerta.setNomeCategoria(c.getNome());
                this.compilaCampi(c,controller,offerta);
                offerta.setStatoAttuale(StatoOfferta.APERTA);
                offerta.setNomeFruitore(nome);
                ArrayList<StatoOfferta> statiPassati=new ArrayList<StatoOfferta>();
                offerta.setStatiPassati(statiPassati);
                continua=false;
            }
            else{
                int temp=controller.richiediInteroIntervalloView(MessaggioAlternativa.RIPROVA_OFFERTA,0,1);
                if(temp==0)
                    return null;
            }
        }while(continua);

        return offerta;
    }

    public void compilaCampi(Categoria c,Controller controller,Offerta offerta){
        for(CampoNativo camp:c.getCampiNativi()){
            if(camp.isObbligatoria()){
                StampaMessaggio.printText(MessaggioGenerale.COMPILAZIONE_CAMPO,camp.getNomeCampo());
                String descr=controller.richiediStringaView(MessaggioGenerale.DUE_PUNTI);
                offerta.getCompliazioni().put(camp, descr);
            }
            else{
                StampaMessaggio.printText(MessaggioGenerale.COMP_CAMPO,camp.getNomeCampo());
                int sce=controller.richiediInteroIntervalloView(MessaggioGenerale.RESTO_COMP,0,1);
                String descr="";
                if(sce==1){
                    StampaMessaggio.printText(MessaggioGenerale.COMPILAZIONE_CAMPO,camp.getNomeCampo());
                    descr=controller.richiediStringaView(MessaggioGenerale.DUE_PUNTI);
                }
                offerta.getCompliazioni().put(camp, descr);
            }
        }
        controller.comunicaAllaView(MessaggioGenerale.FINE_COMPILAZIONI);
    }
}
