
/*
 * Created by wxn
 * 2018/12/16 1:24
 */

import java.util.Vector;

//稠密图_邻接矩阵
public class DenseWeightedGraph<Weight extends Number & Comparable<Weight>>
		implements WeightGraph {

	private int n;
	private int m;
	private boolean directed;
	private Edge<Weight>[][] g;

	public DenseWeightedGraph(int n, boolean directed) {
		this.n = n;
		this.m = 0;
		this.directed = directed;
		g = new Edge[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				g[i][j] = null;
			}
		}
	}

	@Override
	public int V() {
		return m;
	}

	@Override
	public int E() {
		return n;
	}

	@Override
	public void addEdge(Edge e) {
		if (hasEdge(e.v(), e.w())) {
			return;
		}
		g[e.v()][e.w()] = new Edge(e);
		if (e.v() != e.w() && !directed)
			g[e.w()][e.v()] = new Edge(e.w(), e.v(), e.wt());
		m++;
	}

	@Override
	public boolean hasEdge(int v, int w) {
		return g[v][w] != null;
	}

	@Override
	public void show() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				if (g[i][j] != null){
				    if (g[i][j].wt().toString().length()<4){
					System.out.print(g[i][j].wt() + "\t\t");
				    }else {
					System.out.print(g[i][j].wt() + "\t");
				    }
				}
				else
					System.out.print("NULL\t");
			System.out.println();
		}
	}

	// 返回图中一个顶点的所有邻边
	// 由于java使用引用机制，返回一个Vector不会带来额外开销,
	@Override
	public Iterable<Edge<Weight>> adj(int v) {
		Vector<Edge<Weight>> adjV = new Vector<>();
		for (int i = 0; i < n; i++) {
			if (g[v][i]!=null){
				adjV.add(g[v][i]);
			}
		}
		return adjV;
	}
}
