package CSP;
import java.util.*;

public class Graph {
    public int V;
    public List<List<Integer>> adjList;

    public Graph(int V) {
        this.V = V;
        adjList = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    private boolean isSafe(int v, int[] color, int c) {
        for (int u : adjList.get(v)) {
            if (color[u] == c) {
                return false;
            }
        }
        return true;
    }

    private boolean graphColoringUtil(int m, int[] color, int v) {
        if (v == V) {
            return true;
        }

        for (int c = 1; c <= m; c++) {
            if (isSafe(v, color, c)) {
                color[v] = c;
                if (graphColoringUtil(m, color, v + 1)) {
                    return true;
                }
                color[v] = 0;
            }
        }
        return false;
    }

    public boolean graphColoring(int m) {
        int[] color = new int[V];
        Arrays.fill(color, 0);

        if (!graphColoringUtil(m, color, 0)) {
            System.out.println("Solution does not exist");
            return false;
        }

        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + " ---> Color " + color[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        int V = 5;
        Graph graph = new Graph(V);
        graph.addEdge(0, 1);
       
        graph.addEdge(1, 2);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(0, 4);
         //graph.addEdge(1, 4);
       

        int m = 3; // Number of colors
        graph.graphColoring(m);
    }
}










