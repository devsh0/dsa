package com.dsa.data_structures.stacks;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class StackTest {
    @Test
    public void popTest() {
        Stack<String> stack = new StackImpl<>();
        String value = "value";
        stack.push(value);
        assertEquals(stack.pop(), value);
        assertTrue(stack.isEmpty());
    }

    @Test
    public void pushTest() {
        Stack<String> stack = new StackImpl<>();
        assertTrue(stack.isEmpty());
        String value = "value";
        stack.push(value);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void isEmptyTest() {
        Stack<Object> stack = new StackImpl<>();
        stack.push(new Object());
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }
}
