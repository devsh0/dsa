package com.dsa.lists;

public class Vector<T> {
    final double SHRINK_FACTOR = 1.5;
    final double EXPANSION_FACTOR = 2.0;

    Object[] data;
    int size;
    int internalSize;

    public Vector() {
        size = internalSize = 0;
        data = new Object[size];
    }

    public Vector(T... elements) {
        size = elements.length;
        internalSize = (int)(size * EXPANSION_FACTOR);
        data = new Object[internalSize];
        System.arraycopy(elements, 0, data, 0, size);
    }

    private Object[] getAppropriateArray(int deviation) {
        int newSize = size + deviation;
        Object[] appropriate = data;
        if (newSize >= internalSize) {
            internalSize = (int) (newSize * EXPANSION_FACTOR);
            appropriate = new Object[internalSize];
        } else if (newSize < (internalSize / 2)) {
            internalSize = (int) (newSize * SHRINK_FACTOR);
            appropriate = new Object[internalSize];
        }
        return appropriate;
    }

    private void copyArray(Object[] source, Object[] dest) {
        if (source.length > dest.length)
            throw new IllegalArgumentException("source array is bigger than destination array!");
        System.arraycopy(source, 0, dest, 0, source.length);
    }

    private void checkIndex(int index, boolean isSizeValidIndex) throws IndexOutOfBoundsException {
        if (index < 0 || (isSizeValidIndex ? index > size : index >= size))
            throw new IndexOutOfBoundsException("index " + index + " is out of bounds!");
    }

    private boolean shouldReplaceInternalArray(Object[] appropriateArray) {
        return appropriateArray != data;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) throws IndexOutOfBoundsException {
        checkIndex(index, false);
        return (T) data[index];
    }

    public void add(T element) {
        Object[] appropriateArray = getAppropriateArray(1);
        if (shouldReplaceInternalArray(appropriateArray)) {
            copyArray(data, appropriateArray);
            data = appropriateArray;
        }
        data[size++] = element;
    }

    public void addAll(T... elements) {
        Object[] appropriateArray = getAppropriateArray(elements.length);
        if (shouldReplaceInternalArray(appropriateArray)) {
            copyArray(data, appropriateArray);
            data = appropriateArray;
        }
        for (T element : elements)
            data[size++] = element;
    }

    public void insert(int at, T... elements) throws IndexOutOfBoundsException {
        checkIndex(at, true);
        Object[] appropriateArray = getAppropriateArray(elements.length);
        System.arraycopy(data, at, appropriateArray, elements.length + at, (size - at));
        System.arraycopy(elements, 0, appropriateArray, at, elements.length);

        if (shouldReplaceInternalArray(appropriateArray)) {
            System.arraycopy(data, 0, appropriateArray, 0, at);
            data = appropriateArray;
        }

        size += elements.length;
    }

    public void remove(T value) {
        int removed = 0;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value))
                removed++;
            else {
                int newIndex = i - removed;
                data[newIndex] = data[i];
            }
        }

        Object[] appropriateArray = getAppropriateArray(-removed);
        if (shouldReplaceInternalArray(appropriateArray)) {
            System.arraycopy(data, 0, appropriateArray, 0, size - removed);
            data = appropriateArray;
        }
        size -= removed;
    }

    @SuppressWarnings("unchecked")
    public T removeAt(int index) {
        checkIndex(index, false);
        T removed = (T) data[index];
        Object[] appropriateArray = getAppropriateArray(-1);
        System.arraycopy(data, 0, appropriateArray, 0, index);
        System.arraycopy(data, index + 1, appropriateArray, index, size - (index + 1));
        if (shouldReplaceInternalArray(appropriateArray))
            data = appropriateArray;
        size -= 1;
        return removed;
    }

    public int size() {
        return size;
    }

    public boolean contains(T value) {
        for (Object o : data)
            if (o.equals(value))
                return true;

        return false;
    }

    public void clear() {
        size = internalSize = 0;
        data = new Object[size];
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
