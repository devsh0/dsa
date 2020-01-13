package com.dsa.lists;

import org.junit.Test;

import static junit.framework.TestCase.*;


public class DoublyLinkedListTest {
    @Test
    public void insertionTest () {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        assertEquals(0, list.size());
        list.insert(23);
        assertEquals(1, list.size());
        assertEquals(23, list.get(0).intValue());
    }

    @Test
    public void insertAtTailTest () {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.insert(32);

        // inserted at head
        list.insert(23);
        assertEquals(32, list.tail.data.intValue());

        // inserted at tail
        list.insertAtTail(32);
        assertEquals(32, list.tail.data.intValue());
    }

    @Test
    public void removeTest () {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.insert(23);
        list.insert(54);
        list.insert(65);
        assertEquals(3, list.size());

        list.remove(23);
        assertEquals(2, list.size());
        list.remove(54);
        assertEquals(1, list.size());
        list.remove(65);
        assertTrue(list.isEmpty());
    }

    @Test
    public void popTailTest () {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        assertNull(list.popTail());
        list.insert(34);
        assertEquals(34, list.popTail().intValue());
        assertEquals(0, list.size());
    }

    @Test
    public void containsTest () {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        int key = 32;
        assertFalse(list.contains(key));
        list.insert(key);
        assertTrue(list.contains(key));
    }

    @Test
    public void isEmptyTest () {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        assertTrue(list.isEmpty());
        list.insert(43);
        assertFalse(list.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullNotAllowedTest () {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        try {
            list.insert(34);
            list.insert(null);
        } catch (IllegalArgumentException eArg) {
            list.insertAtTail(null);
        }

        fail();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void elementAccessTest () {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        try {
            list.get(0);
        } catch (IndexOutOfBoundsException iOut) {
            list.insert(4);
            assertEquals(4, list.get(0).intValue());
            list.get(1);
        }

        fail();
    }
}