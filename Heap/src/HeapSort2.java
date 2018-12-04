
/*
 * Created by wxn
 * 2018/12/4 23:18
 */


import util.SortTestHelper;

public class HeapSort2 {

	public static void sort(Comparable[] arr){

		int n = arr.length;
		MaxHeap<Comparable> maxHeap = new MaxHeap<>(arr);
		for (int i = n-1 ; i>=0 ;i--){
			arr[i] = maxHeap.extractMax();
		}
	}

	public static void main(String args[]) {
		Integer[] arr1 = SortTestHelper.generateRangeInt(1000000,1,1000);
		Integer[] arr2 = SortTestHelper.copyInt(arr1);
		SortTestHelper.testSort(HeapSort1.class,arr1);
		SortTestHelper.testSort(HeapSort2.class,arr2);
	}
}
