import java.util.*;

public class RatINMaze {
    public static int maze[][] = {
            { 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 10, 0, 0, 1, 0, 0, 1 },
            { 1, 1, 0, 1, 1, 0, 1, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 1, 1, 1, 1, 0, 1 },
            { 1, 0, 1, 0, 0, 0, 0, 1 },
            { 1, 0, 1, 0, 1, 1, -10, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1 }
    };
    static point start = new point(1, 1);
    static point end = new point(6, 6);

    public static void dfs(point cur, point[][] parent, boolean[][] visited) {
        if (visited[cur.x][cur.y] == true) {
            return;
        }
        visited[cur.x][cur.y] = true;
        if (cur.x - 1 >= 0) {
            if (maze[cur.x - 1][cur.y] != 1 && visited[cur.x - 1][cur.y] != true) {
                parent[cur.x - 1][cur.y] = cur;
                dfs(new point(cur.x - 1, cur.y), parent, visited);
            }
        }
        if (cur.x + 1 < maze.length) {
            if (maze[cur.x + 1][cur.y] != 1 && visited[cur.x + 1][cur.y] != true) {
                parent[cur.x + 1][cur.y] = cur;
                dfs(new point(cur.x + 1, cur.y), parent, visited);
            }
        }
        if (cur.y - 1 >= 0) {
            if (maze[cur.x][cur.y - 1] != 1 && visited[cur.x][cur.y - 1] != true) {
                parent[cur.x][cur.y - 1] = cur;
                dfs(new point(cur.x, cur.y - 1), parent, visited);
            }
        }
        if (cur.y + 1 < maze.length) {
            if (maze[cur.x][cur.y + 1] != 1 && visited[cur.x][cur.y + 1] != true) {
                parent[cur.x][cur.y + 1] = cur;
                dfs(new point(cur.x, cur.y + 1), parent, visited);
            }
        }
    }

    public static void main(String args[]) {
        boolean visited[][] = new boolean[maze.length][maze.length];
        point parent[][] = new point[maze.length][maze.length];
        for (boolean i[] : visited) {
            Arrays.fill(i, false);
        }
        for (point i[] : parent) {
            Arrays.fill(i, null);
        }
        dfs(start, parent, visited);
        if (parent[end.x][end.y] != null) {
            System.out.println("Path Found");
        }
        Stack<point> s = new Stack<point>();
        point p = end;
        while (true) {
            s.push(p);
            if (start.x == p.x && start.y == p.y) {
                break;
            }
            p = parent[p.x][p.y];
        }
        while (!s.isEmpty()) {
            p = s.pop();
            System.out.print("(" + p.x + "," + p.y + ")->");
        }
    }
}

class point {
    int x;
    int y;

    point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}