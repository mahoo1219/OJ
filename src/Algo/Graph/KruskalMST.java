package Algo.Graph;

import Algo.UnionFind.UnionFind;

import java.util.LinkedList;

public class KruskalMST<Weight extends Number & Comparable> {
    private LinkedList<Edge<Weight>> mst; // 最小生成树所包含的所有边
    private double mstWeight; // 最小生成树的权值
    private WeightedGraph graph;
    private MinHeap<Edge<Weight>> minHeap;
    private int n, m;

    public KruskalMST(WeightedGraph graph) {
        this.graph = graph;
        this.n = graph.V();
        this.m = graph.E();
        mst = new LinkedList<>();
        minHeap = new MinHeap<Edge<Weight>>(m);
        for (int i = 0; i < n; i++) {
            for (Object item : graph.adj(i)) {
                Edge<Weight> e = (Edge<Weight>) item;
                if (e.v() <= e.w())
                    minHeap.insert(e);
            }
        }
        kruskal();
    }

    public void kruskal() {
        UnionFind uf = new UnionFind(n);
        while (!minHeap.isEmpty() && mst.size() < n - 1) {
            // 从最小堆中依次从小到大取出所有的边
            Edge<Weight> e = minHeap.extract();
            // 如果该边的两个端点是联通的, 说明加入这条边将产生环, 扔掉这条边
            if (uf.isConnected(e.v(), e.w()))
                continue;

            // 否则, 将这条边添加进最小生成树, 同时标记边的两个端点联通
            mst.add(e);
            uf.unionElements(e.v(), e.w());
            mstWeight += e.wt().doubleValue();
        }
    }

    // 返回最小生成树的所有边
    LinkedList<Edge<Weight>> mstEdges() {
        return mst;
    }

    // 返回最小生成树的权值
    Number result() {
        return mstWeight;
    }


    // 测试 Kruskal
    public static void main(String[] args) {

        String filename = "F:\\testG1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Kruskal
        System.out.println("Test Kruskal:");
        KruskalMST<Double> kruskalMST = new KruskalMST<Double>(g);
        LinkedList<Edge<Double>> mst = kruskalMST.mstEdges();
        for (Edge edge : mst) {
            System.out.println(edge + " ");
        }
        System.out.println("The MST weight is: " + kruskalMST.result());

        System.out.println();
    }
}
