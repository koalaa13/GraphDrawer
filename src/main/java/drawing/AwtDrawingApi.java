package drawing;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class AwtDrawingApi implements DrawingApi {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private final Graphics2D graphics2D;

    public AwtDrawingApi() {
        Frame frame = new Frame();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setTitle("Graph drawer");
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        graphics2D = (Graphics2D) frame.getGraphics();
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
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fill(new Ellipse2D.Float(x - r, y - r, 2 * r, 2 * r));
    }

    @Override
    public void drawLine(long x1, long y1, long x2, long y2) {
        graphics2D.setPaint(Color.BLACK);
        graphics2D.draw(new Line2D.Float(x1, y1, x2, y2));
    }
}
