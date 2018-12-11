
/*
 * Created by wxn
 * 2018/12/10 18:46
 */


public class Main {

	public static void main(String args[]) {

		//使用两种图的存储方式读取testG1.txt
		String fileName = "testG2.txt";
		SparseGraph g1 = new SparseGraph(6,false);
		ReadGraph readGraph1 = new ReadGraph(g1,fileName);
		System.out.println("test G1 in Sparse Graph:");
		g1.show();
		Components components1 = new Components(g1);
		System.out.println(components1.count());
		System.out.println();

		DenseGraph g2 = new DenseGraph(6,false);
		ReadGraph readGraph2 = new ReadGraph(g2,fileName);
		System.out.println("test G1 in Dense Graph:");
		g2.show();








//		int N = 20;
//		int M = 100;
//		Random random = new Random();
//
//		SparseGraph g1 = new SparseGraph(N,false);
//		for (int i = 0 ; i<M; i++){
//			int a = random.nextInt(N);
//			int b = random.nextInt(N);
//			g1.addEdge(a,b);
//		}
//
//		for (int v = 0; v<N; v++){
//			System.out.print(v + " : ");
//			Iterator<Integer> adj = g1.adj(v).iterator();
//			while (adj.hasNext()) {
//				System.out.print(adj.next()+" ");
//			}
//			System.out.println();
//		}
//
//		System.out.println();
//
//		DenseGraph g2 = new DenseGraph(N,false);
//		for (int i = 0 ; i<M; i++){
//			int a = random.nextInt(N);
//			int b = random.nextInt(N);
//			g2.addEdge(a,b);
//		}
//
//		for (int v = 0; v<N; v++){
//			System.out.print(v + " : ");
//			Iterator<Integer> adj = g2.adj(v).iterator();
//			while (adj.hasNext()) {
//				System.out.print(adj.next()+" ");
//			}
//			System.out.println();
//		}

	}

}
