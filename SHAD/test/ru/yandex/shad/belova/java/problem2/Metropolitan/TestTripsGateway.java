package ru.yandex.shad.belova.java.problem2.Metropolitan;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import ru.yandex.shad.belova.java.problem1.MyList;
import ru.yandex.shad.belova.java.problem2.Card;
import ru.yandex.shad.belova.java.problem2.Gateway;
import ru.yandex.shad.belova.java.problem2.Metropolitan.CardRegistry;

import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)
public class TestTripsGateway {

    private Card.OwnerType ownerType;
    CardRegistry cardRegistry;
    private MyList list;
    private AggregatedCardInfo aggregatedCardInfo;

    @Before
    public void init(){
        this.aggregatedCardInfo = new AggregatedCardInfo();
        this.cardRegistry = Mockito.mock(CardRegistry.class);
        Mockito.when(this.cardRegistry.getTicketCost()).thenReturn(0);
    }

    @Parameterized.Parameters(name="{0}")
    public static List<Object[]> classes(){
        LinkedList<Object[]> classes = new LinkedList<Object[]>();
        classes.add(new Object[]{Card.OwnerType.PUPIL});
        classes.add(new Object[]{Card.OwnerType.STUDENT});
        classes.add(new Object[]{Card.OwnerType.REGULAR});
        return classes;
    }

    public TestTripsGateway(Card.OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    @Test
    public void TestWhenEnoughTripsThenPayReturnsTrue(){

        this.aggregatedCardInfo.setNumberOfTripsLeft(5);
        Card card = new MetroCard(this.aggregatedCardInfo,this.ownerType,
                Card.UsageType.TRIPS,new TripCardProcessingStrategy(), this.cardRegistry);
        assertTrue(card.pay());
    }

    @Test
    public void TestWhenNotEnoughTripsThenPayReturnsFalse(){

        this.aggregatedCardInfo.setNumberOfTripsLeft(0);
        Card card = new MetroCard(this.aggregatedCardInfo,this.ownerType,
                Card.UsageType.TRIPS,new TripCardProcessingStrategy(), this.cardRegistry);
        assertFalse(card.pay());
    }

    @Test
    public void TestNumberOfTripsIsDecreasedWhenPassing(){

        this.aggregatedCardInfo.setNumberOfTripsLeft(4);
        MetroCard card = new MetroCard(this.aggregatedCardInfo,this.ownerType,
                Card.UsageType.TRIPS,new TripCardProcessingStrategy(), this.cardRegistry);
        card.pay();
        assertEquals(3,card.getCardInfo().getNumberOfTripsLeft());
    }
}
