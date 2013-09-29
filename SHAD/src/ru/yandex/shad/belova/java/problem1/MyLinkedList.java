package ru.yandex.shad.belova.java.problem1;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList implements IMyList, Iterable<Object>{


    public class MyLinkedListIterator implements ListIterator<Object> {

        private Node returned;
        private Node current;

        public MyLinkedListIterator(){
            current = head;
        }

        public MyLinkedListIterator(int index){
            current = getNodeAtIndex(index);
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            returned = current;
            current = current.getNext();
            return returned.value;
        }

        @Override
        public boolean hasPrevious() {
            return current != null;
        }

        @Override
        public Object previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            returned = current;
            current = current.getPrev();
            return returned.value;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(Object o) {
            returned.value = o;
        }

        @Override
        public void add(Object o) {
            throw new UnsupportedOperationException();
        }
    }
    @Override
    public ListIterator<Object> iterator() {
        return new MyLinkedListIterator();
    }

    public ListIterator<Object> listIterator(int index) {
        if(index < 0 || index > size()-1){
            throw new ArrayIndexOutOfBoundsException();
        }
        return new MyLinkedListIterator(index);
    }

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
	}
	
	private Node head;
	private Node tail;
	int size;
	
	private Node getNodeAtIndex(int index) {

		if(index < 0 || index > size()-1)
			throw new ArrayIndexOutOfBoundsException();

		Node currentNode = head;
		for(int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}
		
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
