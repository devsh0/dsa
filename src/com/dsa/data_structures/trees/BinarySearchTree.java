package com.dsa.data_structures.trees;

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

    private void throwExceptionIfTreeIsEmpty(Node...node) {
        Node examine = node.length == 0 ? root : node[0];
        if (examine == null)
            throw new RuntimeException("Tree is empty!");
    }

    public void inorderWalkUsingParentNode() {
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

    public void inorderWalkUsingStack() {
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

    public void inorderWalkRecursive(Node root) {
        if (root != null) {
            inorderWalkRecursive(root.left);
            System.out.println(root.key);
            inorderWalkRecursive(root.right);
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

    // We assume that the key exists in the tree
    public Node getInorderSuccessor (int key) {
        throwExceptionIfTreeIsEmpty();
        Node current = root;
        Node lastLeft = null;

        while (current.key != key) {
            if (current.key > key) {
                lastLeft = current;
                current = current.left;
            }
            else if (current.key < key)
                current = current.right;
        }

        if (current.right != null)
            return minimum(current.right);

        return lastLeft;
    }

    // We assume that the key exists in the tree
    public Node getInorderPredecessor (int key) {
        throwExceptionIfTreeIsEmpty();
        Node current = root;
        Node lastRight = null;

        while (current.key != key) {
            if (current.key > key)
                current = current.left;
            else if (current.key < key) {
                lastRight = current;
                current = current.right;
            }
        }

        if (current.left != null)
            return maximum(current.left);

        return lastRight;
    }

    public Node getInorderPredecessorUsingParent(int key) {
        Node node = inorderSearch(key);

        if (node.left != null)
            return maximum(node.left);

        Node parent = node.parent;
        while (parent != null && parent.left == node) {
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    public Node getInorderSuccessorUsingParent (int key) {
        Node node = inorderSearch(key);

        if (node.right != null)
            return minimum(node.right);

        Node parent = node.parent;
        while (parent != null && parent.right == node) {
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    private Node maximum (Node node) {
        throwExceptionIfTreeIsEmpty(node);
        while (node.right != null)
            node = node.right;
        return node;
    }

    private Node minimum (Node node) {
        throwExceptionIfTreeIsEmpty(node);
        while (node.left != null)
            node = node.left;
        return node;
    }

    public void delete(int key) {

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
        bst.insert(100);

        bst.insert(50);
        bst.insert(25);
        bst.insert(12);
        bst.insert(18);
        bst.insert(37);
        bst.insert(75);
        bst.insert(62);
        bst.insert(87);

        bst.insert(200);
        bst.insert(150);
        bst.insert(125);
        bst.insert(175);
        bst.insert(300);
        bst.insert(250);
        bst.insert(400);

        int leftEdge = 12;
        int rightEdge = 400;

        Node pn1 = bst.getInorderPredecessor(leftEdge);
        Node pn2 = bst.getInorderPredecessorUsingParent(leftEdge);
        System.out.println(pn1 == pn2);

        Node sn1 = bst.getInorderSuccessor(rightEdge);
        Node sn2 = bst.getInorderSuccessorUsingParent(rightEdge);
        System.out.println(sn1 == sn2);
    }
}
