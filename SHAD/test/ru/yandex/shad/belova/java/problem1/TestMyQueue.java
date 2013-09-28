package ru.yandex.shad.belova.java.problem1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(value = JUnit4.class)
public class TestMyQueue {
    private MyQueue queue;

    @Before
    public void setUp(){
        queue = new MyQueue();
    }
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Test
    public void testOffer(){
        queue.offer(2);
        assertArrayEquals("Fail offering in empty queue", new Object[]{2}, queue.toArray());
        queue.offer(4);
        assertArrayEquals("Fail offering in queue", new Object[]{2,4}, queue.toArray());
    }
    
    @Test
    public void testPeek(){
        //TODO
    }
    
    @Test
    public void testPoll(){
        //TODO
    }
    
}

