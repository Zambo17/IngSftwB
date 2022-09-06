package it.unibs.IngSftwB.Controller;

import it.unibs.IngSftwB.mainClasses.Orario;

public class MessaggioIntervallo implements Messaggio{

    private Orario[] ore = new Orario[2];

    public MessaggioIntervallo(Orario[] _ore) {
        this.ore = _ore;
    }

    public Orario[] getOre() {
        return ore;
    }

    public Orario getInizio(){
        return this.ore[0];
    }

    public Orario getFine(){
        return this.ore[1];
    }
}
