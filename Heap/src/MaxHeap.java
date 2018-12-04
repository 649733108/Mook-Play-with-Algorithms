
/*
 * Created by wxn
 * 2018/12/4 21:36
 */

import java.util.Random;

/**
 * 最大堆
 */
public class MaxHeap<T extends Comparable> {
	private T[] data;
	private int count;
	private int capacity;

	public MaxHeap(int capacity) {
		this.data = (T[]) new Comparable[capacity + 1];
		this.count = 0;
		this.capacity = capacity;
	}

	public MaxHeap(T[] arr){
		int n = arr.length;
		capacity = n;
		this.data = (T[])new Comparable[n+1];
		for (int i = 0 ; i < n ; i++){
			data[i+1] = arr[i];
		}
		count = n;

		for (int i = count/2 ; i>=1 ; i--){
			shiftDown(i);
		}

	}

	public int size() {
		return count;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public void insert(T item){
		if (count+1>capacity){
			throw new RuntimeException("The heap is already full");
		}
		data[count+1] = item;
		count++;
		shiftUp(count);
	}

	private void shiftUp(int k) {
		while (k>1 && data[k/2].compareTo(data[k])<0){
			swap(k,k/2);
			k/=2;
		}
	}

	//取出堆中最大的元素
	public T extractMax(){
		if (count<1){
			throw new RuntimeException("The heap is empty");
		}
		T ret = data[1];
		swap(1,count);
		count--;
		shiftDown(1);
		return ret;
	}

	private void shiftDown(int k) {

		while (k*2<=count){//表示有子节点
			int j = k*2;//在此轮循环中，data[k] 和 data[j]交换位置
			if (j+1<=count && data[j+1].compareTo(data[j])>0){
				j++;
			}
			if (data[k].compareTo(data[j])>=0){
				break;
			}
			swap(k,j);
			k=j;
		}

	}

	private void swap(int i, int j) {
		T temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	public static void main(String args[]) {
		MaxHeap<Integer> maxHeap = new MaxHeap<>(100);
		Random random = new Random();
		for (int i = 0 ; i<50 ; i++){
			maxHeap.insert(random.nextInt(100));
		}
		while (!maxHeap.isEmpty()){
			System.out.print(maxHeap.extractMax() + " ");
		}
		System.out.println();
	}

}
