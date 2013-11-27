package ru.yandex.shad.belova.java.problem2.Metropolitan;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.yandex.shad.belova.java.problem2.Card;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(value = JUnit4.class)
public class TestCardConstruction {
    CardRegistry cardRegistry;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init(){
        this.cardRegistry = CardRegistry.getInstance();
    }
    @Test
    public void constructPupilPeriodMonth(){
        Card pupilPeriodMonth = this.cardRegistry.acquireTravelCard(
                Card.OwnerType.PUPIL,Card.PeriodType.MONTH,new DateTime(14102013));
        assertEquals("Test owner",Card.OwnerType.PUPIL, pupilPeriodMonth.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.PERIOD, pupilPeriodMonth.getUsageType());
    }

    @Test
    public void constructPupilPeriod10days(){
        Card pupilPeriod10days =this.cardRegistry.acquireTravelCard(
                Card.OwnerType.PUPIL, Card.PeriodType.TEN_DAYS, new DateTime(04052013));
        assertEquals("Test owner",Card.OwnerType.PUPIL, pupilPeriod10days.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.PERIOD, pupilPeriod10days.getUsageType());
    }

    @Test
    public void constructStudentPeriodMonth(){
        Card studentPeriodMonth = this.cardRegistry.acquireTravelCard(
                Card.OwnerType.STUDENT, Card.PeriodType.MONTH, new DateTime(04052013));
        assertEquals("Test owner",Card.OwnerType.STUDENT, studentPeriodMonth.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.PERIOD, studentPeriodMonth.getUsageType());
    }

    @Test
    public void constructStudentPeriod10days(){
        Card studentPeriod10days = this.cardRegistry.acquireTravelCard(
                Card.OwnerType.STUDENT, Card.PeriodType.TEN_DAYS, new DateTime(04052013));
        assertEquals("Test owner",Card.OwnerType.STUDENT, studentPeriod10days.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.PERIOD, studentPeriod10days.getUsageType());
    }

    @Test
    public void constructRegularPeriodMonth(){
        Card regularPeriodMonth = this.cardRegistry.acquireTravelCard(
                Card.OwnerType.REGULAR, Card.PeriodType.MONTH, new DateTime(04052013));
        assertEquals("Test owner",Card.OwnerType.REGULAR, regularPeriodMonth.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.PERIOD, regularPeriodMonth.getUsageType());
    }

    @Test
    public void constructRegularPeriod10days(){
        Card regularPeriod10days = this.cardRegistry.acquireTravelCard(
                Card.OwnerType.REGULAR, Card.PeriodType.TEN_DAYS, new DateTime(04052013));
        assertEquals("Test owner",Card.OwnerType.REGULAR, regularPeriod10days.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.PERIOD, regularPeriod10days.getUsageType());
    }

    @Test
    public void constructPupilTrip5trips(){
        Card pupilTrip5trips = this.cardRegistry.acquireTravelCard(
                Card.OwnerType.PUPIL, Card.TripsType.FIVE_TRIPS);
        assertEquals("Test owner",Card.OwnerType.PUPIL, pupilTrip5trips.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.TRIPS, pupilTrip5trips.getUsageType());
    }

    @Test
    public void constructPupilTrip10trips(){
        Card pupilTrip10trips = this.cardRegistry.acquireTravelCard(
                Card.OwnerType.PUPIL, Card.TripsType.TEN_TRIPS);
        assertEquals("Test owner",Card.OwnerType.PUPIL, pupilTrip10trips.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.TRIPS, pupilTrip10trips.getUsageType());
    }

    @Test
    public void constructStudentTrip5trips(){
        Card studentTrip5trips = this.cardRegistry.acquireTravelCard(
                Card.OwnerType.STUDENT, Card.TripsType.FIVE_TRIPS);
        assertEquals("Test owner",Card.OwnerType.STUDENT, studentTrip5trips.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.TRIPS, studentTrip5trips.getUsageType());
    }

    @Test
    public void constructStudentTrip10trips(){
        Card studentTrip10trips = this.cardRegistry.acquireTravelCard(
                Card.OwnerType.STUDENT, Card.TripsType.TEN_TRIPS);
        assertEquals("Test owner",Card.OwnerType.STUDENT, studentTrip10trips.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.TRIPS, studentTrip10trips.getUsageType());
    }

    @Test
    public void constructRegularTrip5trips(){
        Card regularTrip5trips = this.cardRegistry.acquireTravelCard(
                Card.OwnerType.REGULAR, Card.TripsType.FIVE_TRIPS);
        assertEquals("Test owner",Card.OwnerType.REGULAR, regularTrip5trips.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.TRIPS, regularTrip5trips.getUsageType());
    }

    @Test
    public void constructRegularTrip10trips(){
        Card regularTrip10trips = this.cardRegistry.acquireTravelCard(
                Card.OwnerType.REGULAR, Card.TripsType.TEN_TRIPS);
        assertEquals("Test owner",Card.OwnerType.REGULAR, regularTrip10trips.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.TRIPS, regularTrip10trips.getUsageType());
    }

    @Test
    public void constructRegularAccumulative(){
        Card regularAccumulative = this.cardRegistry.acquireTravelCard(2);
        assertEquals("Test owner",Card.OwnerType.REGULAR, regularAccumulative.getOwnerType());
        assertEquals("Test usage type", Card.UsageType.ACCUMULATIVE, regularAccumulative.getUsageType());
    }

    @Test
    public void WrongInputAccumulativeCardAmount(){
        exception.expect(IllegalArgumentException.class);
        this.cardRegistry.acquireTravelCard(-1);
    }

    @Test
    public void TestWhenCardRechargeByTicketCostThenPayReturnsTrue(){
        int cost = this.cardRegistry.getTicketCost();
        Card accumulativeCard = this.cardRegistry.acquireTravelCard(0);
        this.cardRegistry.rechargeCardBalance(accumulativeCard, cost);
        assertTrue(accumulativeCard.pay());
    }

    @Test
    public void TestWhenCardRechargeNotEnoughThenPayReturnsFalse(){
        int cost = this.cardRegistry.getTicketCost();
        Card accumulativeCard = this.cardRegistry.acquireTravelCard(0);
        this.cardRegistry.rechargeCardBalance(accumulativeCard, cost-1);
        assertFalse(accumulativeCard.pay());
    }

}


