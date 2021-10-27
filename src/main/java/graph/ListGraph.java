package graph;

import drawing.DrawingApi;
import javafx.util.Pair;

import java.util.List;

public class ListGraph extends Graph {
    private final List<Pair<Integer, Integer>> edges;

    public ListGraph(DrawingApi drawingApi, int n, List<Pair<Integer, Integer>> edges) {
        super(drawingApi, n);
        this.edges = edges;
        if (n == 0) {
            throw new RuntimeException("Graph should be not empty");
        }
        for (Pair<Integer, Integer> e : edges) {
            if (e.getKey() < 1 || e.getKey() > n || e.getValue() < 1 || e.getValue() > n) {
                throw new RuntimeException("Incorrect edge " + e.getKey() + ' ' + e.getValue());
            }
        }
    }

    @Override
    public void drawGraph() {
        drawCircles();
        for (Pair<Integer, Integer> e : edges) {
            drawEdge(e.getValue() - 1, e.getKey() - 1);
        }
    }
}
