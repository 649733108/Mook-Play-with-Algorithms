
/*
 * Created by wxn
 * 2018/12/16 21:39
 */

import java.util.Vector;

// 使用优化的Prim算法求图的最小生成树
public class PrimMst<Weight extends Number & Comparable<Weight>> {
    private WeightGraph graph;			//图的引用
    private IndexMinHeap<Weight> indexMinHeap;	//最小索引堆
    private Edge<Weight>[] edgeTo;		//访问的点所对应的边
    private boolean[] marked;			//标记数组,在算法运行过程中标记节点i是否被访问
    private Vector<Edge<Weight>> mst;		//最小生成树所包含的所右边
    private Number mstWeight;			//最小生成树的权值

    public PrimMst(WeightGraph graph) {
	this.graph = graph;
	assert graph.E()>=1;
	indexMinHeap = new IndexMinHeap<>(graph.V());
	marked = new boolean[graph.V()];
	edgeTo = new Edge[graph.V()];
	for (int i = 0; i < graph.V(); i++) {
	    marked[i] = false;
	    edgeTo[i] = null;
	}
	mst = new Vector<>();
	
	//Prim
	visit(0);
	while (!indexMinHeap.isEmpty()){
	    // 使用最小索引堆找出已经访问的边中权值最小的边
	    // 最小索引堆中存储的是点的索引, 通过点的索引找到相对应的边
	    int v = indexMinHeap.extractMinIndex();
	    assert edgeTo[v]!=null;
	    mst.add(edgeTo[v]);
	    visit(v);
	}

	// 计算最小生成树的权值
	mstWeight = mst.elementAt(0).wt();
	for( int i = 1 ; i < mst.size() ; i ++ )
	    mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
    }


    // 访问节点v
    private void visit(int v) {
        assert !marked[v];
        marked[v] = true;

	// 将和节点v相连接的未访问的另一端点, 和与之相连接的边, 放入最小堆中
	for (Object item : graph.adj(v)) {
	    Edge<Weight> e = (Edge<Weight>) item;
	    int w = e.other(v);
	    //如果边的另一端点未被访问
	    if (!marked[w]){
		// 如果从没有考虑过这个端点, 直接将这个端点和与之相连接的边加入索引堆
		if (edgeTo[w]==null){
		    edgeTo[w] = e;
		    indexMinHeap.insert(w,e.wt());
		}
		// 如果曾经考虑这个端点, 但现在的边比之前考虑的边更短, 则进行替换
		else if( e.wt().compareTo(edgeTo[w].wt()) < 0 ){
		    edgeTo[w] = e;
		    indexMinHeap.change(w, e.wt());
		}
	    }
	}
    }
    // 返回最小生成树的所有边
    Vector<Edge<Weight>> mstEdges(){
	return mst;
    }

    // 返回最小生成树的权值
    Number result(){
	return mstWeight;
    }

    // 测试 Prim
    public static void main(String[] args) {

	String filename = "testG1.txt";
	int V = 8;

	SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
	ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

	// Test Prim MST
	System.out.println("Test Prim MST:");
	PrimMst<Double> primMST = new PrimMst<>(g);
	Vector<Edge<Double>> mst = primMST.mstEdges();
	for( int i = 0 ; i < mst.size() ; i ++ )
	    System.out.println(mst.elementAt(i));
	System.out.println("The MST weight is: " + primMST.result());

	System.out.println();
    }
}
