import java.util.ArrayList;
import java.util.List;

public class NQueens {
    private int N;
    private List<int[]> solutions;

    public NQueens(int N) {
        this.N = N;
        this.solutions = new ArrayList<>();
    }

    // Method to check if a queen can be placed on board[row][col]
    private boolean isSafe(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    // Depth-Limited Search method
    private boolean dls(int[] board, int row, int depthLimit) {
        if (row >= N) {
            solutions.add(board.clone());
            return true;
        }

        if (row == depthLimit) {
            return false;
        }

        boolean foundSolution = false;
        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row] = col;
                foundSolution = dls(board, row + 1, depthLimit) || foundSolution;
                // Backtrack
                board[row] = -1;
            }
        }

        return foundSolution;
    }

    // Method to initiate the depth-limited search
    public void solve(int depthLimit) {
        int[] board = new int[N];
        for (int i = 0; i < N; i++) {
            board[i] = -1;
        }

        dls(board, 0, depthLimit);
    }

    // Method to print all solutions
    public void printSolutions() {
        for (int[] solution : solutions) {
            for (int row : solution) {
                for (int col = 0; col < N; col++) {
                    if (col == row) {
                        System.out.print("Q ");
                    } else {
                        System.out.print(". ");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    // Main method
    public static void main(String[] args) {
        int N = 4; // Change N to the desired board size
        int depthLimit = 2; // Set the depth limit to the size of the board

        NQueens nQueens = new NQueens(N);
        nQueens.solve(depthLimit);
        nQueens.printSolutions();
    }
}