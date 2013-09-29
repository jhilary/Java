package ru.yandex.shad.belova.java.problem1;

public class MyQueue {
    private MyLinkedList list;
    
    public MyQueue(){
        list = new MyLinkedList();
    }
    public void offer(Object e){
        list.addLast(e);
    }
    
    public Object peek(){
        return list.getFirst();
    }
    
    public Object poll(){
        return list.removeFirst();
    }
    
    public Object[] toArray(){
        return list.toArray();
    }
}
