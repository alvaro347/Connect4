import java.io.BufferedReader;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    Connect4 connect4 = new Connect4();
    connect4.startGame();
  }
}

class Connect4 {
  private PlayerInput playerInput = new PlayerInput();
  private Board board = new Board(6, 7);
  private String[][] tokens = new String[6][7]; // Matrix to store the tokens

  private int player = 1; // Player helper variable to count the turns
  private boolean winner = false;
  private boolean boardFull = false;

  // Variables that will be used inhereted in other classes
  protected String[] players = { "1", "CPU" }; // Players in the game. in this case only player 1 and CPU
  protected String[] tokensSymbols = { "0", "X" };

  /**
   * startGame method
   * Displays the game's rules and description and starts the game loop
   */
  public void startGame() {
    // Game intro description and rules
    System.out.println("---------------------------------");
    System.out.println("      Welcome to Connect 4");
    System.out.println("---------------------------------\n");
    System.out.println("To play the game type in the number of the column you want to drop your counter in");
    System.out.println("A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally\n");
    System.out.println("---------------------------------\n");
    // Game loop
    while (!winner && !boardFull) {
      board.displayBoard(tokens);
      // Checks CPU or player input and check if either has won
      if (player % 2 != 0) {
        this.tokens = playerInput.handlePlayerInput(tokens, tokensSymbols, players[0]);
        this.winner = playerInput.isWinner(tokens, tokensSymbols[0]);
        player++;
      } else {
        this.tokens = playerInput.handlePlayerInput(tokens, , players[1]);
        this.winner = playerInput.isWinner(tokens, tokensSymbols[1]);
        player++;
      }
      // Check if the baord is full at the end of every move
      boardFull = board.isBoardFull(tokens);
    }
    gameOver();
  }

  /**
   * gameOver method to handle the end of the game.
   * Game over will check if the board is full and now players have won the game
   * or a message with the player that won the game
   */
  public void gameOver() {
    board.displayBoard(tokens);
    if (boardFull) {
      System.out.println("Draw, No more empty slots!");
      System.out.println("Game Over");
    } else {
      System.out.println("Winner is: Player " + players[player % 2] + "\n");
      System.out.println("Game Over");
    }
  }

}

class Board extends Connect4 {
  private int rows;
  private int columns;
  private String empty = " "; // Empty spaces when there are no tokens

  public Board(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
  }

  /**
   * displayBoard method to display the baord in the console
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
    for (int c = 0; c < columns; c++) {
      if (tokens[0][c] == null) {
        return false;
      }
    }
    return true;
  }

}

class PlayerInput extends Connect4 {

  /**
   * handlePlayerInput method to handle the input from the player and CPU
   * if the current player is the CPU the mehtod will generate a random number.
   * 
   * @param tokens, the matrix of tokens already placed
   * @param player, the current player
   * @return the tokens with the update value
   */
  public String[][] handlePlayerInput(String[][] tokens, String player) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("---- Player " + player + " ----\n");
    int playerMove = -1; // helper vairable to determine a correct input from the player
    while (playerMove == -1) {
      // if the player is CPU the playeMove will be asign to a random number between 1
      // and 7
      if (player == "CPU") {
        playerMove = (int) Math.floor(Math.random() * 7);
        while (tokens[0][playerMove] != null) {
          playerMove = (int) Math.floor(Math.random() * 7);
        }
        System.out.println("CPU adds token in column: " + playerMove);
      } else {
        // Non CPU player will be handled here
        try {
          System.out.printf("Choose a column: ");
          String input = br.readLine();
          if (input.length() != 0) {
            playerMove = Character.getNumericValue(input.charAt(0));
            if (input.length() == 1 && playerMove <= 7) {
              playerMove--;
            } else {
              playerMove = -1;
              System.out.println("Try Again! Enter a number between 1 and 7\n");
            }
          }
        } catch (IOException e) {
          System.err.println(e.getMessage());
          System.exit(1);
        }
      }
      // Handles the user placing a column in a column that is full
      if (playerMove != -1 && tokens[0][playerMove] != null) {
        playerMove = -1;
        System.out.println("The slot is full, try another column\n");
      }
    }
    return updateTokens(tokens, tokensSymbols, player, playerMove);
  }

  /**
   * updateTokens method to add token to the tokens matrix in a specific column
   * 
   * @param tokens,     the matrix of tokens already placed
   * @param player,     the current player
   * @param playerMove, the column where the player place the token
   * @return tokens matrix
   */
  public String[][] updateTokens(String[][] tokens, String[] tokensSymbols, String player, int playerMove) {
    int r = 0;
    // Assigns token when the column (playerMove) is empty
    if (tokens[5][playerMove] == null) {
      tokens[5][playerMove] = (player == "CPU") ? tokensSymbols[1] : tokensSymbols[0];
    } else {
      while (tokens[r][playerMove] == null) {
        r += 1;
      }
      tokens[r - 1][playerMove] = (player == "CPU") ? tokensSymbols[1] : tokensSymbols[0];
      r = 0;
    }
    System.out.printf("\n");
    return tokens;
  }

  /**
   * isWinner method to check if a player has won the game
   * the mothds check 4 connected tokens horizonatlly, veritcally and diagonally
   * 
   * @param tokens, the matrix of tokens already placed
   * @param tokenSymbol, matrix containing the token symbols
   */
  public boolean isWinner(String[][] tokens, String tokenSymbol) {
    int count;
    // check horizontal connection of 4
    for (int r = 0; r < tokens.length; r++) {
      count = 0;
      for (int c = 0; c < tokens[r].length; c++) {
        if (tokens[r][c] == tokenSymbol) {
          count++;
          if (count == 4) {
            return true;
          }
        } else {
          count = 0;
        }
      }
    }

    // check vertical connection of 4
    
    // Place the row loop after the column loop to count vertically
    for (int c = 0; c < tokens[0].length; c++) {
      count = 0;
      for (int r = 0; r < tokens.length; r++) {
        if (tokens[r][c] == tokenSymbol) {
          count++;
          if (count == 4) {
            return true;
          }
        } else {
          count = 0;
        }
      }
    }

    // Check diagonal connection of 4
    
    int countRL;
    for (int r = 0; r < tokens.length; r++) {
      count = 0;
      // Checks diagonally from left to right
      for (int c = 0; c < tokens[0].length; c++) {
        if (tokens[r][c] == tokenSymbol) {
          count++;
          if (r + 1 < tokens.length && c + 1 < tokens[0].length) {
            if (tokens[r + 1][c + 1] == tokenSymbol) {
              r++;
            } else {
              count = 0;
            }
          } else {
            if (count == 4) {
              return true;
            }
            count = 0;
          }
        }
      }
      count = 0;
      countRL = 0;
      // Checks diagonally from right to left
      for (int c = tokens[0].length - 1; c >= 0 ; c--) {
        if (tokens[r][c] == tokenSymbol) {
          countRL++;
          if (r + 1 < tokens.length && c - 1 >= 0) {
            if (tokens[r + 1][c - 1] == tokenSymbol) {
              r++;
            } else {
              countRL = 0;
            }
          } else {
            if (countRL == 4) {
              return true;
            }
            countRL = 0;
          }
        }
      }
    }
    return false;
  }

}
