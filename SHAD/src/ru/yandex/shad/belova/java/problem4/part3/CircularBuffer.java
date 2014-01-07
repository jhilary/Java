package ru.yandex.shad.belova.java.problem4.part3;

import ru.yandex.shad.belova.java.problem4.part3.Buffer;

import java.util.*;

class CircularBuffer<T> implements Buffer<T> {

    private Integer n;
    private List<T> buffer;
    private int toInsert = 0;
    private int toFetch = 0;

    public CircularBuffer(int bufferSize){
        buffer = new ArrayList<T>(bufferSize);
        for(int i = 0; i < bufferSize; i++){
            buffer.add(null);
        }
    }

    @Override
    public synchronized void put(T i) throws InterruptedException {

        while(buffer.get(this.toInsert) != null){
            wait();
        }
        buffer.set(toInsert++, i);

        if(toInsert == buffer.size()) {
            toInsert = 0;
        }
        System.err.println("After put: " + buffer);
        notifyAll();
    }

    @Override
    public synchronized T get() throws InterruptedException {

        while(buffer.get(this.toFetch) == null){
            wait();
        }
        T result = buffer.get(toFetch);
        buffer.set(toFetch++, null);
        if(toFetch == buffer.size()) {
            toFetch = 0;
        }
        System.err.println("After get: " + buffer);
        notifyAll();
        return result;
    }

}