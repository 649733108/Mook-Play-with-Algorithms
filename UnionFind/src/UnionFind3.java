
/*
 * Created by wxn
 * 2018/12/9 16:15
 */


public class UnionFind3 {

	private int[] parent;
	private int[] rank; //rank[i]记录以i为根的树的高度
	private int count;

	public UnionFind3(int count){
		this.parent = new int[count];
		this.rank = new int[count];
		this.count = count;
		for (int i = 0; i < count; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
	}

	public int size(){
		return count;
	}

	public int find(int p){
		while( p != parent[p] ){
			parent[p] = parent[parent[p]];
			p = parent[p];
		}
		return p;
	}

	public boolean isConnected(int p , int q){
		return find(p)==find(q);
	}

	public void union(int p, int q){
		int pRoot = find(p);
		int qRoot = find(q);
		if (pRoot==qRoot)
			return;
		if (rank[pRoot]<rank[qRoot]){
			parent[pRoot] = qRoot;
		}else if (rank[pRoot]>rank[qRoot]){
			parent[qRoot] = pRoot;
		}else {
			parent[pRoot] = qRoot;
			rank[qRoot]+=1;
		}
	}

	public static void main(String args[]) {
		int n = 1000000;
//		UnionFindTestHelper.testUF1(n);
//		UnionFindTestHelper.testUF2(n);
		UnionFindTestHelper.testUF3(n);
	}

}
