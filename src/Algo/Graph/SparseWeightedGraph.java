package Algo.Graph;

import java.util.Iterator;
import java.util.LinkedList;

public class SparseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph {
    private int n;
    private int m;
    private boolean directed;
    private LinkedList<Edge<Weight>>[] edges;

    public SparseWeightedGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        edges = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new LinkedList<>();
        }
    }

    @Override
    public int V() {
        return n;
    }

    @Override
    public int E() {
        return m;
    }

    @Override
    public void addEdge(Edge e) {
        assert e.v() >= 0 && e.v() < n;
        assert e.w() >= 0 && e.w() < n;

        edges[e.v()].add(e);
        if (e.v() != e.w() && !directed)
            edges[e.w()].add(new Edge(e.w(), e.v(), e.wt()));
        m++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        Iterator<Edge<Weight>> it = edges[v].iterator();
        while (it.hasNext()) {
            Edge e = it.next();
            if (e.other(v) == w) return true;
        }
        return false;
    }

    @Override
    public void show() {
        for (int i = 0; i < n; i++) {
            System.out.print("vertex " + i + ":\t");
            Iterator<Edge<Weight>> it = edges[i].iterator();
            while (it.hasNext()) {
                Edge e = it.next();
                System.out.print("( to:" + e.other(i) + ",wt:" + e.wt() + ")\t");
            }
            System.out.println();
        }
    }

    @Override
    public Iterable<Edge<Weight>> adj(int v) {
        assert v >= 0 && v < n;
        return edges[v];
    }
}
