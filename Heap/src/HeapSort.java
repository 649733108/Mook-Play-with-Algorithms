
/*
 * Created by wxn
 * 2018/12/4 23:32
 */


import util.SortTestHelper;

import static util.SortTestHelper.swap;

/**
 * 原地堆排序
 */
public class HeapSort {

	public static void sort(Comparable[] arr){

		int n = arr.length;

		// 注意，此时我们的堆是从0开始索引的
		// 从(最后一个元素的索引-1)/2开始
		// 最后一个元素的索引 = n-1
		for (int i = (n-1-1)/2 ; i>=0; i--){
			shiftDown(arr,n,i);
		}

		for (int i = n-1 ; i>0 ; i--){
			swap(arr,0,i);
			shiftDown(arr,i,0);
		}
	}

	private static void shiftDown(Comparable[] arr, int n, int k) {

		while (k*2+1<n){//表示有子节点
			int j = k*2+1;//在此轮循环中，arr[k] 和 arr[j]交换位置
			if (j+1<n && arr[j+1].compareTo(arr[j])>0){
				j++;
			}
			if (arr[k].compareTo(arr[j])>=0){
				break;
			}
			swap(arr,k,j);
			k=j;
		}
	}

	public static void main(String args[]) {
		Integer[] arr1 = SortTestHelper.generateRangeInt(1000000,1,1000);
		Integer[] arr2 = SortTestHelper.copyInt(arr1);
		Integer[] arr3 = SortTestHelper.copyInt(arr1);
		SortTestHelper.testSort(HeapSort1.class,arr1);
		SortTestHelper.testSort(HeapSort2.class,arr2);
		SortTestHelper.testSort(HeapSort.class,arr3);
	}
}
