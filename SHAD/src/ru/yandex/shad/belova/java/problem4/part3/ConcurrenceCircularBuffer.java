package ru.yandex.shad.belova.java.problem4.part3;

import ru.yandex.shad.belova.java.problem4.part3.Buffer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ConcurrenceCircularBuffer<T> implements Buffer<T> {

    final Lock lock = new ReentrantLock();
    final Condition isFull = lock.newCondition();
    final Condition isEmpty = lock.newCondition();
    private Integer n;
    private List<T> buffer;
    private int toInsert = 0;
    private int toFetch = 0;

    public ConcurrenceCircularBuffer(int bufferSize){
        buffer = new ArrayList<T>(bufferSize);
        for(int i = 0; i < bufferSize; i++){
            buffer.add(null);
        }
    }

    @Override
    public void put(T i) throws InterruptedException {
        lock.lock();
        try{
            while(buffer.get(this.toInsert) != null){
                isFull.await();
            }
            buffer.set(toInsert++, i);

            if(toInsert == buffer.size()) {
                toInsert = 0;
            }
            System.err.println("After put: " + buffer);
            isEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public synchronized T get() throws InterruptedException {
        lock.lock();
        try{
            while(buffer.get(this.toFetch) == null){
                isEmpty.await();
            }
            T result = buffer.get(toFetch);
            buffer.set(toFetch++, null);
            if(toFetch == buffer.size()) {
                toFetch = 0;
            }
            System.err.println("After get: " + buffer);
            notifyAll();
            isFull.signal();
            return result;
        } finally {
            lock.unlock();
        }

    }
}

