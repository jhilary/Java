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
public class TestPeriodGateway {

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

    public TestPeriodGateway(Card.OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    @Test
    public void periodMonthBeforeStartDate(){
        Card periodMonth = CardRegistry.getInstance().acquireTravelCard(
                ownerType,Card.PeriodType.Month,DateTime.now().plusDays(1));
        assertFalse("Before month started", gt.passPassenger(periodMonth));
    }

    @Test
    public void periodMonthAfterEndDate(){
        Card periodMonth = CardRegistry.getInstance().acquireTravelCard(
                ownerType,Card.PeriodType.Month,DateTime.now().minusMonths(1).minusDays(1));

        assertFalse("After month ended", gt.passPassenger(periodMonth));
    }

    @Test
    public void periodMonthInDate(){
        Card periodMonth = CardRegistry.getInstance().acquireTravelCard(
                ownerType,Card.PeriodType.Month,DateTime.now());
        Gateway gt = new Gateway();
        assertTrue("In month", gt.passPassenger(periodMonth));
    }
    // 10 days
    @Test
    public void periodTenDaysBeforeStartDate(){
        Card periodTenDays = CardRegistry.getInstance().acquireTravelCard(
                ownerType,Card.PeriodType.TenDays,DateTime.now().plusDays(1));
        Gateway gt = new Gateway();
        assertFalse("Before 10 days started", gt.passPassenger(periodTenDays));
    }

    @Test
    public void periodTenDaysAfterEndDate(){
        Card periodTenDays = CardRegistry.getInstance().acquireTravelCard(
                ownerType,Card.PeriodType.TenDays,DateTime.now().minusDays(10).minusDays(1));
        Gateway gt = new Gateway();
        assertFalse("After 10 days ended", gt.passPassenger(periodTenDays));
    }

    @Test
    public void periodTenDaysInDate(){
        Card periodTenDays = CardRegistry.getInstance().acquireTravelCard(
                ownerType,Card.PeriodType.TenDays,DateTime.now());
        Gateway gt = new Gateway();
        assertTrue("In 10 days", gt.passPassenger(periodTenDays));
    }

}
