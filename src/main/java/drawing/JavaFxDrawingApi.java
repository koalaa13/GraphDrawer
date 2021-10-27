package drawing;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JavaFxDrawingApi extends Application implements DrawingApi {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static GraphicsContext graphicsContext;

    public JavaFxDrawingApi() {
        if (graphicsContext == null) {
            Canvas canvas = new Canvas(WIDTH, HEIGHT);
            graphicsContext = canvas.getGraphicsContext2D();
        }
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Graph drawer");
        Group root = new Group();
        root.getChildren().add(graphicsContext.getCanvas());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void run() {
        Thread thread = new Thread(JavaFxDrawingApi::launch);
        thread.start();
    }

    @Override
    public long getDrawingAreaWidth() {
        return WIDTH;
    }

    @Override
    public long getDrawingAreaHeight() {
        return HEIGHT;
    }

    @Override
    public void drawCircle(long x, long y, long r) {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillOval(x - r, y - r, 2 * r, 2 * r);
    }

    @Override
    public void drawLine(long x1, long y1, long x2, long y2) {
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.strokeLine(x1, y1, x2, y2);
    }
}
