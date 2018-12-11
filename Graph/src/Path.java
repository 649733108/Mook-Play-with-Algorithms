
/*
 * Created by wxn
 * 2018/12/11 21:05
 */


import java.util.Stack;
import java.util.Vector;

public class Path {
	private Graph G;
	private int s;//起始点
	private boolean[] visited;
	private int[] from;

	private void dfs(int v){
		visited[v] = true;
		for (int i :G.adj(v)){
			if (!visited[i]){
				from[i] = v;
				dfs(i);
			}
		}
	}

	public Path(Graph g , int s){
		this.G = g;
		visited = new boolean[G.V()];
		from = new int[G.V()];
		for (int i = 0 ; i<G.V(); i++){
			visited[i] = false;
			from[i] = -1;
		}
		this.s = s;
		dfs(s);
	}

	boolean hasPath(int w){
		return visited[w];
	}

	Vector<Integer>path(int w){
		if (!hasPath(w)){
			System.out.println("No Path!");
			return null;
		}
		else {
			Stack<Integer>s = new Stack<>();
			int p = w;
			while (p!=-1){
				s.push(p);
				p = from[p];
			}

			Vector<Integer> res = new Vector<>();
			while (!s.empty()){
				res.add(s.pop());
			}
			return res;
		}
	}

	public void shopPath(int w){
		if (!hasPath(w)){
			System.out.println("No Path!");
			return;
		}
		Vector<Integer>vec = path(w);
		for (int i = 0 ; i<vec.size() ; i++){
			System.out.print(vec.get(i));
			if (i==vec.size()-1){
				System.out.println();
			}else {
				System.out.print(" -> ");
			}
		}
	}

	public static void main(String args[]) {
		String fileName = "testG2.txt";
		SparseGraph g = new SparseGraph(6,false);
		ReadGraph readGraph = new ReadGraph(g,fileName);
		g.show();
		System.out.println();

		Path path = new Path(g,5);
		System.out.println("Path from 0 to 5 : ");
		path.shopPath(3);
	}

}
