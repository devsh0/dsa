package com.dsa.data_structures.stacks;

public interface Stack<T> {
    T pop();
    void push(T element);
    boolean isEmpty();
    boolean contains(T value);
    void clear();
    int size();
}
