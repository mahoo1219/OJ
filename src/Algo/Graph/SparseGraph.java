package Algo.Graph;

import java.util.Iterator;
import java.util.LinkedList;

public class SparseGraph implements Graph {
    private int n;
    private int m;
    private boolean directed;
    private LinkedList<Integer>[] g;

    @SuppressWarnings("unchecked")
    public SparseGraph(int n, boolean directed) {
        assert n >= 0;
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = (LinkedList<Integer>[]) new LinkedList[n];
    }

    // 返回节点个数
    @Override
    public int V() {
        return n;
    }

    // 返回边的个数
    @Override
    public int E() {
        return m;
    }

    // 向图中添加一个边
    @Override
    public void addEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        //if (!hasEdge(v, w))
        if (g[v] == null)
            g[v] = new LinkedList<>();
        g[v].add(w);
        if (v != w && !directed) {
            if (g[w] == null)
                g[w] = new LinkedList<>();
            g[w].add(v);
        }
        m++;
    }

    // 验证图中是否有从v到w的边
    @Override
    public boolean hasEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        Iterator<Integer> it = g[v].iterator();
        while (it.hasNext()) {
            if (w == it.next())
                return true;
        }
        return false;
    }

    @Override
    public Iterable<Integer> adj(int v) {
        assert v >= 0 && v < n;
        return g[v];
    }

    @Override
    public void show() {
        for (int i = 0; i < n; i++) {
            System.out.print("vertex " + i + ":\t");
            for (int j = 0; j < g[i].size(); j++) {
                System.out.print(g[i].get(j) + "\t");
            }
            System.out.println();
        }
    }
}
