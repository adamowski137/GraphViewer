import java.util.ArrayList;

public class GraphManager implements DisplayListener {
    private Graph graph;

    public GraphManager()
    {
        graph = new Graph();
        graph.AddVertex("munio");
        graph.AddVertex("munio2");
        graph.AddVertex("munio2");
        graph.AddVertex("munio2");
        graph.AddVertex("munio2");
        graph.AddVertex("munio2");
    }
    @Override
    public void DeleteEdge(Integer from, Integer to) {
        graph.DeleteEdge(from, to);
    }

    @Override
    public void DeleteVertex(Integer Id) {
        graph.DeleteVertex(Id);
    }

    @Override
    public void AddVertex(String content) {
        graph.AddVertex(content);
    }

    @Override
    public void AddEdge(Integer from, Integer to) {
        graph.AddEdge(from, to);
    }

    public ArrayList<VertexComponent> GetVertices()
    {
        ArrayList<VertexComponent> tmp = new ArrayList<>();
        for(var vert : graph.Vertices.values())
        {
            tmp.add(new VertexComponent(vert.Content, vert.ID));
        }
        return tmp;
    }

    public ArrayList<Pair<Integer, Integer>> GetEdges()
    {
        ArrayList<Pair<Integer, Integer>> tmp = new ArrayList<>();
        for(var v : graph.Edges.keySet())
        {
            for (var v2 : graph.Edges.get(v))
            {
                tmp.add(new Pair<>(v, v2));
            }
        }
        return tmp;
    }
}
