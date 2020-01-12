package com.dsa.trees;

class Node<T extends Comparable<T>> {
    Node<T> left;
    Node<T> right;
    T key;

    /**
     * this.key > otherKey
     */
    boolean keyGT(T ob) {
        return key.compareTo(ob) > 0;
    }

    /**
     * this.key < otherKey
     */
    boolean keyST(T ob) {
        return key.compareTo(ob) < 0;
    }

    /**
     * this.key == otherKey
     */
    boolean keyEquals(T ob) {
        return key.compareTo(ob) == 0;
    }

    /**
     * this.key != otherKey
     */
    boolean keyNotEquals(T ob) {
        return !keyEquals(ob);
    }

    /**
     * this.key >= otherKey
     */
    boolean keyGToE(T ob) {
        return keyGT(ob) || keyEquals(ob);
    }

    /**
     * this.key <= otherKey
     */
    boolean keySToE(T ob) {
        return keyST(ob) || keyEquals(ob);
    }

    Node(T val) {
        this.key = val;
    }
}