package ru.yandex.shad.belova.java.problem4;

import junit.framework.TestSuite;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Suite;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ilariyabelova
 * Date: 1/3/14
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */

@RunWith(value=Parameterized.class)
public class TestBinarySearch{
    final int[] array = new int[] {1,2,3,4,5,6,7,7,9,10,11,12};
    int value;
    int start_index;
    int end_index;
    int result;

    public TestBinarySearch(int value, int start_index, int end_index, int result) {
        this.value = value;
        this.start_index = start_index;
        this.end_index = end_index;
        this.result = result;
    }

    @Parameterized.Parameters
    public static List<Object[]> parameters(){
        List<Object[]> result = new LinkedList<Object[]>();
        result.add(new Object[]{8, 0, 11, 8});
        result.add(new Object[]{2, 5, 11, 5});
        result.add(new Object[]{7, 5, 11, 6});
        result.add(new Object[]{9, 0, 5, 6});
        result.add(new Object[]{7, 2, 6, 6});
        result.add(new Object[]{7, 2, 7, 6});
        return result;
    }

    @Test
    public void testBinarySearch(){
        int result_index = Utils.binary_search(this.value, this.array, this.start_index, this.end_index);
        Assert.assertEquals(this.result, result_index);
    }
}
