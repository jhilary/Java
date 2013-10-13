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

class TravelCardException extends Exception {
    //
}

public class TravelCardRegistry {

    private static class SingletonHolder {

        public static final TravelCardRegistry instance = new TravelCardRegistry();
    }

    public static TravelCardRegistry getInstance() {

        return SingletonHolder.instance;
    }

    private MyList travelCards = new MyArrayList();
    private MyList passStates = new MyArrayList();
    private int ticketCost;

    public TravelCard acquireTravelCard(TravelCard.OwnerType ownerType, TripCountable.Type type){

        TravelCard tc = new MetroTravelCard<TripCountable>(
                                            ownerType, TravelCard.UsageType.Trips, new TripCountable(type));

        travelCards.add(tc);
        return tc;
    }

    public TravelCard acquireTravelCard(
                                    TravelCard.OwnerType ownerType,
                                    DateExpirable.Type usageType,
                                    Date startDate){

        TravelCard tc = new MetroTravelCard(ownerType, TravelCard.UsageType.Period, new DateExpirable(usageType, startDate));
        travelCards.add(tc);
        return tc;
    }

    public TravelCard acquireTravelCard(int balance){
        TravelCard tc = new MetroTravelCard(
                                    TravelCard.OwnerType.Regular,
                                    TravelCard.UsageType.Accumulative,
                                    new Accumulative(balance));
        travelCards.add(tc);
        return tc;
    }


    public void setTicketCost(int cost) {

        this.ticketCost = cost;

    }

    public int getTicketCost() {

        return ticketCost;
    }

    public void setTravelCardPassState(TravelCardPassState passState) {

        passStates.add(passState);
    }

    private class MetroTravelCard <T extends CardValidator> implements TravelCard {

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
        public CardInfoDetails getCardInfoDetails() {

            CardInfoDetails cardInfo = new CardInfoDetails();
            validator.fillCardInfoDetails(cardInfo);

            return cardInfo;
        }

        @Override
        public void updateCardInfoDetails(CardInfoDetails cardInfo) {

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
