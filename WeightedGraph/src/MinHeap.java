
/*
 * Created by wxn
 * 2018/12/16 3:16
 */

/**
 * 最小堆
 */
public class MinHeap<T extends Comparable<T>> {

    private T data[];
    private Integer count;
    private Integer capacity;

    public MinHeap(Integer capacity) {
	this.capacity = capacity;
	data = (T[]) new Comparable[capacity + 1];
	count = 0;
    }

    public int size() {
	return count;
    }

    public boolean isEmpty() {
	return count == 0;
    }

    public void insert(T item) {
	assert count < capacity;
	data[count + 1] = item;
	count++;
	shiftUp(count);
    }

    public T extractMin() {
	assert count > 0;
	T ret = data[1];
	swap(1, count);
	count--;
	shiftDown(1);
	return ret;
    }

    private void shiftDown(int k) {
	while (k * 2 <= count) {
	    int j = k * 2;
	    if (j + 1 <= count && data[j + 1].compareTo(data[j]) < 0) {
		j++;
	    }
	    if (data[k].compareTo(data[j]) > 0) {
		swap(j, k);
		k = j;
	    } else {
		break;
	    }
	}
    }

    private void shiftUp(int k) {
	while (k > 1 && data[k / 2].compareTo(data[k]) > 0) {
	    swap(k, k / 2);
	    k /= 2;
	}
    }

    // 交换堆中索引为i和j的两个元素
    private void swap(int i, int j) {
	T t = data[i];
	data[i] = data[j];
	data[j] = t;
    }


    public static void main(String args[]) {
	Integer[] arr = new Integer[20];
	for (int i = 0; i < arr.length; i++) {
	    arr[i] = (int) (Math.random() * 100);
	}
	MinHeap<Integer> minHeap = new MinHeap<>(arr.length);
	for (int i = 0; i < arr.length; i++) {
	    minHeap.insert(arr[i]);
	}
	for (int i = 0; i < arr.length; i++) {
	    System.out.print(minHeap.extractMin() + " ");
	}
    }

}
