package Algo.Graph;

import java.util.LinkedList;

// 稠密图 - 邻接矩阵
public class DenseGraph implements Graph {
    private int n; // 节点数
    private int m; // 边数
    private boolean directed;  // 是否为有向图
    private int[][] g; // 图的具体数据

    public DenseGraph(int n, boolean directed) {
        assert n >= 0;
        this.n = n;
        this.m = 0; // 初始化没有任何边
        this.directed = directed;
        g = new int[n][n];
    }

    @Override
    public int V() {// 返回节点个数
        return n;
    }

    @Override
    public int E() { // 返回边的个数
        return m;
    }

    // 向图中添加一个边
    @Override
    public void addEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;

        if (hasEdge(v, w))
            return;
        g[v][w] = 1;
        if (!directed)
            g[w][v] = 1;
        m++;
    }

    // 验证图中是否有从v到w的边
    @Override
    public boolean hasEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        return g[v][w] != 0;
    }

    @Override
    public Iterable<Integer> adj(int v) {
        assert v >= 0 && v < n;
        LinkedList<Integer> adjV = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (g[v][i] != 0)
                adjV.add(i);
        }
        return adjV;
    }

    @Override
    public void show() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(g[i][j] + "\t");
            System.out.println();
        }
    }
}
