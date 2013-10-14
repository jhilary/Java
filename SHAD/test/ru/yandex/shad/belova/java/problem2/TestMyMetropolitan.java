package ru.yandex.shad.belova.java.problem2;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.yandex.shad.belova.java.problem1.MyArrayList;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(value = JUnit4.class)
public class TestMyMetropolitan {

    @Test
    public void constructPupilPeriodMonth(){
        Card pupilPeriodMonth = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.Pupil,PeriodCardProcessingStrategy.Type.Month,new Date(04052013));
        assertEquals("Test owner",Card.OwnerType.Pupil, pupilPeriodMonth.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.Period, pupilPeriodMonth.getUsageType());
    }

    @Test
    public void constructPupilPeriod10days(){
        Card pupilPeriod10days = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.Pupil,PeriodCardProcessingStrategy.Type.TenDays,new Date(04052013));
        assertEquals("Test owner",Card.OwnerType.Pupil, pupilPeriod10days.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.Period, pupilPeriod10days.getUsageType());
    }

    @Test
    public void constructStudentPeriodMonth(){
        Card studentPeriodMonth = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.Student,PeriodCardProcessingStrategy.Type.Month,new Date(04052013));
        assertEquals("Test owner",Card.OwnerType.Student, studentPeriodMonth.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.Period, studentPeriodMonth.getUsageType());
    }

    @Test
    public void constructStudentPeriod10days(){
        Card studentPeriod10days = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.Student,PeriodCardProcessingStrategy.Type.TenDays,new Date(04052013));
        assertEquals("Test owner",Card.OwnerType.Student, studentPeriod10days.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.Period, studentPeriod10days.getUsageType());
    }

    @Test
    public void constructRegularPeriodMonth(){
        Card regularPeriodMonth = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.Regular,PeriodCardProcessingStrategy.Type.Month,new Date(04052013));
        assertEquals("Test owner",Card.OwnerType.Regular, regularPeriodMonth.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.Period, regularPeriodMonth.getUsageType());
    }

    @Test
    public void constructRegularPeriod10days(){
        Card regularPeriod10days = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.Regular,PeriodCardProcessingStrategy.Type.TenDays,new Date(04052013));
        assertEquals("Test owner",Card.OwnerType.Regular, regularPeriod10days.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.Period, regularPeriod10days.getUsageType());
    }

    ////////
    @Test
    public void constructPupilTrip5trips(){
        Card pupilTrip5trips = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.Pupil,TripCardProcessingStrategy.Type.FiveTrips);
        assertEquals("Test owner",Card.OwnerType.Pupil, pupilTrip5trips.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.Trips, pupilTrip5trips.getUsageType());
    }

    @Test
    public void constructPupilTrip10trips(){
        Card pupilTrip10trips = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.Pupil,TripCardProcessingStrategy.Type.TenTrips);
        assertEquals("Test owner",Card.OwnerType.Pupil, pupilTrip10trips.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.Trips, pupilTrip10trips.getUsageType());
    }

    @Test
    public void constructStudentTrip5trips(){
        Card studentTrip5trips = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.Student,TripCardProcessingStrategy.Type.FiveTrips);
        assertEquals("Test owner",Card.OwnerType.Student, studentTrip5trips.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.Trips, studentTrip5trips.getUsageType());
    }

    @Test
    public void constructStudentTrip10trips(){
        Card studentTrip10trips = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.Student,TripCardProcessingStrategy.Type.TenTrips);
        assertEquals("Test owner",Card.OwnerType.Student, studentTrip10trips.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.Trips, studentTrip10trips.getUsageType());
    }

    @Test
    public void constructRegularTrip5trips(){
        Card regularTrip5trips = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.Regular,TripCardProcessingStrategy.Type.FiveTrips);
        assertEquals("Test owner",Card.OwnerType.Regular, regularTrip5trips.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.Trips, regularTrip5trips.getUsageType());
    }

    @Test
    public void constructRegularTrip10trips(){
        Card regularTrip10trips = CardRegistry.getInstance().acquireTravelCard(
                Card.OwnerType.Regular,TripCardProcessingStrategy.Type.TenTrips);
        assertEquals("Test owner",Card.OwnerType.Regular, regularTrip10trips.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.Trips, regularTrip10trips.getUsageType());
    }

    @Test
    public void constructRegularAccumulative(){
        Card regularAccumulative = CardRegistry.getInstance().acquireTravelCard(2);
        assertEquals("Test owner",Card.OwnerType.Regular, regularAccumulative.getOwnerType());
        assertEquals("Test usage type",Card.UsageType.Accumulative, regularAccumulative.getUsageType());
        regularAccumulative.validate(1);
        regularAccumulative.pay(1);
    }

}


