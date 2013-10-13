package ru.yandex.shad.belova.java.problem2;

import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: olegklymchuk
 * Date: 10/12/13
 * Time: 7:27 PM
 * To change this template use File | Settings | File Templates.
 */


interface TravelCard {

    enum OwnerType {
        Pupil, Student, Regular
    }

    enum UsageType {
        Period, Trips, Accumulative
    }

    String getID();
    OwnerType getOwnerType();
    UsageType getUsageType();

    CardInfoDetails getCardInfoDetails();
    void updateCardInfoDetails(CardInfoDetails cardInfo);

    boolean validate(int amount);
    void pay(int amount);

}

public class MetroTravelCard <T extends CardValidator> implements TravelCard {

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
