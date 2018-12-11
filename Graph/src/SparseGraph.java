
/*
 * Created by wxn
 * 2018/12/10 17:23
 */

import java.util.Random;
import java.util.Vector;

/**
 * 稀疏图——邻接表
 */
public class SparseGraph implements Graph{

	private int n;
	private int m;
	private boolean directed;
	private Vector<Integer>[] g;

	public SparseGraph(int n , boolean directed){
		this.n = n;
		this.m = 0;
		this.directed = directed;
		g = (Vector<Integer>[]) new Vector[n];
		for (int i = 0; i < n; i++) {
			g[i] = new Vector<>();
		}
	}

	public int V(){
		return n;
	}
	public int E(){
		return m;
	}

	public void addEdge(int v , int w){
		if (hasEdge(v,w))
			return;
		g[v].add(w);
		if (v!=w && !directed){
			g[w].add(v);
		}
		m++;
	}

	public boolean hasEdge(int v, int w){
		for (int i = 0 ; i<g[v].size();i++){
			if (g[v].elementAt(i)==w)
				return true;
		}
		return false;
	}

	//显示图的信息
	@Override
	public void show() {
		for (int i = 0 ; i<n; i++){
			System.out.print("vertex " + i + ":\t");
			for (int j = 0 ; j<g[i].size(); j++){
				System.out.print(g[i].get(j) + "\t");
			}
			System.out.println();
		}
	}

	//返回图中一个顶点的所有邻边
	public Iterable<Integer> adj(int v){
		return g[v];
	}

	public static void main(String args[]) {

		int N = 20;
		int M = 100;
		Random random = new Random();

		SparseGraph g1 = new SparseGraph(N,false);
		for (int i = 0 ; i<M; i++){
			int a = random.nextInt(N);
			int b = random.nextInt(N);
			g1.addEdge(a,b);
		}

		for (int v = 0; v<N; v++){
			System.out.println(v + " : ");
			Iterable<Integer> adj = g1.adj(v);
			while (adj.iterator().hasNext()) {
				System.out.println(adj.iterator().next()+" ");
			}
			System.out.println();
		}
	}
}
