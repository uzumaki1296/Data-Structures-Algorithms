/* Java program to solve 8 Queen Problem using Branch and Bound */

import java.io.*;
import java.util.Arrays;
 
public class BranchandBound {

    static int N = 8;
 
//Function to print solution
static void printSolution(int board[][])
{
    int N = board.length;
    for(int i = 0; i < N; i++)
    {
        for(int j = 0; j < N; j++)
            System.out.printf("%2d ", board[i][j]);
             
        System.out.printf("\n");
    }
}
 
//Function to check if a queen can be placed on board[row][col]
static boolean isSafe(int row, int col,
                      int slashCode[][],
                      int backslashCode[][],
                      boolean rowLookup[],
                      boolean slashCodeLookup[],
                      boolean backslashCodeLookup[])
{
    if (slashCodeLookup[slashCode[row][col]] ||
        backslashCodeLookup[backslashCode[row][col]] ||
        rowLookup[row])
        return false;
 
    return true;
}
 
//A recursive function to solve 8-Queen problem
static boolean solve8QueensUtil(
    int board[][], int col, int slashCode[][],
    int backslashCode[][], boolean rowLookup[],
    boolean slashCodeLookup[],
    boolean backslashCodeLookup[])
{
     
    //Base case: If all queens are placed then return true
    int N = board.length;
     
    if (col >= N)
        return true;
 
    //Consider this column and try placing this queen in all rows one by one

    for(int i = 0; i < N; i++)
    {
        //Check if queen can be placed on board[i][col]
        if (isSafe(i, col, slashCode, backslashCode,
                   rowLookup, slashCodeLookup,
                   backslashCodeLookup))
        {
             
            //Place this queen in board[i][col]
            board[i][col] = 1;
            rowLookup[i] = true;
            slashCodeLookup[slashCode[i][col]] = true;
            backslashCodeLookup[backslashCode[i][col]] = true;
 
            //Recur to place rest of the queens
            if (solve8QueensUtil(
                    board, col + 1, slashCode,
                    backslashCode, rowLookup,
                    slashCodeLookup,
                    backslashCodeLookup))
                return true;
 
            // If placing queen in board[i][col] doesn't lead to a solution, then backtrack
 
            // Remove queen from board[i][col]
            board[i][col] = 0;
            rowLookup[i] = false;
            slashCodeLookup[slashCode[i][col]] = false;
            backslashCodeLookup[backslashCode[i][col]] = false;
        }
    }

    return false;
}
 
/* This function solves the 8-Queen problem using Branch and Bound.
It mainly uses solve8QueensUtil() to solve the problem. */
static boolean solve8Queens()
{
    int board[][] = new int[N][N];
     
    //Helper matrices
    int slashCode[][] = new int[N][N];
    int backslashCode[][] = new int[N][N];
 
    //Arrays to tell us which rows are occupied
    boolean[] rowLookup = new boolean[N];
 
    //Keep two arrays to tell us which diagonals are occupied
    
    boolean slashCodeLookup[] = new boolean[2 * N - 1];
    boolean backslashCodeLookup[] = new boolean[2 * N - 1];
 
    // Initialize helper matrices
    for(int r = 0; r < N; r++)
        for(int c = 0; c < N; c++)
        {
            slashCode[r][c] = r + c;
            backslashCode[r][c] = (r - c + 7);
        }
 
    if (solve8QueensUtil(board, 0, slashCode,
                         backslashCode, rowLookup,
                         slashCodeLookup,
                         backslashCodeLookup) == false)
    {
        System.out.printf("Solution does not exist");
        return false;
    }
     
    //Solution found
    printSolution(board);
    return true;
}

public static void main(String[] args)
{
    solve8Queens();
}

}