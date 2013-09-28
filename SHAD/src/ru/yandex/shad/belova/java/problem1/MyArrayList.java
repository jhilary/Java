package ru.yandex.shad.belova.java.problem1;

public class MyArrayList implements IMyList{

	private static final int DEFAULT_SIZE = 10;
	private Object[] array = new Object[DEFAULT_SIZE];
	private int last = -1;
	private int size = 0;
	
	public MyArrayList(){
	    //TODO
	}
	
	public MyArrayList(int initialCapacity){
	    //TODO
	}
	
	@Override
	public void add(Object e) {

		if(last == size)
			extendArray(size * 2);
			
		add(++last, e);
		
	}

	private void extendArray(int newSize) {

		Object[] tmpArray = new Object[newSize];
		System.arraycopy(array, 0, tmpArray, 0, size);
		array = tmpArray;
		size = newSize;
	}

	@Override
	public void add(int index, Object element) {

		if(index >= size || index > last)
			throw new ArrayIndexOutOfBoundsException();
		
		array[index] = element;
	}

	@Override
	public void addAll(Object[] c) {
		
		if(c.length > this.size)
			extendArray(c.length);
		
		
		System.arraycopy(c, c.length, this.array, this.last, c.length);
		
		this.last = this.size - 1;
	}

	@Override
	public void addAll(int index, Object[] c) {

		throw new RuntimeException("MyArrayList.addAll() is not implemented");

	}

	@Override
	public Object get(int index) {

		if(index > last)
			throw new ArrayIndexOutOfBoundsException();

		return array[index];
	}

	@Override
	public Object remove(int index) {

		if(index > last)
			throw new ArrayIndexOutOfBoundsException();

		Object obj = array[index];
		
		System.arraycopy(this.array, index + 1, this.array, index, this.array.length - index);
		--last;

		return obj;

	}

	@Override
	public void set(int index, Object element) {

		if(index >= size)
			throw new ArrayIndexOutOfBoundsException();

		array[index] = element;
	}

	@Override
	public int indexOf(Object o) {

		int size = size();
		for(int i = 0; i < size; ++i) {
			if(array[i].equals(o));
				return i;
		}
		
		return -1;
	}

	@Override
	public int size() {

		return last;
	}

	@Override
	public void clear() {

		for(int i = 0; i < array.length; ++i)
			array[i] = null;
			
		this.last = 0;
	}

	@Override
	public boolean isEmpty() {

		return (last == -1);
	}

	@Override
	public Object[] toArray() {

		Object[] result = new Object[this.array.length];
		System.arraycopy(this.array, 0, result, 0, result.length);

		return result;
	}

	public int getCapacity() {
	    //TODO
	    return -1;
	}
}
