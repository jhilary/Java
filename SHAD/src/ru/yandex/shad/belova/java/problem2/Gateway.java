package ru.yandex.shad.belova.java.problem2;

import ru.yandex.shad.belova.java.problem2.Metropolitan.PassState;
import ru.yandex.shad.belova.java.problem2.Metropolitan.CardRegistry;

import java.util.UUID;

public class Gateway {

    private final String id = UUID.randomUUID().toString();

    public boolean passPassenger(Card card) {

        boolean isPaid = card.pay();

        PassState passState = new PassState();
        passState.gatewayID = id;
        passState.ownerType = card.getOwnerType();
        passState.usageType = card.getUsageType();
        passState.isPassedIn = isPaid;

        CardRegistry.getInstance().setStatistics(passState);

        return isPaid;
    }
}
