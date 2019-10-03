package com.dsa.data_structures.queues;

@SuppressWarnings("unchecked")
public class QueueImpl<T> implements Queue<T> {
    private final int capacity;
    private Object[] data;
    private int size = 0;
    private int head = 0;
    private int tail = 0;

    public QueueImpl(int initialCapacity) {
        capacity = initialCapacity;
        data = new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public void enqueue(T value) {
        if (isFull())
            throw new RuntimeException("Queue overflow");
        data[tail] = value;
        ++size;
        tail = tail == capacity - 1 ? 0 : tail + 1;
    }

    @Override
    public T dequeue() {
        if (isEmpty())
            throw new RuntimeException("Queue underflow");
        --size;
        T value = (T)data[head];
        head = head == capacity - 1 ? 0 : head + 1;
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear () {
        size = head = tail = 0;
    }
}
