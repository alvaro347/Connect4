import java.io.BufferedReader;
import java.io.*;

public class PlayerInput {

  /**
   * handlePlayerInput method to handle the input from the player and CPU
   * if the current player is the CPU the method will generate a random number.
   * 
   * @param tokens, the matrix of tokens already placed
   * @param player, the current player
   * @return the tokens with the update value
   */
  public String[][] handlePlayerInput(String[][] tokens, String[] tokensSymbols, String player) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("---- Player " + player + " ----\n");
    int playerMove = -1; // helper variable to determine a correct input from the player
    while (playerMove == -1) {
      // if the player is CPU the playerMove will be assigned to a random number between 1
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
   * the methods check 4 connected tokens horizontally, vertically and diagonally
   * 
   * @param tokens,      the matrix of tokens already placed
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
    for (int r = 0; r < tokens.length; r++) {
      count = 0;
      // Check diagonally from left to right
      for (int c = 0; c < tokens[0].length - 3; c++) {
        if (tokens[r][c] == tokenSymbol) {
          count++;
          // Loop diagonally when it finds a token
          for (int i = 1; i < 4; i++) {
            if (r + i < tokens.length && c + i < tokens[0].length) {
              if (tokens[r + i][c + i] == tokenSymbol) {
                count++;
              }
            } else {
              break;
            }
          }
          if (count == 4) {
            return true;
          }
          count = 0;
        }
      }
      int countRL = 0;
      // Check diagonally from right to left
      for (int c = 3; c < tokens[0].length; c++) {
        if (tokens[r][c] == tokenSymbol) {
          countRL++;
          // Loop diagonally when it finds a token
          for (int i = 1; i < 4; i++) {
            if (r + i < tokens.length && c - i >= 0) {
              if (tokens[r + i][c - i] == tokenSymbol) {
                countRL++;
              }
            } else {
              break;
            }
          }
          if (countRL == 4) {
            return true;
          }
          countRL = 0;
        }
      }
    }
    return false;
  }

}