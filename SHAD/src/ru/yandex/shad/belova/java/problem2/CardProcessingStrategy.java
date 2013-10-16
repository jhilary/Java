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

public abstract class CardProcessingStrategy {

    protected Date activationDate;
    protected Balance balance;

    public CardProcessingStrategy(Date activationDate, Balance balance) {
        this.activationDate = activationDate;
        this.balance = balance;
    }

    public abstract boolean validate(int ticketCost);
    public abstract void pay(int ticketCost);

    public Balance getBalance() {
        return this.balance;
    }

    public void updateBalance(Balance balance) {
        this.balance = balance;
    }

}

class PeriodCardProcessingStrategy extends CardProcessingStrategy {

    private Calendar startCalendar = new GregorianCalendar();

    public PeriodCardProcessingStrategy(Date activationDate, Balance balance) {

        super(activationDate, balance);
        startCalendar.setTime(activationDate);
    }

    @Override
    public boolean validate(int ticketCost) {
        return !hasExpired();
    }

    @Override
    public void pay(int ticketCost) {
        // do nothing
    }

    boolean hasExpired() {

        Calendar now = new GregorianCalendar();
        now.setTime(new Date());

        //todo - just verify that startDate.days <= 'new Date()' (i.e. 'now') <= startDate.days + balance.value;
        return false;
//        if(type == Type.Month) {
//
//            int startMonth = startCalendar.get(Calendar.MONTH);
//            int nowMonth = now.get(Calendar.MONTH);
//            return (startMonth < nowMonth);
//        }
//
//        Calendar startPlusTen = startCalendar;
//        startPlusTen.add(Calendar.DATE, 10);
//
//        return now.after(startPlusTen);

    }
}

class TripCardProcessingStrategy extends CardProcessingStrategy {

    public TripCardProcessingStrategy(Date activationDate, Balance balance) {

        super(activationDate, balance);
    }

    @Override
    public boolean validate(int ticketCost) {
        return balance.value > 0;
    }

    @Override
    public void pay(int ticketCost) {
        --balance.value;
    }

}

class AccumulativeCardProcessingStrategy extends CardProcessingStrategy {

    public AccumulativeCardProcessingStrategy(Date activationDate, Balance balance) {

        super(activationDate, balance);
    }

    @Override
    public boolean validate(int ticketCost) {

        return (balance.value - ticketCost) >= 0;
    }

    @Override
    public void pay(int ticketCost) {

        balance.value -= ticketCost;
    }

    @Override
    public void updateBalance(Balance balance) {

        this.balance.value += balance.value;
    }
}
