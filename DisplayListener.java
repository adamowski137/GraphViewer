import java.util.ArrayList;

public interface DisplayListener {
    void DeleteEdge(Integer from, Integer to);
    void DeleteVertex(Integer Id);
    void AddVertex(String content);
    void AddEdge(Integer from, Integer to);
    ArrayList<VertexComponent> GetVertices();
    ArrayList<Pair<Integer, Integer>> GetEdges();
}
