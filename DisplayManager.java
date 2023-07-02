import java.util.ArrayList;
import java.util.Random;

public class DisplayManager {
    private Window window;
    ArrayList<VertexComponent> vertices;
    ArrayList<EdgeComponent> edges;
    DisplayListener displayListener;
    public DisplayManager()
    {
        window = new Window();
        displayListener = new GraphManager();
        DrawVertices();
        DrawEdges();
    }
    void DrawVertices()
    {
        vertices = new ArrayList<>();
        Random r = new Random();
        for(var v : displayListener.GetVertices())
        {
            v.setBounds(r.nextInt() % 100 + 100, r.nextInt()% 100 + 100, 100, 100);
            window.AddVertex(v);
            vertices.add(v);
        }
        window.Refresh();
    }
    void DrawEdges()
    {
        edges = new ArrayList<>();
        for(var v : displayListener.GetEdges())
        {
            VertexComponent v1 = vertices.stream().filter(x -> x.VertexId == v.First).findAny().orElse(null);
            VertexComponent v2 = vertices.stream().filter(x -> x.VertexId == v.Second).findAny().orElse(null);
            EdgeComponent edge = new EdgeComponent(v1, v2);
            edges.add(edge);
        }
        window.Refresh();
    }
    
}
