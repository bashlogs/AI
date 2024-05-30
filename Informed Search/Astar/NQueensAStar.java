import java.util.*;

class NQueensAStar {
    static int sol = 0;

    static class BoardState {
        int[] queens; // queens[i] represents the column of the queen in row i
        int g; // Cost to reach this state
        int h; // Heuristic estimate of cost to goal
        int f; // Total cost (g + h)

        BoardState(int[] queens, int g, int h) {
            this.queens = queens.clone();
            this.g = g;
            this.h = h;
            this.f = g + h;
        }
    }

    public static void main(String[] args) {
        int n = 8; // You can change this value for different N
        solveNQueens(n);
        System.out.println("Total Solutions found " + sol);
    }

    public static void solveNQueens(int n) {
        PriorityQueue<BoardState> openList = new PriorityQueue<>(Comparator.comparingInt(state -> state.f));
        HashSet<String> closedList = new HashSet<>();

        // Start with an empty board
        int[] initialQueens = new int[n];
        Arrays.fill(initialQueens, -1);
        BoardState initialState = new BoardState(initialQueens, 0, heuristic(initialQueens, n));

        openList.add(initialState);
        List<int[]> solutions = new ArrayList<>();

        while (!openList.isEmpty()) {
            BoardState current = openList.poll();

            if (isGoalState(current.queens)) {
                solutions.add(current.queens.clone());
                closedList.add(Arrays.toString(current.queens));
                continue; // continue searching for other solutions
            }

            closedList.add(Arrays.toString(current.queens));

            for (BoardState neighbor : generateNeighbors(current, n)) {
                if (closedList.contains(Arrays.toString(neighbor.queens))) {
                    continue;
                }
                openList.add(neighbor);
            }
        }

        if (solutions.isEmpty()) {
            System.out.println("No solution found.");
        } else {
            for (int[] solution : solutions) {
                sol++;
                printBoard(solution);
                System.out.println();
            }
        }
    }

    private static List<BoardState> generateNeighbors(BoardState state, int n) {
        List<BoardState> neighbors = new ArrayList<>();
        int row = getNextRow(state.queens);

        for (int col = 0; col < n; col++) {
            if (isSafe(state.queens, row, col)) {
                int[] newQueens = state.queens.clone();
                newQueens[row] = col;
                int g = state.g + 1;
                int h = heuristic(newQueens, n);
                neighbors.add(new BoardState(newQueens, g, h));
            }
        }
        return neighbors;
    }

    private static int getNextRow(int[] queens) {
        for (int i = 0; i < queens.length; i++) {
            if (queens[i] == -1) {
                return i;
            }
        }
        return queens.length;
    }

    private static boolean isSafe(int[] queens, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == col || Math.abs(i - row) == Math.abs(queens[i] - col)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isGoalState(int[] queens) {
        for (int queen : queens) {
            if (queen == -1) {
                return false;
            }
        }
        return true;
    }

    private static int heuristic(int[] queens, int n) {
        int h = 0;
        int row = getNextRow(queens);
        for (int r = row; r < n; r++) {
            int safePositions = 0;
            for (int col = 0; col < n; col++) {
                if (isSafe(queens, r, col)) {
                    safePositions++;
                }
            }
            h += (n - safePositions);
        }
        return h;
    }

    private static void printBoard(int[] queens) {
        int n = queens.length;
        for (int row : queens) {
            for (int col = 0; col < n; col++) {
                if (col == row) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
