package Algo.Graph;

public class Dijkstra<Weight extends Number & Comparable> {
    private WeightedGraph graph;
    private int start;
    private Number[] distTo;
    private boolean[] marked;
    private Edge<Weight>[] from;

    public Dijkstra(WeightedGraph graph, int s) {
        this.graph = graph;
        this.start = s;
    }
}
