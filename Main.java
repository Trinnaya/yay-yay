import java.util.Scanner;
import java.util.ArrayDeque;


public class Main{

    public static void main(String[] args) {
        
        Scanner user_input = new Scanner(System.in);
        System.out.println("Enter the size of the board (N>=4)");

        int N = user_input.nextInt();

        if(N<4){
            System.out.println("N must be at least 4");
            return;
        }

        int board[][] = new int[N][N];
        
        printBoard(board);

       

    }
    public static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}