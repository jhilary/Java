package ru.yandex.shad.belova.java.problem1;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class TestSort {
   private MyArrayList given;
   private MyArrayList result;
   
   public TestSort(MyArrayList given, MyArrayList result) {
      this.given = given;
      this.result = result;
   }

   @Parameters(name = "{0}")
   public static Collection<Object[]> data() {
     List<Object[]> testData = new LinkedList<Object[]>();
       
     MyArrayList givenList;
     MyArrayList expectedList;
     
     givenList = new MyArrayList();
     expectedList = new MyArrayList();
     givenList.addAll(new Object[] {1,2,4,3});
     expectedList.addAll(new Object[] {1,2,3,4});
     testData.add(new Object[]{givenList, expectedList});
     
     givenList = new MyArrayList();
     expectedList = new MyArrayList();
     givenList.addAll(new Object[] {3,4,1,2});
     expectedList.addAll(new Object[] {1,2,3,4});
     testData.add(new Object[]{givenList, expectedList});
     
     givenList = new MyArrayList();
     expectedList = new MyArrayList();
     givenList.addAll(new Object[] {4,3,2,1});
     expectedList.addAll(new Object[] {1,2,3,4});
     testData.add(new Object[]{givenList, expectedList});
     
     givenList = new MyArrayList();
     expectedList = new MyArrayList();
     givenList.addAll(new Object[] {1,2,3,4,5,6});
     expectedList.addAll(new Object[] {1,2,3,4,5,6});
     testData.add(new Object[]{givenList, expectedList});
     
     givenList = new MyArrayList();
     expectedList = new MyArrayList();
     givenList.addAll(new Object[] {2,2,5,4,5,6});
     expectedList.addAll(new Object[] {2,2,4,5,5,6});
     testData.add(new Object[]{givenList, expectedList});
     
     givenList = new MyArrayList();
     expectedList = new MyArrayList();
     givenList.addAll(new Object[] {-1,13,-8,4,5,183});
     expectedList.addAll(new Object[] {-8,-1,4,5,13,183});
     testData.add(new Object[]{givenList, expectedList});
     
     givenList = new MyArrayList();
     expectedList = new MyArrayList();
     givenList.addAll(new Object[] {-1,-2,-3,-4,-5,-6});
     expectedList.addAll(new Object[] {-6,-5,-4,-3,-2,-1});
     testData.add(new Object[]{givenList, expectedList});
     
     givenList = new MyArrayList();
     expectedList = new MyArrayList();
     givenList.addAll(new Object[] {1,1,0,0,1,1});
     expectedList.addAll(new Object[] {0,0,1,1,1,1});
     testData.add(new Object[]{givenList, expectedList});
     
     return testData;
   }
   @Test
   public void testInsertionSort() {
     MyCollection.sort(given);
     assertArrayEquals("Fail in sorting: ", this.result.toArray(), this.given.toArray());
   }
   
}