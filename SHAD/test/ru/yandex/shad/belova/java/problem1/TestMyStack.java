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
public class TestMyStack {
    private MyStack stack;

    @Before
    public void setUp(){
        stack = new MyStack();
    }
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Test
    public void testPush(){
        //TODO
        fail();
    }
    
    @Test
    public void testPeek(){
        //TODO
        fail();
    }
    
    @Test
    public void testPop(){
        //TODO
        fail();
    }
    
}
