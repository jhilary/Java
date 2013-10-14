package ru.yandex.shad.belova.java.problem2.Metropolitan;

import org.joda.time.DateTime;
import ru.yandex.shad.belova.java.problem1.MyList;
import ru.yandex.shad.belova.java.problem1.MyArrayList;
import ru.yandex.shad.belova.java.problem2.Card;

import java.util.UUID;

public class CardRegistry {

    private static class SingletonHolder {

        public static final CardRegistry instance = new CardRegistry();
    }

    public static CardRegistry getInstance() {

        return SingletonHolder.instance;
    }

    private MyList travelCards = new MyArrayList();
    private MyList passStates = new MyArrayList();
    private final int ticketCost = 1;


    ///// FACTORY METHODS TO CREATE CARDS
    public Card acquireTravelCard(Card.OwnerType ownerType, Card.TripsType type){

        AggregatedCardInfo cardInfo = new AggregatedCardInfo();
        cardInfo.setNumberOfTripsLeft(type.getNumTrips());
        Card card = new MetroCard(cardInfo, ownerType, Card.UsageType.Trips, new TripCardProcessingStrategy());

        travelCards.add(card);
        return card;
    }

    public Card acquireTravelCard(
                                    Card.OwnerType ownerType,
                                    Card.PeriodType usageType,
                                    DateTime startDate){

        AggregatedCardInfo cardInfo = new AggregatedCardInfo();
        cardInfo.setValidFrom(startDate);
        cardInfo.setValidTo(startDate.plus(usageType.getPeriod()));
        Card card = new MetroCard(cardInfo, ownerType, Card.UsageType.Period, new PeriodCardProcessingStrategy());
        travelCards.add(card);
        return card;
    }

    public Card acquireTravelCard(int amount){
        if(amount < 0){
            throw new IllegalArgumentException("Amount for adding to card must be greater then 0");
        }
        AggregatedCardInfo cardInfo = new AggregatedCardInfo();
        cardInfo.setBalance(amount);
        Card card = new MetroCard(cardInfo, Card.OwnerType.Regular, Card.UsageType.Accumulative, new AccumulativeCardProcessingStrategy());
        travelCards.add(card);
        return card;
    }

    public int getTicketCost() {

        return ticketCost;
    }

    public void setTravelCardPassState(CardPassState passState) {

        passStates.add(passState);
    }

    public void rechargeCardBalance(Card card, int amount) {
        ((MetroCard)card).getCardInfo().setBalance(((MetroCard)card).getCardInfo().getBalance() + amount);
    }

    private class MetroCard implements Card {

        private final String id = UUID.randomUUID().toString();
        private OwnerType ownerType;
        private UsageType usageType;
        private AggregatedCardInfo cardInfo;
        private CardProcessingStrategy validator;

        public MetroCard(AggregatedCardInfo cardInfo, OwnerType ownerType, UsageType usageType, CardProcessingStrategy validator){
            this.ownerType = ownerType;
            this.usageType = usageType;
            this.validator = validator;
            this.cardInfo = cardInfo;
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
            cardInfo.setTicketPrice(getTicketCost());
            return validator.pay(cardInfo);
        }

        public AggregatedCardInfo getCardInfo() {
            return cardInfo;
        }
    }

}
