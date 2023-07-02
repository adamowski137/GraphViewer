import java.util.*;

public class Graph {
    public final HashMap<Integer, Vertex>  Vertices;
    public final HashMap<Integer, ArrayList<Integer>> Edges;
    public Integer VertexCount;
    public Integer EdgeCount;
    public void AddEdge(Integer from, Integer to)
    {
        EdgeCount++;
        Edges.get(from).add(to);
    }
    public void AddVertex(String content) {
        Random random = new Random();
        Integer id;
        do {
            id = random.nextInt();
        }
        while (Vertices.containsKey(id));
        Vertex v = new Vertex(id, content);
        VertexCount++;
        Vertices.put(id, v);
        Edges.put(id, new ArrayList<>());
    }
    public void DeleteVertex(Integer id)
    {
        VertexCount--;
        Vertices.remove(id);
        Edges.remove(id);
        for(var v : Edges.values())
        {
            v.remove(id);
        }
    }

    public void DeleteEdge(Integer from, Integer to)
    {
        Edges.get(from).remove(to);
    }
    public Graph()
    {
        VertexCount = 0;
        EdgeCount = 0;
        Vertices = new HashMap<>();
        Edges = new HashMap<>();
    }
}
