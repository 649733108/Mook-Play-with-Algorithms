package util;
/*
 * Created by wxn
 * 2018/12/1 5:45
 */


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class SortTestHelper {

	//生成长度为n的随机数组 range：[rangeL,rangeR]
	public static Integer[] generateRangeInt(int n, int rangeL, int rangeR) {

		if (rangeL > rangeR) {
			throw new IllegalArgumentException("范围不正确");
		}

		Integer[] arr = new Integer[n];
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			arr[i] = random.nextInt((rangeR - rangeL) + 1) + rangeL;
		}
		return arr;
	}
	//生成基本有序的int数组
	public static Integer[] generateSortedLikeInt(int n,int swapNum){
		Integer[]arrs = new Integer[n];
		for (int i = 0; i<n ; i++){
			arrs[i] = i;
		}
		Random random = new Random();
		for (int i = 0 ; i<swapNum; i++){
			int xPos = random.nextInt(n);
			int yPos = random.nextInt(n);
			swap(arrs,xPos,yPos);
		}

		return arrs;
	}

	//生成有序的int数组
	public static Integer[] generateSortedInt(int n){
		Integer[]arrs = new Integer[n];
		for (int i = 0; i<n ; i++){
			arrs[i] = i;
		}

		return arrs;
	}

	//输出数组的元素
	public static void printArr(Comparable[] arrs) {
		for (Comparable arr : arrs) {
			System.out.print(arr + " ");
		}
		System.out.println();
	}

	//判断是否有序
	public static boolean isSorted(Comparable[] arrs){
		for (int i = 0 ; i<arrs.length-1; i++){
			if (arrs[i].compareTo(arrs[i+1])>0)
				return false;
		}
		return true;
	}

	//测试算法的效率
	//通过java的反射机制
	public static void testSort(Class sortClass, Comparable[] arrs) {
		try {
			//通过class对象获得sort方法
			Method sortMethod = sortClass.getMethod("sort", Comparable[].class);
			//排序参数
			Object[] params = new Object[]{arrs};

			long startTime = System.currentTimeMillis();

			sortMethod.invoke(null, params);

			long endTime = System.currentTimeMillis();

			if(isSorted(arrs)){
				System.out.print("排序成功 : ");
			}else {
				System.out.print("排序失败 : ");
			}
			System.out.println(sortClass.getName() + " : " + (endTime - startTime)/1000.0 + "s");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 交换数组中两个元素的位置
	 */
	public static void swap(Comparable[] arr, int i, int j) {
		Comparable temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/**
	 * 复制Integer数组
	 */
	public static Integer[] copyInt(Integer[] arrs){
		Integer[] newArrs = new Integer[arrs.length];
		System.arraycopy(arrs, 0, newArrs, 0, arrs.length);
		return newArrs;
	}
}
