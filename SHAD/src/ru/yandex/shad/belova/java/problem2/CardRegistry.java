package ru.yandex.shad.belova.java.problem2;

/**
 * Created with IntelliJ IDEA.
 * User: olegklymchuk
 * Date: 10/12/13
 * Time: 10:05 PM
 * To change this template use File | Settings | File Templates.
 */

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.ReadablePeriod;
import ru.yandex.shad.belova.java.problem1.MyList;
import ru.yandex.shad.belova.java.problem1.MyArrayList;

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
    private int ticketCost;


    ///// FACTORY METHODS TO CREATE CARDS
    public Card acquireTravelCard(Card.OwnerType ownerType, Card.TripsType type){

        AggregatedCardInfo cardInfo = new AggregatedCardInfo();
        cardInfo.setNumberOfTripsLeft(type.getNumTrips());
        Card tc = new MetroCard(cardInfo, ownerType, Card.UsageType.Trips, new TripCardProcessingStrategy());

        travelCards.add(tc);
        return tc;
    }

    public Card acquireTravelCard(
                                    Card.OwnerType ownerType,
                                    Card.PeriodType usageType,
                                    DateTime startDate){

        AggregatedCardInfo cardInfo = new AggregatedCardInfo();
        cardInfo.setValidFrom(startDate);
        cardInfo.setValidTo(startDate.plus(usageType.getPeriod()));
        Card tc = new MetroCard(cardInfo, ownerType, Card.UsageType.Period, new PeriodCardProcessingStrategy());
        travelCards.add(tc);
        return tc;
    }

    public Card acquireTravelCard(int balance){
        AggregatedCardInfo cardInfo = new AggregatedCardInfo();
        cardInfo.setBalance(balance);
        Card tc = new MetroCard(cardInfo, Card.OwnerType.Regular, Card.UsageType.Accumulative, new AccumulativeCardProcessingStrategy());
        travelCards.add(tc);
        return tc;
    }

    public void setTicketCost(int cost) {

        this.ticketCost = cost;
    }

    public int getTicketCost() {

        return ticketCost;
    }

    public void setTravelCardPassState(CardPassState passState) {

        passStates.add(passState);
    }

    public void rechargeCardBalance(Card card, int amount) {
        card.getCardInfo().setBalance(card.getCardInfo().getBalance() + amount);
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
        public AggregatedCardInfo getCardInfo() {
            return cardInfo;
        }

        @Override
        public boolean pay() {
            cardInfo.setTicketPrice(getTicketCost());
            return validator.pay(cardInfo);
        }
    }

}
