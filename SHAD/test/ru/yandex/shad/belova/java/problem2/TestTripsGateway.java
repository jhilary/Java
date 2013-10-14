package ru.yandex.shad.belova.java.problem2;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.Parameterized;
import ru.yandex.shad.belova.java.problem1.MyList;
import ru.yandex.shad.belova.java.problem1.MyListFactory;
import ru.yandex.shad.belova.java.problem2.Metropolitan.CardRegistry;

import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: ilariyabelova
 * Date: 10/14/13
 * Time: 5:46 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(value = Parameterized.class)
public class TestTripsGateway {

    private Card.OwnerType ownerType;
    Gateway gt = new Gateway();
    private MyList list;

    @Parameterized.Parameters(name="{0}")
    public static List<Object[]> classes(){
        LinkedList<Object[]> classes = new LinkedList<Object[]>();
        classes.add(new Object[]{Card.OwnerType.Pupil});
        classes.add(new Object[]{Card.OwnerType.Student});
        classes.add(new Object[]{Card.OwnerType.Regular});
        return classes;
    }

    public TestTripsGateway(Card.OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    @Test
    public void fiveTrips(){
        Card fiveTrips = CardRegistry.getInstance().acquireTravelCard(
                ownerType,Card.TripsType.FiveTrips);

        assertTrue("First", gt.passPassenger(fiveTrips));
        assertTrue("Second", gt.passPassenger(fiveTrips));
        assertTrue("Third", gt.passPassenger(fiveTrips));
        assertTrue("Fourth", gt.passPassenger(fiveTrips));
        assertTrue("Fifth", gt.passPassenger(fiveTrips));
        assertFalse("Sixth", gt.passPassenger(fiveTrips));
    }

    @Test
    public void tenTrips(){
        Card tenTrips = CardRegistry.getInstance().acquireTravelCard(
                ownerType,Card.TripsType.TenTrips);

        assertTrue("First", gt.passPassenger(tenTrips));
        assertTrue("Second", gt.passPassenger(tenTrips));
        assertTrue("Third", gt.passPassenger(tenTrips));
        assertTrue("Fourth", gt.passPassenger(tenTrips));
        assertTrue("Fifth", gt.passPassenger(tenTrips));
        assertTrue("Sixth", gt.passPassenger(tenTrips));
        assertTrue("Seventh", gt.passPassenger(tenTrips));
        assertTrue("Eighth", gt.passPassenger(tenTrips));
        assertTrue("Ninth", gt.passPassenger(tenTrips));
        assertTrue("Tenth", gt.passPassenger(tenTrips));
        assertFalse("Eleventh", gt.passPassenger(tenTrips));
    }
}
