package graph;

import drawing.DrawingApi;

import java.util.List;

public class MatrixGraph extends Graph {
    private final List<List<Boolean>> matrix;

    public MatrixGraph(DrawingApi drawingApi, List<List<Boolean>> matrix) {
        super(drawingApi, matrix.size());
        this.matrix = matrix;
        if (n == 0) {
            throw new RuntimeException("Matrix should be not empty");
        }
        int sz = matrix.get(0).size();
        for (int i = 1; i < n; ++i) {
            if (matrix.get(i).size() != sz) {
                throw new RuntimeException("Matrix is not square");
            }
        }
    }


    @Override
    public void drawGraph() {
        drawCircles();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (matrix.get(i).get(j)) {
                    drawEdge(i, j);
                }
            }
        }
    }
}
