package ru.yandex.shad.belova.java.problem2;

/**
 * Created with IntelliJ IDEA.
 * User: olegklymchuk
 * Date: 10/12/13
 * Time: 10:05 PM
 * To change this template use File | Settings | File Templates.
 */

import ru.yandex.shad.belova.java.problem1.MyList;
import ru.yandex.shad.belova.java.problem1.MyArrayList;

import java.util.Date;
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
    public Card acquireTravelCard(Card.OwnerType ownerType, TripCardProcessingStrategy.Type type){

        Card tc = new MetroTravelCard<TripCardProcessingStrategy>(
                                            ownerType, Card.UsageType.Trips, new TripCardProcessingStrategy(type));

        travelCards.add(tc);
        return tc;
    }

    public Card acquireTravelCard(
                                    Card.OwnerType ownerType,
                                    PeriodCardProcessingStrategy.Type usageType,
                                    Date startDate){

        Card tc = new MetroTravelCard(ownerType, Card.UsageType.Period, new PeriodCardProcessingStrategy(usageType, startDate));
        travelCards.add(tc);
        return tc;
    }

    public Card acquireTravelCard(int balance){
        Card tc = new MetroTravelCard(
                                    Card.OwnerType.Regular,
                                    Card.UsageType.Accumulative,
                                    new AccumulativeCardProcessingStrategy(balance));
        travelCards.add(tc);
        return tc;
    }
    ///////////


    public void setTicketCost(int cost) {

        this.ticketCost = cost;

    }

    public int getTicketCost() {

        return ticketCost;
    }

    public void setTravelCardPassState(CardPassState passState) {

        passStates.add(passState);
    }

    public void rechargeCardBalance(String cardID, int amount) {
        // TODO - rewrite search appropriately
        for(int i = 0; i < travelCards.size(); ++i) {
            Card tc = (Card)travelCards.get(i);
            if(tc.getID().equals(cardID)){
                CardInfo cardInfo = new CardInfo();
                cardInfo.setBalance(amount);
                tc.updateCardInfo(cardInfo);
                break;
            }
        }
    }

    private class MetroTravelCard <T extends CardProcessingStrategy> implements Card {

        private final String id = UUID.randomUUID().toString();
        private OwnerType ownerType;
        private UsageType usageType;
        private T validator;


        public MetroTravelCard(OwnerType ownerType, UsageType usageType, T validator){

            this.ownerType = ownerType;
            this.usageType = usageType;
            this.validator = validator;

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
        public CardInfo getCardInfo() {

            CardInfo cardInfo = new CardInfo();
            validator.fillCardInfoDetails(cardInfo);

            return cardInfo;
        }

        @Override
        public void updateCardInfo(CardInfo cardInfo) {

            validator.updateCardInfoDetails(cardInfo);
        }

        @Override
        public boolean validate(int ticketCost) {

            return validator.validate(ticketCost);
        }

        @Override
        public void pay(int ticketCost) {

            validator.pay(ticketCost);
        }
    }

}
