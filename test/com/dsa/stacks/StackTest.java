package com.dsa.stacks;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class StackTest {
    private final int defaultCapacity = 5;
    @Test
    public void popTest() {
        Stack<String> stack = new Stack<>(defaultCapacity);
        String value = "value";
        stack.push(value);
        assertEquals(stack.pop(), value);
        assertTrue(stack.isEmpty());
    }

    @Test
    public void pushTest() {
        Stack<String> stack = new Stack<>(defaultCapacity);
        assertTrue(stack.isEmpty());
        String value = "value";
        stack.push(value);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void isEmptyTest() {
        Stack<Object> stack = new Stack<>(defaultCapacity);
        stack.push(new Object());
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void overflowTest() {
        Stack<Integer> stack = new Stack<>(defaultCapacity);
        try {
            for (int i = 0; i <= defaultCapacity; i++)
                stack.push(i);
        } catch (RuntimeException exc) {
            assertEquals(exc.getMessage().toLowerCase(), "stack overflow");
            return;
        }

        fail();
    }

    @Test
    public void underflowTest() {
        Stack<Integer> stack = new Stack<>(defaultCapacity);
        try {
            stack.pop();
        } catch (RuntimeException exc) {
            assertEquals(exc.getMessage().toLowerCase(), "stack underflow");
            return;
        }

        fail();
    }

    @Test
    public void clearTest() {
        Stack<Integer> stack = new Stack<>(defaultCapacity);
        stack.push(2);
        stack.push(5);
        assertTrue(stack.size() > 0);
        stack.clear();
        assertEquals(0, stack.size());
    }

    @Test
    public void sizeTest() {
        Stack<Integer> stack = new Stack<>(defaultCapacity);
        assertEquals(0, stack.size());
        stack.push(10);
        stack.push(20);
        assertEquals(2, stack.size());
        stack.clear();
        assertEquals(0, stack.size());
    }
}
