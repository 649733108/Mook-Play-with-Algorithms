
/*
 * Created by wxn
 * 2018/12/2 20:47
 */


import util.SortTestHelper;

/**
 * 快速排序
 */
public class QuickSort {

	public static void sort(Comparable[] arrs) {
		int n = arrs.length;
		sort(arrs, 0, n - 1);
	}

	//递归调用
	private static void sort(Comparable[] arrs, int l, int r) {

		if (r - l <= 15) {
			InsertionSort.sort(arrs, l, r);
			return;
		}
		int p = partition(arrs, l, r);
		sort(arrs, l, p - 1);
		sort(arrs, p + 1, r);
	}

	//返回p 使得arrs[l,p-1]<arrs[p] , arrs[p+1,r]>arrp
	private static int partition(Comparable[] arrs, int l, int r) {

		SortTestHelper.swap(arrs, l, (int) (Math.random() * (r - l + 1)) + l);

		Comparable v = arrs[l];
//		int j = l;
//		for (int i = l+1 ; i<=r; i++){
//
//			if (arrs[i].compareTo(v)<0){
//				util.SortTestHelper.swap(arrs,i,j+1);
//				j++;
//			}
//		}
//		util.SortTestHelper.swap(arrs,j,l);
//		return j;

		//改进：采用两个游标 双路快排
		int i = l + 1, j = r;

		while (true){
			while (i <= r && arrs[i].compareTo(v) < 0) {
				i++;
			}
			while (j >= l + 1 && arrs[j].compareTo(v) > 0) {
				j--;
			}
			if (i>j){
				break;
			}else {
				SortTestHelper.swap(arrs,i,j);
				i++;
				j--;
			}
		}

		SortTestHelper.swap(arrs,l,j);
		return j;
	}


	public static void main(String args[]) {
		Integer[] arrs1 = SortTestHelper.generateRangeInt(1000000, 1, 10);
		Integer[] arrs2 = SortTestHelper.copyInt(arrs1);
		SortTestHelper.testSort(QuickSort.class, arrs1);
		SortTestHelper.testSort(MergeSort.class, arrs2);
	}

}
