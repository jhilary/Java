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

        TravelCard accumulativeTC  = TravelCardRegistry.getInstance().acquireTravelCard(100);
        TravelCard dateExpirable = TravelCardRegistry.getInstance().acquireTravelCard(
                                                                            TravelCard.OwnerType.Student,
                                                                            DateExpirable.Type.TenDays,
                                                                            new Date());
        TravelCard tripCountable = TravelCardRegistry.getInstance().acquireTravelCard(
                                                                            TravelCard.OwnerType.Pupil,
                                                                            TripCountable.Type.FiveTrips);

        Gateway gt = new Gateway();
        gt.passPassenger(accumulativeTC);
        gt.passPassenger(dateExpirable);
        gt.passPassenger(tripCountable);

    }

}
