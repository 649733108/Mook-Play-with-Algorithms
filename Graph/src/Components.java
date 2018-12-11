
/*
 * Created by wxn
 * 2018/12/10 20:17
 */

/**
 * 求无权图连通分量
 */
public class Components {
	Graph G;
	private boolean[] visited;//记录dfs过程中节点是否被访问
	private int ccount;//记录连通分量个数
	private int[] id;//每个节点所对应的连通分量标记

	//图的深度优先遍历
	private void dfs(int v){
		visited[v] = true;
		id[v] = ccount;

		for (int i : G.adj(v)){
			if (!visited[i]){
				dfs(i);
			}
		}
	}

	public Components(Graph graph){
		G = graph;
		visited = new boolean[G.V()];
		id = new int[G.V()];
		ccount = 0;
		for (int i = 0; i < G.V(); i++) {
			visited[i] = false;
			id[i] = -1;
		}

		for (int i = 0; i < G.V(); i++) {
			if (!visited[i]){
				dfs(i);
				ccount++;
			}
		}
	}

	public int count(){
		return ccount;
	}

	// 查询点v和点w是否联通
	boolean isConnected( int v , int w ){
		assert v >= 0 && v < G.V();
		assert w >= 0 && w < G.V();
		return id[v] == id[w];
	}
}
