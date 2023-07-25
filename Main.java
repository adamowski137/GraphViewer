import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) throws IOException {
        GraphManager graphManager = new GraphManager(Graph.GraphFromFile("graph.txt"));
        DisplayManager displayManager = new DisplayManager(graphManager);
    }
}
