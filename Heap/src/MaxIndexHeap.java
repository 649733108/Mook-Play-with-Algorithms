
/*
 * Created by wxn
 * 2018/12/4 21:36
 */

import java.util.Random;

/**
 * 最大索引堆
 */
public class MaxIndexHeap<T extends Comparable> {
	private T[] data;
	private int[] indexes;// 最大索引堆中的索引, indexes[x] = i 表示索引i在x的位置
	private int[] reverse; // 最大索引堆中的反向索引, reverse[i] = x 表示索引i在x的位置
	private int count;
	private int capacity;

	public MaxIndexHeap(int capacity) {
		this.data = (T[]) new Comparable[capacity + 1];
		this.indexes = new int[capacity + 1];
		this.reverse = new int[capacity + 1];
		this.count = 0;
		this.capacity = capacity;
	}

//	public MaxIndexHeap(T[] arr){
//		int n = arr.length;
//		capacity = n;
//		this.data = (T[])new Comparable[n+1];
//		for (int i = 0 ; i < n ; i++){
//			data[i+1] = arr[i];
//		}
//		count = n;
//
//		for (int i = count/2 ; i>=1 ; i--){
//			shiftDown(i);
//		}
//
//	}

	public int size() {
		return count;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	//向最大索引堆中插入一个新元素，索引为i 元素为item
	public void insert(int i, T item) {
		if (count + 1 > capacity) {
			throw new RuntimeException("The heap is already full");
		}
		if (i + 1 < 1 || i + 1 > capacity) {
			throw new IllegalArgumentException("index illegal");
		}
		i++;
		data[i] = item;
		indexes[count + 1] = i;
		reverse[i] = count+1;
		count++;
		shiftUp(count);
	}

	// 看索引i所在的位置是否存在元素
	boolean contain( int i ){
		if (i + 1 < 1 || i + 1 > capacity) {
			throw new IllegalArgumentException("index illegal");
		}
		return reverse[i+1] != 0;
	}

	private void shiftUp(int k) {
		while (k > 1 && data[indexes[k / 2]].compareTo(data[indexes[k]]) < 0) {
			swapIndexes(k, k / 2);
			k /= 2;
		}
	}

	private void swapIndexes(int i, int j) {
		int t = indexes[i];
		indexes[i] = indexes[j];
		indexes[j] = t;

		reverse[indexes[i]] = i;
		reverse[indexes[j]] = j;
	}

	//取出堆中最大的元素
	public T extractMax() {
		if (count < 1) {
			throw new RuntimeException("The heap is empty");
		}
		T ret = data[indexes[1]];
		swapIndexes(1, count);
		reverse[indexes[count]] = 0;
		count--;
		shiftDown(1);
		return ret;
	}

	//取出堆顶元素的索引
	public int extractMaxIndex() {
		if (count < 1) {
			throw new RuntimeException("The heap is empty");
		}
		int ret = indexes[1] - 1;
		swapIndexes(1, count);
		reverse[indexes[count]] = 0;
		count--;
		shiftDown(1);
		return ret;
	}

	//获取堆顶元素
	public T getMax() {
		if (count < 1) {
			throw new RuntimeException("The heap is empty");
		}
		return data[indexes[1]];
	}

	// 获取最大索引堆中的堆顶元素的索引
	public int getMaxIndex() {
		if (count < 1) {
			throw new RuntimeException("The heap is empty");
		}
		return indexes[1] - 1;
	}

	// 获取最大索引堆中索引为i的元素
	public T getItem(int i) {
		assert i + 1 >= 1 && i + 1 <= capacity;
		return data[i + 1];
	}

	// 将最大索引堆中索引为i的元素修改为newItem
	public void change(int i, T newItem) {

		if (!contain(i)){
			throw new IllegalArgumentException("index illegal");
		}

		i += 1;
		data[i] = newItem;

		// 找到indexes[j] = i, j表示data[i]在堆中的位置
		// 之后shiftUp(j), 再shiftDown(j)
//		for (int j = 1; j <= count; j++)
//			if (indexes[j] == i) {
//				shiftUp(j);
//				shiftDown(j);
//				return;
//			}

		// 有了 reverse 之后,
		// 我们可以非常简单的通过reverse直接定位索引i在indexes中的位置
		shiftUp( reverse[i] );
		shiftDown( reverse[i] );
	}


	private void shiftDown(int k) {

		while (k * 2 <= count) {//表示有子节点
			int j = k * 2;//在此轮循环中，data[k] 和 data[j]交换位置
			if (j + 1 <= count && data[indexes[j + 1]].compareTo(data[indexes[j]]) > 0) {
				j++;
			}
			if (data[indexes[k]].compareTo(data[indexes[j]]) >= 0) {
				break;
			}
			swapIndexes(k, j);
			k = j;
		}

	}

	private void swap(int i, int j) {
		T temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	public static void main(String[] args) {

		int N = 100;
		MaxIndexHeap<Integer> indexMaxHeap = new MaxIndexHeap<>(N);
		for (int i = 0; i < N; i++)
			indexMaxHeap.insert(i, (int) (Math.random() * N));

	}

}
