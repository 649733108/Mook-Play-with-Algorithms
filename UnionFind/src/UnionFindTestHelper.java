
/*
 * Created by wxn
 * 2018/12/7 20:43
 */


// 测试并查集
public class UnionFindTestHelper {

	// 测试第一版本的并查集, 测试元素个数为n
	public static void testUF1( int n ){

		UnionFind uf = new UnionFind(n);

		long startTime = System.currentTimeMillis();

		// 进行n次操作, 每次随机选择两个元素进行合并操作
		for( int i = 0 ; i < n ; i ++ ){
			int a = (int)(Math.random()*n);
			int b = (int)(Math.random()*n);
			uf.union(a,b);
		}
		// 再进行n次操作, 每次随机选择两个元素, 查询他们是否同属一个集合
		for(int i = 0 ; i < n ; i ++ ){
			int a = (int)(Math.random()*n);
			int b = (int)(Math.random()*n);
			uf.isConnected(a,b);
		}
		long endTime = System.currentTimeMillis();

		// 打印输出对这2n个操作的耗时
		System.out.println("UF1, " + 2*n + " ops, " + (endTime-startTime) + "ms");
	}


	public static void testUF2( int n ){

		UnionFind2 uf = new UnionFind2(n);

		long startTime = System.currentTimeMillis();

		// 进行n次操作, 每次随机选择两个元素进行合并操作
		for( int i = 0 ; i < n ; i ++ ){
			int a = (int)(Math.random()*n);
			int b = (int)(Math.random()*n);
			uf.union(a,b);
		}
		// 再进行n次操作, 每次随机选择两个元素, 查询他们是否同属一个集合
		for(int i = 0 ; i < n ; i ++ ){
			int a = (int)(Math.random()*n);
			int b = (int)(Math.random()*n);
			uf.isConnected(a,b);
		}
		long endTime = System.currentTimeMillis();

		// 打印输出对这2n个操作的耗时
		System.out.println("UF2, " + 2*n + " ops, " + (endTime-startTime) + "ms");
	}

	public static void testUF3( int n ){

		UnionFind3 uf = new UnionFind3(n);

		long startTime = System.currentTimeMillis();

		// 进行n次操作, 每次随机选择两个元素进行合并操作
		for( int i = 0 ; i < n ; i ++ ){
			int a = (int)(Math.random()*n);
			int b = (int)(Math.random()*n);
			uf.union(a,b);
		}
		// 再进行n次操作, 每次随机选择两个元素, 查询他们是否同属一个集合
		for(int i = 0 ; i < n ; i ++ ){
			int a = (int)(Math.random()*n);
			int b = (int)(Math.random()*n);
			uf.isConnected(a,b);
		}
		long endTime = System.currentTimeMillis();

		// 打印输出对这2n个操作的耗时
		System.out.println("UF3, " + 2*n + " ops, " + (endTime-startTime) + "ms");
	}
}
