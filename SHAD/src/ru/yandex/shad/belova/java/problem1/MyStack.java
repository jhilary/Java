package ru.yandex.shad.belova.java.problem1;

public class MyStack {
    private MyLinkedList list;
    
    public MyStack(){
        list = new MyLinkedList();
    }
    public void push(Object e){
        list.addLast(e);
    }
    
    public Object pop(){
        return list.removeLast();
    }
    
    public Object peek(){
        return list.getLast();
    }

    public Object[] toArray(){
        return list.toArray();
    }
}