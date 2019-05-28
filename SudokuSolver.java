// A Sudoku Solving Agent
// Uses Backtracking & Constraint Propagation

import java.util.*;

public class SudokuSolver{

  private static int[][] board = new int[9][9];
  private static int[][] blocks = {{0,0},{0,3},{0,6},{3,0},{3,3},{3,6},{6,0},{6,3},{6,6}};
  private static final int EMPTY = 0;

  // Reads input from the user and generates the board
  private static int[][] readInput(){
    int[][] input = new int[9][9];
    Scanner scanner = new Scanner(System.in);
    for(int r = 0; r < 9; r++){
      for(int c = 0; c < 9; c++){
        input[r][c] = scanner.nextInt();
      }
    }
    scanner.close();
    return input;
  }

  // Return the block in which (row, col) lies
  private static int[] getBlock(int row, int col){
    return new int[]{row - (row%3), col - (col%3)};
  }

  // Returns the set of possible values a cell could take
  // Assumes that board[row][col] has value 0
  private static ArrayList<Integer> getPossibleValues(int[][] board, int row, int col){
    if(board[row][col] != EMPTY){
      return null;
    }

    ArrayList<Integer> vals = new ArrayList<Integer>();
    HashSet<Integer> usedVals = new HashSet<Integer>();
    int[] block = getBlock(row, col);

    for(int r = 0; r < 9; r++){
      usedVals.add(board[r][col]);
    }
    for(int c = 0; c < 9; c++){
      usedVals.add(board[row][c]);
    }
    for(int a = 0; a < 3; a++){
      for(int b = 0; b < 3; b++){
        usedVals.add(board[block[0] + a][block[1] + b]);
      }
    }

    for(int i = 1; i <= 9; i++){
      if(!(usedVals.contains(i))){
        vals.add(i);
      }
    }

    return vals;
  }

  // Returns the next cell to look at
  // Based on the least possible values heuristic
  private static int[] getNextCell(int[][] board){
    int min = 10;
    int[] minCell = null;
    for(int r = 0; r < 9; r++){
      for(int c = 0; c < 9; c++){
        if(board[r][c] == EMPTY){
          int numPossibleVals = (getPossibleValues(board, r, c)).size();
          if(numPossibleVals < min){
            min = numPossibleVals;
            minCell = new int[]{r, c};
          }
        }
      }
    }
    return minCell;
  }

  // Recurscive Backtracking Algorithm to find the solution
  private static boolean solve(int[][] board){
    int[] cell = getNextCell(board);
    if(cell == null){
      //Found Solution
      return true;
    }
    for(int val : getPossibleValues(board, cell[0], cell[1])){
      int[][] newBoard = board;
      newBoard[cell[0]][cell[1]] = val;
      boolean result = solve(newBoard);
      if(result == true){
        return true;
      }
      else{
        board[cell[0]][cell[1]] = EMPTY;
      }
    }
    return false;
  }

  // Verifies if solution is valid
  private static boolean verifySolution(int[][] board){
    // All values should be between 1 & 9
    for(int r = 0; r < 9; r++){
      for(int c = 0; c < 9; c++){
        if((board[r][c] < 1) || (board[r][c] > 9)){
          return false;
        }
      }
    }
    // All columns should have different values
    for(int r = 0; r < 9; r++){
      for(int c1 = 0; c1 < 9; c1++){
        for(int c2 = c1+1; c2 < 9; c2++){
          if(board[r][c1] == board[r][c2]){
            return false;
          }
        }
      }
    }
    // All rows should have different values
    for(int c = 0; c < 9; c++){
      for(int r1 = 0; r1 < 9; r1++){
        for(int r2 = r1+1; r2 < 9; r2++){
          if(board[r1][c] == board[r2][c]){
            return false;
          }
        }
      }
    }
    // All blocks should have distinct values
    for(int[] block: blocks){
      HashSet<Integer> values = new HashSet<Integer>();
      for(int r = 0; r < 3; r++){
        for(int c = 0; c < 3; c++){
          if(values.contains(board[block[0] + r][block[1] + c])){
            return false;
          }
          values.add(board[block[0] + r][block[1] + c]);
        }
      }
    }
    return true;
  }

  // Prints the solution
  private static void printBoard(int[][] board){
    if(!(verifySolution(board))){
      return;
    }
    System.out.println("");
    for(int r = 0; r < 9; r++){
      if(r%3 == 0 && r != 0){
        System.out.println("--------------------");
      }
      for(int c = 0; c < 9; c++){
        if(c%3 == 0 && c != 0){
          System.out.print("|");
        }
        System.out.print((board[r][c] == 0? "." : board[r][c]) + " ");
        if(c == 8){
          System.out.println("");
        }
      }
    }
  }

  public static void main(String[] args){
    board = readInput();
    solve(board);
    printBoard(board);
  }

}
