package graph;

import drawing.DrawingApi;

public abstract class Graph {
    protected final DrawingApi drawingApi;
    protected final int n;

    public Graph(DrawingApi drawingApi, int n) {
        this.drawingApi = drawingApi;
        this.n = n;
    }

    public abstract void drawGraph();

    private long getCircleXPos(long width, long height, int v) {
        long wC = width / 2;
        long r = Math.min(width, height) / 4;
        double angle = (2 * v * Math.PI) / n;
        return wC + (long) (r * Math.cos(angle));
    }

    private long getCircleYPos(long width, long height, int v) {
        long hC = height / 2;
        long r = Math.min(width, height) / 4;
        double angle = (2 * v * Math.PI) / n;
        return hC + (int) (r * Math.sin(angle));
    }

    protected void drawCircles() {
        long width = drawingApi.getDrawingAreaWidth();
        long height = drawingApi.getDrawingAreaHeight();
        long circleRadius = Math.min(width, height) / (8L * n);
        for (int i = 0; i < n; ++i) {
            drawingApi.drawCircle(
                    getCircleXPos(width, height, i),
                    getCircleYPos(width, height, i),
                    circleRadius);
        }
    }

    protected void drawEdge(int v, int u) {
        long width = drawingApi.getDrawingAreaWidth();
        long height = drawingApi.getDrawingAreaHeight();
        drawingApi.drawLine(getCircleXPos(width, height, v),
                getCircleYPos(width, height, v),
                getCircleXPos(width, height, u),
                getCircleYPos(width, height, u));
    }
}
