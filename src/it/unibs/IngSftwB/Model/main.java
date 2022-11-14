package it.unibs.IngSftwB.Model;

import it.unibs.IngSftwB.Controller.Controller;
import it.unibs.IngSftwB.View.View;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) throws XMLStreamException, ParserConfigurationException, ParseException, IOException {
        // TODO Auto-generated method stubsss


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
