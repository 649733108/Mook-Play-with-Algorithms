
/*
 * Created by wxn
 * 2018/12/16 1:36
 */


import java.util.Vector;

// 稀疏图 - 邻接表
public class SparseWeightedGraph<Weight extends Number & Comparable<Weight>>
		implements WeightGraph<Weight>{

	private int n;  // 节点数
	private int m;  // 边数
	private boolean directed;   // 是否为有向图
	private Vector<Edge<Weight>>[] g;   // 图的具体数据

	// 构造函数
	public SparseWeightedGraph( int n , boolean directed ){
		assert n >= 0;
		this.n = n;
		this.m = 0;    // 初始化没有任何边
		this.directed = directed;
		// g初始化为n个空的vector, 表示每一个g[i]都为空, 即没有任和边
		g = (Vector<Edge<Weight>>[])new Vector[n];
		for(int i = 0 ; i < n ; i ++)
			g[i] = new Vector<Edge<Weight>>();
	}



	@Override
	public int V() {
		return n;
	}

	@Override
	public int E() {
		return m;
	}

	@Override
	public void addEdge(Edge<Weight> e) {

		// 注意, 由于在邻接表的情况, 查找是否有重边需要遍历整个链表
		// 我们的程序允许重边的出现

		g[e.v()].add(new Edge(e));
		if( e.v() != e.w() && !directed )
			g[e.w()].add(new Edge(e.w(), e.v(), e.wt()));

		m ++;
	}

	@Override
	public boolean hasEdge(int v, int w) {
		for( int i = 0 ; i < g[v].size() ; i ++ )
			if( g[v].elementAt(i).other(v) == w )
				return true;
		return false;
	}

	@Override
	public void show() {
		for( int i = 0 ; i < n ; i ++ ){
			System.out.print("vertex " + i + ":\t");
			for( int j = 0 ; j < g[i].size() ; j ++ ){
				Edge e = g[i].elementAt(j);
				System.out.print( "( to:" + e.other(i) + ",wt:" + e.wt() + ")\t");
			}
			System.out.println();
		}
	}

	@Override
	public Iterable<Edge<Weight>> adj(int v) {
		return g[v];
	}
}
