/**
 * This is a connect 4 style game that plays between one player "1"
 * and a computer player "CPU". The objective is to connect 4 vertically,
 * horizontally or diagonally to win the game.
 */

 public class Main {
    public static void main(String[] args) {
      Connect4 connect4 = new Connect4(6, 7);
      connect4.startGame();
    }
  }
  