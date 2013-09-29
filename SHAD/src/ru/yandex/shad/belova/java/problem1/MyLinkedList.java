package ru.yandex.shad.belova.java.problem1;

public class MyLinkedList implements IMyList {

	class Node {
		Object value;
		Node prev;
		Node next;
		
		public Node(Object value, Node prev) {

			this.value = value;
			if(prev != null) {
				Node next = prev.next;
				prev.next = this;
				this.prev = prev;
				if(next != null)
					next.prev = this;
			}
		}
		
		Node getPrev() {
			return prev;
		}
		
		Node getNext() {
			return next;
		}

		void setPrev(Node prev) {
			// TODO
		}
		
		void setNext(Node next) {
			// TODO
		}
	
	}
	
	private Node head;
	private Node tail;
	int size;
	
	private Node getNodeAtIndex(int index) {

		if(index < 0)
			throw new ArrayIndexOutOfBoundsException();

		Node currentNode = head;
		int i = 0;
		for(; i < index && currentNode != null; ++i) {
			currentNode = currentNode.next;
		}

		if(i < index || currentNode == null)
			throw new ArrayIndexOutOfBoundsException();
		
		return currentNode;

	}
	
	private Node add(Object element, Node prev) {

		Node n = new Node(element, prev);
		++size;
		
		return n;
	}
	
	public void add(Object e) { // – добавляет элемент в конец коллекции

		tail = add(e, tail);

		if(head == null)
			head = tail;
		
	}

	public void add(int index, Object element) { //– добавляет элемент в указанное место коллекции

		if(head == null && index != 0)
			throw new ArrayIndexOutOfBoundsException();

		add(element, getNodeAtIndex(index));

	}

	public void addAll(Object[] c) { // - добавляет массив элементов в конец коллекции
		
		if(c == null || c.length == 0)
			return;//throw new ArrayIndexOutOfBoundsException();

		for(Object obj : c)
			add(obj);

	}

	public void addAll(int index, Object[] c) { // - добавляет массив элементов в указанное место коллекции

        if(c.length < 1)
            return; // TODO - check the right way to handle it

        Node currentNode = getNodeAtIndex(index);
        Node firstAdded = add(c[0], currentNode.prev);
        Node prev = firstAdded;
        for(int i = 1; i < c.length; ++i)
            prev = add(c[i], prev);

        if(currentNode == head && firstAdded != null) {
            prev.next = head;
            head = firstAdded;
        }

	}

	public Object get(int index) { // – возвращает элемент по индексу
		return getNodeAtIndex(index).value;
	}

	public Object remove(int index) { // - удаляет элемент по индексу
		
		Node toRemove = getNodeAtIndex(index);

        Node prev = toRemove.prev;
        Node next = toRemove.next;
        if(prev != null)
            prev.next = next;
        if(next != null)
            next.prev = prev;
        if(prev == null)
            head = next;
        if(next == null)
            tail = prev;

        --size;

		return toRemove.value;
	}

	public void set(int index, Object element) { // – изменяет значение элемента
		getNodeAtIndex(index).value = element;
	}

	public int indexOf(Object o) { // - поиск индекса по значению элемента (выводит индекс первого найденного, или -1 в случае его отсутствия)
		int size = size();
		for(int i = 0; i < size; ++i) {
			Node node = getNodeAtIndex(i);
			if(node.value.equals(o))
				return i;
		}
		
		return -1;
	}

	public int size() { // - размер коллекции
		return this.size;
	}

	public void clear() {// - удаляет содержимое коллекции
		head = null;
		tail = null;
		
	}
	
	public boolean isEmpty() { // - возвращает true если в коллекции нет элементов
		return (head == null);
		
	}
	
	public Object[] toArray() { // - преобразует коллекцию в массив объектов
		
		Object[] result = new Object[size()];

		int i = 0;
		Node n = head;
		while(n != null) {
			result[i++] = n.value;
			n = n.next;
		}
		
		return result;
		
	}
	
	public String toString() { // - возвращает строку, в которой через запятую выводятся значения элементов в коллекции
		
		StringBuilder str = new StringBuilder();

		Node n = head;
		if(n != null) {
			str.append(n.value);
			n = n.next;
		}
		while(n != null) {
			str.append(", " + n.value);
			n = n.next;
		}

		return str.toString();
	}

	//MyLinkedList interface

	public Object getFirst() {
		
		if(this.head == null)
			throw new ArrayIndexOutOfBoundsException(); //TODO - replace with the proper excepiton

		return this.head.value;
	}
	
	public Object getLast() {

		if(this.tail == null)
			throw new ArrayIndexOutOfBoundsException(); //TODO - replace with the proper excepiton

		return this.tail.value;
	}
	
	public Object removeFirst() {

		return remove(0);
	}
	
	public Object removeLast() {

		if(isEmpty())
			throw new ArrayIndexOutOfBoundsException(); //TODO - replace with the proper excepiton

		return remove(size() - 1);
	}
	
	public void addFirst(Object e){

        Node tmp = new Node(e, null);

        if(head != null)
            tmp.next = head;
        else
            tail = head;

        head = tmp;

        ++size;

	}
	
	 public void addLast(Object e){

		 add(e, tail);
	 }
	
	
}
