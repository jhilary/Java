package ru.yandex.shad.belova.java.problem1;

public interface MyList {
		
	void add(Object e); // – добавляет элемент в конец коллекции

	void add(int index, Object element); //– добавляет элемент в указанное место коллекции

	void addAll(Object[] c); // - добавляет массив элементов в конец коллекции

	void addAll(int index, Object[] c); // - добавляет массив элементов в указанное место коллекции

	Object get(int index); // – возвращает элемент по индексу

	Object remove(int index); // - удаляет элемент по индексу

	void set(int index, Object element); // – изменяет значение элемента

	int indexOf(Object o); // - поиск индекса по значению элемента (выводит индекс первого найденного, или -1 в случае его отсутствия)

	int size(); // - размер коллекции

	void clear(); // - удаляет содержимое коллекции
	
	boolean isEmpty(); // - возвращает true если в коллекции нет элементов
	
	Object[] toArray(); // - преобразует коллекцию в массив объектов

	public String toString(); // - возвращает строку, в которой через запятую выводятся значения элементов в коллекции

}
