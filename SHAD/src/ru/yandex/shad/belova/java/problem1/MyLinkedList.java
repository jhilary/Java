package ru.yandex.shad.belova.java.problem1;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList implements MyList, Iterable<Object>{


    private class MyLinkedListIterator implements ListIterator<Object> {

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
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

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
            throw new IndexOutOfBoundsException();
        }
        return new MyLinkedListIterator(index);
    }

    private static class Node {
		Object value;
		private Node prev;
		private Node next;
		
		public Node(Object value, Node prev) {

			this.value = value;
			if(prev != null) {
				Node next = prev.next;
				prev.next = this;
				this.prev = prev;
                this.next = next;
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
	private int size;

    private void checkAddIndex(int index){
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();
    }

    private void checkSetGetRemoveIndex(int index){
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    }

	private Node getNodeAtIndex(int index) {
        checkSetGetRemoveIndex(index);

		Node currentNode = head;
        if(index < size/2){
            for(int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
        } else {
            currentNode = tail;
            for(int i = size - 1; i > index; i--) {
                currentNode = currentNode.prev;
            }
        }

		return currentNode;

	}
	
	private Node add(Object element, Node prev) {

		Node n = new Node(element, prev);
		
		return n;
	}

	public void add(Object e) { // – добавляет элемент в конец коллекции

		tail = add(e, tail);
        size++;
		if(head == null)
			head = tail;
	}

	public void add(int index, Object element) { //– добавляет элемент в указанное место коллекции

        checkAddIndex(index);
        if(index == 0){
            Node tmp = head;
            head = new Node(element, null);
            head.next = tmp;
            if(tail == null)  {
                tail = head;
            }
        } else {
            Node elementNode = add(element, getNodeAtIndex(index-1));
            if(index == size){
                tail = elementNode;
            }
        }
        size++;
	}

	public void addAll(Object[] c) { // - добавляет массив элементов в конец коллекции
        for(Object value: c){
            add(value);
        }
	}

	public void addAll(int index, Object[] c) { // - добавляет массив элементов в указанное место коллекции

        checkAddIndex(index);
        if(c.length == 0){
            return;
        }
        if(index == size){
            addAll(c);
        } else {
            if(index == 0){
                for(int i = c.length-1; i >= 0; i--){
                    add(0, c[i]);
                }
            } else {
                Node tmp = tail;
                tail = add(c[0], getNodeAtIndex(index-1));
                size++;
                for(int i = 1; i < c.length; i++){
                    add(c[i]);
                }
                tail = tmp;
            }
        }

	}

	public Object get(int index) { // – возвращает элемент по индексу
        checkSetGetRemoveIndex(index);
		return getNodeAtIndex(index).value;
	}

	public Object remove(int index) { // - удаляет элемент по индексу

        checkSetGetRemoveIndex(index);
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

        size--;

		return toRemove.value;
	}

	public void set(int index, Object element) { // – изменяет значение элемента
        checkSetGetRemoveIndex(index);
		getNodeAtIndex(index).value = element;
	}

	public int indexOf(Object o) { // - поиск индекса по значению элемента (выводит индекс первого найденного, или -1 в случае его отсутствия)
		int size = size();
		for(int i = 0; i < size; ++i) {
			Node node = getNodeAtIndex(i);
			if(o == null? node.value == null : node.value.equals(o))
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
        size = 0;
	}
	
	public boolean isEmpty() { // - возвращает true если в коллекции нет элементов
		return (head == null);
	}
	
	public Object[] toArray() { // - преобразует коллекцию в массив объектов
		
		Object[] result = new Object[size];
        int i = 0;
        for(Object o: this){
            result[i++] = o;
        }
		
		return result;
	}

    @Override
	public String toString() { // - возвращает строку, в которой через запятую выводятся значения элементов в коллекции
		
		StringBuilder str = new StringBuilder();
        for(Object value: this){
            str.append("," + value);
        }
		return str.substring(1);
	}

	//MyLinkedList interface

	public Object getFirst() {
		
		if(isEmpty())
			throw new NoSuchElementException();

		return this.head.value;
	}
	
	public Object getLast() {
		if(isEmpty())
			throw new NoSuchElementException();

		return this.tail.value;
	}
	
	public Object removeFirst() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
		return remove(0);
	}
	
	public Object removeLast() {

		if(isEmpty())
			throw new NoSuchElementException();

		return remove(size() - 1);
	}
	
	public void addFirst(Object e){
        add(0,e);
	}
	
	 public void addLast(Object e){
		 add(e);
	 }

}