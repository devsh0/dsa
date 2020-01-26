package com.dsa.sort;

import com.dsa.utils.DsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class SortTest {
  private List<Integer> list;
  private List<Integer> duplicate;
  private final int size = 15;
  private final int upperBound = 100;

  @Before
  public void setup () {
     list = DsaUtils.getUniqueRandomInts(size, upperBound);
     duplicate = new ArrayList<>(list);
  }

  private boolean testHelper () {
    duplicate.sort((o1, o2) -> o1 - o2);
    return list.equals(duplicate);
  }

  @Test
  public void selectionSortTest() {
    SelectionSort.sort(list);
    assertTrue(testHelper());
  }

  @Test
  public void insertionSortTest() {
    InsertionSort.sort(list);
    assertTrue(testHelper());
  }
}
