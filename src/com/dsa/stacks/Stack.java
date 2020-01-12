package com.dsa.stacks;

@SuppressWarnings("unchecked")
public class Stack<T> {
    private final int capacity;
    private Object[] data;
    private int topIndex = -1;
    private int size = 0;

    public Stack(int initialCapacity) {
        capacity = initialCapacity;
        data = new Object[capacity];
    }

    public boolean isEmpty () {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void push(T element) {
        if (isFull())
            throw new RuntimeException("Stack overflow");
        data[++topIndex] = element;
        ++size;
    }

    public T pop () {
        if (isEmpty())
            throw new RuntimeException("Stack underflow");
        --size;
        return (T)data[topIndex--];
    }

    public boolean contains (T value) {
        if (!isEmpty()) {
            for (Object ob : data) {
                if (ob.equals(value))
                    return true;
            }
        }

        return false;
    }

    public void clear () {
        topIndex = -1;
        size = 0;
    }

    public int size () {
        return size;
    }
}
