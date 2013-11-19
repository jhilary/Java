package ru.yandex.shad.belova.java.problem1;

public enum MyListFactory {
    LINKED_LIST{
        MyList create(){
            return new MyLinkedList();
        }
    },
    ARRAY{
        MyList create(){
            return new MyArrayList();
        }
    };
    abstract MyList create();
}

