import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.security.PublicKey;

public class VertexComponent extends JButton {
    private boolean mouseOver = false;
    private boolean mousePressed = false;
    private Point dragOffset = new Point();

    public boolean displayed;
    public Integer VertexId;

    public VertexComponent(String text, Integer id){
        super(text);

        VertexId = id;
        displayed = false;

        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);

        MouseAdapter mouseListener = new MouseAdapter(){

            @Override
            public void mousePressed(MouseEvent me){
                if(contains(me.getX(), me.getY())){
                    mousePressed = true;
                    dragOffset = me.getPoint();
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent me){
                mousePressed = false;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent me){
                mouseOver = false;
                mousePressed = false;
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent me){
                mouseOver = contains(me.getX(), me.getY());
                repaint();
            }
            @Override
            public void mouseDragged(MouseEvent me) {
                if (mousePressed) {
                    Point location = getLocation();
                    int x = location.x + me.getX() - dragOffset.x;
                    int y = location.y + me.getY() - dragOffset.y;
                    setLocation(x, y);
                    repaint();
                }
            }
        };
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }

    private int getDiameter(){
        int diameter = Math.min(getWidth(), getHeight());
        return diameter;
    }

    @Override
    public Dimension getPreferredSize(){
        FontMetrics metrics = getGraphics().getFontMetrics(getFont());
        int minDiameter = 20 + Math.max(metrics.stringWidth(getText()), metrics.getHeight());
        return new Dimension(minDiameter, minDiameter);
    }

    @Override
    public boolean contains(int x, int y){
        int radius = getDiameter()/2;
        return Point2D.distance(x, y, getWidth()/2, getHeight()/2) < radius;
    }

    @Override
    public void paintComponent(Graphics g){

        int diameter = getDiameter();
        int radius = diameter/2;

        if(mousePressed){
            g.setColor(Color.LIGHT_GRAY);
        }
        else{
            g.setColor(Color.WHITE);
        }
        g.fillOval(getWidth()/2 - radius, getHeight()/2 - radius, diameter, diameter);

        if(mouseOver){
            g.setColor(Color.BLUE);
        }
        else{
            g.setColor(Color.BLACK);
        }
        g.drawOval(getWidth()/2 - radius, getHeight()/2 - radius, diameter, diameter);

        g.setColor(Color.BLACK);
        g.setFont(getFont());
        FontMetrics metrics = g.getFontMetrics(getFont());
        int stringWidth = metrics.stringWidth(getText());
        int stringHeight = metrics.getHeight();
        g.drawString(getText(), getWidth()/2 - stringWidth/2, getHeight()/2 + stringHeight/4);
    }

    @Override
    public String toString() {
        return  getText();
    }
}
