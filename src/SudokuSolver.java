import java.util.Scanner;

public class SudokuSolver {



        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // Get the size of the Sudoku grid
            System.out.print("Enter the size of the Sudoku grid (N): ");
            int n = scanner.nextInt();

            int[][] board = new int[n][n]; // Initialize an empty N x N board
            System.out.println("Enter the Sudoku grid (use 0 for empty cells):");
            fillBoard(board, n); // Fill the board with user input

            if (solve(board, n)) {
                display(board); // Display the solved board
            } else {
                System.out.println("Cannot solve the Sudoku puzzle.");
            }
            scanner.close(); // Close the scanner
        }
        // Method to fill the Sudoku board with user input
        static void fillBoard(int[][] board, int n) {
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = scanner.nextInt(); // Read input from user
                }
            }
        }
        static boolean solve(int[][] board, int n) {
            int row = -1;
            int col = -1;
            boolean noEmptyLeft = true;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 0) {
                        row = i;
                        col = j;
                        noEmptyLeft = false;
                        break;
                    }
                }
                if (!noEmptyLeft) {
                    break;
                }
            }

            if (noEmptyLeft) {
                // -----sudoku is solved---
                return true;
            }

            for (int number = 1; number <= n; number++) {
                if (isSafe(board, row, col, number, n)) {
                    board[row][col] = number;
                    if (solve(board, n)) {
                        // found the answer
                        return true;
                    } else {
                        // backtrack
                        board[row][col] = 0;
                    }
                }
            }
            return false;
        }

        static void display(int[][] board) {
            for (int[] row : board) {
                for (int nums : row) {
                    System.out.print(nums + " ");
                }
                System.out.println();
            }
        }

        static boolean isSafe(int[][] board, int row, int col, int num, int n) {
            // Check the row
            for (int i = 0; i < n; i++) {
                if (board[row][i] == num) {
                    return false;
                }
            }
            // Check the column
            for (int[] nums : board) {
                if (nums[col] == num) {
                    return false;
                }
            }
            // Check the sub-grid
            int sqrt = (int) Math.sqrt(n);
            int rstart = row - (row % sqrt);
            int colstart = col - (col % sqrt);
            for (int r = rstart; r < rstart + sqrt; r++) {
                for (int c = colstart; c < colstart + sqrt; c++) {
                    if (board[r][c] == num) {
                        return false;
                    }
                }
            }
            return true;
        }
    }


