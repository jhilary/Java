package ru.yandex.shad.belova.java.problem2.Metropolitan;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.yandex.shad.belova.java.problem2.Card;
import ru.yandex.shad.belova.java.problem2.Gateway;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(value = JUnit4.class)
public class TestStatistics {

    @Test
    public void checkHardStatistics(){

        CardRegistry cardRegistry = CardRegistry.getInstance();
        cardRegistry.clearStatistics();

        Gateway gt = new Gateway(cardRegistry);

        Card pupilPeriodMonth = cardRegistry.acquireTravelCard(Card.OwnerType.PUPIL,Card.PeriodType.MONTH,DateTime.now().plusDays(1));

        gt.passPassenger(pupilPeriodMonth); //reject

        Card studentPeriodMonth = cardRegistry.acquireTravelCard(Card.OwnerType.STUDENT,Card.PeriodType.MONTH,DateTime.now());

        gt.passPassenger(studentPeriodMonth); //accept
        gt.passPassenger(studentPeriodMonth); //accept

        Card regularPeriodMonth = cardRegistry.acquireTravelCard(Card.OwnerType.REGULAR,Card.PeriodType.MONTH,DateTime.now().minusMonths(1).minusDays(1));

        gt.passPassenger(regularPeriodMonth); //reject
        gt.passPassenger(regularPeriodMonth); //reject
        gt.passPassenger(regularPeriodMonth); //reject

        Card pupilPeriodTenDays = cardRegistry.acquireTravelCard(Card.OwnerType.PUPIL,Card.PeriodType.TEN_DAYS,DateTime.now());

        gt.passPassenger(pupilPeriodTenDays);  //accept

        Card studentPeriodTenDays = cardRegistry.acquireTravelCard(Card.OwnerType.STUDENT,Card.PeriodType.TEN_DAYS,DateTime.now().plusDays(1));

        gt.passPassenger(studentPeriodTenDays); //reject
        gt.passPassenger(studentPeriodTenDays); //reject

        Card regularPeriodTenDays = cardRegistry.acquireTravelCard(Card.OwnerType.REGULAR,Card.PeriodType.TEN_DAYS,DateTime.now().minusDays(10).minusDays(1));

        gt.passPassenger(regularPeriodTenDays);  //reject
        gt.passPassenger(regularPeriodTenDays);  //reject
        gt.passPassenger(regularPeriodTenDays);  //reject

        Card pupilTripsFiveTrips = cardRegistry.acquireTravelCard(Card.OwnerType.PUPIL,Card.TripsType.FIVE_TRIPS);

        gt.passPassenger(pupilTripsFiveTrips);  //accept
        gt.passPassenger(pupilTripsFiveTrips);  //accept
        gt.passPassenger(pupilTripsFiveTrips);  //accept
        gt.passPassenger(pupilTripsFiveTrips);  //accept
        gt.passPassenger(pupilTripsFiveTrips);  //accept
        gt.passPassenger(pupilTripsFiveTrips);  //reject

        Card studentTripsFiveTrips = cardRegistry.acquireTravelCard(Card.OwnerType.STUDENT,Card.TripsType.FIVE_TRIPS);

        gt.passPassenger(studentTripsFiveTrips);  //accept
        gt.passPassenger(studentTripsFiveTrips);  //accept
        gt.passPassenger(studentTripsFiveTrips);  //accept
        gt.passPassenger(studentTripsFiveTrips);  //accept
        gt.passPassenger(studentTripsFiveTrips);  //accept
        gt.passPassenger(studentTripsFiveTrips);  //reject
        gt.passPassenger(studentTripsFiveTrips);  //reject

        Card regularTripsFiveTrips = cardRegistry.acquireTravelCard(Card.OwnerType.REGULAR,Card.TripsType.FIVE_TRIPS);

        gt.passPassenger(regularTripsFiveTrips);   //accept
        gt.passPassenger(regularTripsFiveTrips);   //accept
        gt.passPassenger(regularTripsFiveTrips);   //accept
        gt.passPassenger(regularTripsFiveTrips);   //accept
        gt.passPassenger(regularTripsFiveTrips);   //accept
        gt.passPassenger(regularTripsFiveTrips);   //reject
        gt.passPassenger(regularTripsFiveTrips);   //reject
        gt.passPassenger(regularTripsFiveTrips);   //reject

        Card pupilTripsTenTrips = cardRegistry.acquireTravelCard(Card.OwnerType.PUPIL,Card.TripsType.TEN_TRIPS);

        gt.passPassenger(pupilTripsTenTrips);   //accept
        gt.passPassenger(pupilTripsTenTrips);   //accept
        gt.passPassenger(pupilTripsTenTrips);   //accept
        gt.passPassenger(pupilTripsTenTrips);   //accept
        gt.passPassenger(pupilTripsTenTrips);   //accept
        gt.passPassenger(pupilTripsTenTrips);   //accept
        gt.passPassenger(pupilTripsTenTrips);   //accept
        gt.passPassenger(pupilTripsTenTrips);   //accept
        gt.passPassenger(pupilTripsTenTrips);   //accept
        gt.passPassenger(pupilTripsTenTrips);   //accept
        gt.passPassenger(pupilTripsTenTrips);   //reject

        Card studentTripsTenTrips = cardRegistry.acquireTravelCard(Card.OwnerType.STUDENT,Card.TripsType.TEN_TRIPS);

        gt.passPassenger(studentTripsTenTrips);   //accept
        gt.passPassenger(studentTripsTenTrips);   //accept
        gt.passPassenger(studentTripsTenTrips);   //accept
        gt.passPassenger(studentTripsTenTrips);   //accept
        gt.passPassenger(studentTripsTenTrips);   //accept
        gt.passPassenger(studentTripsTenTrips);   //accept
        gt.passPassenger(studentTripsTenTrips);   //accept
        gt.passPassenger(studentTripsTenTrips);   //accept
        gt.passPassenger(studentTripsTenTrips);   //accept
        gt.passPassenger(studentTripsTenTrips);   //accept
        gt.passPassenger(studentTripsTenTrips);   //reject
        gt.passPassenger(studentTripsTenTrips);   //reject

        Card regularTripsTenTrips = cardRegistry.acquireTravelCard(Card.OwnerType.REGULAR,Card.TripsType.TEN_TRIPS);

        gt.passPassenger(regularTripsTenTrips);   //accept
        gt.passPassenger(regularTripsTenTrips);   //accept
        gt.passPassenger(regularTripsTenTrips);   //accept
        gt.passPassenger(regularTripsTenTrips);   //accept
        gt.passPassenger(regularTripsTenTrips);   //accept
        gt.passPassenger(regularTripsTenTrips);   //accept
        gt.passPassenger(regularTripsTenTrips);   //accept
        gt.passPassenger(regularTripsTenTrips);   //accept
        gt.passPassenger(regularTripsTenTrips);   //accept
        gt.passPassenger(regularTripsTenTrips);   //accept
        gt.passPassenger(regularTripsTenTrips);   //reject
        gt.passPassenger(regularTripsTenTrips);   //reject
        gt.passPassenger(regularTripsTenTrips);   //reject

        int cost = cardRegistry.getTicketCost();
        Card regularAccumulate = cardRegistry.acquireTravelCard(3*cost);

        gt.passPassenger(regularAccumulate);   //accept
        gt.passPassenger(regularAccumulate);   //accept
        gt.passPassenger(regularAccumulate);   //accept
        gt.passPassenger(regularAccumulate);   //reject
        gt.passPassenger(regularAccumulate);   //reject

        cardRegistry.rechargeCardBalance(regularAccumulate,2*cost);

        gt.passPassenger(regularAccumulate);   //accept
        gt.passPassenger(regularAccumulate);   //accept
        gt.passPassenger(regularAccumulate);   //reject

        Map<Card.Type, Integer> acceptStatisticsByTypes = cardRegistry.getAcceptStatisticsByTypes();
        Map<Card.Type, Integer> rejectStatisticsByTypes = cardRegistry.getRejectStatisticsByTypes();
        int acceptStatisticsTotal = cardRegistry.getAcceptStatisticsTotal();
        int rejectStatisticsTotal = cardRegistry.getRejectStatisticsTotal();

        Map<Card.Type, Integer> testMapAccepted = new HashMap<Card.Type, Integer>();
        testMapAccepted.put(Card.OwnerType.PUPIL, 16);
        testMapAccepted.put(Card.OwnerType.STUDENT, 17);
        testMapAccepted.put(Card.OwnerType.REGULAR, 20);
        testMapAccepted.put(Card.UsageType.PERIOD, 3);
        testMapAccepted.put(Card.UsageType.TRIPS, 45);
        testMapAccepted.put(Card.UsageType.ACCUMULATIVE, 5);

        assertEquals(testMapAccepted, acceptStatisticsByTypes);

        Map<Card.Type, Integer> testMapRejected = new HashMap<Card.Type, Integer>();
        testMapRejected.put(Card.OwnerType.PUPIL, 3);
        testMapRejected.put(Card.OwnerType.STUDENT, 6);
        testMapRejected.put(Card.OwnerType.REGULAR, 15);
        testMapRejected.put(Card.UsageType.PERIOD, 9);
        testMapRejected.put(Card.UsageType.TRIPS, 12);
        testMapRejected.put(Card.UsageType.ACCUMULATIVE, 3);

        assertEquals(testMapRejected, rejectStatisticsByTypes);

        assertEquals(106, acceptStatisticsTotal);
        assertEquals(48, rejectStatisticsTotal);
    }
}