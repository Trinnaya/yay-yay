import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Deque;

public class NQueen_problem {
    private int[][] chessBoard;
    private int boardSize;

    public NQueen_problem(int boardSize) {
        this.boardSize = boardSize;
        this.chessBoard = new int[boardSize][boardSize];
    }

    public boolean isSafePosition(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (chessBoard[i][col] == 1) return false;
        }

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (chessBoard[i][j] == 1) return false;
        }

        for (int i = row, j = col; i >= 0 && j < boardSize; i--, j++) {
            if (chessBoard[i][j] == 1) return false;
        }

        return true;
    }

    public boolean findSolution() {
        Deque<int[]> queenPositions = new ArrayDeque<>();
        int currentRow = 0;
        int currentCol = 0;
    
        while (currentRow < boardSize) {
            boolean positionFound = false;
    
            while (currentCol < boardSize) {
                if (isSafePosition(currentRow, currentCol)) {
                    placeQueen(currentRow, currentCol, queenPositions);
                    positionFound = true;
                    currentRow++;
                    currentCol = 0; // Reset currentCol for the next row
                    break;
                }
                currentCol++;
            }
    
            if (!positionFound) {
                if (queenPositions.isEmpty()) return false;
                int[] lastQueen = queenPositions.pop();
                currentRow = lastQueen[0];
                currentCol = lastQueen[1] + 1; // Move to the next column for backtracking
                removeQueen(currentRow, currentCol - 1); // Remove the last placed queen
            }
        }
    
        return true;
    }

    private void placeQueen(int row, int col, Deque<int[]> queenPositions) {
        chessBoard[row][col] = 1;
        queenPositions.push(new int[]{row, col});
    }

    private void removeQueen(int row, int col) {
        chessBoard[row][col] = 0;
    }

    public void displayBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print((chessBoard[i][j] == 1 ? "Q " : ". "));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) { // Loop to restart the program if the user wants to try again
            int boardSize;

            // Loop until a valid board size is provided
            while (true) {
                System.out.print("Enter the size of the board (N >= 4): ");
                boardSize = scanner.nextInt();
                if (boardSize >= 4) break;
                System.out.println("Invalid input. The board size must be at least 4.");
            }

            NQueen_problem solver = new NQueen_problem(boardSize);

            System.out.print("Would you like to specify the position of the first queen? (yes/no): ");
            String choice = scanner.next();

            if (choice.equalsIgnoreCase("yes")) {
                int row, col;

                // Loop until a valid first queen position is provided
                while (true) {
                    System.out.print("Enter row (1-" + boardSize + "): ");
                    row = scanner.nextInt() - 1; // Adjust for 0-based index
                    System.out.print("Enter col (1-" + boardSize + "): ");
                    col = scanner.nextInt() - 1;

                    if (row >= 0 && row < boardSize && col >= 0 && col < boardSize) {
                        solver.chessBoard[row][col] = 1;
                        System.out.println("First queen placed at (" + (row + 1) + ", " + (col + 1) + ")");
                        break;
                    }
                    System.out.println("Invalid position. Please try again.");
                }
            }

            if (solver.findSolution()) {
                System.out.println("Solution found:");
                solver.displayBoard();
            } else {
                System.out.println("No solution found.");
            }

            // Prompt to try again
            System.out.print("Would you like to try again with a different board? (yes/no): ");
            String tryAgain = scanner.next();
            if (tryAgain.equalsIgnoreCase("no")) {
                System.out.println("Goodbye!");
                break; // Exit the loop and end the program
            }
        }

        scanner.close();
    }
}