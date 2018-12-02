
/*
 * Created by wxn
 * 2018/12/1 7:06
 */

import util.SortTestHelper;

/**
 * 插入排序
 */
public class InsertionSort {

	public static void sort(Comparable[] arrs) {
		int n = arrs.length;
		for (int i = 1; i < n; i++) {
			Comparable e = arrs[i];
			int j;//保存元素i应该插入的位置
			for (j = i; j > 0; j--) {
				if (arrs[j-1].compareTo(e) > 0) {
					arrs[j]=arrs[j-1];
				} else {
					break;
				}
			}
			arrs[j] = e;
		}
	}

	// 对arr[l...r]的区间使用InsertionSort排序
	public static void sort(Comparable[] arr, int l, int r){

		for( int i = l + 1 ; i <= r ; i ++ ){
			Comparable e = arr[i];
			int j = i;
			for( ; j > l && arr[j-1].compareTo(e) > 0 ; j--)
				arr[j] = arr[j-1];
			arr[j] = e;
		}
	}

	public static void main(String args[]) {

//		Integer[] arrs1 = util.SortTestHelper.generateRangeInt(100000, 1, 5);
		Integer[] arrs1 = SortTestHelper.generateSortedLikeInt(100000,10);
		Integer[] arrs2 = SortTestHelper.copyInt(arrs1);

		SortTestHelper.printArr(arrs1);

		SortTestHelper.testSort(SelectionSort.class,arrs1);
		SortTestHelper.testSort(InsertionSort.class,arrs2);

	}

}
