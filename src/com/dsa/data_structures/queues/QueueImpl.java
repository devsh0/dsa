package com.dsa.data_structures.queues;

@SuppressWarnings("unchecked")
public class QueueImpl<T> implements Queue<T> {
    private final int capacity = 1000;
    private Object[] data = new Object[capacity];
    private int size = 0;
    private int head = 0;
    private int tail = 0;

    @Override
    public boolean isEmpty() {
        return this.head == tail;
    }

    @Override
    public void enqueue(T value) {
        if (tail == head)
            throw new RuntimeException("Queue overflow");
        data[tail] = value;
        ++size;
        tail = tail == capacity ? 0 : tail + 1;
    }

    @Override
    public T dequeue() {
        if (head == tail)
            throw new RuntimeException("Queue underflow");
        --size;
        return (T)data[head++];
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
