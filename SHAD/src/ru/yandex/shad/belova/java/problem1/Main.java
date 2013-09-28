package ru.yandex.shad.belova.java.problem1;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		IMyList linkedList = MyListFactory.createList(MyListFactory.TYPE.LINKED_LIST);
		linkedList.add(1);
		linkedList.add(2);
		linkedList.set(0, 2);
		linkedList.remove(1);
		
		String s = linkedList.toString();
		System.out.println(s);
	}

}