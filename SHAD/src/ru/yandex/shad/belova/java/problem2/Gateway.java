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
        int ticketCost = CardRegistry.getInstance().getTicketCost();
        if(!tc.validate(ticketCost)) {

            tcps.travelCardState = CardPassState.State.Denied;
            result = false;
        }
        else {
            try {
                tc.pay(ticketCost);
            }
            catch(Exception ex)
            {
                //todo - define proper exception and exception handler
                result = false;
            }
        }

        CardRegistry.getInstance().setTravelCardPassState(tcps);

        return result;
    }
}
