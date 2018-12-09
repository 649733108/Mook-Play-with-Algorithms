
/*
 * Created by wxn
 * 2018/12/9 16:15
 */


public class UnionFind2 {

	private int[] parent;
	private int count;

	public UnionFind2(int count){
		this.parent = new int[count];
		this.count = count;
		for (int i = 0; i < count; i++) {
			parent[i] = i;
		}
	}

	public int size(){
		return count;
	}

	public int find(int p){
		while (p != parent[p]){
			p = parent[p];
		}
		return p;
	}

	public boolean isConnected(int p , int q){
		return find(p)==find(q);
	}

	public void union(int p, int q){
		int parentP = find(p);
		int parentQ = find(q);
		if (p==q)
			return;
		parent[parentP] = parentQ;
	}

	public static void main(String args[]) {
		int n = 100000;
		UnionFindTestHelper.testUF1(n);
		UnionFindTestHelper.testUF2(n);
	}

}
