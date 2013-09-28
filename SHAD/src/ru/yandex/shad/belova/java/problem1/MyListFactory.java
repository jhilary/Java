package ru.yandex.shad.belova.java.problem1;

public class MyListFactory {
	
	enum TYPE {
		LINKED_LIST,
		ARRAY
	}
	

	static IMyList createList(TYPE t) {

		switch(t){
		
		case LINKED_LIST: return new MyLinkedList();
		case ARRAY : return new MyArrayList();

		}
		
		return null;
	}
	
}
