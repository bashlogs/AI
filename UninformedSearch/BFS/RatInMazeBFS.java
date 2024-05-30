
import java.util.*;

public class RatInMazeBFS {
    public static int maze[][] = {

            // 0 = no wall
            // 1= wall
            // 10 =rat
            // -10= destination
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

    public static void main(String[] args) {
        Queue<point> q = new LinkedList<point>();
        q.add(start);
        boolean[][] visited = new boolean[maze.length][maze.length];
        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i], false);
        }
        point[][] parent = new point[maze.length][maze.length];
        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(parent[i], null);
        }
        point p;
        while (!q.isEmpty()) {
            p = q.remove();
            // System.out.println("(" + p.x + "," + p.y + ")");
            if (p.x == end.x && p.y == end.y) {
                System.out.println("Found Path");
                break;
            }
            visited[p.x][p.y] = true;
            if (p.x - 1 > -1) {
                if (maze[p.x - 1][p.y] != 1 && visited[p.x - 1][p.y] == false) {
                    parent[p.x - 1][p.y] = p;
                    q.add(new point(p.x - 1, p.y));
                }
            }
            if (p.x + 1 < maze.length) {
                if (maze[p.x + 1][p.y] != 1 && visited[p.x + 1][p.y] == false) {
                    parent[p.x + 1][p.y] = p;
                    q.add(new point(p.x + 1, p.y));
                }
            }
            if (p.y - 1 > -1) {
                if (maze[p.x][p.y - 1] != 1 && visited[p.x][p.y - 1] == false) {
                    parent[p.x][p.y - 1] = p;
                    q.add(new point(p.x, p.y - 1));
                }
            }
            if (p.y + 1 < maze.length) {
                if (maze[p.x][p.y + 1] != 1 && visited[p.x][p.y + 1] == false) {
                    parent[p.x][p.y + 1] = p;
                    q.add(new point(p.x, p.y + 1));
                }
            }
        }
        Stack<point> s = new Stack<point>();
        p = end;
        while (true) {
            s.push(p);
            if (p.x == start.x && p.y == start.y) {
                break;
            }
            p = parent[p.x][p.y];
        }
        while (!s.empty()) {
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
