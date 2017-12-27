package Algo.Graph;

import java.util.LinkedList;

public class LazyPrimMST<Weight extends Number & Comparable> {
    private WeightedGraph<Weight> graph;
    private int n, m;
    private MinHeap<Edge<Weight>> minHeap;
    private boolean[] marked;
    private LinkedList<Edge<Weight>> mst;
    private double mstWeight;


    public LazyPrimMST(WeightedGraph<Weight> graph) {
        this.graph = graph;
        this.n = graph.V();
        this.m = graph.E();
        minHeap = new MinHeap<Edge<Weight>>(m);
        marked = new boolean[n];
        mst = new LinkedList<>();

        // Lazy Prim
        visit(0);
        prim();
    }

    private void prim() {
        while (!minHeap.isEmpty()) {
            // 使用最小堆找出已经访问的边中权值最小的边
            Edge edge = minHeap.extract();
            // 如果这条边的两端都已经访问过了, 则扔掉这条边
            if (marked[edge.v()] == marked[edge.w()])
                continue;
            // 否则, 这条边则应该存在在最小生成树中
            mst.add(edge);

            // 访问和这条边连接的还没有被访问过的节点
            if (!marked[edge.v()])
                visit(edge.v());
            else visit(edge.w());

            // 计算最小生成树的权值
            mstWeight += edge.wt().doubleValue();
        }
    }

    private void visit(int i) {
        assert !marked[i];
        marked[i] = true;
        // 将和节点v相连接的所有未访问的边放入最小堆中
        for (Edge edge : graph.adj(i)) {
            if (!marked[edge.other(i)])
                minHeap.insert(edge);
        }
    }

    // 返回最小生成树的所有边
    LinkedList<Edge<Weight>> mstEdges() {
        return mst;
    }

    // 返回最小生成树的权值
    double result() {
        return mstWeight;
    }

    public static void main(String[] args) {

        String filename = "F:\\testG1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Lazy Prim MST
        System.out.println("Test Lazy Prim MST:");
        LazyPrimMST<Double> lazyPrimMST = new LazyPrimMST<Double>(g);
        LinkedList<Edge<Double>> mst = lazyPrimMST.mstEdges();

        for (Edge edge : mst) {
            System.out.println(edge);
        }
        System.out.println("The MST weight is: " + lazyPrimMST.result());

        System.out.println();
    }
}
