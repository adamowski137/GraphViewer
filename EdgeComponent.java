import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Random;

public class EdgeComponent extends JComponent {
    private VertexComponent source;
    private VertexComponent target;
    private Color color;
    public EdgeComponent(VertexComponent source, VertexComponent target) {
        this.source = source;
        this.target = target;
        Random r = new Random();
        color = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
        setOpaque(false);

        // Add a component listener to handle vertex movement
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                repaint(); // Repaint the edge component when vertices move
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int x1 = source.getX() + source.getWidth() / 2;
        int y1 = source.getY() + source.getHeight() / 2;
        int x2 = target.getX() + target.getWidth() / 2;
        int y2 = target.getY() + target.getHeight() / 2;

        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(x1, y1, x2, y2);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1, 1);
    }
}
