
/*
 * Created by wxn
 * 2018/12/1 4:54
 */

import entity.Student;
import util.SortTestHelper;

/**
 * 选择排序
 */
public class SelectionSort {



	public static void sort(Comparable[] arr , int n){

		for (int i = 0; i < n-1; i++) {

			int minIndex = i;
			for (int j = i+1 ; j<n;j++){
				if (arr[minIndex].compareTo(arr[j])>0){
					minIndex = j;
				}
			}
			SortTestHelper.swap(arr,i,minIndex);
		}
	}

	public static void sort(Comparable[] arr){
		sort(arr,arr.length);
	}



	public static void main(String args[]) {
//		Integer[] arr = {10,9,8,7,6,5,4,3,2,1};
//		sort(arr,arr.length);
//		for (Integer anArr : arr) {
//			System.out.print(anArr + " ");
//		}

		Integer[] arrs = SortTestHelper.generateRangeInt(10000,1,100);
//		sort(arrs);
//		util.SortTestHelper.printArr(arrs);

		SortTestHelper.testSort(SelectionSort.class,arrs);

		Double[] doubles = {3.14,10.76,99.1024,0.5,76.0};
		sort(doubles,doubles.length);
		for (Double aDouble : doubles) {
			System.out.print(aDouble+" ");
		}

		Student[] students = {
				new Student("A",58),
				new Student("B",66),
				new Student("D",99),
				new Student("C",99)
		};
		sort(students);

		for (Student student : students) {
			System.out.println(student);
		}
	}

}
