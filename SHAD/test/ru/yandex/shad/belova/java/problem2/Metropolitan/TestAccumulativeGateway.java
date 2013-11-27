package ru.yandex.shad.belova.java.problem2.Metropolitan;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.yandex.shad.belova.java.problem2.Card;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.mockito.Mockito;

@RunWith(value = JUnit4.class)
public class TestAccumulativeGateway {

    CardRegistry cardRegistry;
    AggregatedCardInfo aggregatedCardInfo;
    Card accumulativeCard;

    @Before
    public void init(){
        this.aggregatedCardInfo = new AggregatedCardInfo();
        this.cardRegistry = Mockito.mock(CardRegistry.class);
        this.accumulativeCard = new MetroCard(this.aggregatedCardInfo,Card.OwnerType.REGULAR,
                Card.UsageType.ACCUMULATIVE,new AccumulativeCardProcessingStrategy(), this.cardRegistry);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void TestWhenZeroMoneyPayReturnsFalse(){
        this.aggregatedCardInfo.setBalance(0);
        Mockito.when(this.cardRegistry.getTicketCost()).thenReturn(2);
        assertFalse(accumulativeCard.pay());
    }

    @Test
    public void TestWhenMoneyMoreThanTicketCostPayReturnsTrue(){
        this.aggregatedCardInfo.setBalance(3);
        Mockito.when(this.cardRegistry.getTicketCost()).thenReturn(2);
        assertTrue(accumulativeCard.pay());
    }

    @Test
    public void TestWhenMoneyLessThanTicketCostPayReturnsFalse(){
        this.aggregatedCardInfo.setBalance(1);
        Mockito.when(this.cardRegistry.getTicketCost()).thenReturn(2);
        assertFalse(accumulativeCard.pay());
    }

    @Test
    public void TestWhenPayReturnsFalseNotWithdrawMoney(){
        this.aggregatedCardInfo.setBalance(1);
        Mockito.when(this.cardRegistry.getTicketCost()).thenReturn(2);
        assertEquals(1,this.aggregatedCardInfo.getBalance());
    }

}
