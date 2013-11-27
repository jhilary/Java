package ru.yandex.shad.belova.java.problem2.Metropolitan;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import ru.yandex.shad.belova.java.problem1.MyList;
import ru.yandex.shad.belova.java.problem2.Card;

import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)
public class TestPeriodMetroCard {

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

    public TestPeriodMetroCard(Card.OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    @Test
    public void periodBeforeStartDate(){
        this.aggregatedCardInfo.setValidFrom(DateTime.now().plusDays(1));
        this.aggregatedCardInfo.setValidTo(DateTime.now().plusDays(2));
        Card card = new MetroCard(this.aggregatedCardInfo,this.ownerType,
                Card.UsageType.PERIOD,new PeriodCardProcessingStrategy(), this.cardRegistry);
        assertFalse(card.pay());
    }

    @Test
    public void periodAfterEndDate(){
        this.aggregatedCardInfo.setValidFrom(DateTime.now().minusDays(5));
        this.aggregatedCardInfo.setValidTo(DateTime.now().minusDays(1));
        Card card = new MetroCard(this.aggregatedCardInfo,this.ownerType,
                Card.UsageType.PERIOD,new PeriodCardProcessingStrategy(), this.cardRegistry);
        assertFalse(card.pay());
    }

    @Test
    public void periodInDate(){
        this.aggregatedCardInfo.setValidFrom(DateTime.now().minusDays(1));
        this.aggregatedCardInfo.setValidTo(DateTime.now().plusDays(1));
        Card card = new MetroCard(this.aggregatedCardInfo,this.ownerType,
                Card.UsageType.PERIOD,new PeriodCardProcessingStrategy(), this.cardRegistry);
        assertTrue(card.pay());
    }

}
