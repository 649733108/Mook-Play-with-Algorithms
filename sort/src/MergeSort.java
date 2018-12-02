
/*
 * Created by wxn
 * 2018/12/1 15:16
 */

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

	public static void sort(Comparable[] arrs) {
		int n = arrs.length;
		sort(arrs, 0, n - 1);
	}

	//递归调用，对arrs数组中[l,r]位置元素排序
	@SuppressWarnings("unchecked")
	private static void sort(Comparable[] arrs, int l, int r) {

		if (l >= r) {
			return;
		}

		int m = (r - l) / 2 + l;
		sort(arrs, l, m);
		sort(arrs, m + 1, r);
		if (arrs[m].compareTo(arrs[m+1])>0){
			merge(arrs, l, m, r);
		}
	}

	//对arrs [l,m] [m+1,r]进行归并
	@SuppressWarnings("unchecked")
	private static void merge(Comparable[] arrs, int l, int m, int r) {

		//复制一个临时数组
		Comparable[] aux = Arrays.copyOfRange(arrs, l, r + 1);

		int i = l, j = m + 1;
		for (int k = l; k <= r; k++) {

			if (i > m) {
				arrs[k] = aux[j - l];
				j++;
			} else if (j > r) {
				arrs[k] = aux[i - l];
				i++;
			} else if (aux[i - l].compareTo(aux[j - l]) > 0) {
				arrs[k] = aux[j - l];
				j++;
			} else {
				arrs[k] = aux[i - l];
				i++;
			}
		}
	}

	public static void main(String args[]) {
		Integer[] arrs = SortTestHelper.generateRangeInt(50000, 0, 1000);

		SortTestHelper.testSort(InsertionSort.class, arrs);
		SortTestHelper.testSort(MergeSort.class, arrs);


	}

}
