package com.dsa.queues;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueueImplTest {
    private final int defaultSize = 5;

    @Test
    public void simpleEnqueueTest() {
        Queue<Integer> queue = new QueueImpl<>(defaultSize);
        assertTrue(queue.isEmpty());
        queue.enqueue(10);
        assertEquals(1, queue.size());
    }

    @Test
    public void simpleDequeueTest() {
        Queue<Integer> queue = new QueueImpl<>(defaultSize);
        queue.enqueue(10);
        queue.enqueue(20);
        assertEquals(10, (int)queue.dequeue());
        assertEquals(1, queue.size());
    }

    @Test
    public void underflowTest() {
        Queue<Integer> queue = new QueueImpl<>(defaultSize);
        queue.enqueue(10);
        try {
            queue.dequeue();
            queue.dequeue();
        } catch (RuntimeException exc) {
            assertEquals(exc.getMessage().toLowerCase(), "queue underflow");
            return;
        }

        fail();
    }

    @Test
    public void overflowTest() {
        Queue<Integer> queue = new QueueImpl<>(defaultSize);
        for (int i = 0; i < defaultSize; i++)
            queue.enqueue(i);

        try {
            queue.enqueue(defaultSize);
        } catch (RuntimeException exc) {
            String msg = exc.getMessage();
            System.out.println(msg);
            assertEquals(msg.toLowerCase(), "queue overflow");
            return;
        }

        fail();
    }

    @Test
    public void circularEnqueueTest() {
        Queue<Integer> queue = new QueueImpl<>(defaultSize);
        for (int i = 0; i < defaultSize; i++)
            queue.enqueue(i);

        try {
            queue.enqueue(defaultSize);
        } catch (RuntimeException exc) {
            String msg = exc.getMessage();
            System.out.println(msg);
            assertEquals(msg.toLowerCase(), "queue overflow");
            queue.dequeue();
            queue.enqueue(defaultSize);
            assertEquals(defaultSize, queue.size());
            return;
        }

        fail();
    }

    @Test
    public void circularDequeueTest() {
        Queue<Integer> queue = new QueueImpl<>(defaultSize);
        for (int i = 0; i < defaultSize; i++)
            queue.enqueue(i);

        for (int i = 0; i < defaultSize; i++)
            queue.dequeue();

        try {
            queue.dequeue();
        } catch (RuntimeException exc) {
            String msg = exc.getMessage();
            assertEquals(msg.toLowerCase(), "queue underflow");
            queue.enqueue(10);
            assertEquals(10, (int)queue.dequeue());
            return;
        }

        fail();
    }

    @Test
    public void clearTest() {
        Queue<Integer> queue = new QueueImpl<>(defaultSize);
        for (int i = 0; i < defaultSize; i++)
            queue.enqueue(i);

        assertEquals(defaultSize, queue.size());
        queue.clear();
        assertEquals(0, queue.size());
    }
}