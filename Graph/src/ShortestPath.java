
/*
 * Created by wxn
 * 2018/12/11 21:49
 */


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

public class ShortestPath {
	private Graph G;
	private int s;
	private boolean[] visited;
	private int[] from;
	private int[] order;

	public ShortestPath(Graph g, int s) {
		this.G = g;
		visited = new boolean[G.V()];
		from = new int[G.V()];
		order = new int[G.V()];
		for (int i = 0; i < G.V(); i++) {
			visited[i] = false;
			from[i] = -1;
			order[i] = -1;
		}
		this.s = s;

		//无向图最短路径算法，从s开始广度优先遍历整张图
		Queue<Integer>q = new LinkedList<>();
		q.add(s);
		visited[s] = true;
		order[s] = 0;
		while (!q.isEmpty()){
			int v = q.remove();
			for (int i : G.adj(v)){
				if (!visited[i]){
					q.add(i);
					visited[i] = true;
					from[i] = v;
					order[i] = order[v]+1;
				}
			}
		}
	}

	// 查询从s点到w点是否有路径
	public boolean hasPath(int w) {
		assert w >= 0 && w < G.V();
		return visited[w];
	}

	public Vector<Integer> path(int w) {
		if (!hasPath(w)) {
			System.out.println("no path");
			return null;
		}
		Stack<Integer> s = new Stack<Integer>();
		// 通过from数组逆向查找到从s到w的路径, 存放到栈中
		int p = w;
		while (p != -1) {
			s.push(p);
			p = from[p];
		}

		// 从栈中依次取出元素, 获得顺序的从s到w的路径
		Vector<Integer> res = new Vector<>();
		while (!s.empty())
			res.add(s.pop());

		return res;
	}

	// 打印出从s点到w点的路径
	public void showPath(int w){

		assert hasPath(w) ;

		Vector<Integer> vec = path(w);
		for( int i = 0 ; i < vec.size() ; i ++ ){
			System.out.print(vec.elementAt(i));
			if( i == vec.size() - 1 )
				System.out.println();
			else
				System.out.print(" -> ");
		}
	}

	// 查看从s点到w点的最短路径长度
	// 若从s到w不可达，返回-1
	public int length(int w){
		assert w >= 0 && w < G.V();
		return order[w];
	}

	public static void main(String args[]) {
		String fileName = "testG2.txt";
		SparseGraph graph = new SparseGraph(6,false);
		new ReadGraph(graph,fileName);
		graph.show();

		Path dfs = new Path(graph,0);
		System.out.print("DFS : ");
		dfs.shopPath(4);

		ShortestPath bfs = new ShortestPath(graph,0);
		System.out.print("BFS : ");
		bfs.showPath(4);
		System.out.println(bfs.length(4));
	}

}
