
/*
 * Created by wxn
 * 2018/12/6 16:39
 */


public class BinarySearch<T extends Comparable<T>> {


	/**
	 * 非递归二分查找
	 *
	 * @param arr
	 * @param target
	 * @return
	 */
	public static int binarySearch(Comparable[] arr, Comparable target) {

		int n = arr.length;
		int l = 0, r = n - 1;
		//在arr[l,r]中查找target
		while (l <= r) {
			int mid = (r - l) / 2 + l;
			if (arr[mid].compareTo(target) == 0) {
				return mid;
			}
			if (arr[mid].compareTo(target) > 0) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return -1;
	}

	/**
	 * 递归二分查找
	 */
	public static int binartSearchR(Comparable[] arr, Comparable target) {
		int n = arr.length;
		return binarySearch(arr, 0, n - 1, target);
	}

	/**
	 * 在arr[l，r]中查找target
	 */
	private static int binarySearch(Comparable[] arr, int l, int r, Comparable target) {
		if (l > r) {
			return -1;
		}
		int mid = (r - l / 2) + l;
		if (arr[mid].compareTo(target) == 0)
			return mid;
		if (arr[mid].compareTo(target) < 0) {
			return binarySearch(arr, mid + 1, r, target);
		} else {
			return binarySearch(arr, l, mid - 1, target);
		}
	}

	// 二分查找法, 在有序数组arr中, 查找target
	// 如果找到target, 返回第一个target相应的索引index
	// 如果没有找到target, 返回比target小的最大值相应的索引, 如果这个最大值有多个, 返回最大索引
	// 如果这个target比整个数组的最小元素值还要小, 则不存在这个target的floor值, 返回-1
	static int floor(Comparable[] arr, Comparable target) {

		// 寻找比target小的最大索引
		int l = -1, r = arr.length - 1;
		while (l < r) {
			// 使用向上取整避免死循环
			int mid = l + (r - l + 1) / 2;
			if (arr[mid].compareTo(target) >= 0)
				r = mid - 1;
			else
				l = mid;
		}

		assert l == r;

		// 如果该索引+1就是target本身, 该索引+1即为返回值
		if (l + 1 < arr.length && arr[l + 1] == target)
			return l + 1;

		// 否则, 该索引即为返回值
		return l;
	}

	// 二分查找法, 在有序数组arr中, 查找target
	// 如果找到target, 返回最后一个target相应的索引index
	// 如果没有找到target, 返回比target大的最小值相应的索引, 如果这个最小值有多个, 返回最小的索引
	// 如果这个target比整个数组的最大元素值还要大, 则不存在这个target的ceil值, 返回整个数组元素个数n
	static int ceil(Comparable[] arr, Comparable target) {

		int l = 0, r = arr.length;
		while (l < r) {
			int mid = l + (r - l) / 2;
			if (arr[mid].compareTo(target) <= 0) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}

		assert l == r;

		if (r - 1 >= 0 && arr[r - 1] == target) {
			return r - 1;
		} else {
			return r;
		}
	}




	public static void main(String args[]) {
		Integer[] arr = {1, 2, 3, 3, 3, 3, 4};
//		System.out.println(binarySearch(arr, 9));
//		System.out.println(binartSearchR(arr,9));
//		System.out.println(floor(arr,0));
		System.out.println(ceil(arr, 0));
	}

}
