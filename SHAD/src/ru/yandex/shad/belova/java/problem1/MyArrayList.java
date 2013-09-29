package ru.yandex.shad.belova.java.problem1;

public class MyArrayList implements IMyList{

	private static final int DEFAULT_SIZE = 10;
	private Object[] array = new Object[0];
	private int last = -1;
	//private int size = 0;
	
	public MyArrayList(){

        array = new Object[DEFAULT_SIZE];

	}
	
	public MyArrayList(int initialCapacity){

        ensureCapacity(initialCapacity);

	}
	
	@Override
	public void add(Object e) {

		//if(last == size)
        if(last >= array.length)
            ensureCapacity(array.length * 2);

		add(++last, e);
		
	}

	@Override
	public void add(int index, Object element) {

		if(index > last)
			throw new ArrayIndexOutOfBoundsException();
		
		array[index] = element;
	}

	@Override
	public void addAll(Object[] c) {

        int count = last + 1;
		if(c.length + (count) > array.length)
            ensureCapacity(c.length + count);
		
		
		System.arraycopy(c, 0, array, count, c.length);
		
		last = count + c.length;

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
	public void set(int index, Object element)   {

		if(index > last)
			throw new ArrayIndexOutOfBoundsException();

		array[index] = element;
	}

	@Override
	public int indexOf(Object o) {

		for(int i = 0; i <= last; ++i) {
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

        //int size = (last != -1 ? last : 0);
		Object[] result = new Object[last + 1];
		System.arraycopy(array, 0, result, 0, last + 1);

		return result;
	}

    public void ensureCapacity(int minCapacity) {

        if(minCapacity < 0)
            throw new IllegalArgumentException();

        if(array.length >= minCapacity)
            return;

        Object[] tmpArray = new Object[minCapacity];
        System.arraycopy(array, 0, tmpArray, 0, array.length);
        array = tmpArray;

    }

    public int getCapacity() {

	    return array.length;

	}
}
