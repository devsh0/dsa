package com.dsa.trees.printer;

public class Box<T extends Comparable<T>> {
    public T getValue() {
        return value;
    }

    public int getWidth() {
        return width;
    }

    private T value;
    private int width;

    public Box (T value, int width) {
        this.value = value;
        this.width = width;
    }

    public void draw () {

    }
}
