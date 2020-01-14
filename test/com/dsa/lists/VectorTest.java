package com.dsa.lists;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class VectorTest {
    @Test
    public void addTest() {
        Vector<Integer> vector = new Vector<>();
        assertTrue(vector.isEmpty());
        assertEquals(0, vector.internalSize);
        vector.add(4);
        assertEquals(1, vector.size());
    }

    @Test
    public void removeTest() {
        Vector<Integer> vector = new Vector<>();
        assertTrue(vector.isEmpty());
        int key = 34;
        vector.add(key);
        assertFalse(vector.isEmpty());
        vector.remove(key);
        assertTrue(vector.isEmpty());
    }

    @Test
    public void insertTest() {
        Vector<Integer> vector = new Vector<>();
        vector.addAll(23, 545, 65, 34, 6, 64);
        int index = 3;
        assertEquals(34, vector.get(index).intValue());
        vector.insert(index, 76);
        assertEquals(76, vector.get(index).intValue());
        assertEquals(7, vector.size());
    }

    @Test
    public void expansionTest() {
        Vector<Integer> vector = new Vector<>(1, 2, 3, 4);
        vector.addAll(6, 5, 5);
        assertEquals(8, vector.internalSize);
        vector.add(2);
        assertEquals(16, vector.internalSize);
        vector.addAll(1, 33, 45, 56, 43, 65, 76);
        assertEquals(16, vector.internalSize);
        vector.insert(0, 34);
        assertEquals(32, vector.internalSize);
    }

    @Test
    public void shrinkTest() {
        Vector<Integer> vector = new Vector<>(23, 43, 53, 54, 65);
        assertEquals(10, vector.internalSize);
        vector.removeAt(0);
        assertEquals(6, vector.internalSize);
        vector.addAll(1, 1, 1, 1);
        assertEquals(16, vector.internalSize);
        vector.remove(1);
        assertEquals(6, vector.internalSize);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getTest() {
        Vector<Integer> vector = new Vector<>();
        try {
            vector.get(0);
        } catch (IndexOutOfBoundsException iob) {
            vector.addAll(3, 4, 54, 6);
            vector.get(4);
        }

        fail();
    }
}