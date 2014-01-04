package ru.yandex.shad.belova.java.problem4;

import org.apache.commons.math3.stat.StatUtils;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: ilariyabelova
 * Date: 12/29/13
 * Time: 1:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConcurrentSin {

    public enum Methods{
        THREAD {
            @Override
            public double calculate(int N, int numOfThreads){
                final double [] result = new double[numOfThreads];
                class SinRunnable implements Runnable{

                    public int threadID;
                    public int startSegment;
                    public int endSegment;

                    public SinRunnable(int threadID, int start, int end){
                        this.threadID = threadID;
                        this.startSegment = start;
                        this.endSegment = end;
                    }

                    @Override
                    public void run() {
                        for(int i = this.startSegment; i <= this.endSegment; i++){
                            result[threadID] += Math.sin(i);
                        }
                    }
                }
                int numOfElements = 2 * N + 1;
                int numOfElementsInThread = numOfElements / numOfThreads;
                int tail = numOfElements % numOfThreads;
                Thread[] threads = new Thread[numOfThreads];
                for(int k = 0; k < numOfThreads; k++){
                    int start = -1 * N + k * numOfElementsInThread;
                    int end   = -1 * N + (k+1) * numOfElementsInThread;
                    if(k == numOfThreads - 1){
                        end += tail - 1;
                    }
                    Thread t = new Thread(new SinRunnable(k, start, end));
                    t.start();
                    threads[k] = t;
                }
                for(Thread t: threads){
                    try {
                        t.join();
                    } catch (InterruptedException ie){
                        System.err.println("Thread was interrupted");
                    }
                }
                return StatUtils.sum(result);
            }
        }, EXECUTORSERVICE{
            @Override
            public double calculate(int N, int numOfThreads){

                class SinExecutorCallable implements Callable<Double> {

                    public int threadID;
                    public int startSegment;
                    public int endSegment;

                    public SinExecutorCallable(int threadID, int start, int end){
                        this.threadID = threadID;
                        this.startSegment = start;
                        this.endSegment = end;
                    }

                    @Override
                    public Double call() {
                        double totalSin = 0;
                        for(int i = this.startSegment; i <= this.endSegment; i++){
                            totalSin += Math.sin(i);
                        }
                        return totalSin;
                    }
                }

                int numOfElements = 2 * N + 1;
                int numOfElementsInThread = numOfElements / numOfThreads;
                int tail = numOfElements % numOfThreads;
                List<Callable<Double>> allCallables = new ArrayList<Callable<Double>>(numOfThreads);
                for(int k = 0; k < numOfThreads; k++){
                    int start = -1 * N + k * numOfElementsInThread;
                    int end   = -1 * N + (k+1) * numOfElementsInThread;
                    if(k == numOfThreads - 1){
                        end += tail - 1;
                    }
                    allCallables.add(new SinExecutorCallable(k, start, end));
                }
                double total = 0;
                ExecutorService pool = Executors.newFixedThreadPool(numOfThreads);
                try {
                    List<Future<Double>> results = pool.invokeAll(allCallables);
                    for(Future<Double> partSum: results){
                        total += partSum.get();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                return total;
            }

        };

        public abstract double calculate(int N, int numOfThreads);
    }


    public static Map timeMeasure(int N, int maxThreads, Methods method){
        Map threadTimeMap = new HashMap();
        for(int i = 1; i <= maxThreads; i++){
            long begin = System.currentTimeMillis();
            method.calculate(N, i);
            float timeResult = (float)(System.currentTimeMillis() - begin) / 1000; //seconds
            threadTimeMap.put(i, timeResult);
        }
        return threadTimeMap;
    }


    public static void main(String[] args){

        System.out.println(Methods.THREAD.calculate(100000, 2));
        System.out.println(Methods.EXECUTORSERVICE.calculate(100000, 2));
        System.out.println(timeMeasure(10000000,12,Methods.THREAD));
        System.out.println(timeMeasure(10000000,12,Methods.EXECUTORSERVICE));
    }
}
