package ru.yandex.shad.belova.java.problem2;

/**
 * Created with IntelliJ IDEA.
 * User: olegklymchuk
 * Date: 10/12/13
 * Time: 7:31 PM
 * To change this template use File | Settings | File Templates.
 */

import org.joda.time.DateTime;
import ru.yandex.shad.belova.java.problem2.Metropolitan.CardRegistry;

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

        CardRegistry.getInstance().rechargeCardBalance(accumulativeTC, 5000);
    }

}
