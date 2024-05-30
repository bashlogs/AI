import java.util.*;

class point {
    int x, y;

    point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class IDDFSRATINMAZE {
    public static int[][] maze = {
            { 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 10, 0, 0, 1, 0, 0, 1 },
            { 1, 1, 0, 1, 1, 0, 1, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 1, 1, 1, 1, 0, 1 },
            { 1, 0, 1, 0, 0, 0, 0, 1 },
            { 1, 0, 1, 0, 1, 1, -10, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1 }
    };

    public static point start = new point(1, 1);
    public static point end = new point(6, 6);

    public static void Dfs(point node, int depthLimit, boolean[][] visited, point[][] parent) {
        if (visited[node.x][node.y] == true || depthLimit == 0) {
            return;
        }
        visited[node.x][node.y] = true;
        if (node.x + 1 < maze.length) {
            if (maze[node.x + 1][node.y] != 1 && visited[node.x + 1][node.y] != true) {
                parent[node.x + 1][node.y] = node;
                Dfs(new point(node.x + 1, node.y), depthLimit - 1, visited, parent);
            }
        }
        if (node.x - 1 > -1) {
            if (maze[node.x - 1][node.y] != 1 && visited[node.x - 1][node.y] != true) {
                parent[node.x - 1][node.y] = node;
                Dfs(new point(node.x - 1, node.y), depthLimit - 1, visited, parent);
            }
        }
        if (node.y + 1 < maze.length) {
            if (maze[node.x][node.y + 1] != 1 && visited[node.x][node.y + 1] != true) {
                parent[node.x][node.y + 1] = node;
                Dfs(new point(node.x, node.y + 1), depthLimit - 1, visited, parent);
            }
        }
        if (node.y - 1 > -1) {
            if (maze[node.x][node.y - 1] != 1 && visited[node.x][node.y - 1] != true) {
                parent[node.x][node.y - 1] = node;
                Dfs(new point(node.x, node.y - 1), depthLimit - 1, visited, parent);
            }
        }
    }

    public static void main(String args[]) {
        boolean visited[][] = new boolean[maze.length][maze.length];
        for (boolean i[] : visited) {
            Arrays.fill(i, false);
        }
        point parent[][] = new point[maze.length][maze.length];
        for (point i[] : parent) {
            Arrays.fill(i, null);
        }
        int depth = 1;
        while (true) {
            Dfs(start, depth, visited, parent);
            if (parent[end.x][end.y] != null) {
                break;
            }
            depth++;
            for (boolean i[] : visited) {
                Arrays.fill(i, false);
            }
        }
        if (parent[end.x][end.y] != null) {
            System.out.println("Path found at depth limit " + depth);
            Stack<point> s = new Stack<point>();
            point k = end;
            while (true) {
                s.push(k);
                if (k.x == start.x && k.y == start.y) {
                    break;
                }
                k = parent[k.x][k.y];
            }
            while (!s.isEmpty()) {
                k = s.pop();
                System.out.print("(" + k.x + "," + k.y + ")->");
            }
        }
    }
}