package ru.yandex.shad.belova.java.problem4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ilariyabelova
 * Date: 1/3/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

    private static void checkIndices(int[] array, int start_index, int end_index){
        if (start_index < 0 || end_index > array.length - 1){
            throw new IllegalArgumentException("Some index not exists in array");
        }
    }

    public static int binary_search(int value, int[] array, int start_index, int end_index){
        checkIndices(array, start_index, end_index);
        end_index = Math.max(start_index, end_index + 1);
        while(start_index < end_index){
            int medium_index = (end_index + start_index)/2;
            if(value <= array[medium_index]){
                end_index = medium_index;
            } else {
                start_index = medium_index + 1;
            }
        }
        return end_index;
    }

    public static void mergeSort(int[] array){
        mergeSort(array, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int startIndex, int endIndex){
        checkIndices(array, startIndex, endIndex);
        if (startIndex == endIndex){
            return;
        }
        int middleIndex = (startIndex + endIndex)/2;
        mergeSort(array, startIndex, middleIndex);
        mergeSort(array, middleIndex + 1, endIndex);
        merge(array, startIndex, middleIndex, endIndex);
    }

    private static void merge(int[] array, int startIndex, int middleIndex, int endIndex){

            int leftArrayLength = middleIndex - startIndex + 1;
            int[] leftCopy = new int[leftArrayLength];
            System.arraycopy(array, startIndex, leftCopy, 0, leftArrayLength);

            int leftArrayPosition = 0;
            int rightArrayPosition = middleIndex+1;
            int targetArrayPosition = startIndex;

            while ( leftArrayPosition <= middleIndex - startIndex && rightArrayPosition <= endIndex ){
                if(leftCopy[leftArrayPosition] <= array[rightArrayPosition]) {
                    array[targetArrayPosition++] = leftCopy[leftArrayPosition++];
                } else {
                    array[targetArrayPosition++] = array[rightArrayPosition++];
                }
            }

            while ( leftArrayPosition <= middleIndex - startIndex){
                array[targetArrayPosition++] = leftCopy[leftArrayPosition++];
            }
    }

    private static class pMergeRunnable implements Runnable {
        int[] array;
        int startIndexA;
        int endIndexA;
        int startIndexB;
        int endIndexB;
        int[] resultedArray;
        int startIndexResult;

        private pMergeRunnable(int[] array, int startIndexA, int endIndexA, int startIndexB, int endIndexB, int[] resultedArray, int startIndexResult) {
            this.array = array;
            this.startIndexA = startIndexA;
            this.endIndexA = endIndexA;
            this.startIndexB = startIndexB;
            this.endIndexB = endIndexB;
            this.resultedArray = resultedArray;
            this.startIndexResult = startIndexResult;
        }

        @Override
        public void run() {
//            System.err.println("Array: " + Arrays.toString(this.array) + "\n" +
//                                "Astart: " + this.startIndexA + "\n" +
//                                "Aend: " + this.endIndexA + "\n" +
//                                "Bstart: " + this.startIndexB + "\n" +
//                                "Bend: " + this.endIndexB + "\n" +
//                                "ResultedArray: " + Arrays.toString(this.resultedArray) + "\n" +
//                                "StartIndexResult: " + this.startIndexResult);
            pMerge(this.array,
                   this.startIndexA, this.endIndexA,
                   this.startIndexB, this.endIndexB,
                   this.resultedArray, this.startIndexResult);
        }
    }

    private static class pMergeSortRunnable implements Runnable {
        int[] array;
        int startIndex;
        int endIndex;
        int[] resultedArray;
        int startIndexResult;

        private pMergeSortRunnable(int[] array, int startIndex, int endIndex, int[] resultedArray, int startIndexResult) {
            this.array = array;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.resultedArray = resultedArray;
            this.startIndexResult = startIndexResult;
        }

        @Override
        public void run() {
            pMergeSort(this.array, this.startIndex, this.endIndex, this.resultedArray, this.startIndexResult);
        }
    }

    private static void pMerge(int[] array,
                               int startIndexA, int endIndexA,
                               int startIndexB, int endIndexB,
                               int[] resultedArray, int startIndexResult){

        int lengthA = endIndexA - startIndexA + 1;
        int lengthB = endIndexB - startIndexB + 1;
        if(lengthA < lengthB){
            int temp = startIndexB;
            startIndexB = startIndexA;
            startIndexA = temp;
            temp = endIndexB;
            endIndexB = endIndexA;
            endIndexA = temp;
        }
        if(endIndexA - startIndexA + 1 == 0){     // both empty
            return;
        }

        int middleIndexOfArrayA = (endIndexA + startIndexA)/2;

        int correspondingMiddleIndexOfArrayB =
                binary_search(array[middleIndexOfArrayA],array, startIndexB, endIndexB);

        int middleIndexInResultedArray = startIndexResult + (middleIndexOfArrayA - startIndexA) + (correspondingMiddleIndexOfArrayB - startIndexB);
        resultedArray[middleIndexInResultedArray] =
                array[middleIndexOfArrayA];
        Thread t = new Thread(new pMergeRunnable(array, startIndexA, middleIndexOfArrayA - 1, startIndexB, correspondingMiddleIndexOfArrayB - 1, resultedArray, startIndexResult));
        //System.err.println("Start MergeRunnable: " + t.getName());
        t.start();
        pMerge(array, middleIndexOfArrayA + 1, endIndexA, correspondingMiddleIndexOfArrayB, endIndexB, resultedArray, middleIndexInResultedArray + 1);
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted by user");
        }
    }

    private static void pMergeSort(int[] array, int startIndex, int endIndex, int[] resultedArray, int startIndexResult){
        checkIndices(array, startIndex, endIndex);
        if (endIndex - startIndex + 1 == 1){
            resultedArray[startIndexResult]= array[startIndex];
            return;
        }
        int middleIndex = (startIndex + endIndex)/2;

        int[] newResultArray = new int[endIndex - startIndex + 1];
        int middleIndexResult = middleIndex - startIndex;

        Thread t = new Thread(new pMergeSortRunnable(array, startIndex, middleIndex, newResultArray, 0));
        t.start();
        //System.out.println("Start MergeSortRunnable: " + t.getName());
        pMergeSort(array, middleIndex + 1, endIndex, newResultArray, middleIndexResult + 1);
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted by user");
        }
        pMerge(newResultArray, 0, middleIndexResult, middleIndexResult + 1, endIndex - startIndex,
                resultedArray, startIndexResult);
    }

    public static void pMergeSort(int[] array){
        int[] sortedArray = new int[array.length];
        pMergeSort(array, 0, array.length - 1, sortedArray, 0);
        System.arraycopy(sortedArray,0,array,0,array.length);
    }

    public enum MergeSortMethods{
        STRAIGHT{
            @Override
            public void sort(int[] array) {
                mergeSort(array);
            }
        },
        PARALLEL{
            @Override
            public void sort(int[] array) {
                pMergeSort(array);
            }
        };
        public abstract void sort(int[] array);
    }

    public static float timeMeasure(int[] array, MergeSortMethods method){
        long begin = System.currentTimeMillis();
        method.sort(array);
        float timeResult = (float)(System.currentTimeMillis() - begin) / 1000; //seconds
        return timeResult;
    }

    public static void main(String[] args){
        int[] array = new int[100];
        for(int i = 0; i < array.length; i++){
            array[i] = array.length - i;
        }
        System.out.println(timeMeasure(array,MergeSortMethods.STRAIGHT));
        System.out.println(timeMeasure(array,MergeSortMethods.PARALLEL));

    }
}
