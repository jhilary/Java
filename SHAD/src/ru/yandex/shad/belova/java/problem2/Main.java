package ru.yandex.shad.belova.java.problem2;

/**
 * Created with IntelliJ IDEA.
 * User: olegklymchuk
 * Date: 10/12/13
 * Time: 7:31 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Card accumulativeTC  = CardRegistry.getInstance().acquireTravelCard(100);
        Card dateExpirable = CardRegistry.getInstance().acquireTravelCard(
                                                                            Card.OwnerType.Student,
                                                                            PeriodCardProcessingStrategy.Type.TenDays,
                                                                            new Date());
        Card tripCountable = CardRegistry.getInstance().acquireTravelCard(
                                                                            Card.OwnerType.Pupil,
                                                                            TripCardProcessingStrategy.Type.FiveTrips);

        Gateway gt = new Gateway();
        gt.passPassenger(accumulativeTC);
        gt.passPassenger(dateExpirable);
        gt.passPassenger(tripCountable);

        CardInfo cardInfo = accumulativeTC.getCardInfo();
        System.out.println(cardInfo.getBalance());
        cardInfo.setBalance(999);
        accumulativeTC.updateCardInfo(cardInfo);
        cardInfo = accumulativeTC.getCardInfo();
        System.out.println(cardInfo.getBalance());

        CardRegistry.getInstance().rechargeCardBalance(accumulativeTC.getID(), 5000);
        cardInfo = accumulativeTC.getCardInfo();
        System.out.println(cardInfo.getBalance());

    }

}
