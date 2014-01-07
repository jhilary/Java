package ru.yandex.shad.belova.java.problem4.part3;

/**
 * Created with IntelliJ IDEA.
 * User: ilariyabelova
 * Date: 1/7/14
 * Time: 2:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConsumerProducerRun {
    public static void main(String[] args) {
        Buffer<Integer> buffer = new CircularBuffer<Integer>(20);
        new Thread(new Producer(buffer)).start();
        new Thread(new Consumer(buffer)).start();

        Buffer<Integer> concBuffer = new ConcurrenceCircularBuffer<Integer>(20);
        new Thread(new Producer(concBuffer)).start();
        new Thread(new Consumer(concBuffer)).start();


    }
}
