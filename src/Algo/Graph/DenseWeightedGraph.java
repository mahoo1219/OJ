package Algo.Graph;

import java.util.LinkedList;

public class DenseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph {
    private int n;
    private int m;
    private boolean directed;
    private Edge<Weight>[][] edges;

    public DenseWeightedGraph(int n, boolean directed) {
        assert n >= 0;
        this.n = n;
        this.m = 0;
        this.directed = directed;
        edges = new Edge[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                edges[i][j] = null;
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
        if (hasEdge(e.v(), e.w())) return;
        edges[e.v()][e.w()] = new Edge<Weight>(e);
        if (e.v() != e.w() && !directed) {
            edges[e.w()][e.v()] = new Edge(e.w(), e.v(), e.wt());
        }
        m++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        return edges[v][w] != null;
    }

    @Override
    public void show() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                if (edges[i][j] != null)
                    System.out.print(edges[i][j].wt() + "\t");
                else
                    System.out.print("NULL" + "\t");
            System.out.println();
        }
    }

    @Override
    public Iterable<Edge<Weight>> adj(int v) {
        assert v >= 0 && v < n;
        LinkedList<Edge<Weight>> adjV = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (edges[v][i] != null)
                adjV.add(edges[v][i]);
        }
        return adjV;
    }
}
