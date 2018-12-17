
/*
 * Created by wxn
 * 2018/12/16 1:17
 */


public interface WeightGraph<Weight extends Number & Comparable<Weight>> {

	int V();

	int E();

	void addEdge(Edge<Weight> e);

	boolean hasEdge(int v, int w);

	void show();

	Iterable<Edge<Weight>> adj(int v);
}
