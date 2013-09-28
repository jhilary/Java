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
//        stack.push(2);
//        assertArrayEquals("Fail offering in empty stack", new Object[]{2}, stack.toArray());
//        stack.push(4);
//        assertArrayEquals("Fail offering in queue", new Object[]{2,4}, stack.toArray());
        fail();
    }
    
    @Test
    public void testPeek(){
//        stack.push(3);
//        stack.push(10);
//        stack.push(145);
//        stack.push(-1);
//        assertEquals("Wrong element peeked", -1, stack.peek());
//        assertArrayEquals("Peek have changed array", new Object[]{3,10,145,-1}, stack.toArray());
        fail();
    }
    
    @Test
    public void testPeekException(){
        exception.expect(NoSuchElementException.class);
        stack.peek();
    }
    
    @Test
    public void testPop(){
//        stack.push(3);
//        stack.push(10);
//        stack.push(145);
//        stack.push(-1);
//        assertEquals("Wrong element peeked", -1, stack.pop());
//        assertArrayEquals("Peek have changed array", new Object[]{3,10,145}, stack.toArray());
        fail();
    }
    
    @Test
    public void testPopException(){
        exception.expect(NoSuchElementException.class);
        stack.pop();
    }
    
}
