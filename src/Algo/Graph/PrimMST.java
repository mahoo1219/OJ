package Algo.Graph;

import java.util.Arrays;
import java.util.LinkedList;

public class PrimMST<Weight extends Number & Comparable> {
    private WeightedGraph graph;
    private IndexMinHeap2<Weight> ipq;
    private Edge<Weight>[] edgeTo; // 访问的点所对应的边, 算法辅助数据结构
    private boolean[] marked;
    private LinkedList<Edge<Weight>> mst;
    private double mstWeight;
    private int n, m;

    public PrimMST(WeightedGraph graph) {
        this.graph = graph;
        this.n = graph.V();
        this.m = graph.E();
        ipq = new IndexMinHeap2<>(m);
        edgeTo = new Edge[n];
        marked = new boolean[n];
        Arrays.fill(marked, false);
        mst = new LinkedList<>();

        // Prim
        visit(0);
        while (!ipq.isEmpty()) {
            // 使用最小索引堆找出已经访问的边中权值最小的边
            // 最小索引堆中存储的是点的索引, 通过点的索引找到相对应的边
            int v = ipq.extractMinIndex();
            assert (edgeTo[v] != null);
            mst.add(edgeTo[v]);
            mstWeight += edgeTo[v].wt().doubleValue();
            visit(v);
        }
    }

    private void visit(int i) {
        assert !marked[i];
        marked[i] = true;
        // 将和节点v相连接的所有未访问的边放入最小堆中
        for (Object item : graph.adj(i)) {
            Edge<Weight> edge = (Edge<Weight>) item;
            int w = edge.other(i);
            if (!marked[w])
                // 如果从没有考虑过这个端点, 直接将这个端点和与之相连接的边加入索引堆
                if (edgeTo[w] == null) {
                    edgeTo[w] = edge;
                    ipq.insert(w, edge.wt());
                }
                // 如果曾经考虑这个端点, 但现在的边比之前考虑的边更短, 则进行替换
                else if (edge.wt().compareTo(edgeTo[w].wt()) < 0) {
                    edgeTo[w] = edge;
                    ipq.change(w, edge.wt());
                }
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

    // 测试 Prim
    public static void main(String[] args) {

        String filename = "F:\\testG1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Prim MST
        System.out.println("Test Prim MST:");
        PrimMST<Double> primMST = new PrimMST<Double>(g);
        LinkedList<Edge<Double>> mst = primMST.mstEdges();
        for (Edge edge : mst) {
            System.out.println(edge);
        }
        System.out.println("The MST weight is: " + primMST.result());

        System.out.println();
    }
}
