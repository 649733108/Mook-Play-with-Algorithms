
/*
 * Created by wxn
 * 2018/12/7 20:32
 */

/**
 * 并查集
 */
public class UnionFind {

	private Integer[] id;
	private int count;

	public UnionFind(int n) {
		this.count = n;
		this.id = new Integer[n];
		for (int i = 0; i < n; i++) {
			id[i] = i;
		}
	}

	public int find(int p){
		assert(p>=0 && p<count);
		return id[p];
	}

	public boolean isConnected(int p , int q){
		return find(p)==find(q);
	}

	public void union(int p, int q){
		int pId = find(p);
		int qId = find(q);
		if (pId==qId)
			return;
		for (int i = 0 ; i<count; i++){
			if (id[i]==pId){
				id[i] = qId;
			}
		}
	}

	public static void main(String args[]) {
		int n = 100000;
		UnionFindTestHelper.testUF1(n);
		UnionFindTestHelper.testUF2(n);
	}

}
