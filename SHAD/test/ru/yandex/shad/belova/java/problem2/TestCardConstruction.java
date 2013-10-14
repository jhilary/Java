package ru.yandex.shad.belova.java.problem2;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.yandex.shad.belova.java.problem2.Metropolitan.CardRegistry;

import static org.junit.Assert.assertEquals;

@RunWith(value = JUnit4.class)
public class TestCardConstruction {

    @Test
    public void constructPupilPeriodMonth(){
        Card pupilPeriodMonth = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.PUPIL,Card.PeriodType.MONTH,new DateTime(14102013));
        assertEquals("Test owner",Card.OwnerType.PUPIL, pupilPeriodMonth.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.PERIOD, pupilPeriodMonth.getUsageType());
    }

    @Test
    public void constructPupilPeriod10days(){
        Card pupilPeriod10days = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.PUPIL,Card.PeriodType.TEN_DAYS,new DateTime(04052013));
        assertEquals("Test owner",Card.OwnerType.PUPIL, pupilPeriod10days.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.PERIOD, pupilPeriod10days.getUsageType());
    }

    @Test
    public void constructStudentPeriodMonth(){
        Card studentPeriodMonth = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.STUDENT,Card.PeriodType.MONTH,new DateTime(04052013));
        assertEquals("Test owner",Card.OwnerType.STUDENT, studentPeriodMonth.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.PERIOD, studentPeriodMonth.getUsageType());
    }

    @Test
    public void constructStudentPeriod10days(){
        Card studentPeriod10days = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.STUDENT,Card.PeriodType.TEN_DAYS,new DateTime(04052013));
        assertEquals("Test owner",Card.OwnerType.STUDENT, studentPeriod10days.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.PERIOD, studentPeriod10days.getUsageType());
    }

    @Test
    public void constructRegularPeriodMonth(){
        Card regularPeriodMonth = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.REGULAR,Card.PeriodType.MONTH,new DateTime(04052013));
        assertEquals("Test owner",Card.OwnerType.REGULAR, regularPeriodMonth.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.PERIOD, regularPeriodMonth.getUsageType());
    }

    @Test
    public void constructRegularPeriod10days(){
        Card regularPeriod10days = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.REGULAR,Card.PeriodType.TEN_DAYS,new DateTime(04052013));
        assertEquals("Test owner",Card.OwnerType.REGULAR, regularPeriod10days.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.PERIOD, regularPeriod10days.getUsageType());
    }

    @Test
    public void constructPupilTrip5trips(){
        Card pupilTrip5trips = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.PUPIL,Card.TripsType.FIVE_TRIPS);
        assertEquals("Test owner",Card.OwnerType.PUPIL, pupilTrip5trips.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.TRIPS, pupilTrip5trips.getUsageType());
    }

    @Test
    public void constructPupilTrip10trips(){
        Card pupilTrip10trips = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.PUPIL,Card.TripsType.TEN_TRIPS);
        assertEquals("Test owner",Card.OwnerType.PUPIL, pupilTrip10trips.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.TRIPS, pupilTrip10trips.getUsageType());
    }

    @Test
    public void constructStudentTrip5trips(){
        Card studentTrip5trips = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.STUDENT,Card.TripsType.FIVE_TRIPS);
        assertEquals("Test owner",Card.OwnerType.STUDENT, studentTrip5trips.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.TRIPS, studentTrip5trips.getUsageType());
    }

    @Test
    public void constructStudentTrip10trips(){
        Card studentTrip10trips = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.STUDENT,Card.TripsType.TEN_TRIPS);
        assertEquals("Test owner",Card.OwnerType.STUDENT, studentTrip10trips.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.TRIPS, studentTrip10trips.getUsageType());
    }

    @Test
    public void constructRegularTrip5trips(){
        Card regularTrip5trips = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.REGULAR,Card.TripsType.FIVE_TRIPS);
        assertEquals("Test owner",Card.OwnerType.REGULAR, regularTrip5trips.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.TRIPS, regularTrip5trips.getUsageType());
    }

    @Test
    public void constructRegularTrip10trips(){
        Card regularTrip10trips = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.REGULAR,Card.TripsType.TEN_TRIPS);
        assertEquals("Test owner",Card.OwnerType.REGULAR, regularTrip10trips.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.TRIPS, regularTrip10trips.getUsageType());
    }

    @Test
    public void constructRegularAccumulative(){
        Card regularAccumulative = CardRegistry.getInstance().acquireTravelCard(2);
        assertEquals("Test owner",Card.OwnerType.REGULAR, regularAccumulative.getOwnerType());
        assertEquals("Test usage type", Card.UsageType.ACCUMULATIVE, regularAccumulative.getUsageType());
        regularAccumulative.pay();
    }

}


