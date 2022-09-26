package it.unibs.IngSftwB.Model;

import it.unibs.IngSftwB.Controller.Controller;
import it.unibs.IngSftwB.View.View;
import it.unibs.IngSftwB.xmlUtilities.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) throws XMLStreamException, ParserConfigurationException, ParseException, IOException {
        // TODO Auto-generated method stubsss
        /*
        ArrayList <Utente> l=new ArrayList<Utente>();
        DatiUtenti x=new DatiUtenti(l);
        File fileUtenti = new File("listaUtenti.xml");
        if(fileUtenti.exists() && !fileUtenti.isDirectory()) {
            x= XmlReader.leggiUtenti("listaUtenti.xml");
        }
        File fileScambi=new File("scambi.xml");
        ArrayList<Scambio> ls=new ArrayList<>();
        ListaScambi scambi=new ListaScambi(ls);
        if(fileScambi.exists() && !fileScambi.isDirectory()){
           scambi= XmlReader.leggiScambi("scambi.xml");
        }
        Utente acceduto=x.menuAccesso();
        ArrayList <Gerarchia> gs=new ArrayList<>();
        Sistema sistema=new Sistema(gs);
        ParametriScambi param=null;
        File fileParametri=new File("parametriSalvati.xml");
        boolean parametriFatti=true;
        if(fileParametri.exists() && !fileParametri.isDirectory()){
            param= XmlReader.leggiParametri("parametriSalvati.xml");
        }
        else{
            parametriFatti=false;
        }
        File fileSistema = new File("sistema.xml");
        if(fileSistema.exists() && !fileSistema.isDirectory()) {
            sistema= XmlReader.readSis("sistema.xml");
        }
        if(!parametriFatti && acceduto instanceof Configuratore){
            int sceltaPar=Utilita.leggiIntero("Non sono presenti parametri\n premere 1 per inserirli tramite l'applicazione\n premere 2 per inserirli tramite file xml",1,2);
            if(sceltaPar==2){
                String nomefilePar=Utilita.leggiStringaNonVuota("Inserire il percorso del file per esempio: C:\\Users\\apote\\Desktop\\testxml\\testing.xml\nInserisci il nome del file: ");
                if(Utilita.fileExists(nomefilePar) && Utilita.isXmlFile(nomefilePar)){
                    param= XmlReader.leggiParametri(nomefilePar);
                }
                else{
                    System.out.println("File non esistente o di un formato sbagliato");
                    param=ParametriScambi.inserimentoParametri();
                }
            }
            else{
                param=ParametriScambi.inserimentoParametri();
            }
        }
        Configurazione conf=new Configurazione(sistema,param);
        ArrayList <Offerta> listaOff=new ArrayList<>();
        File fileOfferte = new File("offerte.xml");
        if(fileOfferte.exists() && !fileOfferte.isDirectory()) {
            listaOff.addAll(XmlReader.leggiOfferte("offerte.xml").getListaOfferte());
        }
        Offerte offerte=new Offerte(listaOff);
        if(scambi!=null && offerte.getListaOfferte().size()!=0){
            if(scambi.getScambi().size()>0)
                scambi.controllaValiditaScambi(conf.getParametri(), offerte);
        }
        if(acceduto instanceof Configuratore){
            String titolo="Benvenuto nel sistema di gestione baratti";
            String[] voci=new String[]{};
            Menu m=new Menu(titolo,voci);
            m.MenuConfiguratore(conf, offerte);
        }
        if(acceduto instanceof  Fruitore){
            String titolo="Benvenuto nel sistema di gestione baratti";
            String[] voci=new String[]{};
            Menu m=new Menu(titolo,voci);
            m.MenuFruitore(conf, (Fruitore) acceduto, offerte,scambi);
        }
        XmlWriter.scriviScambi(scambi,"scambi.xml");

        if(offerte.getListaOfferte().size()!=0)
            XmlWriter.salvaOfferte(offerte, "offerte.xml");

        if(param != null) {
            XmlWriter.salvaParametri(conf.getParametri(), "parametriSalvati.xml");
        }
        if(conf.getSis().getListaGerarchie().size()!=0){
            XmlWriter.salvaSistema(conf.getSis(), "sistema.xml");
        }
        XmlWriter.utentiWrite(x, "listaUtenti.xml");
        System.out.println("\nFINE PROGRAMMA");

         */


        ArrayList <Utente> l=new ArrayList<Utente>();
        DatiUtenti x=new DatiUtenti(l);
        ArrayList<Scambio> ls=new ArrayList<>();
        ListaScambi scambi=new ListaScambi(ls);
        ArrayList <Gerarchia> gs=new ArrayList<>();
        Sistema sistema=new Sistema(gs);
        ParametriScambi param=null;
        ArrayList <Offerta> listaOff=new ArrayList<>();
        Offerte offerte=new Offerte(listaOff);

        Configurazione conf=new Configurazione(sistema,param);

        //sistemare il discorso degli orari uguali

        View view= new View();
        Applicazione app= new Applicazione(offerte,scambi,conf,x);
        Controller controller=new Controller(view,app);
        controller.run();

    }


}
