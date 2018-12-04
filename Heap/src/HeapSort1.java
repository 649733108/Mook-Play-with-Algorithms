
/*
 * Created by wxn
 * 2018/12/4 22:52
 */

import util.SortTestHelper;

/**
 * 堆排序
 */
public class HeapSort1 {

	public static void sort(Comparable[] arr){
		int n = arr.length;
		MaxHeap<Comparable> maxHeap = new MaxHeap<>(n);

		for (Comparable comparable : arr) {
			maxHeap.insert(comparable);
		}

		for (int i = n-1 ; i>=0 ; i--){
			arr[i] = maxHeap.extractMax();
		}
	}

	public static void main(String args[]) {
		Integer[] arr = SortTestHelper.generateRangeInt(100,1,1000);
		SortTestHelper.testSort(HeapSort1.class,arr);
		SortTestHelper.printArr(arr);
	}

}
