
/*
 * Created by wxn
 * 2018/12/16 22:43
 */

import java.util.Vector;

/**
 * Kruskal算法求最小生成树
 */
public class KruskalMst<Weight extends Number & Comparable<Weight>> {

    private Vector<Edge<Weight>> mst;        //最小生成树所包含的所右边
    private Number mstWeight;                //最小生成树的权值

    public KruskalMst(WeightGraph graph) {

	mst = new Vector<>();
	//将图中所有边存放到一个最小堆中
	MinHeap<Edge<Weight>> minHeap = new MinHeap<>(graph.E());
	for (int i = 0; i < graph.V(); i++) {
	    for (Object item : graph.adj(i)) {
		Edge<Weight> e = (Edge<Weight>) item;
		if (e.v() < e.w()) {
		    minHeap.insert(e);
		}
	    }
	}
	//创建一个并查集,查看已经访问的节点的联通情况
	UnionFind uf = new UnionFind(graph.V());
	while (!minHeap.isEmpty() && mst.size() < graph.V() - 1) {
	    //从最小堆中一次从小到大取出所有边
	    Edge<Weight> e = minHeap.extractMin();
	    // 如果该边的两个端点是联通的, 说明加入这条边将产生环, 扔掉这条边
	    if (uf.isConnected(e.v(), e.w())) {
		continue;
	    }
	    // 否则, 将这条边添加进最小生成树, 同时标记边的两个端点联通
	    mst.add(e);
	    uf.unionElements(e.v(), e.w());
	}
	// 计算最小生成树的权值
	mstWeight = mst.elementAt(0).wt();
	for (int i = 1; i < mst.size(); i++)
	    mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
    }
    // 返回最小生成树的所有边
    Vector<Edge<Weight>> mstEdges(){
	return mst;
    }

    // 返回最小生成树的权值
    Number result(){
	return mstWeight;
    }


    // 测试 Kruskal
    public static void main(String[] args) {

	String filename = "testG1.txt";
	int V = 8;

	SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
	ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

	// Test Kruskal
	System.out.println("Test Kruskal:");
	KruskalMst<Double> kruskalMST = new KruskalMst<>(g);
	Vector<Edge<Double>> mst = kruskalMST.mstEdges();
	for( int i = 0 ; i < mst.size() ; i ++ )
	    System.out.println(mst.elementAt(i));
	System.out.println("The MST weight is: " + kruskalMST.result());

	System.out.println();
    }
}
