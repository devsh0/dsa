package com.dsa.utils;

import com.dsa.trees.BinarySearchTree;

import java.util.Random;

@SuppressWarnings("unused")
public abstract class DsaUtils {
    private static int[] getRandomInts(int n, int upperBound) {
        int[] array = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(upperBound);
        }

        return array;
    }

    private static <T extends Comparable<T>> void insertArrayToBst(T[] array, BinarySearchTree<T> tree) {
        for (T value : array)
            tree.insert(value);
    }
}
