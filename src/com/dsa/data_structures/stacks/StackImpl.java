package com.dsa.data_structures.stacks;

@SuppressWarnings("unchecked")
public class StackImpl<T> implements Stack<T> {
    private final int capacity = 1000;
    private Object[] data = new Object[capacity];
    private int topIndex = -1;

    @Override
    public boolean isEmpty () {
        return topIndex < 0;
    }

    @Override
    public void push (T element) {
        try {
            data[++topIndex] = element;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Stack overflow");
        }
    }

    @Override
    public T pop () {
        if (isEmpty())
            throw new RuntimeException("Stack underflow");
        return (T)data[topIndex--];
    }

    @Override
    public boolean contains (T value) {
        for (Object ob : data) {
            if (ob.equals(value))
                return true;
        }

        return false;
    }

    @Override
    public void clear () {
        data = new Object[capacity];
    }
}
