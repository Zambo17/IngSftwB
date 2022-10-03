package it.unibs.IngSftwB.Controller;

import java.io.File;

public abstract class ControlloFile {

    public static boolean fileExists(String filename){
        boolean esiste=false;
        File file=new File(filename);
        if(file.exists() && !file.isDirectory()){
            esiste=true;
        }
        return esiste;
    }
    public static boolean isXmlFile(String filename){
        boolean isXml=false;
        if(".xml".equals(filename.substring(filename.length()-4,filename.length()))){
            isXml=true;
        }
        return isXml;
    }
}
