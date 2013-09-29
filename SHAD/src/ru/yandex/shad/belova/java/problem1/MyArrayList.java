package ru.yandex.shad.belova.java.problem1;

public class MyArrayList implements MyList {

	private static final int DEFAULT_SIZE = 10;
	private Object[] array = new Object[0];
	private int size = 0;

	public MyArrayList(){
        array = new Object[DEFAULT_SIZE];
	}
	
	public MyArrayList(int initialCapacity){
        ensureCapacity(initialCapacity);
	}
	
	@Override
	public void add(Object e) {

        if(size == array.length)
            ensureCapacity(array.length * 2);

        array[size++] = e;
	}

	@Override
	public void add(int index, Object element) {

		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		
        Object[] tmp = new Object[array.length + 1];
        tmp[index] = element;

        System.arraycopy(array, 0, tmp, 0, index);
        System.arraycopy(array, index, tmp, index + 1, array.length - index);

        array = tmp;
        size++;

	}

	@Override
	public void addAll(Object[] c) {

		if(size + c.length > array.length)
            ensureCapacity(size + c.length);

		System.arraycopy(c, 0, array, size, c.length);
		
		size += c.length;

	}

	@Override
	public void addAll(int index, Object[] c) {

        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        Object[] tmp = new Object[array.length + c.length];

        System.arraycopy(array, 0, tmp, 0, index);
        System.arraycopy(c, 0, tmp, index, c.length);
        System.arraycopy(array, index, tmp, c.length + index, array.length - index);

        size += c.length;

        array = tmp;

	}

	@Override
	public Object get(int index) {

        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

		return array[index];
	}

	@Override
	public Object remove(int index) {

        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();


        Object obj = array[index];
		
		Object[] tmp = new Object[array.length - 1];
        System.arraycopy(array, 0, tmp, 0, index);
        System.arraycopy(array, index + 1, tmp, index, array.length - (index + 1));

        array = tmp;
		size--;

		return obj;

	}

	@Override
	public void set(int index, Object element)   {

        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();


        array[index] = element;
	}

	@Override
	public int indexOf(Object o) {

		for(int i = 0; i < size; ++i) {
			if(array[i].equals(o))
				return i;
		}
		
		return -1;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public void clear() {

		for(int i = 0; i < array.length; ++i)
			array[i] = null;
		this.size = 0;
	}

	@Override
	public boolean isEmpty() {

		return (size == 0);
	}

	@Override
	public Object[] toArray() {

		Object[] result = new Object[size];
        if(result.length > 0)
		    System.arraycopy(array, 0, result, 0, size);

		return result;
	}

    public final void ensureCapacity(int minCapacity) {

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

    public String toString() {

        StringBuilder str = new StringBuilder();
        for(int i = 0; i < size; ++i)
            str.append("," + array[i]);
        return str.substring(1);
    }

}
