package ru.yandex.shad.belova.java.problem1;

public class MyArrayList implements MyList {

	private static final int DEFAULT_SIZE = 10;
	private Object[] array;
	private int size = 0;

	public MyArrayList(){
        array = new Object[DEFAULT_SIZE];
	}
	
	public MyArrayList(int initialCapacity){
        if(initialCapacity < 0)
            throw new IllegalArgumentException();
        array = new Object[initialCapacity];
	}
	
	@Override
	public void add(Object e) {
        add(size(), e);
	}

	@Override
	public void add(int index, Object element) {
        checkAddIndex(index);

        ensureCapacity(size + 1);

        System.arraycopy(array, index, array, index+1, size - index);
        array[index] = element;

        size++;
	}

    private void checkAddIndex(int index){
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();
    }

    private void checkSetGetRemoveIndex(int index){
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    }

	@Override
	public void addAll(Object[] c) {
		addAll(size,c);
	}

	@Override
	public void addAll(int index, Object[] c) {
        checkAddIndex(index);

        ensureCapacity(size + c.length);

        System.arraycopy(array, index, array, c.length + index, size - index);
        System.arraycopy(c, 0, array, index, c.length);

        size += c.length;
	}

	@Override
	public Object get(int index) {
        checkSetGetRemoveIndex(index);
		return array[index];
	}

	@Override
	public Object remove(int index) {
        checkSetGetRemoveIndex(index);

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

        checkSetGetRemoveIndex(index);
        array[index] = element;
	}

	@Override
	public int indexOf(Object o) {

		for(int i = 0; i < size; ++i) {
			if(o == null? array[i] == null : array[i].equals(o))
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
		for(int i = 0; i < size; ++i)
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

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        for(int i = 0; i < size; ++i)
            str.append("," + array[i]);
        return str.substring(1);
    }

}
