package com.dsa.data_structures.dynamic_lists;

public interface DynamicList<T> {
    T get(int index) throws IndexOutOfBoundsException;

    void add(T element);
    void addAll(T...values);

    void insert(int at, T value) throws IndexOutOfBoundsException;
    void insertAll(int at, T...values) throws IndexOutOfBoundsException;

    boolean remove(T value);
    int size();
    boolean contains(T value);
    int count(T value);
    void clear();
    boolean isEmpty();

    @Override
    boolean equals (Object o);
}
