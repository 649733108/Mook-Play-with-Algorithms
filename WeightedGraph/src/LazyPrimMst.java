
/*
 * Created by wxn
 * 2018/12/16 3:43
 */

import java.util.Vector;

/**
 * LazyPrim最小生成树
 */
public class LazyPrimMst<Weight extends Number & Comparable<Weight>> {

    private WeightGraph<Weight> graph;  // 图的引用
    private MinHeap<Edge<Weight>> minHeap;    // 最小堆, 算法辅助数据结构
    private boolean[] mark;             // 标记数组, 在算法运行过程中标记节点i是否被访问
    private Vector<Edge<Weight>>mst;    // 最小生成树所包含的所有边
    private Number mstWeight;           // 最小生成树的权值

    public LazyPrimMst(WeightGraph<Weight> graph){
        this.graph = graph;
        minHeap = new MinHeap<>(graph.E());
        mark = new boolean[graph.V()];
        mst = new Vector<>();

        visit(0);
        while (!minHeap.isEmpty()){
            // 使用最小堆找出已经访问的边中权值最小的边
            Edge<Weight> e = minHeap.extractMin();
            // 如果这条边的两端都已经访问过了, 则扔掉这条边
            if (mark[e.v()]==mark[e.w()]){
                continue;
            }
            // 否则, 这条边则应该存在在最小生成树中
            mst.add(e);
            // 访问和这条边连接的还没有被访问过的节点
            if (!mark[e.v()]){
                visit(e.v());
            }else {
                visit(e.w());
            }
        }

        // 计算最小生成树的权值
        mstWeight = mst.get(0).wt();
        for (int i = 1 ; i<mst.size(); i++){
            mstWeight = mstWeight.doubleValue() + mst.get(i).wt().doubleValue();
        }

    }

    private void visit(int v) {
        assert !mark[v];
        mark[v] = true;
        //将所有和节点v相连的未访问的边放入最小堆中
        for (Edge<Weight> e : graph.adj(v)) {
            if (!mark[e.other(v)]){
                minHeap.insert(e);
            }
        }
    }


    public static void main(String args[]) {
        WeightGraph<Double>graph = new SparseWeightedGraph<>(8,false);
        String filename = "TestG1.txt";
        ReadWeightedGraph readWeightedGraph = new ReadWeightedGraph(graph,filename);
        graph.show();

        LazyPrimMst<Double> lazyPrimMst = new LazyPrimMst<>(graph);
        for (Edge<Double> edge : lazyPrimMst.mst) {
            System.out.println(edge);
        }
        System.out.println("total weight: " + lazyPrimMst.mstWeight);
    }


}
