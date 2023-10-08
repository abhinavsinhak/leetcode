public class SudokuSolver {
    public static boolean isValid(int[][] board, int row, int col, int num) {
        // Check if the number is already in the current row or column
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // Check if the number is already in the current 3x3 grid
        int startRow = 3 * (row / 3);
        int startCol = 3 * (col / 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // Find an empty cell
                if (board[row][col] == 0) {
                    // Try placing numbers 1-9
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            // Place the number if it's valid
                            board[row][col] = num;

                            // Recursively attempt to solve the remaining puzzle
                            if (solveSudoku(board)) {
                                return true;
                            }

                            // If the current placement doesn't lead to a solution, backtrack
                            board[row][col] = 0;
                        }
                    }

                    // If no number can be placed, return false to backtrack
                    return false;
                }
            }
        }

        // If all cells are filled, the puzzle is solved
        return true;
    }

    public static void printSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] sudokuBoard = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if (solveSudoku(sudokuBoard)) {
            printSudoku(sudokuBoard);
        } else {
            System.out.println("No solution exists.");
        }
    }
}
