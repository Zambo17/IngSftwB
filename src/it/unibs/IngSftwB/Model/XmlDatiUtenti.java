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

public abstract class XmlDatiUtenti {
    /**
     * metodo per la lettura dei parametri salvati in xml
     * @param filename nome file xml da cui leggere i dati
     * @return
     * @throws XMLStreamException
     */
    public static DatiUtenti leggiUtenti(String filename) throws XMLStreamException {
        ArrayList<Utente> listaUtenti = new ArrayList<>();
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
        while(xmlr.hasNext()){
            if(xmlr.isStartElement() && xmlr.getLocalName().equals("datiUtenti")){
                boolean fineUtenti=false;
                while(!fineUtenti){
                    if(xmlr.isStartElement() && xmlr.getLocalName().equals("utente")){
                        boolean fineUtente=false;
                        String username=null;
                        String password=null;
                        String tipo=null;
                        while(!fineUtente){
                            if(xmlr.isStartElement() && xmlr.getLocalName().equals("username")){
                                boolean fineUser=false;
                                while(!fineUser){
                                    if(xmlr.isCharacters()){
                                        username=xmlr.getText();
                                        fineUser=true;
                                    }
                                    xmlr.next();
                                }
                            }
                            if(xmlr.isStartElement() && xmlr.getLocalName().equals("password")){
                                boolean finePass=false;
                                while(!finePass){
                                    if(xmlr.isCharacters()){
                                        password=xmlr.getText();
                                        finePass=true;
                                    }
                                    xmlr.next();
                                }
                            }
                            if(xmlr.isStartElement() && xmlr.getLocalName().equals("tipoUtente")){
                                boolean fineTipo=false;
                                while(!fineTipo){
                                    if(xmlr.isCharacters()){
                                        tipo=xmlr.getText();
                                        fineTipo=true;
                                    }
                                    if(!fineTipo){
                                        xmlr.next();
                                    }
                                }
                            }
                            if(xmlr.isEndElement() && xmlr.getLocalName().equals("utente")){
                                fineUtente=true;
                            }
                            if(!fineUtente){
                                xmlr.next();
                            }

                        }
                        if(tipo.equals("configuratore")){
                            Configuratore c=new Configuratore(username,password);
                            listaUtenti.add(c);
                        }
                        else{
                            Fruitore f=new Fruitore(username,password);
                            listaUtenti.add(f);
                        }

                    }
                    if(xmlr.isEndElement() && xmlr.getLocalName().equals("datiUtenti"))
                        fineUtenti=true;

                    if(!fineUtenti){
                        xmlr.next();
                    }
                }

            }
            xmlr.next();
        }
        DatiUtenti dati=new DatiUtenti(listaUtenti);
        return dati;
    }

    public static void utentiWrite(DatiUtenti utenti, String filename) throws ParserConfigurationException {
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element datiUtenti= document.createElement("datiUtenti");
            document.appendChild(datiUtenti);
            for(Utente x: utenti.getListaUtenti()){
                Element utente= document.createElement("utente");
                datiUtenti.appendChild(utente);

                //username
                Element username=document.createElement("username");
                username.appendChild(document.createTextNode(x.getUsername()));
                utente.appendChild(username);


                //password
                Element password= document.createElement("password");
                password.appendChild(document.createTextNode(x.getPassword()));
                utente.appendChild(password);

                //configuratore o fruitore
                Element tipoUtente= document.createElement("tipoUtente");
                if(x instanceof Configuratore){
                    tipoUtente.appendChild(document.createTextNode("configuratore"));
                }
                else{
                    tipoUtente.appendChild(document.createTextNode("fruitore"));
                }
                utente.appendChild(tipoUtente);

            }
            Transformer transformer2 = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File(filename));
            Source input = new DOMSource(document);
            transformer2.transform(input, output);
        }catch (ParserConfigurationException | TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
