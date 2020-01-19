package datastructure.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The implementation of the fixed size array
 */
public class FixedSizeArray<T> implements Array<T> {

    /**
     * Defines how many elements the array contains right now
     */
    private int count;

    /**
     * The actual array that stores the elements
     */
    private Object[] array;

    public FixedSizeArray(int capacity) {
        this.array = new Object[capacity];
    }

    @Override
    public void append(T element) {
        if (count == array.length) {
            throw new IllegalStateException("The array is already full");
        }
        array[count++] = element;
    }

    @Override
    public void insertTo(int index, T element) {
        if (count == array.length) {
            throw new IllegalStateException("The array is already full");
        }

        for (int i = array.length - 1; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = element;
        count++;
    }

    @Override
    public T getBy(int index) {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("Invalid index");
        }
        return (T) array[index];
    }

    @Override
    public T removeBy(int index) {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("Invalid index");
        }
        T element = (T) array[index];

        for (int i = index; i < count - 1;i++) {
            array[index] = array[index + 1];
        }

        array[count--] = null;

        return element;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns the number of elements which the array contains right now i.e.
     * not the length of array itself.
     */
    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return new LasyIteratorImpl();
    }

    // todo next improvement
    // implement remove() and check for modification during iteration
    private class LasyIteratorImpl<T> implements Iterator<T> {

        int cursor;

        @Override
        public boolean hasNext() {
            return cursor < count;
        }

        @Override
        public T next() {
            if (cursor == count) {
                throw new NoSuchElementException();
            }

            return (T) array[cursor++];
        }
    }
}
