package ru.yandex.shad.belova.java.problem2.Metropolitan;

import org.joda.time.*;

/**
 * Created with IntelliJ IDEA.
 * User: olegklymchuk
 * Date: 10/13/13
 * Time: 1:54 AM
 * To change this template use File | Settings | File Templates.
 */

public interface CardProcessingStrategy {

    boolean pay(AggregatedCardInfo cardInfo);
}

class PeriodCardProcessingStrategy implements CardProcessingStrategy {

 @Override
    public boolean pay(AggregatedCardInfo cardInfo) {
        Interval interval = new Interval(cardInfo.getValidFrom(), cardInfo.getValidTo());
        if(interval.containsNow()){
            return true;
        }
        return false;
    }
}

class TripCardProcessingStrategy implements CardProcessingStrategy {

    @Override
    public boolean pay(AggregatedCardInfo cardInfo) {
        if(cardInfo.getNumberOfTripsLeft() > 0){
            cardInfo.setNumberOfTripsLeft(cardInfo.getNumberOfTripsLeft()-1);
            return true;
        }
        return false;

    }
}

class AccumulativeCardProcessingStrategy implements CardProcessingStrategy {

    @Override
    public boolean pay(AggregatedCardInfo cardInfo) {
        if((cardInfo.getBalance() - cardInfo.getTicketPrice()) >= 0){
            cardInfo.setBalance(cardInfo.getBalance() - cardInfo.getTicketPrice());
            return true;
        }
        return false;

    }

}
