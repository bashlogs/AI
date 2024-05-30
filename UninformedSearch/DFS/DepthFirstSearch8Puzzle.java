import java.util.*;

public class DepthFirstSearch8Puzzle {

    public static int[][] start = { { 2, 8, 3 }, { 1, 6, 4 }, { 7, 0, 5 } };
    public static int[][] goal = { { 1, 2, 3 }, { 8, 0, 4 }, { 7, 6, 5 } };

    public static void main(String[] args) {
        PuzzleState initialState = new PuzzleState(copyArray(start), 2, 1, null);
        Stack<PuzzleState> s = new Stack<PuzzleState>();
        HashSet<String> hs = new HashSet<String>();
        s.push(initialState);
        hs.add(initialState.getStateId());
        String goals = new String("123804765");
        PuzzleState temp1 = null, temp2 = null;
        while (!s.isEmpty()) {
            temp1 = s.pop();
            if (temp1.getStateId().equals(goals)) {
                System.out.println("Path Found");
                break;
            }
            if (temp1.ex + 1 < 3) {
                temp2 = new PuzzleState(copyArray(temp1.State), temp1.ex, temp1.ey, temp1);
                temp2.State[temp1.ex][temp1.ey] = temp2.State[temp1.ex + 1][temp1.ey];
                temp2.State[temp1.ex + 1][temp1.ey] = 0;
                temp2.ex += 1;
                if (!hs.contains(temp2.getStateId())) {
                    s.push(temp2);
                    hs.add(temp2.getStateId());
                }
            }
            if (temp1.ex - 1 >= 0) {
                temp2 = new PuzzleState(copyArray(temp1.State), temp1.ex, temp1.ey, temp1);
                temp2.State[temp1.ex][temp1.ey] = temp2.State[temp1.ex - 1][temp1.ey];
                temp2.State[temp1.ex - 1][temp1.ey] = 0;
                temp2.ex -= 1;
                if (!hs.contains(temp2.getStateId())) {
                    s.push(temp2);
                    hs.add(temp2.getStateId());
                }
            }
            if (temp1.ey + 1 < 3) {
                temp2 = new PuzzleState(copyArray(temp1.State), temp1.ex, temp1.ey, temp1);
                temp2.State[temp1.ex][temp1.ey] = temp2.State[temp1.ex][temp1.ey + 1];
                temp2.State[temp1.ex][temp1.ey + 1] = 0;
                temp2.ey += 1;
                if (!hs.contains(temp2.getStateId())) {
                    s.push(temp2);
                    hs.add(temp2.getStateId());
                }
            }
            if (temp1.ey - 1 >= 0) {
                temp2 = new PuzzleState(copyArray(temp1.State), temp1.ex, temp1.ey, temp1);
                temp2.State[temp1.ex][temp1.ey] = temp2.State[temp1.ex][temp1.ey - 1];
                temp2.State[temp1.ex][temp1.ey - 1] = 0;
                temp2.ey -= 1;
                if (!hs.contains(temp2.getStateId())) {
                    s.push(temp2);
                    hs.add(temp2.getStateId());
                }
            }
        }
        s.clear();
        while (temp1 != null) {
            s.push(temp1);
            temp1 = temp1.parent;
        }
        while (!s.isEmpty()) {
            s.pop().printBoard();
        }
    }

    public static int[][] copyArray(int array[][]) {
        int arr[][] = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = array[i][j];
            }
        }
        return arr;
    }
}

class PuzzleState {
    int[][] State;
    int ex, ey;
    PuzzleState parent;

    PuzzleState(int[][] State, int ex, int ey, PuzzleState parent) {
        this.State = State;
        this.ex = ex;
        this.ey = ey;
        this.parent = parent;
    }

    public String getStateId() {
        String str = new String();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                str += this.State[i][j];
            }
        }
        return str;
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("|" + ((this.State[i][j] == 0) ? " " : this.State[i][j]));
            }
            System.out.println("|");
        }
        System.out.println();
    }
}