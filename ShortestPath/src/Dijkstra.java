
/*
 * Created by wxn
 * 2018/12/17 23:12
 */

import java.util.Stack;
import java.util.Vector;

/**
 * Dijkstra单源最短路径算法
 */
public class Dijkstra<Weight extends Number & Comparable<Weight>> {

    private WeightGraph<Weight> graph;		//图的引用
    private int s;				//起始点
    private Number[] distTo;			//distTo[i]记录从起始点s到i的最短路径长度
    private boolean[] marked;			//标记数组,在算法运行过程中标记节点i是否被访问
    private Edge[] from;			//from[i]记录最短路径中,到达i节点的是哪一条边
    						//用来回复整个最短路径


    public Dijkstra(WeightGraph<Weight> graph, int s) {
	this.graph = graph;
	this.s = s;
	distTo = new Number[graph.V()];
	marked = new boolean[graph.V()];
	from = new Edge[graph.V()];
	for (int i = 0; i < graph.V(); i++) {
	    distTo[i] = 0.0;
	    marked[i] = false;
	    from[i] = null;
	}

	// 使用索引堆记录当前找到的到达每个顶点的最短距离
	IndexMinHeap<Weight> indexMinHeap = new IndexMinHeap<>(graph.V());

	//对起始点s初始化
	distTo[s] = 0.0;
	from[s] = new Edge<>(s, s, (Weight) (Number) 0.0);
	indexMinHeap.insert(s, (Weight) distTo[s]);
	marked[s] = true;
	while (!indexMinHeap.isEmpty()){
	    int v = indexMinHeap.extractMinIndex();
	    // distTo[v]就是s到v的最短距离
	    marked[v] = true;

	    //对v的所有相邻节点进行更新
	    for (Edge<Weight> edge : graph.adj(v)) {
		int w = edge.other(v);
		//如果从s到w点的最短路径还没有找到
		if (!marked[w]){
		    //如果w点以前没有访问过
		    //或者访问过,但是通过当前的v点到w距离更短 则更新
		    if (from[w]==null || distTo[v].doubleValue()+ edge.wt().doubleValue()<distTo[w].doubleValue()){
		        distTo[w] = distTo[v].doubleValue()+ edge.wt().doubleValue();
		        from[w] = edge;
		        if (indexMinHeap.contain(w)){
		            indexMinHeap.change(w, (Weight) distTo[w]);
			}else {
		            indexMinHeap.insert(w, (Weight) distTo[w]);
			}
		    }
		}
	    }
	}
    }

    // 返回从s点到w点的最短路径长度
    Number shortestPathTo( int w ){
	assert w >= 0 && w < graph.V();
	assert hasPathTo(w);
	return distTo[w];
    }

    // 判断从s点到w点是否联通
    boolean hasPathTo( int w ){
	assert w >= 0 && w < graph.V() ;
	return marked[w];
    }

    // 寻找从s到w的最短路径, 将整个路径经过的边存放在vec中
    Vector<Edge<Weight>> shortestPath(int w){

	assert w >= 0 && w < graph.V();
	assert hasPathTo(w);

	// 通过from数组逆向查找到从s到w的路径, 存放到栈中
	Stack<Edge<Weight>> s = new Stack<>();
	Edge<Weight> e = from[w];
	while( e.v() != this.s ){
	    s.push(e);
	    e = from[e.v()];
	}
	s.push(e);

	// 从栈中依次取出元素, 获得顺序的从s到w的路径
	Vector<Edge<Weight>> res = new Vector<>();
	while( !s.empty() ){
	    e = s.pop();
	    res.add( e );
	}

	return res;
    }
    // 打印出从s点到w点的路径
    void showPath(int w){

	assert w >= 0 && w < graph.V();
	assert hasPathTo(w);

	Vector<Edge<Weight>> path =  shortestPath(w);
	for( int i = 0 ; i < path.size() ; i ++ ){
	    System.out.print( path.elementAt(i).v() + " -> ");
	    if( i == path.size()-1 )
		System.out.println(path.elementAt(i).w());
	}
    }

    public static void main(String args[]) {
	String filename = "testG1.txt";
	int V = 5;

	SparseWeightedGraph<Double> g = new SparseWeightedGraph<>(V, false);
	// Dijkstra最短路径算法同样适用于有向图
	//SparseGraph<int> g = SparseGraph<int>(V, false);
	ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

	System.out.println("Test Dijkstra:\n");
	Dijkstra<Double> dij = new Dijkstra<>(g,0);
	for( int i = 1 ; i < V ; i ++ ){
	    if(dij.hasPathTo(i)) {
		System.out.println("Shortest Path to " + i + " : " + dij.shortestPathTo(i));
		dij.showPath(i);
	    }
	    else
		System.out.println("No Path to " + i );

	    System.out.println("----------");
	}
    }


}
