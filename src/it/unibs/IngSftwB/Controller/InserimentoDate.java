package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.Model.Utilita;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class InserimentoDate {

    public static boolean checkGiornoSettimana(String dataStringa, ArrayList<Integer> listaGiorniSettimana){
        Calendar c=Calendar.getInstance(Locale.ITALY);
        int year=Integer.valueOf(dataStringa.substring(0,4));
        int mm=Integer.valueOf(dataStringa.substring(5,7));
        int day=Integer.valueOf(dataStringa.substring(8,10));
        c.set(year,mm,day);
        boolean valido=false;
        int i= c.get(Calendar.DAY_OF_WEEK);
        if(listaGiorniSettimana.contains(i))
            valido=true;
        return valido;
    }
    public static String inserisciData(Controller controller){
        StringBuffer sb=new StringBuffer();
        int anno=controller.richiediInteroIntervalloView(MessaggioGenerale.INSERISCI_ANNO,2021,2100);
        sb.append(Integer.toString(anno));
        sb.append("-");
        if(anno % 400 == 0 || anno %4 == 0 && anno % 100 != 0){
            sb.append(inserisciGiornoMese(true,controller));
        }
        else{
            sb.append(inserisciGiornoMese(false,controller));
        }
        return sb.toString();
    }
    public static String inserisciGiornoMese(boolean bisestile,Controller controller){
        int meseNum=controller.richiediInteroIntervalloView(MessaggioGenerale.INSERISCI_MESE, 1,12);
        StringBuffer meseGiorno=new StringBuffer();
        if(meseNum<10){
            meseGiorno.append("0");
            meseGiorno.append(Integer.toString(meseNum));
        }
        else{
            meseGiorno.append(Integer.toString(meseNum));
        }
        int giorniMassimi=0;
        if(meseNum==9 || meseNum==4 || meseNum==6 || meseNum==11){
            giorniMassimi=30;
        }else if(meseNum==2){
            if(bisestile)
                giorniMassimi=29;
            else
                giorniMassimi=28;
        }
        else{
            giorniMassimi=31;
        }
        meseGiorno.append("-");
        int giorno=controller.richiediInteroIntervalloView(MessaggioGenerale.INSERISCI_GIORNO_NUMERO,1,giorniMassimi);
        if(giorno<10){
            meseGiorno.append("0");
            meseGiorno.append(Integer.toString(giorno));
        }
        else
            meseGiorno.append(Integer.toString(giorno));
        return meseGiorno.toString();
    }
}
