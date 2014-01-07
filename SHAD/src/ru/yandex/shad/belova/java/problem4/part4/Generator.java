package ru.yandex.shad.belova.java.problem4.part4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 * User: ilariyabelova
 * Date: 1/7/14
 * Time: 5:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class Generator {
    AtomicReference<BigInteger> value = new AtomicReference<BigInteger>(BigInteger.ONE);
    public BigInteger next(){
         while(true){
             BigInteger current = value.get();
             BigInteger next = current.multiply(new BigInteger("2"));
             if(value.compareAndSet(current, next)){
                 return current;
             }
         }
    }

    public static class GenerationCallable implements Callable<Integer>{

        private Generator generator;
        private Vector<BigInteger> result;
        private int numOfElementsToGenerate;

        public GenerationCallable(Generator generator, int numOfElementsToGenerate, Vector<BigInteger> result){
            this.generator = generator;
            this.result = result;
            this.numOfElementsToGenerate = numOfElementsToGenerate;
        }

        @Override
        public Integer call() {
            for(int i = 0; i < numOfElementsToGenerate; i++){
                result.add(generator.next());
            }
            return 0;
        }
    }

    public static void main(String[] args){
        ExecutorService pool = Executors.newFixedThreadPool(10);
        Generator generator = new Generator();
        Vector<BigInteger> result = new Vector<BigInteger>();
        ArrayList<Callable<Integer>> callables = new ArrayList<Callable<Integer>>(10);
        for(int i = 0; i < 10; i++){
            callables.add(new GenerationCallable(generator, 100, result));
        }
        try {
            pool.invokeAll(callables);
        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted");
        }
        System.out.println(result);

    }
}
