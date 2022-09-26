package it.unibs.IngSftwB.View;

import it.unibs.IngSftwB.Controller.MessaggioStampabile;


public class StampaMessaggio {


    public static void printText( MessaggioStampabile text) {
        System.out.println(text.getMessage());
    }

    public static void printText( MessaggioStampabile text,int limite) {
        System.out.println(text.getMessage() + " " + limite);
    }

    public static void printText( MessaggioStampabile text,String limite) {
        System.out.println(text.getMessage() + " " + limite + " ");
    }



}
