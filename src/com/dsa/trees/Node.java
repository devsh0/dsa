package com.dsa.trees;

public class Node<T extends Comparable<T>> implements TreePrinter.PrintableNode {
    Node<T> left;
    Node<T> right;
    T key;

    /**
     * this.key > otherKey
     */
    boolean keyGT(T otherKey) {
        return key.compareTo(otherKey) > 0;
    }

    /**
     * this.key < otherKey
     */
    boolean keyST(T otherKey) {
        return key.compareTo(otherKey) < 0;
    }

    /**
     * this.key == otherKey
     */
    boolean keyEquals(T otherKey) {
        return key.compareTo(otherKey) == 0;
    }

    /**
     * this.key != otherKey
     */
    boolean keyNotEquals(T otherKey) {
        return !keyEquals(otherKey);
    }

    /**
     * this.key >= otherKey
     */
    boolean keyGToE(T otherKey) {
        return keyGT(otherKey) || keyEquals(otherKey);
    }

    /**
     * this.key <= otherKey
     */
    boolean keySToE(T otherKey) {
        return keyST(otherKey) || keyEquals(otherKey);
    }

    Node(T val) {
        this.key = val;
    }

    @Override
    public TreePrinter.PrintableNode getLeft() {
        return left;
    }

    @Override
    public TreePrinter.PrintableNode getRight() {
        return right;
    }

    @Override
    public String getText() {
        return String.valueOf(key);
    }

    public T getKey() {
        return key;
    }
}