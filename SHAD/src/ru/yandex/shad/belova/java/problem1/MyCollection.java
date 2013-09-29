package ru.yandex.shad.belova.java.problem1;

import java.util.ListIterator;

public class MyCollection {
    //TODO: Add private constructor

    private static void swap(MyList a, int i, int j){
        Object temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    private interface IPivotStrategy {
        int getPivotIndex(MyArrayList a, int start, int end);
    }

    private static IPivotStrategy firstPivot = new IPivotStrategy() {
        public int getPivotIndex(MyArrayList a, int start, int end) {
            return start;
        }
    };

    private static IPivotStrategy lastPivot = new IPivotStrategy() {
        public int getPivotIndex(MyArrayList a, int start, int end) {
            return end;
        }
    };

    private static IPivotStrategy medianPivot = new IPivotStrategy() {
        public int getPivotIndex(MyArrayList a, int start, int end) {
            int first = (Integer)a.get(start);
            int second = (Integer)a.get((start+end)/2);
            int third = (Integer)a.get(end);
            int firstInd = start;
            int secondInd = (start+end)/2;
            int thirdInd = end;

            if(first <= second){
                if(third <= first){
                    return firstInd;
                }
                else {
                    if (third >= second){
                        return secondInd;
                    } else {
                        return thirdInd;
                    }
                }
            } else if(third >= first){
                return firstInd;
            }
            else {
                if (third <= second){
                    return secondInd;
                } else {
                    return thirdInd;
                }
            }
        }
    };

    public static void sort(MyArrayList list){
        quickSortClassic(list, medianPivot);
    }
    
    public static void copy(MyLinkedList dest, MyLinkedList src){
        for(Object o: src){
            dest.add(o);
        }
    }
    
    public static void reverse(MyLinkedList list){
        int size = list.size();
        if(size == 0 || size == 1){
            return;
        }
        ListIterator<Object> iterForward = list.listIterator(0);
        ListIterator<Object> iterBackward = list.listIterator(size - 1);
        for(int i = 0; i < size/2; i++){
            Object temp = iterForward.next();
            iterForward.set(iterBackward.previous());
            iterBackward.set(temp);
        }
    }
    
    public static int binarySearch(MyArrayList list, Object key){
        if(list.isEmpty())
            return -1;
        return binarySearch(list, 0, list.size()-1, key);
    }

    private static int binarySearch(MyArrayList list, int start, int end, Object key){

        int index = start + (end-start)/2;

        int compare = ((Integer)key).compareTo((Integer)list.get(index));

        if(compare < 0){
            if(start == index){
                return -1*index - 1;
            }
            index = binarySearch(list,start,index-1,key);
        }
        if(compare > 0){
            if(end == index){
                return -1*(index + 1) - 1;
            }
            index = binarySearch(list,index+1, end, key);
        }
        return index;
    }

    public static void quickSortClassic (MyArrayList a, IPivotStrategy pivotStrategy) {
        quickSortClassic(a, 0, a.size() - 1, pivotStrategy);
    }

    private static void quickSortClassic (MyArrayList a, int start, int end, IPivotStrategy pivotStrategy) {
        if(end-start < 1){
            return;
        }
        int pivotIndex = pivotStrategy.getPivotIndex(a, start, end);
        int pivotValue = (Integer)a.get(pivotIndex);
        swap(a,start,pivotIndex);
        int i = start + 1;
        int j = start + 1;
        while (j <= end){
            if ((Integer)a.get(j) < pivotValue) {
                swap(a,j,i);
                i++;
            }
            j++;
        }
        swap(a, start, i-1);
        quickSortClassic(a, start, i-2, pivotStrategy);
        quickSortClassic(a, i, end, pivotStrategy);
    }


}
