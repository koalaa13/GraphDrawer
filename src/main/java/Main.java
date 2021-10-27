import drawing.AwtDrawingApi;
import drawing.DrawingApi;
import drawing.JavaFxDrawingApi;
import graph.Graph;
import graph.ListGraph;
import graph.MatrixGraph;
import javafx.application.Application;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String USAGE_INFO = "First argument should be drawing api type (awt or fx), second argument should be graph input type (matrix or list)";

    public static void main(String[] args) {
        if (args == null || args.length != 2 || args[0] == null || args[1] == null) {
            System.err.println(USAGE_INFO);
            return;
        }
        Scanner scanner = new Scanner(System.in);
        DrawingApi drawingApi = getApi(args[0]);
        Graph graph = getGraph(args[1], drawingApi, scanner);

        graph.drawGraph();
        if (drawingApi instanceof JavaFxDrawingApi) {
            JavaFxDrawingApi.run();
        }
    }

    private static DrawingApi getApi(String api) {
        if ("awt".equals(api)) {
            return new AwtDrawingApi();
        }
        if ("fx".equals(api)) {
            return new JavaFxDrawingApi();
        }
        throw new RuntimeException("Unrecognized drawing api type " + api);
    }

    private static Graph getGraph(String graphType, DrawingApi drawingApi, Scanner scanner) {
        if ("matrix".equals(graphType)) {
            return getMatrixGraph(drawingApi, scanner);
        }
        if ("list".equals(graphType)) {
            return getListGraph(drawingApi, scanner);
        }
        throw new RuntimeException("Unrecognized graph type " + graphType);
    }

    private static MatrixGraph getMatrixGraph(DrawingApi drawingApi, Scanner scanner) {
        System.out.println("Enter count of vertexes");
        int n = scanner.nextInt();
        System.out.println("Enter matrix with size n x n");
        List<List<Boolean>> matrix = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            List<Boolean> row = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                int x = scanner.nextInt();
                if (x == 1) {
                    row.add(true);
                } else {
                    row.add(false);
                }
            }
            matrix.add(row);
        }
        return new MatrixGraph(drawingApi, matrix);
    }

    private static ListGraph getListGraph(DrawingApi drawingApi, Scanner scanner) {
        System.out.println("Enter count of vertexes and count of edges");
        int n = scanner.nextInt(), m = scanner.nextInt();
        System.out.println("Enter " + m + " edges");
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            int v = scanner.nextInt(), u = scanner.nextInt();
            edges.add(new Pair<>(v, u));
        }
        return new ListGraph(drawingApi, n, edges);
    }
}
