package com.dsa.data_structures.queues;

public interface Queue<T> {
    int size();
    boolean isEmpty();
    void enqueue(T value);
    T dequeue();
    void clear();
}
