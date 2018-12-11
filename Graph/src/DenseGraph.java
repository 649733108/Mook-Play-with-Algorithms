
/*
 * Created by wxn
 * 2018/12/10 16:59
 */


import java.util.Vector;

/**
 * 稠密图——邻接矩阵
 */
public class DenseGraph implements Graph {

	private int n;//节点数
	private int m;//边数
	private boolean directed;//是否为有向图
	private boolean[][] g;//图的具体数据

	public DenseGraph(int n, boolean directed) {
		this.n = n;
		this.m = 0;
		this.directed = directed;
		this.g = new boolean[n][n];
	}

	//返回节点个数
	public int V() {
		return n;
	}

	//返回边的条数
	public int E() {
		return m;
	}

	//向图中添加一条边
	public void addEdge(int v, int w) {

		assert v >= 0 && v < n;
		assert w >= 0 && w < n;

		if (hasEdge(v, w))
			return;

		g[v][w] = true;
		if (!directed)
			g[w][v] = true;

		m++;
	}

	//验证图中是否有从v到w的边
	public boolean hasEdge(int v, int w) {
		assert v >= 0 && v < n;
		assert w >= 0 && w < n;
		return g[v][w];
	}

	@Override
	public void show() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int flag = g[i][j] ? 1 : 0;
				System.out.print(flag + "\t");
			}
			System.out.println();
		}
	}

	// 返回图中一个顶点的所有邻边
	// 由于java使用引用机制，返回一个Vector不会带来额外开销,
	public Iterable<Integer> adj(int v) {
		Vector<Integer> adjV = new Vector<>();
		for (int i = 0; i < n; i++) {
			if (g[v][i]) {
				adjV.add(i);
			}
		}
		return adjV;
	}
}
