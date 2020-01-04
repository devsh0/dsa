package com.dsa.data_structures.trees;

import com.dsa.data_structures.stacks.StackImpl;

import java.util.Random;
import java.util.Stack;

/**
 * BinarySearchTree with a nodes having pointer to parent nodes.
 * The parent nodes is only supposed to be used to implement
 * certain algorithms. Overusing the parent node might spoil the fun.
 */
public class BinarySearchTree {
    private static class Node {
        Node left;
        Node right;
        Node parent;
        int key;

        Node(int val, Node parent) {
            this.key = val;
            this.parent = parent;
        }
    }

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(int key) {
        if (root == null) {
            root = new Node(key, null);
            return;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            current = current.key > key ? current.left : current.right;
        }

        Node newNode = new Node(key, parent);
        if (parent.key > key)
            parent.left = newNode;
        else parent.right = newNode;
    }

    private void throwExceptionIfTreeIsEmpty() {
        if (root == null)
            throw new RuntimeException("Tree is empty!");
    }

    public void inorderTreeWalkUsingParent() {
        if (root == null)
            return;

        Node current = root;
        Node visited = null;

        while (root != visited) {
            if (current == visited) {
                // if current node is at the right hand side of its parent
                // mark the parent as visited
                if (current == current.parent.right)
                    visited = current.parent;

                current = current.parent;
                continue;
            }

            if (current.left != visited && current.left != null) {
                current = current.left;
                continue;
            }

            System.out.println(current.key);

            if (current.right != visited && current.right != null) {
                current = current.right;
                continue;
            }

            visited = current;
        }
    }

    public void inorderTreeWalkUsingStack () {
        if (root == null)
            return;

        Node current = root;
        Stack<Node> nodes = new Stack<>();

        while (current != null || nodes.size() > 0) {
            while (current != null) {
                nodes.push(current);
                current = current.left;
            }

            current = nodes.pop();
            System.out.println(current.key);
            current = current.right;
        }
    }

    public void inorderTreeWalkRecursive(Node root) {
        if (root != null) {
            inorderTreeWalkRecursive(root.left);
            System.out.println(root.key);
            inorderTreeWalkRecursive(root.right);
        }
    }

    public Node inorderSearch(int key) {
        throwExceptionIfTreeIsEmpty();
        Node current = root;
        while (current != null) {
            if (current.key == key)
                return current;
            current = current.key > key ? current.left : current.right;
        }
        throw new RuntimeException("Key does not exist!");
    }

    public void delete(int key) {
        throwExceptionIfTreeIsEmpty();
        Node node = inorderSearch(key);

        // fixme: what if we are deleting the root of the tree?
        Node parent = node.parent;
        boolean atLeft = parent.left == node;

        if (node.left == null && node.right == null) {
            // leaf node
            if (atLeft)
                parent.left = null;
            else parent.right = null;
            return;
        }

        if (node.left == null) {
            node.right.parent = parent;
            if (atLeft)
                // node is at left and has no left children
                parent.left = node.right;
            else
                // node is at right and has no left children
                parent.right = node.right;
        } else if (node.right == null) {
            node.left.parent = parent;
            if (atLeft)
                // node is at left and has no right children
                parent.left = node.left;
            else
                // node is at right and has no right children
                parent.right = node.left;
        }
    }

    private static int[] arrayOfRandoms(int length) {
        int[] array = new int[length];
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(length);
        }

        return array;
    }

    private static void insertArray(BinarySearchTree tree, int[] array) {
        for (int value : array)
            tree.insert(value);
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        insertArray(bst, arrayOfRandoms(10000));
        bst.inorderTreeWalkUsingStack();
    }
}
