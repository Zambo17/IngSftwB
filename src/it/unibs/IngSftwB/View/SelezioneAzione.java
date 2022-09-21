package it.unibs.IngSftwB.View;

import it.unibs.IngSftwB.Controller.AzioneUtente;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

public class SelezioneAzione {

    public AzioneUtente selectAction(@NotNull View view, List<AzioneUtente> list, Function<AzioneUtente, String> function) {
        return view.scegli(list, function);
    }
}
