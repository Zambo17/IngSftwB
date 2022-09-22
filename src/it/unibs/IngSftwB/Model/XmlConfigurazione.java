package it.unibs.IngSftwB.Model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public interface XmlConfigurazione {

    public static void salvaSistema(Sistema s, String filename){
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            //sistema
            Element sistema= document.createElement("insiemeGerarchie");
            document.appendChild(sistema);
            int countGer=0;
            for(Gerarchia g: s.getListaGerarchie()){
                //gerarchia
                Element gerarchia =document.createElement("gerarchia");
                // Attr numberGerarchia= document.createAttribute("id");
                //numberGerarchia.setValue(""+countGer);
                sistema.appendChild(gerarchia);

                ArrayList<Categoria> allCat=new ArrayList<>();
                int countCat=0;
                for(Categoria x:g.getRamo().keySet()){
                    //categoria
                    Element categoria=document.createElement("categoria");
                    // Attr numberCategoria=document.createAttribute("id");
                    //numberCategoria.setValue(""+countCat);
                    gerarchia.appendChild(categoria);
                    //nome categoria
                    Element nomeCategoria=document.createElement("nomeCategoria");
                    categoria.appendChild(nomeCategoria);
                    nomeCategoria.appendChild(document.createTextNode(x.getNome()));
                    //descrizione categoria
                    Element descrizione =document.createElement("descrizione");
                    categoria.appendChild(descrizione);
                    descrizione.appendChild(document.createTextNode(x.getDescrizione()));

                    //campi nativi
                    Element campiNativi=document.createElement("campiNativi");
                    categoria.appendChild(campiNativi);
                    for(CampoNativo c:x.getCampiNativi()){
                        //campoNativo
                        Element campoNativo=document.createElement("campoNativo");
                        //Attr numberCampo=document.createAttribute("id");
                        //numberCategoria.setValue(""+countCampo);
                        campiNativi.appendChild(campoNativo);

                        //nome campo nativo
                        Element nomeCampo=document.createElement("nomeCampo");
                        campoNativo.appendChild(nomeCampo);
                        nomeCampo.appendChild(document.createTextNode(c.getNomeCampo()));

                        //obbligo descrzione campo
                        Element obbligoCampo=document.createElement("obbligoCampo");
                        campoNativo.appendChild(obbligoCampo);
                        if(c.isObbligatoria()){
                            obbligoCampo.appendChild(document.createTextNode("true"));
                        }
                        else {
                            obbligoCampo.appendChild(document.createTextNode("false"));
                        }
                    }
                    //padre categoria
                    Element padre=document.createElement("categoriaPadre");
                    categoria.appendChild(padre);
                    padre.appendChild(document.createTextNode(g.getRamo().get(x).getNome()));
                    countCat++;

                }

                countGer++;

            }
            Transformer transformer2 = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File(filename));
            Source input = new DOMSource(document);
            transformer2.transform(input, output);

            //trasforma il DOM Object in un file xml da salvare in un percorso valido
            //TransformerFactory transformerFactory = TransformerFactory.newInstance();
            //Transformer transformer = transformerFactory.newTransformer();
            //DOMSource domSource = new DOMSource(document);
            //StreamResult streamResult = new StreamResult(new File(xmlFilePath)); //xmleFilePath percoso valido dove salvare nel pc

        }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }
    /**
     * metodo per leggere i dati del sistema salvato su file xml
     * @param filename nome del file di cui si vogliono leggere i dati
     * @return sis ovvero il sistema che era salvato su file xml
     * @throws XMLStreamException
     */
    public static Sistema readSis(String filename) throws XMLStreamException {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        //inizialiazzo il reader
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }
        ArrayList<Gerarchia> listaG= new ArrayList<Gerarchia>();

        while(xmlr.hasNext()){
            if(xmlr.isStartElement() && xmlr.getLocalName().equals("insiemeGerarchie")){
                boolean fineSis=false;
                while(!fineSis){
                    if(xmlr.isStartElement() && xmlr.getLocalName().equals("gerarchia")){
                        //xmlr.next();
                        boolean fineGerarchia=false;
                        HashMap<Categoria,Categoria> linkPadri=new HashMap<>();
                        HashMap<Categoria,String> linkTemp=new HashMap<>();
                        ArrayList <Categoria> allCat= new ArrayList<>();
                        Categoria radice=null;
                        while(!fineGerarchia){

                            String nomeCat="";
                            String descrCat="";
                            ArrayList <CampoNativo> campiCat=new ArrayList<>();
                            String nomePadre = null;
                            //inizio a leggere i dati di una categoria
                            if(xmlr.isStartElement() && xmlr.getLocalName().equals("categoria")){
                                boolean fineCategoria=false;
                                boolean rad=false;
                                while(!fineCategoria){
                                    //xmlr.next();
                                    if(xmlr.isStartElement()){
                                        boolean fineCheckCharacterCat=false;
                                        switch(xmlr.getLocalName()){
                                            case "nomeCategoria":
                                                //salvo il nome della categoria
                                                do{
                                                    if(xmlr.isCharacters()){
                                                        nomeCat=xmlr.getText();
                                                        fineCheckCharacterCat=true;
                                                    }
                                                    xmlr.next();
                                                }while(!fineCheckCharacterCat);
                                                break;
                                            case "descrizione":
                                                //salvo la descrizione della categoria
                                                do{
                                                    if(xmlr.isCharacters()){
                                                        descrCat=xmlr.getText();
                                                        fineCheckCharacterCat=true;
                                                    }
                                                    xmlr.next();
                                                }while(!fineCheckCharacterCat);

                                                break;
                                            case "campiNativi":
                                                boolean fineCampi=false;
                                                while(!fineCampi){

                                                    if(xmlr.isStartElement() && xmlr.getLocalName().equals("campoNativo")){
                                                        //xmlr.next();
                                                        String nomeCampo="";
                                                        boolean obbligoCampo = false;
                                                        boolean fineCampo=false;
                                                        while (!fineCampo){

                                                            if(xmlr.isStartElement()){
                                                                boolean fineCicloControlloCampo=false;
                                                                switch (xmlr.getLocalName()){
                                                                    case  "nomeCampo":
                                                                        //salvo il nome del campo
                                                                        do{
                                                                            if(xmlr.isCharacters()){
                                                                                nomeCampo=xmlr.getText();
                                                                                fineCicloControlloCampo=true;
                                                                            }
                                                                            xmlr.next();
                                                                        }while(!fineCicloControlloCampo);
                                                                        break;
                                                                    case "obbligoCampo":
                                                                        //vedo se è true o false l'obbligo del campo
                                                                        do{
                                                                            if(xmlr.isCharacters()){
                                                                                if(xmlr.getText().equals("true")){
                                                                                    obbligoCampo=true;
                                                                                }
                                                                                else{
                                                                                    obbligoCampo=false;
                                                                                }
                                                                                fineCicloControlloCampo=true;
                                                                            }
                                                                            xmlr.next();
                                                                        }while(!fineCicloControlloCampo);

                                                                        break;
                                                                }
                                                            }
                                                            if(xmlr.isEndElement() && xmlr.getLocalName().equals("campoNativo"))
                                                                fineCampo=true;
                                                            if(!fineCampo)
                                                                xmlr.next();
                                                        }
                                                        CampoNativo c=new CampoNativo(nomeCampo, obbligoCampo);
                                                        campiCat.add(c);
                                                    }
                                                    if(xmlr.isEndElement() && xmlr.getLocalName().equals("campiNativi"))
                                                        fineCampi=true;
                                                    if(!fineCampi)
                                                        xmlr.next();
                                                }
                                                break;
                                            case "categoriaPadre":

                                                //salvo il nome del padre della cat
                                                do{
                                                    if(xmlr.isCharacters()){
                                                        nomePadre=xmlr.getText();
                                                        if(nomePadre.equals("inesistente")){
                                                            rad=true;
                                                        }
                                                        fineCheckCharacterCat=true;
                                                    }
                                                    xmlr.next();
                                                }while(!fineCheckCharacterCat);

                                                break;

                                        }

                                    }
                                    if(xmlr.isEndElement() && xmlr.getLocalName().equals("categoria"))
                                        fineCategoria=true;
                                    if(!fineCategoria)
                                        xmlr.next();
                                }
                                if(!rad){
                                    Categoria cat=new Categoria(nomeCat,descrCat, campiCat);
                                    allCat.add(cat);
                                    linkTemp.put(cat,nomePadre);
                                }
                                else{
                                    radice=new Categoria(nomeCat,descrCat, campiCat);
                                    allCat.add(radice);
                                    linkTemp.put(radice,"inesistente");
                                }

                            }
                            else if(xmlr.isEndElement() && xmlr.getLocalName().equals("gerarchia")){
                                fineGerarchia=true;
                            }

                            if(!fineGerarchia)
                                xmlr.next();
                        }
                        //metodo per trovare il padre e fare i giusti put a linkPadri
                        for(Categoria x: linkTemp.keySet()){
                            if(linkTemp.get(x).equals("inesistente")){

                                CampoNativo uno=new CampoNativo("stato di conservazione",true);
                                CampoNativo due=new CampoNativo("descrizione libera",false);
                                ArrayList <CampoNativo> campiIniziali=new ArrayList<>();
                                campiIniziali.add(uno);
                                campiIniziali.add(due);
                                Categoria fantoccio=new Categoria("inesistente","", campiIniziali);
                                linkPadri.put(x, fantoccio);
                            }
                            else{
                                for(Categoria y:allCat){

                                    if(y.getNome().equals(linkTemp.get(x))){
                                        linkPadri.put(x,y);
                                    }
                                }
                            }

                        }
                        Gerarchia ger=new Gerarchia(linkPadri, radice);
                        listaG.add(ger);
                    }
                    if(xmlr.isEndElement() && xmlr.getLocalName().equals("insiemeGerarchie")){
                        fineSis=true;
                    }
                    else
                        xmlr.next();
                }
            }
            if(xmlr.hasNext())
                xmlr.next();
        }
        Sistema sis=new Sistema(listaG);
        return sis;
    }
    /**
     * metodo per la lettura dei paramentri degli scambi
     * @param filename nome file xml contente i dati
     * @return ParametriScambi che erano salvati in filename
     * @throws XMLStreamException
     */
    public static ParametriScambi leggiParametri(String filename) throws XMLStreamException {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        //inizialiazzo il reader
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }
        String piazza = null;
        ArrayList <String> luoghi=new ArrayList<>();
        ArrayList <Giorno> giorni=new ArrayList<>();

        ArrayList <Intervallo> valli=new ArrayList<>();
        int scadenza = 0;
        while(xmlr.hasNext()){
            boolean fineParametri=false;

            if(xmlr.isStartElement() && xmlr.getLocalName().equals("ParametriScambi")){
                while(!fineParametri){
                    if(xmlr.isStartElement()){
                        switch(xmlr.getLocalName()){
                            case "piazza":
                                boolean finePiazza=false;
                                while(!finePiazza){
                                    if(xmlr.isCharacters()){
                                        piazza=xmlr.getText();
                                    }
                                    if(xmlr.isEndElement() && xmlr.getLocalName().equals("piazza"))
                                        finePiazza=true;
                                    if(!finePiazza)
                                        xmlr.next();
                                }
                                break;
                            case "luoghi":
                                boolean fineLuoghi=false;
                                while(!fineLuoghi){
                                    if(xmlr.isStartElement() && xmlr.getLocalName().equals("luogo")){
                                        boolean fineLuogo=false;
                                        String l=null;
                                        while(!fineLuogo){
                                            if(xmlr.isCharacters()){
                                                l=xmlr.getText();
                                            }
                                            if(xmlr.isEndElement() && xmlr.getLocalName().equals("luogo"))
                                                fineLuogo=true;
                                            if(!fineLuogo)
                                                xmlr.next();
                                        }
                                        luoghi.add(l);
                                    }
                                    if(xmlr.isEndElement() && xmlr.getLocalName().equals("luoghi"))
                                        fineLuoghi=true;
                                    if(!fineLuoghi)
                                        xmlr.next();
                                }
                                break;
                            case "giorni":
                                boolean fineGiorni=false;
                                while(!fineGiorni){
                                    if(xmlr.isStartElement() && xmlr.getLocalName().equals("giorno")){
                                        boolean fineGiorno=false;
                                        Giorno g=null;
                                        while(!fineGiorno){
                                            if(xmlr.isCharacters()){
                                                g=Giorno.getGiornoFromString(xmlr.getText());
                                            }
                                            if(xmlr.isEndElement() && xmlr.getLocalName().equals("giorno"))
                                                fineGiorno=true;
                                            if(!fineGiorno)
                                                xmlr.next();
                                        }
                                        giorni.add(g);
                                    }
                                    if(xmlr.isEndElement() && xmlr.getLocalName().equals("giorni"))
                                        fineGiorni=true;
                                    if(!fineGiorni)
                                        xmlr.next();
                                }
                                break;
                            case "intervalli":
                                boolean fineIntervalli=false;
                                while(!fineIntervalli){
                                    if(xmlr.isStartElement() && xmlr.getLocalName().equals("intervallo")){
                                        boolean fineIntervallo=false;
                                        Orario [] ore=new Orario[2];
                                        while(!fineIntervallo){
                                            if(xmlr.isStartElement() && xmlr.getLocalName().equals("orarioIniziale")){
                                                boolean fineInizio=false;
                                                Orario or=null;
                                                while(!fineInizio){
                                                    if(xmlr.isCharacters()){
                                                        or=Orario.getOrarioFromString(xmlr.getText());
                                                    }
                                                    if(xmlr.isEndElement() && xmlr.getLocalName().equals("orarioIniziale")){
                                                        ore[0]=or;
                                                        fineInizio=true;
                                                    }

                                                    if(!fineInizio)
                                                        xmlr.next();
                                                }
                                            }
                                            if(xmlr.isStartElement() && xmlr.getLocalName().equals("orarioFinale")){
                                                boolean fineFinale=false;
                                                Orario oFin=null;
                                                while(!fineFinale){
                                                    if(xmlr.isCharacters()){
                                                        oFin=Orario.getOrarioFromString(xmlr.getText());
                                                    }
                                                    if(xmlr.isEndElement() && xmlr.getLocalName().equals("orarioFinale")){
                                                        fineFinale=true;
                                                        ore[1]=oFin;
                                                    }
                                                    if(!fineFinale)
                                                        xmlr.next();
                                                }
                                            }
                                            if(xmlr.isEndElement() && xmlr.getLocalName().equals("intervallo")){
                                                fineIntervallo=true;
                                            }
                                            if(!fineIntervallo)
                                                xmlr.next();
                                        }
                                        Intervallo i=new Intervallo(ore);
                                        valli.add(i);
                                    }
                                    if(xmlr.isEndElement() & xmlr.getLocalName().equals("intervalli"))
                                        fineIntervalli=true;
                                    if(!fineIntervalli){
                                        xmlr.next();
                                    }
                                }

                                break;
                            case "scadenza":
                                boolean fineScad=false;
                                while(!fineScad){
                                    if(xmlr.isCharacters()){
                                        scadenza=Integer.valueOf(xmlr.getText());
                                    }
                                    if(xmlr.isEndElement() && xmlr.getLocalName().equals("scadenza"))
                                        fineScad=true;
                                    if(!fineScad){
                                        xmlr.next();
                                    }
                                }
                        }
                    }
                    if(xmlr.isEndElement() && xmlr.getLocalName().equals("ParametriScambi" ))
                        fineParametri=true;
                    if(!fineParametri)
                        xmlr.next();
                }
            }
            xmlr.next();
        }
        ParametriScambi par=new ParametriScambi(piazza,luoghi, giorni,valli,scadenza);
        return par;
    }
    /**
     * metodo per il salvataggio xml dei parametri
     * @param p parametri da salvare
     * @param filename nom file xml su cui salvare
     */
    public static void salvaParametri(ParametriScambi p, String filename){
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element parametri=document.createElement("ParametriScambi");
            document.appendChild(parametri);

            Element piazza=document.createElement("piazza");
            piazza.appendChild(document.createTextNode(p.getPiazza()));
            parametri.appendChild(piazza);

            Element luoghi=document.createElement("luoghi");
            parametri.appendChild(luoghi);

            for(String l: p.getLuoghi()){
                Element luogo=document.createElement("luogo");
                luogo.appendChild(document.createTextNode(l));
                luoghi.appendChild(luogo);
            }

            Element giorni= document.createElement("giorni");
            parametri.appendChild(giorni);

            for(Giorno g:p.getGiorni()){
                Element giorno=document.createElement("giorno");
                giorno.appendChild(document.createTextNode(g.toString()));
                giorni.appendChild(giorno);
            }

            Element intervalli=document.createElement("intervalli");
            parametri.appendChild(intervalli);
            for(Intervallo x: p.getIntervalli()){
                Element intervallo=document.createElement("intervallo");
                intervalli.appendChild(intervallo);

                Element orarioIniziale=document.createElement("orarioIniziale");
                orarioIniziale.appendChild(document.createTextNode(x.getOre()[0].toStringOrario()));
                intervallo.appendChild(orarioIniziale);
                Element orarioFinale=document.createElement("orarioFinale");
                orarioFinale.appendChild(document.createTextNode(x.getOre()[1].toStringOrario()));
                intervallo.appendChild(orarioFinale);
            }



            Element scadenza=document.createElement("scadenza");
            scadenza.appendChild(document.createTextNode(Integer.toString(p.getScadenza())));
            parametri.appendChild(scadenza);

            Transformer transformer2 = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File(filename));
            Source input = new DOMSource(document);
            transformer2.transform(input, output);

        }catch(ParserConfigurationException | TransformerConfigurationException e){

        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}
