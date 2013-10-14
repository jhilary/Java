package ru.yandex.shad.belova.java.problem2;

import ru.yandex.shad.belova.java.problem2.Metropolitan.CardPassState;
import ru.yandex.shad.belova.java.problem2.Metropolitan.CardRegistry;

import java.util.UUID;

public class Gateway {

    private final String id = UUID.randomUUID().toString();

    public boolean passPassenger(Card card) {

        CardPassState tcps = new CardPassState();
        tcps.gatewayID = id;
        tcps.cardID = card.getID();
        boolean result = card.pay();
        if(result){
            tcps.cardState = CardPassState.State.Allowed;
        } else {
            tcps.cardState = CardPassState.State.Denied;
        }
        CardRegistry.getInstance().setTravelCardPassState(tcps);

        return result;
    }
}
