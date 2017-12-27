package Algo.Graph;

import java.util.Arrays;

public class Components {
    private Graph graph;  // 图的引用
    private boolean[] visited;  // 记录dfs的过程中节点是否被访问
    private int ccount;  // 记录联通分量个数
    private int[] id; // 每个节点所对应的联通分量标记

    public Components(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.V()];
        id = new int[graph.V()];
        ccount = 0;
        Arrays.fill(visited, false);
        Arrays.fill(id, -1);

        for (int i = 0; i < graph.V(); i++) {
            if (!visited[i]) {
                dfs(i);
                ccount++;
            }
        }
    }

    private void dfs(int i) {
        visited[i] = true;
        id[i] = ccount;
        for (Integer j : graph.adj(i)) {
            if (!visited[j]) {
                dfs(j);
            }
        }
    }

    // 返回图的联通分量个数
    int count() {
        return ccount;
    }

    // 查询点v和点w是否联通
    boolean isConnected(int v, int w) {
        assert v >= 0 && v < graph.V();
        assert w >= 0 && w < graph.V();
        return id[v] == id[w];
    }

    public static void main(String[] args) {

        // TestG1.txt
        String filename1 = "F:\\testG1.txt";
        SparseGraph g1 = new SparseGraph(13, false);
        ReadGraph readGraph1 = new ReadGraph(g1, filename1);
        Components component1 = new Components(g1);
        System.out.println("TestG1.txt, Component Count: " + component1.count());
        System.out.println();

        // TestG2.txt
        String filename2 = "F:\\testG2.txt";
        SparseGraph g2 = new SparseGraph(6, false);
        ReadGraph readGraph2 = new ReadGraph(g2, filename2);
        Components component2 = new Components(g2);
        System.out.println("TestG2.txt, Component Count: " + component2.count());
    }
}
