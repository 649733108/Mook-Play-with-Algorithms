
/*
 * Created by wxn
 * 2018/12/16 0:15
 */

//边
public class Edge<Weight extends Number & Comparable<Weight>> implements Comparable<Edge<Weight>> {

	private int a, b; //边的两个端点
	private Weight weight;//边的权值

	public Edge(int a, int b, Weight weight) {
		this.a = a;
		this.b = b;
		this.weight = weight;
	}

	public Edge(Edge<Weight> e) {
		this.a = e.a;
		this.b = e.b;
		this.weight = e.weight;
	}
	//返回第一个顶点
	public int v() {
		return a;
	}
	//返回第二个顶点
	public int w() {
		return b;
	}
	//返回权值
	public Weight wt(){
		return weight;
	}

	//给定一个顶点,返回另一个顶点
	public int other(int x) {
		return x == a ? b : a;
	}

	//输出边的信息
	@Override
	public String toString() {
		return "" + a + "-" + b + ": " + weight;
	}

	//边之间的比较
	@Override
	public int compareTo(Edge<Weight> that) {
		if (weight.compareTo(that.wt())<0){
			return -1;
		}else if (weight.compareTo(that.wt())>0){
			return 1;
		}else {
			return 0;
		}
	}

}
