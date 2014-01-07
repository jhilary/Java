package ru.yandex.shad.belova.java.problem4.part3;

import ru.yandex.shad.belova.java.problem4.part3.Buffer;

public class Producer implements Runnable{
    volatile static int i = 0;
    private Buffer buf;
    public Producer(Buffer buf){
        this.buf = buf;
    }
    @Override
    public void run() {

        while(true){
            try {
                Thread.sleep(1000);
                System.err.println("Try put: " + i);
                buf.put(i++);
            } catch (InterruptedException ex) { }
        }
    }
}