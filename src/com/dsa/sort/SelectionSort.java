package com.dsa.sort;

import java.util.List;

public class SelectionSort {
  public static void sort (List<Integer> data) {
    for (int i = 0; i < data.size() - 1; i++) {
      Integer smallest = data.get(i);
      int swapWith = i;
      for (int j = i + 1; j < data.size(); j++) {
        Integer current = data.get(j);
        if (smallest > current) {
          smallest = current;
          swapWith = j;
        }
      }
      data.set(swapWith, data.get(i));
      data.set(i, smallest);
    }
  }
}
