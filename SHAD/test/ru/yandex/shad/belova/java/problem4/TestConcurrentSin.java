package ru.yandex.shad.belova.java.problem4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ilariyabelova
 * Date: 1/2/14
 * Time: 5:35 PM
 * To change this template use File | Settings | File Templates.
 */

@RunWith(value = Parameterized.class)
public class TestConcurrentSin {
    private int numOfThreads;
    private int N;

    public TestConcurrentSin(int numOfThreads, int N){
        this.numOfThreads = numOfThreads;
        this.N = N;

    }
    @Parameterized.Parameters(name="th: {0}, N: {1}")
    public static List<Object[]> parameters(){
        LinkedList<Object[]> parameters = new LinkedList<Object[]>();
        parameters.add(new Object[]{1, 10});
        parameters.add(new Object[]{1, 100});
        parameters.add(new Object[]{1, 1000});
        parameters.add(new Object[]{2, 10});
        parameters.add(new Object[]{2, 100});
        parameters.add(new Object[]{2, 1000});
        parameters.add(new Object[]{4, 10});
        parameters.add(new Object[]{4, 100});
        parameters.add(new Object[]{4, 1000});
        parameters.add(new Object[]{8, 10000000});
        return parameters;
    }

    @Test
    public void testConcurrentSinThread() {
        Assert.assertEquals(0, ConcurrentSin.Methods.THREAD.calculate(this.N, this.numOfThreads), 0.01);

    }

    @Test
    public void testConcurrentSinExecutorService() {
        Assert.assertEquals(0, ConcurrentSin.Methods.EXECUTORSERVICE.calculate(this.N, this.numOfThreads), 0.01);

    }
}
