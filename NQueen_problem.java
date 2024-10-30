import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Deque;

public class NQueen_problem{
    private int[][] board;
    private int N;

    public NQueen_problem(int N){
        this.N=N;
        this.board=new int[N][N];
        
    }

    public boolean PlaceQueen(int row, int col){
        //check column by row 
        for(int i=0; i<row; i++){
            if(board[i][col]==1){
                return false;
            }
        }
        //check upper left diagonal
        for(int i=row,j=col;i>=0 && j>=0; i--,j--){
            if(board[i][j]==1){
                return false;
            }
        }
        //check upper right diagonal
        for(int i = row, j= col; i>=0 && j<N ;i--, j++){
            if(board[i][j]==1){
                return false;
            }
        }

        return true;
    }

    public void showBoard(){
        for(int i = 0; i<N ;i++){
            for(int j=0;j<N;j++){
                if(board[i][j]==1){
                    System.out.println("Q");
                }else{
                    System.out.println(".");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        
        Scanner user_input = new Scanner(System.in); 
        
        while(true){
            System.out.println("Enter the size of the board (N>=4):");
            int N = user_input.nextInt();

            if(N<4){
                System.out.println("N must be at least 4");
                return;
            }

        }

        NQueen_problem nQueensSolver = new NQueen_problem(N);

        System.err.println("Would you like to place the first queen yourself?(yes/no)");
        String QueenChoice = user_input.next();

        if(QueenChoice.equalsIgnoreCase("yes"))
            System.out.println("Enter row(1-"+N+") and column (1="+N+") for the first queen:");
    }
}


    
