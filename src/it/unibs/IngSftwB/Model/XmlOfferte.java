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

public interface XmlOfferte {
    /**
     * metodo le la lettur adelle offerte salvate nel fil xml
     * @param filename nome file xml
     * @return Offerte contenente le ooferte che erano salvate in filename
     * @throws XMLStreamException
     */
    public static Offerte leggiOfferte(String filename) throws XMLStreamException {
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
        ArrayList<Offerta> listOfferte=new ArrayList<>();
        while(xmlr.hasNext()){
            if(xmlr.isStartElement() && xmlr.getLocalName().equals("offerte")){
                boolean fineOfferte=false;
                while(!fineOfferte){
                    if(xmlr.isStartElement() && xmlr.getLocalName().equals("offerta")){
                        String nomeCategoria="";
                        String nomeRad="";
                        StatoOfferta statoAttuale = null;
                        ArrayList<StatoOfferta> statiPassati=new ArrayList<>();
                        String nomeFruitore="";
                        HashMap<CampoNativo,String> compilazioni=new HashMap<>();
                        boolean fineOfferta=false;
                        while(!fineOfferta){
                            if(xmlr.isStartElement() && xmlr.getLocalName().equals("nomeRadice")){
                                boolean fineRad=false;
                                while(!fineRad){
                                    if(xmlr.isCharacters()){
                                        nomeRad=xmlr.getText();
                                    }
                                    if(xmlr.isEndElement() && xmlr.getLocalName().equals("nomeRadice"))
                                        fineRad=true;
                                    if(!fineRad)
                                        xmlr.next();
                                }
                            }
                            if(xmlr.isStartElement() && xmlr.getLocalName().equals("nomeCategoria")){
                                boolean fineNomeCat=false;
                                while(!fineNomeCat){
                                    if(xmlr.isCharacters()){
                                        nomeCategoria=xmlr.getText();
                                    }
                                    if(xmlr.isEndElement() && xmlr.getLocalName().equals("nomeCategoria"))
                                        fineNomeCat=true;
                                    if(!fineNomeCat)
                                        xmlr.next();
                                }
                            }
                            if(xmlr.isStartElement() && xmlr.getLocalName().equals("statoAttuale")) {
                                boolean fineStatoAttuale=false;
                                while(!fineStatoAttuale){
                                    if(xmlr.isCharacters()){
                                        statoAttuale=StatoOfferta.getStatoFromString(xmlr.getText());
                                    }
                                    if(xmlr.isEndElement() && xmlr.getLocalName().equals("statoAttuale"))
                                        fineStatoAttuale=true;
                                    if(!fineStatoAttuale)
                                        xmlr.next();
                                }
                            }
                            if(xmlr.isStartElement() && xmlr.getLocalName().equals("nomeFruitore")){
                                boolean fineFruitore=false;
                                while (!fineFruitore){
                                    if(xmlr.isCharacters()){
                                        nomeFruitore=xmlr.getText();
                                    }
                                    if(xmlr.isEndElement() && xmlr.getLocalName().equals("nomeFruitore"))
                                        fineFruitore=true;
                                    if(!fineFruitore)
                                        xmlr.next();
                                }
                            }
                            if(xmlr.isStartElement() && xmlr.getLocalName().equals("compilazioni")){
                                boolean fineCompilazioni=false;
                                while(!fineCompilazioni){
                                    if(xmlr.isStartElement() && xmlr.getLocalName().equals("compilazione")){
                                        boolean fineCompiazione=false;
                                        String nomeCampo="";
                                        boolean obbligoCampo=false;
                                        String descrizione="";
                                        while(!fineCompiazione){
                                            if(xmlr.isStartElement() && xmlr.getLocalName().equals("campoNativo")){
                                                boolean fineCampo=false;
                                                while(!fineCampo){

                                                    if(xmlr.isStartElement() && xmlr.getLocalName().equals("nomeCampo")){
                                                        boolean fineNomeCampo=false;
                                                        while(!fineNomeCampo){
                                                            if(xmlr.isCharacters()){
                                                                nomeCampo=xmlr.getText();
                                                            }
                                                            if(xmlr.isEndElement() && xmlr.getLocalName().equals("nomeCampo"))
                                                                fineNomeCampo=true;
                                                            if(!fineNomeCampo)
                                                                xmlr.next();
                                                        }
                                                    }
                                                    if(xmlr.isStartElement() && xmlr.getLocalName().equals("obbligoCampo")){
                                                        boolean fineObbligo=false;
                                                        while(!fineObbligo){
                                                            if(xmlr.isCharacters()){
                                                                if(xmlr.getText().equals("true")){
                                                                    obbligoCampo=true;
                                                                }
                                                            }
                                                            if(xmlr.isEndElement()  && xmlr.getLocalName().equals("obbligoCampo"))
                                                                fineObbligo=true;
                                                            if(!fineObbligo)
                                                                xmlr.next();
                                                        }
                                                    }

                                                    if(xmlr.isEndElement() && xmlr.getLocalName().equals("campoNativo"))
                                                        fineCampo=true;
                                                    if(!fineCampo)
                                                        xmlr.next();
                                                }
                                            }
                                            if(xmlr.isStartElement() && xmlr.getLocalName().equals("compilazioneInserita")){
                                                boolean fineInserita=false;
                                                while(!fineInserita){
                                                    if(xmlr.isCharacters()){
                                                        descrizione=xmlr.getText();
                                                    }
                                                    if(xmlr.isEndElement() && xmlr.getLocalName().equals("compilazioneInserita"))
                                                        fineInserita=true;
                                                    if(!fineInserita)
                                                        xmlr.next();
                                                }
                                            }
                                            if(xmlr.isEndElement() && xmlr.getLocalName().equals("compilazione")) {
                                                fineCompiazione = true;
                                                compilazioni.put(new CampoNativo(nomeCampo, obbligoCampo), descrizione);
                                            }
                                            if(!fineCompiazione)
                                                xmlr.next();
                                        }
                                    }
                                    if(xmlr.isEndElement() && xmlr.getLocalName().equals("compilazioni"))
                                        fineCompilazioni=true;
                                    if(!fineCompilazioni)
                                        xmlr.next();
                                }
                            }
                            if(xmlr.isStartElement() && xmlr.getLocalName().equals("statiPassati")){
                                boolean fineStati=false;
                                while(!fineStati){
                                    if(xmlr.isStartElement() && xmlr.getLocalName().equals("statoPassato")){
                                        boolean fineStato=false;
                                        while(!fineStato){
                                            if(xmlr.isCharacters()){
                                                statiPassati.add(StatoOfferta.getStatoFromString(xmlr.getText()));
                                            }
                                            if(xmlr.isEndElement() && xmlr.getLocalName().equals("statoPassato"))
                                                fineStato=true;
                                            if(!fineStato)
                                                xmlr.next();
                                        }
                                    }
                                    if(xmlr.isEndElement() && xmlr.getLocalName().equals("statiPassati"))
                                        fineStati=true;
                                    if(!fineStati)
                                        xmlr.next();
                                }
                            }
                            if(xmlr.isEndElement() && xmlr.getLocalName().equals("offerta")) {
                                fineOfferta = true;
                                Offerta of = new Offerta(nomeCategoria, compilazioni, statoAttuale, nomeFruitore, statiPassati, nomeRad);
                                listOfferte.add(of);
                            }
                            if(!fineOfferta){
                                xmlr.next();
                            }
                        }
                    }
                    if(xmlr.isEndElement() && xmlr.getLocalName().equals("offerte"))
                        fineOfferte=true;
                    if ((!fineOfferte)){
                        xmlr.next();
                    }
                }
            }
            xmlr.next();
        }
        Offerte ofs=new Offerte(listOfferte);
        return ofs;
    }

    /**
     * metodo per salvare le offerte in un file xml
     * @param offerte offerte da salvare
     * @param filename nome del file xml in cui salvare
     */
    public static void salvaOfferte(Offerte offerte, String filename){
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element listaOfferte=document.createElement("offerte");
            document.appendChild(listaOfferte);

            for(Offerta x : offerte.getListaOfferte() ){
                Element offerta=document.createElement("offerta");
                listaOfferte.appendChild(offerta);

                Element nomeRadice=document.createElement("nomeRadice");
                offerta.appendChild(nomeRadice);
                nomeRadice.appendChild(document.createTextNode(x.getNomeRadice()));

                Element nomeCategoria=document.createElement("nomeCategoria");
                offerta.appendChild(nomeCategoria);
                nomeCategoria.appendChild(document.createTextNode(x.getNomeCategoria()));

                Element statoAttuale= document.createElement("statoAttuale");
                offerta.appendChild(statoAttuale);
                statoAttuale.appendChild(document.createTextNode(x.getStatoAttuale().toStringStato()));

                Element nomeFruitore=document.createElement("nomeFruitore");
                offerta.appendChild(nomeFruitore);
                nomeFruitore.appendChild(document.createTextNode(x.getNomeFruitore()));

                Element compilazioni=document.createElement("compilazioni");
                offerta.appendChild(compilazioni);

                for(CampoNativo c: x.getCompliazioni().keySet()){
                    Element compilazione = document.createElement("compilazione");
                    compilazioni.appendChild(compilazione);
                    //campoNativo
                    Element campoNativo=document.createElement("campoNativo");
                    compilazione.appendChild(campoNativo);

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
                    Element compilazioneInserita=document.createElement("compilazioneInserita");
                    compilazione.appendChild(compilazioneInserita);
                    compilazioneInserita.appendChild(document.createTextNode(x.getCompliazioni().get(c)));

                }
                Element statiPassati=document.createElement("statiPassati");
                offerta.appendChild(statiPassati);

                for(StatoOfferta s:x.getStatiPassati()){
                    Element st=document.createElement("statoPassato");
                    statiPassati.appendChild(st);
                    st.appendChild(document.createTextNode(s.toStringStato()));
                }

            }
            Transformer transformer2 = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File(filename));
            Source input = new DOMSource(document);
            transformer2.transform(input, output);
        }catch(TransformerConfigurationException | ParserConfigurationException e){

        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
