package ru.yandex.shad.belova.java.problem4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;

@RunWith(value = Parameterized.class)
public class TestSorting {
    private int[] given;
    private int[] result;

    public TestSorting(int[] given, int[] result) {
        this.given = given;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        List<Object[]> expectedTestData = new LinkedList<Object[]>();

        expectedTestData.add(new Object[]{new int[] {3}, new int[] {3}});
        expectedTestData.add(new Object[]{new int[] {3,1}, new int[] {1,3}});
        expectedTestData.add(new Object[]{new int[] {2,1,4,3}, new int[] {1,2,3,4}});
        expectedTestData.add(new Object[]{new int[] {3,4,1,2}, new int[] {1,2,3,4}});
        expectedTestData.add(new Object[]{new int[] {4,3,2,1}, new int[] {1,2,3,4}});
        expectedTestData.add(new Object[]{new int[] {1,2,3,4,5,6}, new int[] {1,2,3,4,5,6}});
        expectedTestData.add(new Object[]{new int[] {2,2,5,4,5,6}, new int[] {2,2,4,5,5,6}});
        expectedTestData.add(new Object[]{new int[] {-1,13,-8,4,5,183}, new int[] {-8,-1,4,5,13,183}});
        expectedTestData.add(new Object[]{new int[] {-1,-2,-3,-4,-5,-6}, new int[] {-6,-5,-4,-3,-2,-1}});
        expectedTestData.add(new Object[]{new int[] {1,1,0,0,1,1}, new int[] {0,0,1,1,1,1}});
        expectedTestData.add(new Object[]{new int[] {3,4,6,1,2,5}, new int[] {1,2,3,4,5,6}});
        return expectedTestData;
    }


    @Test
    public void testMergeSort() {
        int[] sorted = this.given.clone();
        Utils.mergeSort(sorted);
        Assert.assertArrayEquals("Fail in inversions " + Arrays.toString(this.given), this.result, sorted);
    }

    @Test
    public void testPMergeSort() {
        int[] sorted = this.given.clone();
        Utils.pMergeSort(sorted);
        Assert.assertArrayEquals("Fail in array " + Arrays.toString(this.given) + " which become " + Arrays.toString(sorted),
                this.result, sorted);
    }
}
