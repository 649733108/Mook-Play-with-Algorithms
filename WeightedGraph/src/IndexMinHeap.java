
/*
 * Created by wxn
 * 2018/12/16 21:22
 */


/**
 * 最小索引堆
 */
public class IndexMinHeap<T extends Comparable<T>> {
    private T[] data;                //最小索引堆中的数据
    private int[] indexes;        //最小索引堆中的索引,indexes[x]=i表示索引i在x的位置
    private int[] reverse;        //最小索引堆中的反向索引,reverse[i]=x表示索引i在x的位置
    private int count;
    private int capacity;

    public IndexMinHeap(int capacity) {
	this.capacity = capacity;
	data = (T[]) new Comparable[capacity + 1];
	indexes = new int[capacity + 1];
	reverse = new int[capacity + 1];
	for (int i = 0; i <= capacity; i++) {
	    reverse[i] = 0;
	}
	count = 0;
    }

    // 返回索引堆中的元素个数
    public int size() {
	return count;
    }
    // 返回一个布尔值, 表示索引堆中是否为空
    public boolean isEmpty(){
	return count == 0;
    }

    //向最小索引堆中插入一个新元素,索引为i 元素为item
    //传入的i对用户而言是从0索引的
    public void insert(int i, T item) {
	assert count + 1 <= capacity;
	assert i + 1 >= 1 && i + 1 <= capacity;
	assert !contain(i);
	i++;
	data[i] = item;
	indexes[count+1] = i;
	reverse[i] = count+1;
	count++;
	shiftUp(count);
    }

    // 从最小索引堆中取出堆顶元素, 即索引堆中所存储的最小数据
    public T extractMin(){
	assert count > 0;

	T ret = data[indexes[1]];
	swapIndexes( 1 , count );
	reverse[indexes[count]] = 0;
	count --;
	shiftDown(1);

	return ret;
    }

    // 从最小索引堆中取出堆顶元素的索引
    public int extractMinIndex(){
	assert count > 0;

	int ret = indexes[1] - 1;
	swapIndexes( 1 , count );
	reverse[indexes[count]] = 0;
	count --;
	shiftDown(1);

	return ret;
    }

    // 获取最小索引堆中的堆顶元素
    public T getMin(){
	assert count > 0;
	return data[indexes[1]];
    }

    // 获取最小索引堆中的堆顶元素的索引
    public int getMinIndex(){
	assert count > 0;
	return indexes[1]-1;
    }

    // 索引堆中, 数据之间的比较根据data的大小进行比较, 但实际操作的是索引
    private void shiftDown(int k){

	while( 2*k <= count ){
	    int j = 2*k;
	    if( j+1 <= count && data[indexes[j+1]].compareTo(data[indexes[j]]) < 0 )
		j ++;

	    if( data[indexes[k]].compareTo(data[indexes[j]]) <= 0 )
		break;

	    swapIndexes(k, j);
	    k = j;
	}
    }

    private void shiftUp(int k) {
	while (k>1 && data[indexes[k/2]].compareTo(data[indexes[k]])>0){
	    swapIndexes(k,k/2);
	    k/=2;
	}
    }

    // 交换索引堆中的索引i和j
    // 由于有了反向索引reverse数组，
    // indexes数组发生改变以后， 相应的就需要维护reverse数组
    private void swapIndexes(int i, int j){
	int t = indexes[i];
	indexes[i] = indexes[j];
	indexes[j] = t;

	reverse[indexes[i]] = i;
	reverse[indexes[j]] = j;
    }
    // 看索引i所在的位置是否存在元素
    private boolean contain(int i) {
	assert  i + 1 >= 1 && i + 1 <= capacity;
	return reverse[i+1] != 0;
    }
    // 获取最小索引堆中索引为i的元素
    public T getItem( int i ){
	assert contain(i);
	return data[i+1];
    }

    // 将最小索引堆中索引为i的元素修改为newItem
    public void change( int i , T newItem ){

	assert contain(i);

	i += 1;
	data[i] = newItem;

	// 有了 reverse 之后,
	// 我们可以非常简单的通过reverse直接定位索引i在indexes中的位置
	shiftUp( reverse[i] );
	shiftDown( reverse[i] );
    }

    // 测试 IndexMinHeap
    public static void main(String[] args) {

	int N = 1000000;
	IndexMinHeap<Integer> indexMinHeap = new IndexMinHeap<>(N);
	for( int i = 0 ; i < N ; i ++ )
	    indexMinHeap.insert( i , (int)(Math.random()*N) );

    }
}
