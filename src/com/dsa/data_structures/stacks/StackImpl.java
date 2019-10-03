package com.dsa.data_structures.stacks;

@SuppressWarnings("unchecked")
public class StackImpl<T> implements Stack<T> {
    private final int capacity;
    private Object[] data;
    private int topIndex = -1;
    private int size = 0;

    public StackImpl (int initialCapacity) {
        capacity = initialCapacity;
        data = new Object[capacity];
    }

    @Override
    public boolean isEmpty () {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public void push(T element) {
        if (isFull())
            throw new RuntimeException("Stack overflow");
        data[++topIndex] = element;
        ++size;
    }

    @Override
    public T pop () {
        if (isEmpty())
            throw new RuntimeException("Stack underflow");
        --size;
        return (T)data[topIndex--];
    }

    @Override
    public boolean contains (T value) {
        if (!isEmpty()) {
            for (Object ob : data) {
                if (ob.equals(value))
                    return true;
            }
        }

        return false;
    }

    @Override
    public void clear () {
        topIndex = -1;
        size = 0;
    }

    @Override
    public int size () {
        return size;
    }
}
