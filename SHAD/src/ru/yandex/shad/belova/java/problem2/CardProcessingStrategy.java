package ru.yandex.shad.belova.java.problem2;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: olegklymchuk
 * Date: 10/13/13
 * Time: 1:54 AM
 * To change this template use File | Settings | File Templates.
 */

public interface CardProcessingStrategy {

    boolean validate(int ticketCost);
    void pay(int ticketCost);

    void fillCardInfoDetails(CardInfo cardInfo);
    void updateCardInfoDetails(CardInfo cardInfo);

}

class PeriodCardProcessingStrategy implements CardProcessingStrategy {

    Type type;
    private Date startDate;
    Calendar startCalendar = new GregorianCalendar();

    public enum Type {
        TenDays,
        Month
    }

    public PeriodCardProcessingStrategy(Type type, Date startDate) {
        this.type = type;
        this.startDate = startDate;
        startCalendar.setTime(this.startDate);
    }

    @Override
    public boolean validate(int ticketCost) {
        // todo - check whether the time has not expired
        // the same way as in  fillCardInfoDetails()
        return false;
    }

    @Override
    public void pay(int ticketCost) {
        // do nothing
    }

    @Override
    public void fillCardInfoDetails(CardInfo cardInfo) {

        cardInfo.setValidFrom(startDate);

        Calendar now = new GregorianCalendar();
        now.setTime(new Date());

        if(type == Type.Month) {

            int startMonth = startCalendar.get(Calendar.MONTH);
            int nowMonth = now.get(Calendar.MONTH);
            cardInfo.setExpired(startMonth < nowMonth);
        }
        else
        {
            // todo - calculate difference for 10 days case
        }
    }

    @Override
    public void updateCardInfoDetails(CardInfo cardInfo) {
        //do nothing
    }
}

class TripCardProcessingStrategy implements CardProcessingStrategy {

    public enum Type {
        FiveTrips(5),
        TenDays(10);

        int numTrips;

        Type(int numTrips) {
            this.numTrips = numTrips;
        }

        int getNumTrips() {
            return numTrips;
        }
    }

    private final Type type;
    private int numTrips;

    public TripCardProcessingStrategy(Type type) {

        this.type = type;
        numTrips = type.getNumTrips();

    }

    @Override
    public boolean validate(int ticketCost) {
        return numTrips > 0;
    }

    @Override
    public void pay(int ticketCost) {
        --numTrips;
    }

    @Override
    public void fillCardInfoDetails(CardInfo cardInfo) {
        cardInfo.setNumberOfTripsLeft(numTrips);
    }

    @Override
    public void updateCardInfoDetails(CardInfo cardInfo) {
        //do nothing
    }
}

class AccumulativeCardProcessingStrategy implements CardProcessingStrategy {

    private int balance;

    public AccumulativeCardProcessingStrategy(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean validate(int ticketCost) {
        return (balance - ticketCost) >= 0;
    }

    @Override
    public void pay(int ticketCost) {
        balance -= ticketCost;
    }

    @Override
    public void fillCardInfoDetails(CardInfo cardInfo) {
        cardInfo.setBalance(balance);
    }

    @Override
    public void updateCardInfoDetails(CardInfo cardInfo) {
        this.balance += cardInfo.getBalance();
    }
}