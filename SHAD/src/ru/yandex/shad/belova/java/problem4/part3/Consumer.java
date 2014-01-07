package ru.yandex.shad.belova.java.problem4.part3;

import ru.yandex.shad.belova.java.problem4.part3.Buffer;

public class Consumer<T> implements Runnable{
    private Buffer<T> buf;
    public Consumer(Buffer<T> buf){
        this.buf = buf;
    }
    @Override
    public void run() {
        while(true){
            T i;
            try {
                i = buf.get();
                System.err.println("Got: " + i);
            } catch (InterruptedException ex) { }
        }
    }
}