import org.junit.*;
import static org.junit.Assert.*;

public class GomokuTester extends Object {
  public static int[][] makeBoard(int row, int column) {
    Gomoku gomoku = new Gomoku();
    int[][] board = new int[row][column];
    gomoku.setBoard(board);
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        board[i][j] = 0;
      }
    }
    return gomoku.getBoard();
  }
  
  @Test
  public void testNumberInLine() {
    int[][] board = makeBoard(5, 5);
    /* Test 0: No piece of the same color in that direction */
    // Test counting in the right direction
    assertEquals("Number of pieces in the right direction should be 0", Gomoku.numberInLine(board, 1, 1, 0, 1), 0); 
    // Test counting in the left direction
    assertEquals("Number of pieces in the left direction should be 0", Gomoku.numberInLine(board, 1, 1, 0, -1), 0);
    // Test counting in the up direction
    assertEquals("Number of pieces in the up direction should be 0", Gomoku.numberInLine(board, 1, 1, -1, 0), 0);
    // Test counting in the down direction
    assertEquals("Number of pieces in the down direction should be 0", Gomoku.numberInLine(board, 1, 1, 1, 0), 0);
    // Test counting in the up right direction
    assertEquals("Number of pieces in the up right direction should be 0", Gomoku.numberInLine(board, 1, 1, -1, 1), 0);
    // Test counting in the up left direction
    assertEquals("Number of pieces in the up left direction should be 0", Gomoku.numberInLine(board, 1, 1, -1, -1), 0);
    // Test counting in the down right direction
    assertEquals("Number of pieces in the down right direction should be 0", Gomoku.numberInLine(board, 1, 1, 1, 1), 0);
    // Test counting in the down left direction
    assertEquals("Number of pieces in the down left direction should be 0", Gomoku.numberInLine(board, 1, 1, 1, -1), 0);
    
    /* Test 1: 1 piece of the same color in that direction */
    // Method stops counting as it encounters an empty square
    board[1][1] = 1;
    // Test counting in the right direction
    assertEquals("Number of pieces in the right direction should be 1", Gomoku.numberInLine(board, 1, 1, 0, 1), 1); 
    // Test counting in the left direction
    assertEquals("Number of pieces in the left direction should be 1", Gomoku.numberInLine(board, 1, 1, 0, -1), 1);
    // Test counting in the up direction
    assertEquals("Number of pieces in the up direction should be 1", Gomoku.numberInLine(board, 1, 1, -1, 0), 1);
    // Test counting in the down direction
    assertEquals("Number of pieces in the down direction should be 1", Gomoku.numberInLine(board, 1, 1, 1, 0), 1);
    // Test counting in the up right direction
    assertEquals("Number of pieces in the up right direction should be 1", Gomoku.numberInLine(board, 1, 1, -1, 1), 1);
    // Test counting in the up left direction
    assertEquals("Number of pieces in the up left direction should be 1", Gomoku.numberInLine(board, 1, 1, -1, -1), 1);
    // Test counting in the down right direction
    assertEquals("Number of pieces in the down right direction should be 1", Gomoku.numberInLine(board, 1, 1, 1, 1), 1);
    // Test counting in the down left direction
    assertEquals("Number of pieces in the down left direction should be 1", Gomoku.numberInLine(board, 1, 1, 1, -1), 1);
    // Method stops counting as it encounters a piece of different color
    board[0][0] = 2;
    board[0][1] = 2;
    board[0][2] = 2;
    board[1][0] = 2;
    board[1][2] = 2;
    board[2][0] = 2;
    board[2][1] = 2;
    board[2][2] = 2;
    // Test counting in the right direction
    assertEquals("Number of pieces in the right direction should be 1", Gomoku.numberInLine(board, 1, 1, 0, 1), 1); 
    // Test counting in the left direction
    assertEquals("Number of pieces in the left direction should be 1", Gomoku.numberInLine(board, 1, 1, 0, -1), 1);
    // Test counting in the up direction
    assertEquals("Number of pieces in the up direction should be 1", Gomoku.numberInLine(board, 1, 1, -1, 0), 1);
    // Test counting in the down direction
    assertEquals("Number of pieces in the down direction should be 1", Gomoku.numberInLine(board, 1, 1, 1, 0), 1);
    // Test counting in the up right direction
    assertEquals("Number of pieces in the up right direction should be 1", Gomoku.numberInLine(board, 1, 1, -1, 1), 1);
    // Test counting in the up left direction
    assertEquals("Number of pieces in the up left direction should be 1", Gomoku.numberInLine(board, 1, 1, -1, -1), 1);
    // Test counting in the down right direction
    assertEquals("Number of pieces in the down right direction should be 1", Gomoku.numberInLine(board, 1, 1, 1, 1), 1);
    // Test counting in the down left direction
    assertEquals("Number of pieces in the down left direction should be 1", Gomoku.numberInLine(board, 1, 1, 1, -1), 1);
    
    /* Test many: Many pieces of the same color in that direction */
    int[][] board2 = makeBoard(7, 7);
    board2[0][4] = 1;
    board2[1][3] = 1;
    board2[2][0] = 1;
    board2[2][1] = 1;
    board2[2][2] = 1;
    board2[3][0] = 1;
    board2[3][1] = 1;
    board2[3][2] = 1;
    board2[3][3] = 1;
    board2[3][4] = 1;
    board2[4][0] = 1;
    board2[4][1] = 1;
    board2[4][2] = 1;
    // Test counting in the right direction
    assertEquals("Number of pieces in the right direction should be 4", Gomoku.numberInLine(board2, 3, 1, 0, 1), 4); 
    // Test counting in the left direction
    assertEquals("Number of pieces in the left direction should be 2", Gomoku.numberInLine(board2, 3, 1, 0, -1), 2);
    // Test counting in the up direction
    assertEquals("Number of pieces in the up direction should be 2", Gomoku.numberInLine(board2, 3, 1, -1, 0), 2);
    // Test counting in the down direction
    assertEquals("Number of pieces in the down direction should be 2", Gomoku.numberInLine(board2, 3, 1, 1, 0), 2);
    // Test counting in the up right direction
    assertEquals("Number of pieces in the up right direction should be 4", Gomoku.numberInLine(board2, 3, 1, -1, 1), 4);
    // Test counting in the up left direction
    assertEquals("Number of pieces in the up left direction should be 2", Gomoku.numberInLine(board2, 3, 1, -1, -1), 2);
    // Test counting in the down right direction
    assertEquals("Number of pieces in the down right direction should be 2", Gomoku.numberInLine(board2, 3, 1, 1, 1), 2);
    // Test counting in the down left direction
    assertEquals("Number of pieces in the down left direction should be 2", Gomoku.numberInLine(board2, 3, 1, 1, -1), 2);
  }
  
  @Test
  public void testIsOpen() {
    int[][] board2 = makeBoard(7, 7);
    /* Test it is not open because it is blocked by the game board */
    board2[1][3] = 1;
    board2[2][0] = 1;
    board2[2][1] = 1;
    board2[2][2] = 1;
    board2[3][0] = 1;
    board2[3][1] = 1;
    board2[3][2] = 1;
    board2[3][3] = 1;
    board2[3][4] = 1;
    board2[4][0] = 1;
    board2[4][1] = 1;
    board2[4][2] = 1;
    // Test whether it is open in the right direction
    assertEquals("The right direction is open", Gomoku.isOpen(board2, 3, 1, 0, 1), true); 
    // Test whether it is open in the left direction
    assertEquals("The left direction is not open", Gomoku.isOpen(board2, 3, 1, 0, -1), false);
    // Test whether it is open in the up direction
    assertEquals("The up direction is open", Gomoku.isOpen(board2, 3, 1, -1, 0), true);
    // Test whether it is open in the down direction
    assertEquals("The down direction is open", Gomoku.isOpen(board2, 3, 1, 1, 0), true);
    // Test whether it is open in the up right direction
    assertEquals("The up right direction is open", Gomoku.isOpen(board2, 3, 1, -1, 1), true);
    // Test whether it is open in the up left direction
    assertEquals("The up left direction is not open", Gomoku.isOpen(board2, 3, 1, -1, -1), false);
    // Test whether it is open in the down right direction
    assertEquals("The down right direction is open", Gomoku.isOpen(board2, 3, 1, 1, 1), true);
    // Test whether it is open in the down left direction
    assertEquals("The down left direction is not open", Gomoku.isOpen(board2, 3, 1, 1, -1), false);
    
    /* Test it is not open because it is blocked by pieces of different color */
    board2[0][4] = 2;
    board2[1][1] = 2;
    board2[5][1] = 2;
    board2[3][5] = 2;
    board2[5][3] = 2;
    assertEquals("The right direction is not open", Gomoku.isOpen(board2, 3, 1, 0, 1), false); 
    // Test whether it is open in the left direction
    assertEquals("The left direction is not open", Gomoku.isOpen(board2, 3, 1, 0, -1), false);
    // Test whether it is open in the up direction
    assertEquals("The up direction is not open", Gomoku.isOpen(board2, 3, 1, -1, 0), false);
    // Test whether it is open in the down direction
    assertEquals("The down direction is not open", Gomoku.isOpen(board2, 3, 1, 1, 0), false);
    // Test whether it is open in the up right direction
    assertEquals("The up right direction is not open", Gomoku.isOpen(board2, 3, 1, -1, 1), false);
    // Test whether it is open in the up left direction
    assertEquals("The up left direction is not open", Gomoku.isOpen(board2, 3, 1, -1, -1), false);
    // Test whether it is open in the down right direction
    assertEquals("The down right direction is not open", Gomoku.isOpen(board2, 3, 1, 1, 1), false);
    // Test whether it is open in the down left direction
    assertEquals("The down left direction is not open", Gomoku.isOpen(board2, 3, 1, 1, -1), false);
  }
  
  @Test
  public void testIsLegal() {
    int[][] board2 = makeBoard(7, 7);
    board2[1][3] = 1;
    board2[2][0] = 1;
    board2[2][1] = 1;
    board2[2][2] = 1;
    board2[3][0] = 1;
    board2[3][1] = 1;
    board2[3][2] = 1;
    board2[3][3] = 1;
    board2[3][4] = 1;
    board2[4][0] = 1;
    board2[4][1] = 1;
    board2[4][2] = 1;
    /* Test the move is not legal because the square is not empty */
    assertEquals("The square is not empty", Gomoku.isLegal(board2, 1, 3, 2, 5, false), false);
    /* Test the move is legal because the square is empty */
    assertEquals("The square is empty", Gomoku.isLegal(board2, 2, 4, 2, 5, false), true);
    
    /* Test the move is not legal because the matched has ended */
    assertEquals("The match has ended", Gomoku.isLegal(board2, 0, 0, 2, 5, true), false);
    /* Test the move is legal because the match has not ended */
    assertEquals("The match has not ended", Gomoku.isLegal(board2, 0, 1, 2, 5, false), true);
    
    /* Test three-three situation */
    // Test 0: there is no line having three pieces 
    int[][] board3 = makeBoard(5, 5);
    assertEquals("Not violating 3-3 as there is no line having 3 pieces", Gomoku.isLegal(board3, 1, 1, 2, 5, false), true);
    // Test 1: there is one line having three pieces 
    board3[3][1] = 2;
    board3[4][1] = 2;
    assertEquals("Not violating 3-3 as there is only 1 line having 3 pieces", Gomoku.isLegal(board3, 2, 2, 2, 5, false), true);
    // Test many: There are many lines having 3 pieces
    board3[3][3] = 2;
    board3[4][4] = 2;
    assertEquals("Violating 3-3 as there are 2 lines having 3 pieces", Gomoku.isLegal(board3, 2, 2, 2, 5, false), false);
  
  /* Test four-four situation */
    // Test 0: there is no line having four pieces 
    assertEquals("Not violating 4-4 as there is no line having 4 pieces", Gomoku.isLegal(board3, 0, 0, 2, 5, false), true);
    // Test 1: there is one line having three pieces 
    board3[1][1] = 2;
    assertEquals("Not violating 4-4 as there is only 1 line having 4 pieces", Gomoku.isLegal(board3, 2, 1, 2, 5, false), true);
    // Test many: There are many lines having 4 pieces
    board3[1][3] = 2;
    assertEquals("Violating 4-4 as there are 2 lines having 4 pieces", Gomoku.isLegal(board3, 2, 2, 2, 5, false), false);
  }
  
  @Test
  public void testIsEnded() {
    // Test 0: there is no line having needed numbers of pieces to win
    int[][] board = makeBoard(10, 10);
    assertEquals("Not ended as there is no line having 5 pieces of same color", Gomoku.isEnded(board, 3, 3, 5), false);
    // - test lines with many pieces but not up to the needed pieces to win
    board[3][3] = 2;
    board[3][4] = 2;
    assertEquals("Not ended as there is no line having 5 pieces of same color", Gomoku.isEnded(board, 3, 3, 5), false);
    board[3][5] = 2;
    assertEquals("Not ended as there is no line having 5 pieces of same color", Gomoku.isEnded(board, 3, 3, 5), false);
    // Test 1: there is one line having needed numbers of pieces to win
    board[3][6] = 2;
    board[3][7] = 2;
    assertEquals("Ended as there is 1 line having 5 pieces of same color", Gomoku.isEnded(board, 3, 3, 5), true);
    // Test many: there are more than one lines having needed number of pieces to win
    board[4][4] = 2;
    board[5][5] = 2;
    board[6][6] = 2;
    board[7][7] = 2;
    assertEquals("Ended as there are 2 lines having 5 pieces of same color", Gomoku.isEnded(board, 4, 4, 5), true);
  }
}