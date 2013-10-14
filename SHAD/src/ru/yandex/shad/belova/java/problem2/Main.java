package ru.yandex.shad.belova.java.problem2;

/**
 * Created with IntelliJ IDEA.
 * User: olegklymchuk
 * Date: 10/12/13
 * Time: 7:31 PM
 * To change this template use File | Settings | File Templates.
 */

import org.joda.time.DateTime;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Card accumulativeTC  = CardRegistry.getInstance().acquireTravelCard(100);
        Card dateExpirable = CardRegistry.getInstance().acquireTravelCard(
                                                                            Card.OwnerType.Student,
                                                                            Card.PeriodType.TenDays,
                                                                            new DateTime("14102013"));
        Card tripCountable = CardRegistry.getInstance().acquireTravelCard(
                                                                            Card.OwnerType.Pupil,
                                                                            Card.TripsType.FiveTrips);

        Gateway gt = new Gateway();
        gt.passPassenger(accumulativeTC);
        gt.passPassenger(dateExpirable);
        gt.passPassenger(tripCountable);

        AggregatedCardInfo cardInfo = accumulativeTC.getCardInfo();
        System.out.println(cardInfo.getBalance());
        cardInfo.setBalance(999);
        cardInfo = accumulativeTC.getCardInfo();
        System.out.println(cardInfo.getBalance());

        CardRegistry.getInstance().rechargeCardBalance(accumulativeTC, 5000);
        cardInfo = accumulativeTC.getCardInfo();
        System.out.println(cardInfo.getBalance());

    }

}
