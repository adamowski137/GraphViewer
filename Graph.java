import java.io.*;
import java.util.*;

public class Graph {

    public static void SaveGraphToFile(Graph graph, String path) throws IOException {
        FileWriter writer = new FileWriter(path);
        writer.write(graph.VertexCount + "\r");
        writer.write(graph.EdgeCount + "\r");
        for (var v : graph.Vertices.values())
        {
            writer.write(v.ID + " " + v.Content + "\r");
        }
        for(var v1 : graph.Edges.keySet())
        {
            for(var v2 : graph.Edges.get(v1))
            {
                writer.write(v1 + " " + v2 + "\r");
            }
        }
        writer.close();
    }
    public static Graph GraphFromFile(String path) throws FileNotFoundException {
        File input = new File(path);
        Scanner scanner = new Scanner(input);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Graph g = new Graph();
        for(int i = 0; i < n; i++)
        {
            int t = scanner.nextInt();
            g.AddVertex(scanner.next(), t);
        }
        for(int i = 0; i < m; i++)
        {
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            g.AddEdge(v1, v2);
        }
        return g;
    }
    public final HashMap<Integer, Vertex>  Vertices;
    public final HashMap<Integer, ArrayList<Integer>> Edges;
    public Integer VertexCount;
    public int maxId;
    public Integer EdgeCount;
    public void AddEdge(Integer from, Integer to)
    {
        EdgeCount++;
        Edges.get(from).add(to);
    }
    public void AddVertex(String content, Integer id) {
        maxId++;
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
