
/*
 * Created by wxn
 * 2018/12/2 23:17
 */

import util.SortTestHelper;

/**
 * 三路快排
 */
public class QuickSort3Ways {

	public static void sort(Comparable[] arrs){
		int n = arrs.length;
		sort(arrs,0,n-1);
	}

	private static void sort(Comparable[] arrs, int l, int r) {

		if (r-l<=15){
			InsertionSort.sort(arrs,l,r);
			return;
		}

		SortTestHelper.swap(arrs,l,(int)(Math.random()*(r-l+1))+l);
		Comparable v = arrs[l];

		//arrs[l+1...lt]<v
		int lt =l;
		//arrs[gt...r]>v
		int gt = r+1;
		//arrs[lt+1...i]==v
		int i = l+1;

		while (i<gt){
			if (arrs[i].compareTo(v)<0){
				SortTestHelper.swap(arrs,i,lt+1);
				i++;
				lt++;
			}else if (arrs[i].compareTo(v)>0){
				SortTestHelper.swap(arrs,i,gt-1);
				gt--;
			}else {
				i++;
			}
		}
		SortTestHelper.swap(arrs,l,lt);

		sort(arrs,l,lt-1);
		sort(arrs,gt,r);

	}

	public static void main(String args[]) {

		Integer[] arrs1 = SortTestHelper.generateRangeInt(1000000,0,5);
		Integer[] arrs2 = SortTestHelper.copyInt(arrs1);
		Integer[] arrs3 = SortTestHelper.copyInt(arrs1);

		SortTestHelper.testSort(QuickSort3Ways.class,arrs1);
		SortTestHelper.testSort(QuickSort.class,arrs3);
		SortTestHelper.testSort(MergeSort.class,arrs2);
	}


}
