package com.dsa.data_structures.dynamic_lists;

public class DynamicArray<T> implements DynamicList<T> {
    private int capacity = 10;
    private int size = 0;
    private final Object[] data = new Object[capacity];

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index >= size)
            throw new IndexOutOfBoundsException();

        return (T)(data[index]);
    }

    @Override
    public void add(Object element) {

    }

    @Override
    public void addAll(Object[] values) {

    }

    @Override
    public void insert(int at, Object value) throws IndexOutOfBoundsException {

    }

    @Override
    public void insertAll(int at, Object[] values) throws IndexOutOfBoundsException {

    }

    @Override
    public boolean remove(Object value) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean contains(Object value) {
        return false;
    }

    @Override
    public int count(Object value) {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
