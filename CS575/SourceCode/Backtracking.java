
/* Java program to solve 8-Queen Problem using Backtracking */

public class Backtracking {

    static int N = 8;
 
    //Function to print the solution
    void printSolution(int board[][])
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + board[i][j]
                                 + " ");
            System.out.println();
        }
    }
 
    /* Function to check if a queen can be placed on board[row][col].
    This function is called when "col" queens are already placed in columns from 0 to col -1.
    So we need to check only left side for attacking queens */

    boolean isSafe(int board[][], int row, int col)
    {
        int i, j;
 
        //Checking row on left side 
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;
 
        //Checking upper diagonal on left side
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;
 
        //Checking lower diagonal on left side
        for (i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }
 
    //A recursive function to solve 8-Queen problem

    boolean solve8QProb(int board[][], int col)
    {
        //Base case: If all queens are placed then return true
        if (col >= N)
            return true;
 
        //Considering this as a column and placing this queen in all rows one by one
        for (int i = 0; i < N; i++) {
            //Checking if the queen can be placed on board[i][col]
            if (isSafe(board, i, col)) {
                //Placing this queen in board[i][col]
                board[i][col] = 1;
 
                //Recur to place rest of the queens
                if (solve8QProb(board, col + 1) == true)
                    return true;
 
                //If placing queen in board[i][col] doesn't lead to a solution then remove queen from board[i][col]
                board[i][col] = 0; //Backtracking
            }
        }
        //If queen cannot be placed in any row, return false
        return false;
    }
 
    /* This function solves the 8-Queen problem using Backtracking.
    It mainly uses solve8QUtil() to solve the problem.
    It returns false if queens cannot be placed, else returns true and
    prints placement of queens in the form of 1s. */

    boolean solve8Q()
    {
        int board[][] = { { 0, 0, 0, 0, 0, 0, 0, 0 },
                          { 0, 0, 0, 0, 0, 0, 0, 0 },
                          { 0, 0, 0, 0, 0, 0, 0, 0 },
                          { 0, 0, 0, 0, 0, 0, 0, 0 }, 
                          { 0, 0, 0, 0, 0, 0, 0, 0 },
                          { 0, 0, 0, 0, 0, 0, 0, 0 },
                          { 0, 0, 0, 0, 0, 0, 0, 0 },
                          { 0, 0, 0, 0, 0, 0, 0, 0 } };
 
        if (solve8QProb(board, 0) == false) {
            System.out.print("Solution does not exist!");
            return false;
        }
        printSolution(board);
        return true;
    }

    public static void main(String args[])
    {
        Backtracking Queen = new Backtracking();
        Queen.solve8Q();
    }
}