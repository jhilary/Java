package ru.yandex.shad.belova.java.problem2;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: olegklymchuk
 * Date: 10/13/13
 * Time: 3:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class Gateway {

    private final String id = UUID.randomUUID().toString();

    public boolean passPassenger(Card tc) {

        boolean result = false;

        CardPassState tcps = new CardPassState();
        tcps.gatewayID = id;
        tcps.travelCardID = tc.getID();
        result = tc.pay();
        if(result){
            tcps.travelCardState = CardPassState.State.Allowed;
        } else {
            tcps.travelCardState = CardPassState.State.Denied;
        }
        CardRegistry.getInstance().setTravelCardPassState(tcps);

        return result;
    }
}
