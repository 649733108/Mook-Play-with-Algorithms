
/*
 * Created by wxn
 * 2018/12/6 16:39
 */


public class BinarySearch<T extends Comparable<T>> {


	/**
	 * 非递归二分查找
	 * @param arr
	 * @param target
	 * @return
	 */
	public static int binarySearch(Comparable[] arr, Comparable target) {

		int n = arr.length;
		int l = 0, r = n - 1;
		//在arr[l,r]中查找target
		while (l<=r){
			int mid = (r - l) / 2 + l;
			if (arr[mid].compareTo(target)==0){
				return mid;
			}
			if (arr[mid].compareTo(target)>0){
				r = mid-1;
			}else {
				l = mid+1;
			}
		}
		return -1;
	}

	/**
	 * 递归二分查找
	 */
	public static int binartSearchR(Comparable[]arr,Comparable target){
		int n = arr.length;
		return binarySearch(arr,0,n-1,target);
	}

	/**
	 * 在arr[l，r]中查找target
	 */
	private static int binarySearch(Comparable[] arr, int l, int r, Comparable target) {
		if (l>r){
			return -1;
		}
		int mid = (r-l/2)+l;
		if (arr[mid].compareTo(target)==0)
			return mid;
		if (arr[mid].compareTo(target)<0){
			return binarySearch(arr,mid+1,r,target);
		}else {
			return binarySearch(arr,l,mid-1,target);
		}
	}


	public static void main(String args[]) {
		Integer[]arr = {1,2,3,4,5,6,7,8};
		System.out.println(binarySearch(arr, 9));
		System.out.println(binartSearchR(arr,9));
	}

}
