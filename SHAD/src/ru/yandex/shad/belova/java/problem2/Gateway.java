package ru.yandex.shad.belova.java.problem2;

import ru.yandex.shad.belova.java.problem2.Metropolitan.PassState;
import ru.yandex.shad.belova.java.problem2.Metropolitan.CardRegistry;

import java.util.UUID;

public class Gateway {

    private final String id = UUID.randomUUID().toString();

    public boolean passPassenger(Card card) {

        boolean isPaid = card.pay();

        PassState tcps = new PassState();
        tcps.gatewayID = id;
        tcps.cardID = card.getID();
        tcps.isPassedIn = isPaid;

        CardRegistry.getInstance().setPassState(tcps);

        return isPaid;
    }
}
