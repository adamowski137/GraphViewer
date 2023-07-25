import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class DisplayManager implements GraphListener {
    public JFrame frame;
    private JPanel mainPanel;
    private JPanel graphPanel;
    private JPanel controlPanel;
    private JButton addVertexButton;
    private JButton addEdgeButton;
    private JButton refreshButton;
    private JButton saveButton;
    ArrayList<VertexComponent> vertices;
    ArrayList<EdgeComponent> edges;
    DisplayListener displayListener;

    public DisplayManager(GraphManager g) {
        frame = new JFrame("Graph viewer");
        mainPanel = new JPanel();
        graphPanel = new JPanel();
        controlPanel = new JPanel();
        addEdgeButton = new JButton("Add Edge");
        addVertexButton = new JButton("Add Vertex");
        refreshButton = new JButton("Refresh");
        saveButton = new JButton("Save");

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.WindowWidth, Constants.WindowHeight);
        frame.setVisible(true);

        mainPanel.setLayout(new BorderLayout());

        g.setGraphListener(this);
        setDisplayListener(g);

        graphPanel.setLayout(null); // Set the layout manager to null

        mainPanel.add(graphPanel, BorderLayout.CENTER); // Add graphPanel to the center of the mainPanel
        mainPanel.add(controlPanel, BorderLayout.SOUTH); // Add controlPanel to the south of the mainPanel

        controlPanel.add(addEdgeButton);
        controlPanel.add(addVertexButton);
        controlPanel.add(refreshButton);
        controlPanel.add(saveButton);

        DrawGraph();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    displayListener.SaveGraph("outputGraph.txt");
                }
                catch (IOException ioException)
                {

                };
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphPanel.revalidate();
                graphPanel.repaint();
            }
        });

        addEdgeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(frame, "Add new edge");
                dialog.setBounds(200, 200, 400, 200);
                JPanel dialogPanel = new JPanel();
                dialog.setContentPane(dialogPanel);
                JComboBox<VertexComponent> from = new JComboBox<>();
                JComboBox<VertexComponent> to = new JComboBox<>();

                for(var v : vertices)
                {
                    from.addItem(v);
                    to.addItem(v);
                }
                dialogPanel.add(from);
                dialogPanel.add(to);
                JButton accept = new JButton("Accept");
                accept.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        VertexComponent f = (VertexComponent) from.getSelectedItem();
                        VertexComponent t = (VertexComponent) to.getSelectedItem();
                        displayListener.AddEdge(f.VertexId, t.VertexId);
                        dialog.dispose();
                    }
                });
                dialogPanel.add(accept);
                dialog.setVisible(true);
            }


        });

        addVertexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(frame, "Add new vertex");
                dialog.setBounds(200, 200, 200, 200);
                JPanel dialogPanel = new JPanel();
                JTextArea input = new JTextArea();
                dialogPanel.add(input);
                JButton accept = new JButton("Accept");
                accept.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        displayListener.AddVertex(input.getText());
                        dialog.dispose();
                    }
                });
                dialogPanel.add(accept);
                dialog.setContentPane(dialogPanel);
                dialog.setVisible(true);
            }
        });
    }

    public void setDisplayListener(DisplayListener d) {
        displayListener = d;
    }

    void DrawVertices() {
        vertices = new ArrayList<>();
        Random r = new Random();
        int graphWidth = Math.max(frame.getWidth() - 100, 0);
        int graphHeight = Math.max(frame.getHeight() - 100, 0);
        for (var v : displayListener.GetVertices()) {
            int x = r.nextInt(graphWidth - v.getWidth());
            int y = r.nextInt(graphHeight - v.getHeight());
            v.setBounds(x, y, Constants.NodeRadius, Constants.NodeRadius);
            graphPanel.add(v);
            vertices.add(v);
        }
    }

    void DrawEdges() {
        edges = new ArrayList<>();
        for (var v : displayListener.GetEdges()) {
            VertexComponent v1 = vertices.stream().filter(x -> x.VertexId == v.First).findAny().orElse(null);
            VertexComponent v2 = vertices.stream().filter(x -> x.VertexId == v.Second).findAny().orElse(null);
            EdgeComponent edge = new EdgeComponent(v1, v2);
            edge.setBounds(0, 0, 100000, 1000000);
            graphPanel.add(edge);
            edges.add(edge);
        }
    }

    public void DrawGraph() {
        mainPanel.remove(graphPanel);
        graphPanel = new JPanel();
        graphPanel.setLayout(null);
        mainPanel.add(graphPanel, BorderLayout.CENTER);
        DrawVertices();
        DrawEdges();
        graphPanel.validate();
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
