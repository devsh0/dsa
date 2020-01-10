package com.dsa.utils;

import com.dsa.trees.BinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("unused")
public abstract class DsaUtils {
    public static List<Integer> getUniqueRandomInts(int n, int upperBound) {
        List<Integer> ints = new ArrayList<>();
        Random random = new Random();

        while(ints.size() < n) {
            int r = random.nextInt(upperBound);
            if (!ints.contains(r))
                ints.add(r);
        }

        return ints;
    }

    public static <T extends Comparable<T>> void insertArrayToBst(T[] array, BinarySearchTree<T> tree) {
        for (T value : array)
            tree.insert(value);
    }

    public static int getRandomInt (int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
