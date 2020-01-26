package com.dsa.sort;

import java.util.List;

public class InsertionSort {
  public static void sort (List<Integer> data) {
    for (int i = 1; i < data.size(); i++) {
      int j = i;
      int key = data.get(j);
      while (j > 0 && key < data.get(j - 1))
        data.set(j, data.get(--j));
      data.set(j, key);
    }
  }
}
