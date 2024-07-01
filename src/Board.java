/**
 * Board class
 * Prints the board and checks if its full
 */

 public class Board {
  private int rows;
  private int columns;
  private String empty = " "; // Empty spaces when there are no tokens

  public Board(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
  }

  /**
   * displayBoard method to display the board in the console
   * 
   * @param tokens its a matrix that stores the tokens placement in the board
   */
  public void displayBoard(String[][] tokens) {
    for (int i = 0; i <= rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (i < tokens.length) {
          System.out.printf("| " + (tokens[i][j] == null ? empty : tokens[i][j]) + " ");
          if (j == columns - 1) {
            System.out.printf("|");
          }
        } else {
          // Print numbers of the columns
          System.out.printf("  " + (j + 1) + " ");
        }
      }
      System.out.println("\n");
    }
  }

  /**
   * isBoardFull method to check if the board is full of tokens
   * 
   * @return boolean true if the first row is full of tokens
   */
  public boolean isBoardFull(String[][] tokens) {
    // Checks if top row is full
    for (int c = 0; c < columns; c++) {
      if (tokens[0][c] == null) {
        return false;
      }
    }
    return true;
  }

}