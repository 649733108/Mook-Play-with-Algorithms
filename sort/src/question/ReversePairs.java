package question;
/*
 * Created by wxn
 * 2018/12/2 23:57
 */

import com.sun.javafx.collections.SortHelper;
import util.SortTestHelper;

import java.util.Arrays;

/**
 * 用归并排序的方法求逆序对
 */
public class ReversePairs {

	public static int getReversePairs(Integer[] arr) {
		int n = arr.length;
		return getReversePairs(arr, 0, n - 1);
	}

	private static int getReversePairs(Integer[] arr, int l, int r) {

		if (l >= r) {
			return 0;
		}

		int m = (r - l) / 2 + l;
		int res1 = getReversePairs(arr, l, m);
		int res2 = getReversePairs(arr, m + 1, r);

		return res1 + res2 + merge(arr, l, m, r);

	}

	private static int merge(Integer[] arr, int l, int m, int r) {

		Integer[] aux = Arrays.copyOfRange(arr, l, r + 1);

		int res = 0;
		int i = l, j = m + 1;
		for (int k = l; k <= r; k++) {
			if (i > m) {
				arr[k] = aux[j - l];
				j++;
			} else if (j>r){
				arr[k] = aux[i-l];
				i++;
			}else if (aux[i-l].compareTo(aux[j-l])<=0){
				arr[k] = aux[i-l];
				i++;
			}else {//aux[i-l].compareTo(aux[j-l])>0
				arr[k] = aux[j-l];
				j++;
				res+=(m-i+1);
			}
		}
		return res;
	}


	public static void main(String args[]) {
		Integer[] arr = SortTestHelper.generateSortedInt(100);
		SortTestHelper.printArr(arr);

		System.out.println(getReversePairs(arr));
	}


}
