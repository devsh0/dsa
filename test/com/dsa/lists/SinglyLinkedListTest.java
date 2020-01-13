package com.dsa.lists;

import org.junit.Test;

import static junit.framework.TestCase.*;


public class SinglyLinkedListTest {
    @Test
    public void insertionTest() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertEquals(0, list.size());
        list.insert(23);
        assertEquals(1, list.size());
        assertEquals(23, list.get(0).intValue());
    }

    @Test
    public void removeTest() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
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
    public void containsTest() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        int key = 32;
        assertFalse(list.contains(key));
        list.insert(key);
        assertTrue(list.contains(key));
    }

    @Test
    public void isEmptyTest() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertTrue(list.isEmpty());
        list.insert(43);
        assertFalse(list.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullNotAllowedTest() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insert(34);
        list.insert(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void elementAccessTest () {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
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