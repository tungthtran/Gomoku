/* import */
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import java.util.List;

/** A window representing the Gomoku game
 * @author <em>Kris Tran</em>
 */
public class Gomoku extends Application {
  /* store the button to start a new game */
  private Button button;
  /* store button array representing the game board */
  private Button[][] button2;
  /* store the int array representing the game board */
  private int[][] board;
  /* store the current player */
  private int player = 1;
  /* store the number of rows of the board */
  private int row = 19;
  /* store the number of columns of the board */
  private int column = 19;
  /* store whether the match has ended or not */
  private boolean isEnded = false;
  /* store the number of pieces needed to win the match */
  private int winNumber = 5;
  /* store the text that will pop up during different events of the match */
  private TextArea text;
  
  /**
   * set the game board 
   * @param board  the game board
   */
  public void setBoard(int[][] board) {
    this.board = board;
  }
  
  /**
   * return the game board
   * @return the game board
   */ 
  public int[][] getBoard() {
    return this.board;
  }
  
  /**
   * set the player in turn
   * @param player  the player
   */
  public void setPlayer(int player) {
    this.player = player;
  }
  
  /**
   * return the player in turn
   * @return the player in turn
   */ 
  public int getPlayer() {
    return this.player;
  }
  
  /**
   * set the number of rows in the board
   * @param row  the number of rows in the board
   */
  public void setRow(int row) {
    this.row = row;
  }
  
  /**
   * return the number of rows in the board
   * @return the number of rows in the board
   */ 
  public int getRow() {
    return this.row;
  }
  
  /**
   * set the number of columns in the board
   * @param column  the number of columns in the board
   */
  public void setColumn(int column) {
    this.column = column;
  }
  
  /**
   * return the number of columns in the board
   * @return the number of columns in the board
   */ 
  public int getColumn() {
    return this.column;
  }
  
  /**
   * set whether the game has ended or not
   * @param isEnded  boolean value of whether the game has ended 
   */
  public void setIsEnded(boolean isEnded) {
    this.isEnded = isEnded;
  }
  
  /**
   * return whether the game has ended or not
   * @return whether the game has ended or not
   */ 
  public boolean getIsEnded() {
    return this.isEnded;
  }
  
  /**
   * set the number of needed pieces to win
   * @param winNumber  the number of needed pieces to win
   */
  public void setWinNumber(int winNumber) {
    this.winNumber = winNumber;
  }
  
  /**
   * return the number of needed pieces to win
   * @return the number of needed pieces to win
   */ 
  public int getWinNumber() {
    return this.winNumber;
  }
  
  /**
   * switch the player for every new turn in the game */
  public void switchPlayer() {
    this.player = 3 - this.player;
  }
  
  /** 
   * Overrides the start method of Application to create the GUI with the game board simulating Gomoku game
   * @param primaryStage the JavaFX main window
   */
  public void start(Stage primaryStage) {
    executeGame();
    /* the game board represented by a two-dimensional array of button */
    button2 = new Button[getRow()][getColumn()];
    /* the game board represented by a two-dimensional array of int */
    board = new int[getRow()][getColumn()];
    GridPane grid = new GridPane();
    /* goes through every row of the game board */
    for(int i = 0; i < getRow(); i++) {
      /* goes through every column of the game board */
      for(int j = 0; j < getColumn(); j++) {
        button2[i][j] = new Button("");
        button2[i][j].setPrefSize(25, 25);
        button2[i][j].setOnAction(new ButtonAction());
        button2[i][j].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, new Insets(1.0))));
        grid.add(button2[i][j], j, i);
        board[i][j] = 0;
      }
    }
    /* create new Text Area to inform player of different events */ 
    text = new TextArea();
    /* create a new border pane to hold buttons, text area and game board */
    BorderPane pane = new BorderPane();             
    button = new Button("START A NEW GAME");
    button.setOnAction(new ButtonAction());
    pane.setTop(button);
    pane.setCenter(grid);
    pane.setBottom(text);
    /* Create a "scene" that contains this border area */
    Scene scene = new Scene(pane);           
    primaryStage.setTitle("GOMOKU");
    /* Add the "scene" to the main window */
    primaryStage.setScene(scene);       
    /* Display the window */
    primaryStage.show();                     
  }
  
  /* set the size of the board with rows, columns and number of pieces needed to win from the command line arguments */
  public void executeGame() {
    /* lsit of string stores the list of string parameters */
    List<String> list = this.getParameters().getRaw();
    switch (list.size()) {
      case 0:
        break;
      case 1:
        try {
        setWinNumber(Integer.parseInt(list.get(0)));
      }
        catch(Exception e) {
          System.out.println("Please enter realistic numbers next time");
        }
        break;
      case 2:
        try {
        setRow(Integer.parseInt(list.get(0)));
        setColumn(Integer.parseInt(list.get(1)));
      }
        catch(Exception e) {
          System.out.println("Please enter realistic numbers next time");
        }
        break;
      case 3:
        try {
        setWinNumber(Integer.parseInt(list.get(0)));
        setRow(Integer.parseInt(list.get(1)));
        setColumn(Integer.parseInt(list.get(2)));
      }
        catch(Exception e) {
          System.out.println("Please enter realistic numbers next time");
        }
        break;
    }
  }
      
  
  /**
   * play the game in turn and return the boolean value of whether the move has been made or not
   * @param row  the row of the currently played piece
   * @param column  the column of the currently played piece
   * @return whether the move has been made
   */ 
  public boolean move(int row, int column) {
    if (isLegal(getBoard(), row, column, getPlayer(), getWinNumber(), getIsEnded())) {
      text.setText("");
      board[row][column] = player; 
      if (isEnded(getBoard(), row, column, getWinNumber()) == true){
        setIsEnded(true);
        switchPlayer();
        if (player == 1)
          text.setText("Match ended. The winner is White!!!");
        else
          text.setText("Match ended. The winner is Black!!!");
        return true;
      }
      else{ 
        switchPlayer();
        return true;
      }
    }
    else {
      text.setText("Illegal move. You cannot place the piece there");
      return false;
    }
  }
  
  /**
   * return the number of pieces of the same color in a straight line
   * @param board  a two-dimensional array representing the game board
   * @param row  the row of the currently played piece
   * @param column  the column of the currently played piece
   * @param xcord  the direction moved in the x-cordinate
   * @param ycord  the direction moved in the y-cordinate
   * @return the number of pieces of the same color in a straight line
   */ 
  public static int numberInLine(int[][] board, int row, int column, int xcord, int ycord) {
    if (board[row][column] == 0)
      return 0;
    else {
     /* store the number of pieces of the same color in a straight line */
     int count = 1;
     /* check if it is still in the board and the piece has the same color as the previous piece, and then the count adds up */
     while (row + xcord >= 0 && row + xcord < board.length && column + ycord >= 0 && column + ycord < board[0].length 
           && board[row][column] == board[row + xcord][column + ycord]) {
      row += xcord;
      column += ycord;
      count ++;
     }
     return count;
    }
  }
  
  /**
   * check if player reaches an empty square in the game board and return the according boolean result 
   * @param board  a two-dimensional array representing the game board
   * @param row  the row of the currently played piece
   * @param column  the column of the currently played piece
   * @param xcord  the direction moved in the x-cordinate
   * @param ycord  the direction moved in the y-cordinate
   * @return whether the player reaches an empty square in the game board or not
   */ 
  public static boolean isOpen(int[][] board, int row, int column, int xcord, int ycord) {
    int initialRow = row;
    if (board[row][column] == 0)
      return true;
    else {
      row += xcord * numberInLine(board, row, column, xcord, ycord);
      column += ycord * numberInLine(board, initialRow, column, xcord, ycord);
      return (row >= 0 && row < board.length && column >= 0 && column < board[0].length
              && board[row][column] == 0);
    }
  }
  
  /**
   * check if the move is legal or not and return the according boolean result 
   * @param board  a two-dimensional array representing the game board
   * @param row  the row of the currently played piece
   * @param column  the column of the currently played piece
   * @param player  the player in turn
   * @param winNumber  the number of pieces of the same color in a straight line
   * @param isEnded  the boolean value of whether the game has ended
   * @return whether the move is legal or not
   */ 
  public static boolean isLegal(int[][] board, int row, int column, int player, int winNumber, boolean isEnded) {
    if (isEnded == true)
      return false;
    else if (board[row][column] != 0)
      return false;
    else {
      board[row][column] = player;
      /* store the time the number of pieces of the same color in a straight line equals "four" */
      int count4 = 0;
      /* store the time the number of pieces of the same color in a straight line equals "three" */
      int count3 = 0;
      if (numberInLine(board, row, column, 1, 1) + numberInLine(board, row, column, -1, -1) - 1 == winNumber - 1)
        count4++;
      if (numberInLine(board, row, column, -1, 1) + numberInLine(board, row, column, 1, -1) - 1 == winNumber - 1)
        count4++;
      if (numberInLine(board, row, column, 0, 1) + numberInLine(board, row, column, 0, -1) - 1 == winNumber - 1)
        count4++;
      if (numberInLine(board, row, column, 1, 0) + numberInLine(board, row, column, -1, 0) - 1 == winNumber - 1)
        count4++;
      if (numberInLine(board, row, column, 1, 1) + numberInLine(board, row, column, -1, -1) - 1 == winNumber - 2 
          && isOpen(board, row, column, 1, 1) == true && isOpen(board, row, column, -1, -1) == true)
        count3++;
      if (numberInLine(board, row, column, -1, 1) + numberInLine(board, row, column, 1, -1) - 1 == winNumber - 2
               && isOpen(board, row, column, -1, 1) == true && isOpen(board, row, column, 1, -1) == true)
        count3++;
      if (numberInLine(board, row, column, 0, 1) + numberInLine(board, row, column, 0, -1) - 1 == winNumber - 2
               && isOpen(board, row, column, 0, 1) == true && isOpen(board, row, column, 0, -1) == true)
        count3++;
      if (numberInLine(board, row, column, 1, 0) + numberInLine(board, row, column, -1, 0) - 1 == winNumber - 2
               && isOpen(board, row, column, 1, 0) == true && isOpen(board, row, column, -1, 0) == true)
        count3++;
      if (count4 >= 2){
        board[row][column] = 0;
        return false;
      }
      if (count3 >= 2){
        board[row][column] = 0;
        return false;
      }
      else
        return true;
    }
  }
  
  /**
   * check if the game has ended or not and return the according boolean result 
   * @param board  a two-dimensional array representing the game board
   * @param row  the row of the currently played piece
   * @param column  the column of the currently played piece
   * @param winNumber  the number of pieces of the same color in a straight line
   * @return whether the game has ended or not
   */ 
  public static boolean isEnded(int[][] board, int row, int column, int winNumber) {
      if (numberInLine(board, row, column, 1, 1) + numberInLine(board, row, column, -1, -1) - 1 >= winNumber)
        return true;
      if (numberInLine(board, row, column, -1, 1) + numberInLine(board, row, column, 1, -1) - 1 >= winNumber)
        return true;
      if (numberInLine(board, row, column, 0, 1) + numberInLine(board, row, column, 0, -1) - 1 >= winNumber)
        return true;
      if (numberInLine(board, row, column, 1, 0) + numberInLine(board, row, column, -1, 0) - 1 >= winNumber)
        return true;
      else
        return false;
  }
  
  /**
   * The method to launch the program.
   * @param args  The command line arguments. The arguments are passed on to the JavaFX application.
   */
   public static void main(String[] args) {
     Application.launch(args);
   }
  
   /* A class to take clicks from the user and display in the game board
    */ 
   private class ButtonAction implements EventHandler<ActionEvent>
   {
     /** 
      * invoked when a specific event of the type for which this handler is registered happens
      * @param e  the event which occurred
      */ 
     public void handle(ActionEvent e) {
       Button b = (Button) e.getSource();
       if (b == button){
         /* goes through every row of the game board */
         for(int i = 0 ; i < getRow(); i ++){
           /* goes through every column of the game board */
           for(int j = 0; j < getColumn(); j ++)
           {
             board[i][j] = 0;
             button2[i][j].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, new Insets(1.0))));;
             setIsEnded(false);
             setPlayer(1);
             text.setText("");
           }
         }
       }
       
       else {
         /* goes through every row of the game board */
         for (int i = 0; i < getRow(); i++) {
           /* goes through every column of the game board */
           for (int j = 0; j < getColumn(); j++) {
             if (b == button2[i][j]) {
               if (move(i, j)) { 
                 if (getPlayer() == 2)
                   button2[i][j].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, new Insets(1.0)), new BackgroundFill(Color.BLACK, new CornerRadii(25), new Insets(3.0))));
                 else 
                   button2[i][j].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, new Insets(1.0)), new BackgroundFill(Color.WHITE, new CornerRadii(25), new Insets(3.0))));
               }
             }
           }
         }
       }
     }
   }
}
       