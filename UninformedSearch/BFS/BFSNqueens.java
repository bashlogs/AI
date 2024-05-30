import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BFSNqueens {

    private final int N;
    private int solutionCount;

    public BFSNqueens(int N) {
        this.N = N;
        this.solutionCount = 0;
    }

    public void solve() {
        int[] board = new int[N];
        Arrays.fill(board, -1);
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(board);

        while (!queue.isEmpty()) {
            int[] currentBoard = queue.poll();
            int col = findNextEmptyCol(currentBoard);

            if (col == N) {
                // All queens are placed successfully
                printSolution(currentBoard);
                solutionCount++;
            } else {
                for (int row = 0; row < N; row++) {
                    if (isSafe(currentBoard, row, col)) {
                        int[] newBoard = currentBoard.clone();
                        newBoard[col] = row;
                        queue.offer(newBoard);
                    }
                }
            }
        }

        if (solutionCount == 0) {
            System.out.println("No solutions exist for N = " + N);
        }
    }

    private int findNextEmptyCol(int[] board) {
        for (int col = 0; col < N; col++) {
            if (board[col] == -1) {
                return col;
            }
        }
        return N;
    }

    private boolean isSafe(int[] board, int row, int col) {
        for (int prevCol = 0; prevCol < col; prevCol++) {
            int prevRow = board[prevCol];
            // Check if queen can attack in the same row or diagonals
            if (prevRow == row || Math.abs(prevRow - row) == Math.abs(prevCol - col)) {
                return false;
            }
        }
        return true;
    }

    private void printSolution(int[] board) {
        System.out.println("Solution " + (solutionCount + 1) + ":");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[j] == i) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int N = 8; // Board size for N-Queens problem
        BFSNqueens csp = new BFSNqueens(N);
        csp.solve();
    }
}