package ru.yandex.shad.belova.java.problem2;

import ru.yandex.shad.belova.java.problem2.Metropolitan.CardRegistry;

import java.util.UUID;

public class Gateway {

    private final String id = UUID.randomUUID().toString();

    private CardRegistry cardRegistry;

    public Gateway(CardRegistry cardRegistry) {
        this.cardRegistry = cardRegistry;
    }

    public boolean passPassenger(Card card) {

        if(card == null){
            throw new IllegalArgumentException();
        }

        boolean isPaid = card.pay();

        PassState passState = new PassState();
        passState.gatewayID = id;
        passState.ownerType = card.getOwnerType();
        passState.usageType = card.getUsageType();
        passState.isPassedIn = isPaid;

        this.cardRegistry.setStatistics(passState);

        return isPaid;
    }
}
