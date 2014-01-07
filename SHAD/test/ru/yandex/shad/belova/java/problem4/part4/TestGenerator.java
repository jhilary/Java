package ru.yandex.shad.belova.java.problem4.part4;

import junit.framework.TestResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.Parameterized;

import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: ilariyabelova
 * Date: 1/7/14
 * Time: 5:53 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(value = Parameterized.class)
public class TestGenerator {
    private Vector<BigInteger> result;
    private int numOfElements;

    public TestGenerator(int numOfThreads) throws InterruptedException{
        ExecutorService pool = Executors.newFixedThreadPool(numOfThreads);
        Generator generator = new Generator();
        this.result = new Vector<BigInteger>();
        ArrayList<Callable<Integer>> callables = new ArrayList<Callable<Integer>>(numOfThreads);
        for(int i = 0; i < numOfThreads; i++){
            callables.add(new Generator.GenerationCallable(generator, 100, this.result));
        }
        pool.invokeAll(callables);
        this.numOfElements = numOfThreads * 100;
    }

    @Parameterized.Parameters
    public static List<Object[]> parameters(){
        List<Object[]> result = new LinkedList<Object[]>();
        result.add(new Object[]{1});
        result.add(new Object[]{2});
        result.add(new Object[]{4});
        result.add(new Object[]{10});
        return result;
    }


    @Test
    public void testDuplicates() throws InterruptedException{
        Set resultSet = new HashSet(result);
        Assert.assertEquals(result.size(), resultSet.size());
    }

    @Test
    public void testGaps() throws InterruptedException{
        Collections.sort(this.result);
        for(int i = 0; i < result.size() - 1; i++){
            Assert.assertEquals(result.get(i + 1), this.result.get(i).multiply(new BigInteger("2")));
        }
    }

    @Test
    public void testSize() throws InterruptedException{
        Assert.assertEquals(this.numOfElements, this.result.size()); // Check result length
    }
}
