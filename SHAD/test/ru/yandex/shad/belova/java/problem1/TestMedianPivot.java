package ru.yandex.shad.belova.java.problem1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class TestMedianPivot {
    private MyArrayList list;
    int start;
    int end;
    int pivotIndex;

    @Parameters
    public static Collection<Object[]> data() {
        List<Object[]> list = new LinkedList<Object[]>();

        MyArrayList myArrayList = new MyArrayList();
        myArrayList.addAll(new Object[]{1,2,3});
        list.add(new Object[]{myArrayList, 0, 2, 1});

        myArrayList = new MyArrayList();
        myArrayList.addAll(new Object[]{3,2,1});
        list.add(new Object[]{myArrayList, 0, 2, 1});

        myArrayList = new MyArrayList();
        myArrayList.addAll(new Object[]{1,3,2});
        list.add(new Object[]{myArrayList, 0, 2, 2});

        myArrayList = new MyArrayList();
        myArrayList.addAll(new Object[]{2,3,1});
        list.add(new Object[]{myArrayList, 0, 2, 0});

        myArrayList = new MyArrayList();
        myArrayList.addAll(new Object[]{2,1,3});
        list.add(new Object[]{myArrayList, 0, 2, 0});

        myArrayList = new MyArrayList();
        myArrayList.addAll(new Object[]{3,1,2});
        list.add(new Object[]{myArrayList, 0, 2, 2});

        return list;
    }

    public TestMedianPivot(MyArrayList list, int start, int end, int pivotIndex){
        this.list = list;
        this.start = start;
        this.end = end;
        this.pivotIndex = pivotIndex;
    }
    @Test
    public void testMedianPivot(){
        int pivot = MyCollection.medianPivot.getPivotIndex(this.list, this.start, this.end);
        assertEquals(this.pivotIndex, pivot);
    }
}
