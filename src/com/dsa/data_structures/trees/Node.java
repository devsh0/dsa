package com.dsa.data_structures.trees;

public class Node<T> {
    private Node<T> left;
    private Node<T> right;
    private final T value;

    Node (T value, Node<T> left, Node<T> right) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public T value() {
        return this.value;
    }

    public Node<T> left () {
        return this.left;
    }

    public Node<T> right () {
        return this.right;
    }

    public void setLeft(Node<T> node) {
        this.left = node;
    }

    public void setRight(Node<T> node) {
        this.right = node;
    }
}
