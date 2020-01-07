package com.dsa.trees;

class Node<T extends Comparable<T>> {
    Node<T> left;
    Node<T> right;
    T key;

    /**
     * this.key > param.key
     */
    boolean keyGT(T ob) {
        return key.compareTo(ob) > 0;
    }

    /**
     * this.key < param.key
     */
    boolean keyST(T ob) {
        return key.compareTo(ob) < 0;
    }

    /**
     * this.key == param.key
     */
    boolean keyEquals(T ob) {
        return key.compareTo(ob) == 0;
    }

    /**
     * this.key != param.key
     */
    boolean keyNotEquals(T ob) {
        return !keyEquals(ob);
    }

    /**
     * this.key >= param.key
     */
    boolean keyGToE(T ob) {
        return keyGT(ob) || keyEquals(ob);
    }

    /**
     * this.key <= param.key
     */
    boolean keySToE(T ob) {
        return keyST(ob) || keyEquals(ob);
    }

    Node(T val) {
        this.key = val;
    }
}