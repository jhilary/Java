package ru.yandex.shad.belova.java.problem4.part3;

public interface Buffer<T>{
    public void put(T i) throws InterruptedException;
    public T get() throws InterruptedException;
}
