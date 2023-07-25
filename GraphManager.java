import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class GraphManager implements DisplayListener {
    private Graph graph;
    private GraphListener graphListener;
    public GraphManager()
    {
        graph = new Graph();
    }

    public GraphManager(Graph g) throws IOException {
        graph = g;
    }
    @Override
    public  void SaveGraph(String file) throws IOException {
        Graph.SaveGraphToFile(graph, file);
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

        graph.AddVertex(content, graph.maxId);
        graphListener.DrawGraph();
    }

    @Override
    public void AddEdge(Integer from, Integer to) {

        graph.AddEdge(from, to);
        graphListener.DrawGraph();
    }

    public void setGraphListener(GraphListener graphListener) {
        this.graphListener = graphListener;
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
