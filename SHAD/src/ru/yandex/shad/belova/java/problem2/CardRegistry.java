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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    public Card acquireCard(Card.OwnerType ownerType, Date startDate, Balance balance){

        Card card = null;

        switch (balance.type)
        {
            case Trips:
                card = new MetroTravelCard<TripCardProcessingStrategy>(ownerType, new TripCardProcessingStrategy(startDate, balance));
                break;
            case Money:
                card = new MetroTravelCard(Card.OwnerType.Regular, new AccumulativeCardProcessingStrategy(startDate, balance));
                break;
            case Month:
            case Days:
                card = new MetroTravelCard(ownerType, new PeriodCardProcessingStrategy(startDate, balance));
                break;
        }

        travelCards.add(card);

        return card;
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

    public void rechargeCardBalance(String cardID, Balance balance) {

        Card card = findCardByID(cardID);
        if(card != null)
            card.updateBalance(balance);
        else
            throw new SecurityException("Card with id '" + cardID + "' not found in the registry.");
    }

    public Card findCardByID(String cardID) {
        // TODO - rewrite search appropriately
        for(int i = 0; i < travelCards.size(); ++i) {
            Card card = (Card)travelCards.get(i);
            if(card.getID().equals(cardID))
                return card;
        }

        return null;
    }

    public boolean isCardRegistered(String cardID) {

        return findCardByID(cardID) != null;
    }

    private class MetroTravelCard <T extends CardProcessingStrategy> implements Card {

        private final String id = UUID.randomUUID().toString();
        private OwnerType ownerType;
        private T validator;

        public MetroTravelCard(OwnerType ownerType, T validator){

            this.ownerType = ownerType;
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
        public Balance getBalance() {

            return validator.getBalance();

        }

        @Override
        public void updateBalance(Balance balance) {

            validator.updateBalance(balance);

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
