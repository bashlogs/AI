import java.util.*;

public class NQueens {
    static int sol = 0;

    public static void printBoard(int board[][]) {
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print("|" + ((board[i][j] == 1) ? 'Q' : '-'));
            }
            System.out.println("|");
        }
        System.out.println();
    }

    public static boolean isSafe(int row, int col, int[][] board, int n) {
        for (int i = col; i >= 0; i--) {
            if (board[row][i] == 1) {
                return false;
            }
        }
        int i = row, j = col;
        while (i >= 0 && j >= 0) {
            if (board[i--][j--] == 1) {
                return false;
            }
        }
        i = row;
        j = col;
        while (i < n && j >= 0) {
            if (board[i++][j--] == 1) {
                return false;
            }
        }
        return true;
    }

    public static void placeQueen(int n, int[][] board, int pos) {
        if (pos >= n) {
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isSafe(i, pos, board, n)) {
                board[i][pos] = 1;
                if (pos == n - 1) {
                    sol++;
                    printBoard(board);
                }
                placeQueen(n, board, pos + 1);
                board[i][pos] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("NQueens");
        System.out.println("Enter n");
        int n = s.nextInt();
        int chessBoard[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(chessBoard[i], 0);
        }
        placeQueen(n, chessBoard, 0);
        System.out.println("Solutions found " + sol);
        s.close();
    }
}