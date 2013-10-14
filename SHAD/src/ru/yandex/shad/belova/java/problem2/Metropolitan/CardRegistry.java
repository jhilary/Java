package ru.yandex.shad.belova.java.problem2.Metropolitan;

import org.joda.time.DateTime;
import ru.yandex.shad.belova.java.problem1.MyLinkedList;
import ru.yandex.shad.belova.java.problem1.MyList;
import ru.yandex.shad.belova.java.problem1.MyArrayList;
import ru.yandex.shad.belova.java.problem2.Card;

import java.util.HashMap;
import java.util.Map;

public class CardRegistry {

    private static class SingletonHolder {

        public static final CardRegistry instance = new CardRegistry();
    }

    public static CardRegistry getInstance() {

        return SingletonHolder.instance;
    }


    private MyList travelCards = new MyArrayList();
    private MyLinkedList passStates = new MyLinkedList();
    private Map<Card.Type, IncrementedInteger> acceptStatistics = new HashMap<Card.Type, IncrementedInteger>();
    private Map<Card.Type, IncrementedInteger> rejectStatistics = new HashMap<Card.Type, IncrementedInteger>();

    {
        for(Card.OwnerType value: Card.OwnerType.values()){
            acceptStatistics.put(value,new IncrementedInteger());
            rejectStatistics.put(value,new IncrementedInteger());
        }
        for(Card.UsageType value: Card.UsageType.values()){
            acceptStatistics.put(value,new IncrementedInteger());
            rejectStatistics.put(value,new IncrementedInteger());
        }
    }

    private final int ticketCost = 1;

    private class IncrementedInteger{
        int value = 0;
        public void increment(){
            value++;
        }
    }

    public Map<Card.Type, Integer> getAcceptStatisticsByTypes(){

        Map<Card.Type, Integer> statistics = new HashMap<Card.Type, Integer>();
        for(Card.Type key: acceptStatistics.keySet()){
            statistics.put(key, acceptStatistics.get(key).value);
        }
        return statistics;
    }

    public Map<Card.Type, Integer> getRejectStatisticsByTypes(){

        Map<Card.Type, Integer> statistics = new HashMap<Card.Type, Integer>();
        for(Card.Type key: rejectStatistics.keySet()){
            statistics.put(key, rejectStatistics.get(key).value);
        }
        return statistics;
    }

    public int getAcceptStatisticsTotal(){
        int total = 0;
        for(Card.Type key: acceptStatistics.keySet()){
            total += acceptStatistics.get(key).value;
        }
        return total;
    }

    void clearStatistics(){
        for(Card.Type key: rejectStatistics.keySet()){
            acceptStatistics.put(key, new IncrementedInteger());
            rejectStatistics.put(key, new IncrementedInteger());
        }
        passStates.clear();
    }

    public int getRejectStatisticsTotal(){
        int total = 0;
        for(Card.Type key: rejectStatistics.keySet()){
            total += rejectStatistics.get(key).value;
        }
        return total;
    }

    ///// FACTORY METHODS TO CREATE CARDS
    public Card acquireTravelCard(Card.OwnerType ownerType, Card.TripsType type){

        AggregatedCardInfo cardInfo = new AggregatedCardInfo();
        cardInfo.setNumberOfTripsLeft(type.getNumTrips());
        Card card = new MetroCard(cardInfo, ownerType, Card.UsageType.TRIPS, new TripCardProcessingStrategy());

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
        Card card = new MetroCard(cardInfo, ownerType, Card.UsageType.PERIOD, new PeriodCardProcessingStrategy());
        travelCards.add(card);
        return card;
    }

    public Card acquireTravelCard(int amount){
        if(amount < 0){
            throw new IllegalArgumentException("Amount for adding to card must be greater then 0");
        }
        AggregatedCardInfo cardInfo = new AggregatedCardInfo();
        cardInfo.setBalance(amount);
        Card card = new MetroCard(cardInfo, Card.OwnerType.REGULAR, Card.UsageType.ACCUMULATIVE, new AccumulativeCardProcessingStrategy());
        travelCards.add(card);
        return card;
    }

    public int getTicketCost() {

        return ticketCost;
    }

    public void setPassState(PassState passState) {
        passStates.add(passState);
        boolean isPassed = passState.isPassedIn;
        if(isPassed){
            acceptStatistics.get(passState.usageType).increment();
            acceptStatistics.get(passState.ownerType).increment();
        } else {
            rejectStatistics.get(passState.usageType).increment();
            rejectStatistics.get(passState.ownerType).increment();
        }
    }

    public void rechargeCardBalance(Card card, int amount) {
        ((MetroCard)card).getCardInfo().setBalance(((MetroCard)card).getCardInfo().getBalance() + amount);
    }

}
