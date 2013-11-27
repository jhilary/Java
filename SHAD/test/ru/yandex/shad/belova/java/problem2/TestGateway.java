package ru.yandex.shad.belova.java.problem2;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.yandex.shad.belova.java.problem2.Metropolitan.CardRegistry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.mockito.Mockito;
import org.mockito.InOrder;

@RunWith(value = JUnit4.class)
public class TestGateway {

    CardRegistry cardRegistry;
    Gateway gt;
    Card card;

    @Before
    public void init(){
        this.cardRegistry = Mockito.mock(CardRegistry.class);
        this.card = Mockito.mock(Card.class);
        this.gt = new Gateway(this.cardRegistry);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void TestWhenCardRejectThenNotPass(){
        Mockito.when(this.card.pay()).thenReturn(false);
        assertFalse("Pass when rejected", gt.passPassenger(this.card));
    }

    @Test
    public void TestWhenCardAcceptThenPass(){
        Mockito.when(this.card.pay()).thenReturn(true);
        assertTrue("Not pass when accepted", gt.passPassenger(this.card));
    }

    @Test
    public void TestWhenNullThenException(){
        exception.expect(IllegalArgumentException.class);
        gt.passPassenger(null);
    }

    @Test
    public void TestSendStatisticsToCardRegistryWhenPass(){
        Mockito.when(this.card.pay()).thenReturn(true);
        gt.passPassenger(this.card);
        Mockito.verify(this.cardRegistry).setStatistics(Mockito.any(PassState.class));
    }

    @Test
    public void TestSetStatisticsCalledAfterSuccessfulPay(){
        Mockito.when(this.card.pay()).thenReturn(true);
        gt.passPassenger(this.card);
        InOrder inOrder = Mockito.inOrder(this.card,this.cardRegistry);
        inOrder.verify(this.card).pay();
        inOrder.verify(this.cardRegistry).setStatistics(Mockito.any(PassState.class));
    }

    @Test
    public void TestSetStatisticsCalledAfterUnsuccessfulPay(){
        Mockito.when(this.card.pay()).thenReturn(false);
        gt.passPassenger(this.card);
        InOrder inOrder = Mockito.inOrder(this.card,this.cardRegistry);
        inOrder.verify(this.card).pay();
        inOrder.verify(this.cardRegistry).setStatistics(Mockito.any(PassState.class));
    }
}
