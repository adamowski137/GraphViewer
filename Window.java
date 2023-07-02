import javax.swing.*;
import java.awt.*;
import java.sql.Ref;

public class Window {
    public JFrame frame;
    public JPanel panel;

    public Window()
    {
        frame = new JFrame("Graph viewer");
        panel = new JPanel();
        panel.setLayout(null);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.WindowWidth, Constants.WindowHeight);
        frame.setVisible(true);
    }

    public void AddVertex(VertexComponent v)
    {
        panel.add(v);
        Refresh();
    }

    public void AddEdge(VertexComponent v1, VertexComponent v2)
    {
        EdgeComponent edge = new EdgeComponent(v1, v2);
        edge.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        panel.add(edge);
        Refresh();
    }
    public void Refresh()
    {
        panel.revalidate();
        panel.repaint();
    }
}
