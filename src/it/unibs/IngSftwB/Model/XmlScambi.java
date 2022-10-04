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

public abstract class XmlScambi {

    /**
     * metodo per leggere gli scambi salvati in xml
     * @param filename nome del file xml
     * @return ListScambi
     * @throws XMLStreamException
     */
    public static ListaScambi leggiScambi(String filename) throws XMLStreamException {

        ArrayList<Scambio> scambi=new ArrayList<>();
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
        xmlr.next();
        while (xmlr.hasNext()) {
            if (xmlr.isStartElement() & xmlr.getLocalName().equals("scambi")) {
                boolean fineScambi = false;
                while (!fineScambi) {
                    if (xmlr.isStartElement() && xmlr.getLocalName().equals("scambio")) {
                        Offerta offerente = null;
                        Offerta ricevente = null;
                        PropostaIncontro ultimaProposta = null;
                        Long time=null;
                        boolean fineScambio = false;
                        while (!fineScambio) {
                            if (xmlr.isStartElement()) {
                                switch (xmlr.getLocalName()) {
                                    case "offertaOfferente":
                                        String nomeCategoria = "";
                                        String nomeRad = "";
                                        StatoOfferta statoAttuale = null;
                                        ArrayList<StatoOfferta> statiPassati = new ArrayList<>();
                                        String nomeFruitore = "";
                                        HashMap<CampoNativo, String> compilazioni = new HashMap<>();
                                        boolean fineOfferente = false;
                                        while (!fineOfferente) {
                                            if (xmlr.isStartElement() && xmlr.getLocalName().equals("nomeRadice")) {
                                                boolean fineRad = false;
                                                while (!fineRad) {
                                                    if (xmlr.isCharacters()) {
                                                        nomeRad = xmlr.getText();
                                                    }
                                                    if (xmlr.isEndElement() && xmlr.getLocalName().equals("nomeRadice"))
                                                        fineRad = true;
                                                    if (!fineRad)
                                                        xmlr.next();
                                                }
                                            }
                                            if (xmlr.isStartElement() && xmlr.getLocalName().equals("nomeCategoria")) {
                                                boolean fineNomeCat = false;
                                                while (!fineNomeCat) {
                                                    if (xmlr.isCharacters()) {
                                                        nomeCategoria = xmlr.getText();
                                                    }
                                                    if (xmlr.isEndElement() && xmlr.getLocalName().equals("nomeCategoria"))
                                                        fineNomeCat = true;
                                                    if (!fineNomeCat)
                                                        xmlr.next();
                                                }
                                            }
                                            if (xmlr.isStartElement() && xmlr.getLocalName().equals("statoAttuale")) {
                                                boolean fineStatoAttuale = false;
                                                while (!fineStatoAttuale) {
                                                    if (xmlr.isCharacters()) {
                                                        statoAttuale = StatoOfferta.getStatoFromString(xmlr.getText());
                                                    }
                                                    if (xmlr.isEndElement() && xmlr.getLocalName().equals("statoAttuale"))
                                                        fineStatoAttuale = true;
                                                    if (!fineStatoAttuale)
                                                        xmlr.next();
                                                }
                                            }
                                            if (xmlr.isStartElement() && xmlr.getLocalName().equals("nomeFruitore")) {
                                                boolean fineFruitore = false;
                                                while (!fineFruitore) {
                                                    if (xmlr.isCharacters()) {
                                                        nomeFruitore = xmlr.getText();
                                                    }
                                                    if (xmlr.isEndElement() && xmlr.getLocalName().equals("nomeFruitore"))
                                                        fineFruitore = true;
                                                    if (!fineFruitore)
                                                        xmlr.next();
                                                }
                                            }
                                            if (xmlr.isStartElement() && xmlr.getLocalName().equals("compilazioni")) {
                                                boolean fineCompilazioni = false;
                                                while (!fineCompilazioni) {
                                                    if (xmlr.isStartElement() && xmlr.getLocalName().equals("compilazione")) {
                                                        boolean fineCompiazione = false;
                                                        String nomeCampo = "";
                                                        boolean obbligoCampo = false;
                                                        String descrizione = "";
                                                        while (!fineCompiazione) {
                                                            if (xmlr.isStartElement() && xmlr.getLocalName().equals("campoNativo")) {
                                                                boolean fineCampo = false;
                                                                while (!fineCampo) {

                                                                    if (xmlr.isStartElement() && xmlr.getLocalName().equals("nomeCampo")) {
                                                                        boolean fineNomeCampo = false;
                                                                        while (!fineNomeCampo) {
                                                                            if (xmlr.isCharacters()) {
                                                                                nomeCampo = xmlr.getText();
                                                                            }
                                                                            if (xmlr.isEndElement() && xmlr.getLocalName().equals("nomeCampo"))
                                                                                fineNomeCampo = true;
                                                                            if (!fineNomeCampo)
                                                                                xmlr.next();
                                                                        }
                                                                    }
                                                                    if (xmlr.isStartElement() && xmlr.getLocalName().equals("obbligoCampo")) {
                                                                        boolean fineObbligo = false;
                                                                        while (!fineObbligo) {
                                                                            if (xmlr.isCharacters()) {
                                                                                if (xmlr.getText().equals("true")) {
                                                                                    obbligoCampo = true;
                                                                                }
                                                                            }
                                                                            if (xmlr.isEndElement() && xmlr.getLocalName().equals("obbligoCampo"))
                                                                                fineObbligo = true;
                                                                            if (!fineObbligo)
                                                                                xmlr.next();
                                                                        }
                                                                    }

                                                                    if (xmlr.isEndElement() && xmlr.getLocalName().equals("campoNativo"))
                                                                        fineCampo = true;
                                                                    if (!fineCampo)
                                                                        xmlr.next();
                                                                }
                                                            }
                                                            if (xmlr.isStartElement() && xmlr.getLocalName().equals("compilazioneInserita")) {
                                                                boolean fineInserita = false;
                                                                while (!fineInserita) {
                                                                    if (xmlr.isCharacters()) {
                                                                        descrizione = xmlr.getText();
                                                                    }
                                                                    if (xmlr.isEndElement() && xmlr.getLocalName().equals("compilazioneInserita"))
                                                                        fineInserita = true;
                                                                    if (!fineInserita)
                                                                        xmlr.next();
                                                                }
                                                            }
                                                            if (xmlr.isEndElement() && xmlr.getLocalName().equals("compilazione")) {
                                                                fineCompiazione = true;
                                                                compilazioni.put(new CampoNativo(nomeCampo, obbligoCampo), descrizione);
                                                            }
                                                            if (!fineCompiazione)
                                                                xmlr.next();
                                                        }
                                                    }
                                                    if (xmlr.isEndElement() && xmlr.getLocalName().equals("compilazioni"))
                                                        fineCompilazioni = true;
                                                    if (!fineCompilazioni)
                                                        xmlr.next();
                                                }
                                            }
                                            if (xmlr.isStartElement() && xmlr.getLocalName().equals("statiPassati")) {
                                                boolean fineStati = false;
                                                while (!fineStati) {
                                                    if (xmlr.isStartElement() && xmlr.getLocalName().equals("statoPassato")) {
                                                        boolean fineStato = false;
                                                        while (!fineStato) {
                                                            if (xmlr.isCharacters()) {
                                                                statiPassati.add(StatoOfferta.getStatoFromString(xmlr.getText()));
                                                            }
                                                            if (xmlr.isEndElement() && xmlr.getLocalName().equals("statoPassato"))
                                                                fineStato = true;
                                                            if (!fineStato)
                                                                xmlr.next();
                                                        }
                                                    }
                                                    if (xmlr.isEndElement() && xmlr.getLocalName().equals("statiPassati"))
                                                        fineStati = true;
                                                    if (!fineStati)
                                                        xmlr.next();
                                                }
                                            }
                                            if (xmlr.isEndElement() && xmlr.getLocalName().equals("offertaOfferente")) {
                                                fineOfferente = true;
                                                offerente= new Offerta(nomeCategoria, compilazioni, statoAttuale, nomeFruitore, statiPassati, nomeRad);
                                            }
                                            if (!fineOfferente) {
                                                xmlr.next();
                                            }
                                        }
                                        break;
                                    case "offertaRicevente":
                                        String nomeCategoriaRic = "";
                                        String nomeRadRic = "";
                                        StatoOfferta statoAttualeRic = null;
                                        ArrayList<StatoOfferta> statiPassatiRic = new ArrayList<>();
                                        String nomeFruitoreRic = "";
                                        HashMap<CampoNativo, String> compilazioniRic = new HashMap<>();
                                        boolean fineRicevente = false;
                                        while (!fineRicevente) {
                                            if (xmlr.isStartElement() && xmlr.getLocalName().equals("nomeRadice")) {
                                                boolean fineRadRic = false;
                                                while (!fineRadRic) {
                                                    if (xmlr.isCharacters()) {
                                                        nomeRadRic = xmlr.getText();
                                                    }
                                                    if (xmlr.isEndElement() && xmlr.getLocalName().equals("nomeRadice"))
                                                        fineRadRic = true;
                                                    if (!fineRadRic)
                                                        xmlr.next();
                                                }
                                            }
                                            if (xmlr.isStartElement() && xmlr.getLocalName().equals("nomeCategoria")) {
                                                boolean fineNomeCat = false;
                                                while (!fineNomeCat) {
                                                    if (xmlr.isCharacters()) {
                                                        nomeCategoriaRic = xmlr.getText();
                                                    }
                                                    if (xmlr.isEndElement() && xmlr.getLocalName().equals("nomeCategoria"))
                                                        fineNomeCat = true;
                                                    if (!fineNomeCat)
                                                        xmlr.next();
                                                }
                                            }
                                            if (xmlr.isStartElement() && xmlr.getLocalName().equals("statoAttuale")) {
                                                boolean fineStatoAttualeRic = false;
                                                while (!fineStatoAttualeRic) {
                                                    if (xmlr.isCharacters()) {
                                                        statoAttualeRic = StatoOfferta.getStatoFromString(xmlr.getText());
                                                    }
                                                    if (xmlr.isEndElement() && xmlr.getLocalName().equals("statoAttuale"))
                                                        fineStatoAttualeRic = true;
                                                    if (!fineStatoAttualeRic)
                                                        xmlr.next();
                                                }
                                            }
                                            if (xmlr.isStartElement() && xmlr.getLocalName().equals("nomeFruitore")) {
                                                boolean fineFruitoreRic = false;
                                                while (!fineFruitoreRic) {
                                                    if (xmlr.isCharacters()) {
                                                        nomeFruitoreRic = xmlr.getText();
                                                    }
                                                    if (xmlr.isEndElement() && xmlr.getLocalName().equals("nomeFruitore"))
                                                        fineFruitoreRic = true;
                                                    if (!fineFruitoreRic)
                                                        xmlr.next();
                                                }
                                            }
                                            if (xmlr.isStartElement() && xmlr.getLocalName().equals("compilazioni")) {
                                                boolean fineCompilazioniRic = false;
                                                while (!fineCompilazioniRic) {
                                                    if (xmlr.isStartElement() && xmlr.getLocalName().equals("compilazione")) {
                                                        boolean fineCompiazioneRic = false;
                                                        String nomeCampoRic = "";
                                                        boolean obbligoCampoRic = false;
                                                        String descrizioneRic = "";
                                                        while (!fineCompiazioneRic) {
                                                            if (xmlr.isStartElement() && xmlr.getLocalName().equals("campoNativo")) {
                                                                boolean fineCampoRic = false;
                                                                while (!fineCampoRic) {

                                                                    if (xmlr.isStartElement() && xmlr.getLocalName().equals("nomeCampo")) {
                                                                        boolean fineNomeCampoRic = false;
                                                                        while (!fineNomeCampoRic) {
                                                                            if (xmlr.isCharacters()) {
                                                                                nomeCampoRic = xmlr.getText();
                                                                            }
                                                                            if (xmlr.isEndElement() && xmlr.getLocalName().equals("nomeCampo"))
                                                                                fineNomeCampoRic = true;
                                                                            if (!fineNomeCampoRic)
                                                                                xmlr.next();
                                                                        }
                                                                    }
                                                                    if (xmlr.isStartElement() && xmlr.getLocalName().equals("obbligoCampo")) {
                                                                        boolean fineObbligoRic = false;
                                                                        while (!fineObbligoRic) {
                                                                            if (xmlr.isCharacters()) {
                                                                                if (xmlr.getText().equals("true")) {
                                                                                    obbligoCampoRic = true;
                                                                                }
                                                                            }
                                                                            if (xmlr.isEndElement() && xmlr.getLocalName().equals("obbligoCampo"))
                                                                                fineObbligoRic = true;
                                                                            if (!fineObbligoRic)
                                                                                xmlr.next();
                                                                        }
                                                                    }

                                                                    if (xmlr.isEndElement() && xmlr.getLocalName().equals("campoNativo"))
                                                                        fineCampoRic = true;
                                                                    if (!fineCampoRic)
                                                                        xmlr.next();
                                                                }
                                                            }
                                                            if (xmlr.isStartElement() && xmlr.getLocalName().equals("compilazioneInserita")) {
                                                                boolean fineInseritaRic = false;
                                                                while (!fineInseritaRic) {
                                                                    if (xmlr.isCharacters()) {
                                                                        descrizioneRic = xmlr.getText();
                                                                    }
                                                                    if (xmlr.isEndElement() && xmlr.getLocalName().equals("compilazioneInserita"))
                                                                        fineInseritaRic = true;
                                                                    if (!fineInseritaRic)
                                                                        xmlr.next();
                                                                }
                                                            }
                                                            if (xmlr.isEndElement() && xmlr.getLocalName().equals("compilazione")) {
                                                                fineCompiazioneRic = true;
                                                                compilazioniRic.put(new CampoNativo(nomeCampoRic, obbligoCampoRic), descrizioneRic);
                                                            }
                                                            if (!fineCompiazioneRic)
                                                                xmlr.next();
                                                        }
                                                    }
                                                    if (xmlr.isEndElement() && xmlr.getLocalName().equals("compilazioni"))
                                                        fineCompilazioniRic = true;
                                                    if (!fineCompilazioniRic)
                                                        xmlr.next();
                                                }
                                            }
                                            if (xmlr.isStartElement() && xmlr.getLocalName().equals("statiPassati")) {
                                                boolean fineStatiRic = false;
                                                while (!fineStatiRic) {
                                                    if (xmlr.isStartElement() && xmlr.getLocalName().equals("statoPassato")) {
                                                        boolean fineStatoRic = false;
                                                        while (!fineStatoRic) {
                                                            if (xmlr.isCharacters()) {
                                                                statiPassatiRic.add(StatoOfferta.getStatoFromString(xmlr.getText()));
                                                            }
                                                            if (xmlr.isEndElement() && xmlr.getLocalName().equals("statoPassato"))
                                                                fineStatoRic = true;
                                                            if (!fineStatoRic)
                                                                xmlr.next();
                                                        }
                                                    }
                                                    if (xmlr.isEndElement() && xmlr.getLocalName().equals("statiPassati"))
                                                        fineStatiRic = true;
                                                    if (!fineStatiRic)
                                                        xmlr.next();
                                                }
                                            }
                                            if (xmlr.isEndElement() && xmlr.getLocalName().equals("offertaRicevente")) {
                                                fineRicevente = true;
                                                ricevente= new Offerta(nomeCategoriaRic, compilazioniRic, statoAttualeRic, nomeFruitoreRic, statiPassatiRic, nomeRadRic);
                                            }
                                            if (!fineRicevente) {
                                                xmlr.next();
                                            }
                                        }
                                        break;
                                    case "proposta":
                                        boolean fineProposta=false;
                                        String tempoProp="";
                                        String luogo="";
                                        String data="";
                                        String nomeFruiPorp="";
                                        String ora="";
                                        Orario oraProposta=null;
                                        while(!fineProposta){
                                            if(xmlr.isStartElement()){
                                                switch (xmlr.getLocalName()){
                                                    case "luogo":
                                                        boolean fineLuogo=false;
                                                        while(!fineLuogo){
                                                            if(xmlr.isCharacters()){
                                                                luogo=xmlr.getText();
                                                                fineLuogo=true;
                                                            }
                                                            if(!fineLuogo){
                                                                xmlr.next();
                                                            }
                                                        }

                                                        break;
                                                    case "data":
                                                        boolean fineData=false;
                                                        while(!fineData){
                                                            if(xmlr.isCharacters()){
                                                                data=xmlr.getText();
                                                                fineData=true;
                                                            }
                                                            if(!fineData){
                                                                xmlr.next();
                                                            }
                                                        }
                                                        break;
                                                    case "nomeF":
                                                        boolean fineNomeFru=false;
                                                        while(!fineNomeFru){
                                                            if(xmlr.isCharacters()){
                                                                nomeFruiPorp=xmlr.getText();
                                                                fineNomeFru=true;
                                                            }
                                                            if(!fineNomeFru){
                                                                xmlr.next();
                                                            }
                                                        }

                                                        break;
                                                    case "tempoProposta":
                                                        boolean fineTampPro=false;
                                                        while(!fineTampPro){
                                                            if(xmlr.isCharacters()){
                                                                tempoProp=xmlr.getText();
                                                                fineTampPro=true;
                                                            }
                                                            if(!fineTampPro){
                                                                xmlr.next();
                                                            }
                                                        }

                                                    case "ora":
                                                        boolean fineOra=false;
                                                        while(!fineOra){
                                                            if(xmlr.isCharacters()){
                                                                ora=xmlr.getText();
                                                                oraProposta=Orario.getOrarioFromString(ora);
                                                                fineOra=true;
                                                            }
                                                            if(!fineOra){
                                                                xmlr.next();
                                                            }
                                                        }

                                                        break;
                                                }
                                            }
                                            if(xmlr.isEndElement() && xmlr.getLocalName().equals("proposta"))
                                                fineProposta=true;
                                            if(!fineProposta)
                                                xmlr.next();
                                        }
                                        if(tempoProp.length()>0){
                                            ultimaProposta=new PropostaIncontro(nomeFruiPorp,luogo,oraProposta, data, Long.valueOf(tempoProp));
                                        }
                                        break;
                                    case "time":
                                        boolean finTime=false;

                                        while(!finTime){
                                            if(xmlr.isCharacters()){
                                                time=Long.valueOf(xmlr.getText());
                                            }
                                            if(xmlr.isEndElement() && xmlr.getLocalName().equals("time"))
                                                finTime=true;
                                            if(!finTime)
                                                xmlr.next();
                                        }
                                        break;
                                }
                            }
                            if (xmlr.isEndElement() && xmlr.getLocalName().equals("scambio")) {
                                fineScambio = true;
                                Scambio scambioTemp = new Scambio(offerente, ricevente, ultimaProposta, time);
                                scambi.add(scambioTemp);
                            }
                            if (!fineScambio)
                                xmlr.next();
                        }
                    }
                    if (xmlr.isEndElement() && xmlr.getLocalName().equals("scambi"))
                        fineScambi = true;
                    if (!fineScambi)
                        xmlr.next();
                }
            }
            xmlr.next();
        }

        ListaScambi scambiToRet=new ListaScambi(scambi);
        return scambiToRet;
    }
    public static void scriviScambi(ListaScambi listaScambi,String filename)  {
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element scambi=document.createElement("scambi");
            document.appendChild(scambi);

            for(Scambio sca:listaScambi.getScambi()){
                //offerta offerente
                Element scambio=document.createElement("scambio");
                scambi.appendChild(scambio);

                Offerta offerente=sca.getOfferente();
                Element offerta=document.createElement("offertaOfferente");
                scambio.appendChild(offerta);

                Element nomeRadice=document.createElement("nomeRadice");
                offerta.appendChild(nomeRadice);
                nomeRadice.appendChild(document.createTextNode(offerente.getNomeRadice()));

                Element nomeCategoria=document.createElement("nomeCategoria");
                offerta.appendChild(nomeCategoria);
                nomeCategoria.appendChild(document.createTextNode(offerente.getNomeCategoria()));

                Element statoAttuale= document.createElement("statoAttuale");
                offerta.appendChild(statoAttuale);
                statoAttuale.appendChild(document.createTextNode(offerente.getStatoAttuale().toStringStato()));

                Element nomeFruitore=document.createElement("nomeFruitore");
                offerta.appendChild(nomeFruitore);
                nomeFruitore.appendChild(document.createTextNode(offerente.getNomeFruitore()));

                Element compilazioni=document.createElement("compilazioni");
                offerta.appendChild(compilazioni);

                for(CampoNativo c: offerente.getCompliazioni().keySet()){
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
                    compilazioneInserita.appendChild(document.createTextNode(offerente.getCompliazioni().get(c)));

                }
                Element statiPassati=document.createElement("statiPassati");
                offerta.appendChild(statiPassati);

                for(StatoOfferta s:offerente.getStatiPassati()){
                    Element st=document.createElement("statoPassato");
                    statiPassati.appendChild(st);
                    st.appendChild(document.createTextNode(s.toStringStato()));
                }
                //offerta ricevente
                Offerta ricevente=sca.getRicevente();
                Element offertaRicevente=document.createElement("offertaRicevente");
                scambio.appendChild(offertaRicevente);

                Element nomeRadiceRicevente=document.createElement("nomeRadice");
                offertaRicevente.appendChild(nomeRadiceRicevente);
                nomeRadiceRicevente.appendChild(document.createTextNode(ricevente.getNomeRadice()));

                Element nomeCategoriaRicevente=document.createElement("nomeCategoria");
                offertaRicevente.appendChild(nomeCategoriaRicevente);
                nomeCategoriaRicevente.appendChild(document.createTextNode(ricevente.getNomeCategoria()));

                Element statoAttualeRicevente= document.createElement("statoAttuale");
                offertaRicevente.appendChild(statoAttualeRicevente);
                statoAttualeRicevente.appendChild(document.createTextNode(ricevente.getStatoAttuale().toStringStato()));

                Element nomeFruitoreRicevente=document.createElement("nomeFruitore");
                offertaRicevente.appendChild(nomeFruitoreRicevente);
                nomeFruitoreRicevente.appendChild(document.createTextNode(ricevente.getNomeFruitore()));

                Element compilazioniRicevente=document.createElement("compilazioni");
                offertaRicevente.appendChild(compilazioniRicevente);

                for(CampoNativo c: ricevente.getCompliazioni().keySet()){
                    Element compilazione = document.createElement("compilazione");
                    compilazioniRicevente.appendChild(compilazione);
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
                    compilazioneInserita.appendChild(document.createTextNode(ricevente.getCompliazioni().get(c)));

                }
                Element statiPassatiRicevente=document.createElement("statiPassati");
                offertaRicevente.appendChild(statiPassatiRicevente);

                for(StatoOfferta s:ricevente.getStatiPassati()){
                    Element st=document.createElement("statoPassato");
                    statiPassatiRicevente.appendChild(st);
                    st.appendChild(document.createTextNode(s.toStringStato()));
                }
                Element proposta=document.createElement("proposta");
                scambio.appendChild(proposta);
                if(sca.getUltimaProposta()!=null){

                    Element luogo=document.createElement("luogo");
                    proposta.appendChild(luogo);
                    if(sca.getUltimaProposta()==null)
                        luogo.appendChild(document.createTextNode(""));
                    else{
                        luogo.appendChild(document.createTextNode(sca.getUltimaProposta().getLuogo()));
                    }

                    Element data=document.createElement("data");
                    proposta.appendChild(data);
                    if(sca.getUltimaProposta()==null)
                        data.appendChild(document.createTextNode(""));
                    else
                        data.appendChild(document.createTextNode(sca.getUltimaProposta().getData()));

                    Element nomeF=document.createElement("nomeF");
                    if(sca.getUltimaProposta()==null)
                        nomeF.appendChild(document.createTextNode(""));
                    else
                        nomeF.appendChild(document.createTextNode(sca.getUltimaProposta().getNomeFruitore()));
                    proposta.appendChild(nomeF);


                    Element tempoProposta=document.createElement("tempoProposta");
                    proposta.appendChild(tempoProposta);
                    if(sca.getUltimaProposta()==null)
                        tempoProposta.appendChild(document.createTextNode(""));
                    else
                        tempoProposta.appendChild(document.createTextNode(Long.toString(sca.getUltimaProposta().getTempo())));

                    Element oraProposta=document.createElement("ora");
                    proposta.appendChild(oraProposta);
                    if(sca.getUltimaProposta()==null)
                        oraProposta.appendChild(document.createTextNode(""));
                    else
                        oraProposta.appendChild(document.createTextNode(sca.getUltimaProposta().getOra().toStringOrario()));
                }


                Element timeIstant=document.createElement("time");
                scambio.appendChild(timeIstant);
                timeIstant.appendChild(document.createTextNode(Long.toString(sca.getTempo())));
            }

            Transformer transformer2 = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File(filename));
            Source input = new DOMSource(document);
            transformer2.transform(input, output);
        } catch (TransformerConfigurationException transformerConfigurationException) {
            transformerConfigurationException.printStackTrace();
        } catch (ParserConfigurationException parserConfigurationException) {
            parserConfigurationException.printStackTrace();
        } catch (TransformerException transformerException) {
            transformerException.printStackTrace();
        }
    }

}
