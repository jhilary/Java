package ru.yandex.shad.belova.java.problem2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.yandex.shad.belova.java.problem2.Metropolitan.CardRegistry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(value = JUnit4.class)
public class TestAccumulativeGateway {

    Gateway gt = new Gateway();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void EmptyAccumulativeCard(){
        Card accumulativeCard = CardRegistry.getInstance().acquireTravelCard(0);
        assertFalse("Empty accumulative card", gt.passPassenger(accumulativeCard));
    }

    @Test
    public void WrongInputAccumulativeCardAmount(){
        exception.expect(IllegalArgumentException.class);
        Card accumulativeCard = CardRegistry.getInstance().acquireTravelCard(-1);
    }

    @Test
    public void AccumulativeCardPass(){
        int cost = CardRegistry.getInstance().getTicketCost();
        Card accumulativeCard = CardRegistry.getInstance().acquireTravelCard(cost * 3);
        assertTrue("First", gt.passPassenger(accumulativeCard));
        assertTrue("Second", gt.passPassenger(accumulativeCard));
        assertTrue("Third", gt.passPassenger(accumulativeCard));
        assertFalse("Forth", gt.passPassenger(accumulativeCard));
    }

    @Test
    public void AccumulativeCardRecharge(){
        int cost = CardRegistry.getInstance().getTicketCost();
        Card accumulativeCard = CardRegistry.getInstance().acquireTravelCard(cost);
        assertTrue("First", gt.passPassenger(accumulativeCard));
        assertFalse("Second", gt.passPassenger(accumulativeCard));
        CardRegistry.getInstance().rechargeCardBalance(accumulativeCard, cost);
        assertTrue("First after recharge", gt.passPassenger(accumulativeCard));
        assertFalse("Second after recharge", gt.passPassenger(accumulativeCard));

    }

}
