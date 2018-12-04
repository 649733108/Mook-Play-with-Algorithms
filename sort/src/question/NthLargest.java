package question;
/*
 * Created by wxn
 * 2018/12/3 0:00
 */

import util.SortTestHelper;

/**
 * 用快速排序的方法求数组中第n小的元素
 */
public class NthLargest {

	public static int getNthLargest(Integer[] arr, int n) {
		int length = arr.length;

		return sort(arr, length-n, 0, length - 1);
	}

	private static int sort(Integer[] arr, int n, int l, int r) {

		if (l == r) {
			return arr[l];
		}
		int p = partition(arr, l, r);
		if (p == n) {
			return arr[p];
		} else if (p < n) {
			return sort(arr, n, p + 1, r); // 如果 n > p, 则需要在nums[p+1...r]中找第n-p-1小元素
			// 注意: 由于我们传入__selection的依然是nums, 而不是nums[p+1...r],
			//       所以传入的最后一个参数依然是k, 而不是k-p-1
		} else {//p>n
			return sort(arr, n, l, p - 1);
		}
	}

	private static int partition(Integer[] arr, int l, int r) {

		SortTestHelper.swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);
		int e = arr[l];

		int i = l + 1, j = r;
		while (true) {
			while (i <= r && arr[i] < e) {
				i++;
			}
			while (j >= l + 1 && arr[j] > e) {
				j--;
			}
			if (i > j) {
				break;
			} else {
				SortTestHelper.swap(arr, i, j);
				i++;
				j--;
			}
		}
		SortTestHelper.swap(arr, l, j);
		return j;
	}

	public static void main(String args[]) {
		Integer[]arr = SortTestHelper.generateRangeInt(10,1,100);
//		Integer[] arr = {1,2,3,4,5,6,7,8};
		SortTestHelper.printArr(arr);

		System.out.println(getNthLargest(arr, 1));
	}


}
