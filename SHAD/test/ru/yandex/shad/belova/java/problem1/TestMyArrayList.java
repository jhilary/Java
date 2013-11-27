package ru.yandex.shad.belova.java.problem1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(value = JUnit4.class)
public class TestMyArrayList {
    private MyArrayList list;

    @Before
    public void setUp(){
        list = new MyArrayList();
    }
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Test
    public void testGetCapacity(){
        MyArrayList m1 = new MyArrayList();
        assertEquals("Fail on default capacity", 10, m1.getCapacity());
        MyArrayList m2 = new MyArrayList(0);
        assertEquals("Fail on default capacity", 0, m2.getCapacity());
        MyArrayList m3 = new MyArrayList(100);
        assertEquals("Fail on default capacity", 100, m3.getCapacity());
    }
    
    @Test
    public void testConstructorException(){
        exception.expect(IllegalArgumentException.class);
        new MyArrayList(-3);
    }
    
    @Test
    public void testEnsureCapacity(){
        MyArrayList m3 = new MyArrayList(100);
        m3.ensureCapacity(125);
        assertEquals("Fail increase", 125, m3.getCapacity());
        MyArrayList m4 = new MyArrayList(100);
        m4.ensureCapacity(99);
        assertEquals("Fail not insrease", 100, m4.getCapacity());
    }
    
    @Test
    public void testEnsureCapacityException(){
        exception.expect(IllegalArgumentException.class);
        MyArrayList m3 = new MyArrayList(100);
        m3.ensureCapacity(-5);
    }
    
    @Test
    public void testRemoveInCapacityTerms(){
        MyArrayList m = new MyArrayList(12);
        m.addAll(new Object[]{1,2,3,4,5,6,7,8,9,10,11,12});
        m.remove(3);
        assertEquals("Failed capacity decreasing", 11, m.getCapacity());
    }
    
    @Test
    public void testAddInCapacityTerms(){
        MyArrayList m = new MyArrayList(1);
        m.addAll(new Object[]{1,2,3});
        assertEquals("Failed capacity increasing", 3, m.getCapacity());
    }
    
}

