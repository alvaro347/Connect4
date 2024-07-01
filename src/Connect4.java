/**
 * Connect4 class
 * Handles the game and connect the different classes
 */

 public class Connect4 {
  private Board board;
  private String[][] tokens; // Matrix to store the tokens that player place
  private PlayerInput playerInput = new PlayerInput();
  private int player = 1; // Player helper variable to count the turns
  private boolean winner = false;
  private boolean boardFull = false;
  private final String[] players = { "1", "CPU" };
  private final String[] tokensSymbols = { "0", "X" };

  public Connect4(int rows, int columns) {
    board = new Board(rows, columns);
    tokens = new String[rows][columns];
  }

  /**
   * startGame method
   * Displays the game's rules and description and starts the game loop
   */
  public void startGame() {
    // Game intro description and rules
    System.out.println("---------------------------------");
    System.out.println("      Welcome to Connect 4");
    System.out.println("---------------------------------\n");
    System.out.println("To play the game type in the number of the column you want to drop your token in.");
    System.out.println("A player wins by connecting 4 counters in a row vertically, horizontally or diagonally.\n");
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
        this.tokens = playerInput.handlePlayerInput(tokens, tokensSymbols, players[1]);
        this.winner = playerInput.isWinner(tokens, tokensSymbols[1]);
        player++;
      }
      // Check if the board is full at the end of every move
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