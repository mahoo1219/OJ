package Algo.Graph;

public class Edge<Weight extends Number & Comparable> implements Comparable<Edge> {
    private int a, b;
    private Weight weight;

    public Edge(int a, int b, Weight weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    public Edge(Edge<Weight> edge) {
        this(edge.a, edge.b, edge.weight);
    }

    public int v() {
        return a;
    } // 返回第一个顶点

    public int w() {
        return b;
    } // 返回第二个顶点

    public Weight wt() {
        return weight;
    }    // 返回权值

    // 给定一个顶点, 返回另一个顶点
    public int other(int s) {
        assert s == a || s == b;
        return s == a ? b : a;
    }

    // 输出边的信息
    public String toString() {
        return "" + a + "-" + b + ": " + weight;
    }

    // 边之间的比较
    @Override
    public int compareTo(Edge that) {
        if (weight.compareTo(that.wt()) < 0)
            return -1;
        else if (weight.compareTo(that.wt()) > 0)
            return +1;
        else
            return 0;
    }
}
