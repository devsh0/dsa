package com.dsa.lists;

public class DoublyLinkedList<T> {
    static class Node<T> {
        T data;
        Node<T> pred;
        Node<T> succ;

        Node(T data) {
            this.data = data;
        }
    }

    Node<T> head;
    Node<T> tail;
    private int size;

    /**
     * Returns nth element in the linked list.
     *
     * @param index index of the element.
     * @return value at {@code index} in this linked list.
     * @throws IndexOutOfBoundsException if index not in [0, size)
     */
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds!");

        Node<T> current = head;
        for (int i = 0; i < index; i++)
            current = current.succ;

        return current.data;
    }

    /**
     * Inserts the given value to head of the list.
     *
     * @param value value to be inserted in the list.
     */
    public void insert(T value) {
        if (value == null)
            throw new IllegalArgumentException("null not allowed here!");
        Node<T> newNode = new Node<>(value);
        if (head != null) {
            head.pred = newNode;
            newNode.succ = head;
        } else
            tail = newNode;

        head = newNode;
        size += 1;
    }

    public void insertAtTail(T value) {
        if (value == null)
            throw new IllegalArgumentException("null not allowed here!");
        else {
            Node<T> newNode = new Node<>(value);
            tail.succ = newNode;
            newNode.pred = tail;
            tail = newNode;
            size += 1;
        }
    }

    private Node<T> getNode(T value) {
        Node<T> current = head;
        while (current != null && !current.data.equals(value))
            current = current.succ;

        return current;
    }

    /**
     * Removes the first instance of {@code value} from the list
     *
     * @param value value to be removed from the list
     * @return true if element was found and removed, false otherwise
     */
    public boolean remove(T value) {
        Node<T> node = getNode(value);
        if (node == null)
            return false;

        Node<T> succ = node.succ;
        Node<T> pred = node.pred;

        if (node == head)
            head = head.succ;
        else
            pred.succ = node.succ;

        if (node == tail)
            tail = tail.pred;
        else
            succ.pred = node.pred;

        size -= 1;
        return true;
    }

    public T popTail() {
        if (tail != null) {
            T lData = tail.data;
            if (tail.pred != null)
                tail.pred.succ = null;
            tail = tail.pred;
            size -= 1;
            return lData;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean contains(T value) {
        Node<T> current = getNode(value);
        return current != null;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null && tail == null;
    }
}
