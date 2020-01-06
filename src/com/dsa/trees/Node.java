package com.dsa.trees;

class Node<T extends Comparable<T>> {
    Node<T> left;
    Node<T> right;
    T key;

    boolean keyGT(T ob) {
        return key.compareTo(ob) > 0;
    }

    boolean keyST(T ob) {
        return key.compareTo(ob) < 0;
    }

    boolean keyEquals(T ob) {
        return key.compareTo(ob) == 0;
    }

    boolean keyNotEquals(T ob) {
        return !keyEquals(ob);
    }

    Node(T val) {
        this.key = val;
        //this.parent = parent;
    }
}