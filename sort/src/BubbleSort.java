
/*
 * Created by wxn
 * 2018/12/18 23:12
 */

import util.SortTestHelper;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void sort(Comparable<Comparable>[] arr) {
	int n = arr.length;
	int newN;
	do {
	    newN = 0;
	    for (int i = 1; i < n; i++) {
		if (arr[i - 1].compareTo(arr[i]) > 0) {
		    swap(arr, i - 1, i);
		    newN = i;
		}
	    }
	    n = newN;
	} while (newN>0);
    }

    private static void swap(Object[] arr, int i, int j) {
	Object temp = arr[i];
	arr[i] = arr[j];
	arr[j] = temp;
    }

    public static void main(String args[]) {
	Integer[] arrs1 = SortTestHelper.generateSortedLikeInt(100000, 10);
	Integer[] arrs2 = SortTestHelper.copyInt(arrs1);

//	SortTestHelper.printArr(arrs1);

	SortTestHelper.testSort(InsertionSort.class, arrs1);
	SortTestHelper.testSort(BubbleSort.class, arrs2);
    }

}
