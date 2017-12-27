package Algo.Graph;

import java.util.Arrays;
import java.util.LinkedList;

public class Path {
    private Graph graph; // 图的引用
    private boolean[] visited;
    private int[] from; // 记录路径, from[i]表示查找的路径上 i 的上一个节点(对于每一个节点,只是上一个节点)
    private int n;  // 顶点数
    private int start; // 起始点

    public Path(Graph graph, int start) {
        this.graph = graph;
        this.n = graph.V();
        assert start >= 0 && start < n;

        this.start = start;
        visited = new boolean[n];
        from = new int[n];
        Arrays.fill(visited, false);
        Arrays.fill(from, -1);

        dfs(start);
    }

    // 查询从s点到w点是否有路径
    public boolean hasPath(int w) {
        assert w >= 0 && w < n;
        return visited[w]; // 这里visited 表示从起始点开始的访问路径，可能没有遍历所有的顶点
    }

    // 查询从s点到w点的路径, 存放在vec中
    public LinkedList<Integer> path(int w) {
        assert hasPath(w);
        int p = w;
        LinkedList<Integer> path = new LinkedList<>();
        while (p != -1) {
            path.addFirst(p);
            p = from[p];
        }
        return path;
    }

    private void dfs(int s) {
        visited[s] = true;
        for (Integer i : graph.adj(s)) {
            if (!visited[i]) {
                dfs(i);
                from[i] = s;
            }
        }
    }

    void showPath(int w) {
        assert hasPath(w);
        for (Integer i : path(w)) {
            System.out.print(i);
            System.out.print(" -> ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String filename = "F:\\testG.txt";
        SparseGraph g = new SparseGraph(7, false);
        ReadGraph readGraph = new ReadGraph(g, filename);
        g.show();
        System.out.println();

        Path path = new Path(g, 0);
        System.out.println("Path from 0 to 6 : ");
        path.showPath(6);
    }
}
