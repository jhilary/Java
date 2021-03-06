package ru.yandex.shad.belova.java.problem2.Metropolitan;

import ru.yandex.shad.belova.java.problem2.Card;

import java.util.UUID;

class MetroCard implements Card {

    private final String id = UUID.randomUUID().toString();
    private OwnerType ownerType;
    private UsageType usageType;
    private AggregatedCardInfo cardInfo;
    private CardProcessingStrategy validator;
    private CardRegistry cardRegistry;

    MetroCard(AggregatedCardInfo cardInfo, OwnerType ownerType, UsageType usageType, CardProcessingStrategy validator, CardRegistry cardRegistry){
        this.ownerType = ownerType;
        this.usageType = usageType;
        this.validator = validator;
        this.cardInfo = cardInfo;
        this.cardRegistry = cardRegistry;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public OwnerType getOwnerType() {

        return ownerType;
    }

    @Override
    public UsageType getUsageType() {
        return usageType;
    }

    @Override
    public boolean pay() {
        cardInfo.setTicketPrice(cardRegistry.getTicketCost());
        return validator.pay(cardInfo);
    }

    AggregatedCardInfo getCardInfo() {
        return cardInfo;
    }
}