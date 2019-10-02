package com.dsa.data_structures.trees;

public class BinaryTree<T> {
    private final Node<T> root;

    public BinaryTree (T rootValue) {
        this.root = new Node<T>(rootValue, null, null);
    }

    private boolean binaryFilled(Node<T> myNode) {
        return myNode.left() != null && myNode.right() != null;
    }

    // todo: plain wrong
    private void insert(Node<T> parent, Node<T> child) {
        if (!binaryFilled(parent)) {
            if (parent.left() == null) {
                parent.setLeft(child);
            } else parent.setRight(child);
            return;
        }

        insert(parent.left(), child);
        insert(parent.right(), child);
    }

    public void addNode (T value) {
        Node<T> newNode = new Node<>(value, null, null);
        insert(root, newNode);
    }
}
