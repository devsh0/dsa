package com.dsa;

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

    private static void insertArrayToBst(int[] array, BinarySearchTree tree) {
        for (int value : array)
            tree.insert(value);
    }
}
