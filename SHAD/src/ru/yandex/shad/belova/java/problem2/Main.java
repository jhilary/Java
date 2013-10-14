package ru.yandex.shad.belova.java.problem2;

import org.joda.time.DateTime;
import ru.yandex.shad.belova.java.problem2.Metropolitan.CardRegistry;

public class Main {

    public static void main(String[] args) {

        Gateway gt = new Gateway();

        int passInCost = CardRegistry.getInstance().getTicketCost();
        System.out.println("Pass cost: " + passInCost);

        System.out.println("\nACCUMULATIVE CARD:");
        Card accumulativeCard  = CardRegistry.getInstance().acquireTravelCard(passInCost);
        System.out.println("Pass in accepted: " + gt.passPassenger(accumulativeCard));
        System.out.println("Pass in accepted: " + gt.passPassenger(accumulativeCard));
        CardRegistry.getInstance().rechargeCardBalance(accumulativeCard, 2*passInCost);
        System.out.println("Pass in accepted: " + gt.passPassenger(accumulativeCard));
        System.out.println("Pass in accepted: " + gt.passPassenger(accumulativeCard));
        System.out.println("Pass in accepted: " + gt.passPassenger(accumulativeCard));

        System.out.println("\nPERIOD CARD:");
        Card periodCard = CardRegistry.getInstance().acquireTravelCard(Card.OwnerType.STUDENT,Card.PeriodType.TEN_DAYS, DateTime.now());
        System.out.println("Pass in accepted: " + gt.passPassenger(periodCard));

        System.out.println("\nTRIP CARD:");
        Card tripCard = CardRegistry.getInstance().acquireTravelCard(Card.OwnerType.PUPIL, Card.TripsType.FIVE_TRIPS);

        System.out.println("Pass in accepted: " + gt.passPassenger(tripCard));
        System.out.println("Pass in accepted: " + gt.passPassenger(tripCard));
        System.out.println("Pass in accepted: " + gt.passPassenger(tripCard));
        System.out.println("Pass in accepted: " + gt.passPassenger(tripCard));
        System.out.println("Pass in accepted: " + gt.passPassenger(tripCard));
        System.out.println("Pass in accepted: " + gt.passPassenger(tripCard));



    }

}
