package Algo.Graph;

import java.util.Arrays;
import java.util.LinkedList;

public class ShortestPath {
    private Graph graph;
    private int start;
    private boolean[] visited;
    private int[] from; // 记录路径, from[i]表示查找的路径上i的上一个节点
    private int[] ord; // 记录路径中节点的次序。ord[i]表示i节点在路径中的次序。
    private int n;

    public ShortestPath(Graph graph, int start) {
        this.graph = graph;
        this.start = start;
        n = graph.V();
        visited = new boolean[n];
        from = new int[n];
        ord = new int[n];
        Arrays.fill(visited, false);
        Arrays.fill(from, -1);
        Arrays.fill(ord, -1);

        bfs(start);
    }

    // 无向图最短路径算法, 从s开始广度优先遍历整张图
    private void bfs(int start) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        ord[start] = 0;
        while (!queue.isEmpty()) {
            int q = queue.remove();
            for (Integer i : graph.adj(q)) {
                if (!visited[i]) {
                    visited[i] = true;
                    from[i] = q;
                    ord[i] = ord[q] + 1;
                    queue.add(i);
                }
            }

        }
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

    private void showPath(int w) {
        assert hasPath(w);
        for (Integer i : path(w)) {
            System.out.print(i);
            System.out.print(" -> ");
        }
        System.out.println();
    }


    // 查看从s点到w点的最短路径长度
    // 若从s到w不可达，返回-1
    public int length(int w) {
        assert w >= 0 && w < n;
        return ord[w];
    }

    // 测试无权图最短路径算法
    public static void main(String[] args) {

        String filename = "F:\\testG.txt";
        SparseGraph g = new SparseGraph(7, false);
        ReadGraph readGraph = new ReadGraph(g, filename);
        g.show();
        System.out.println();

        // 比较使用深度优先遍历和广度优先遍历获得路径的不同
        // 广度优先遍历获得的是无权图的最短路径
        ShortestPath bfs = new ShortestPath(g, 0);
        System.out.print("BFS : ");
        bfs.showPath(4);

        Path dfs = new Path(g, 0);
        System.out.print("DFS : ");
        dfs.showPath(4);
    }
}
