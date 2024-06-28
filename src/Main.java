
/**
 * This is a connect 4 style game that plays between one player "1"
 * and a computer player "CPU". The objective is to connect 4 vertically,
 * horizontally or diagonally to win the game.
 */

import java.io.BufferedReader;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Game connect4 = new Game();
        connect4.startGame();
    }
}

class Game {
    String[][] moves = new String[6][7];
    Board board = new Board();
    PlayerInput playerInput = new PlayerInput();
    String[] players = { "1", "CPU" };
    int player = 1;

    public void startGame() {
        boolean winner = false;

        System.out.println("Start Game\n");
        System.out.println("Welcome to Connect 4\n");
        System.out.println("To play the game type in the number of the column you want to drop your counter in");
        System.out
                .println("A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally\n");

        while (!winner) {
            board.displayBoard(moves);
            if (player % 2 != 0) {
                moves = playerInput.move(moves, players[0]);
                winner = playerInput.checkWinner(moves, "0");
                player++;
            } else {
                moves = playerInput.move(moves, players[1]);
                winner = playerInput.checkWinner(moves, "X");
                player++;
            }
        }
        gameOver();
    }

    public void gameOver() {
        board.displayBoard(moves);
        System.out.println("Winner is " + players[player % 2] + "\n");
        System.out.println("Game Over");
    }

}

class Board {
    int rows = 6;
    int columns = 7;
    String empty = " ";

    public void displayBoard(String[][] moves) {
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i < moves.length) {
                    System.out.printf("| " + (moves[i][j] == null ? empty : moves[i][j]) + " ");
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

}

class PlayerInput {

    public String[][] move(String[][] moves, String player) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n---- Player " + player + " ----\n");
        int playerMove = -1;
        while (playerMove == -1) {
            if (player == "CPU") {
                playerMove = (int) Math.floor(Math.random() * 6);
                System.out.println(playerMove);
            } else {
                try {
                    System.out.printf("Place next move: ");
                    String input = br.readLine();
                    if (input.length() != 0) {
                        playerMove = Character.getNumericValue(input.charAt(0));
                        if (input.length() == 1 && playerMove <= 7) {
                            playerMove--;
                            break;
                        } else {
                            playerMove = -1;
                            System.out.println("Try Again! Enter a number between 1 and 7\n");
                        }
                    }

                } catch (IOException e) {
                    System.err.println(e.getMessage());
                    System.exit(1);
                }

                int r = 0;
                if (moves[5][playerMove] == null) {
                    moves[5][playerMove] = (player == "1") ? "0" : "X";
                } else {
                    while (moves[r][playerMove] == null) {
                        r += 1;
                        if (r == moves.length - 1) {
                            break;
                        }

                    }
                    if (moves[0][playerMove] != null) {
                        System.out.println("The slot is full, try another column");
                    } else {
                        moves[r - 1][playerMove] = (player == "1") ? "0" : "X";
                    }
                }
                r = 0;
            }
        }
        return moves;
    }

    public boolean checkWinner(String[][] moves, String player) {
        int count = 0;
        // check horizontal
        for (int i = 0; i < moves.length; i++) {
            for (int j = 0; j < moves[i].length; j++) {
                if (moves[i][j] == player) {
                    count++;
                    if (count == 4) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }

        // check vertical
        count = 0;
        for (int i = 0; i < moves[0].length; i++) {
            for (int j = 0; j < moves.length; j++) {
                if (moves[j][i] == player) {
                    count++;
                    if (count == 4) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }

        // Check diagonally
        count = 0;
        for (int i = 0; i < moves.length; i++) {
            for (int j = 0; j < moves[0].length; j++) {
                if (moves[i][j] == player) {
                    count++;
                    if (i + 1 < moves.length && j + 1 < moves[0].length) {
                        if (moves[i + 1][j + 1] == player) {
                            i++;
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
            for (int j = 0; j < moves[0].length; j++) {
                if (moves[i][j] == player) {
                    count++;
                    if (i + 1 < moves.length && j - 1 >= 0) {
                        if (moves[i + 1][j - 1] == player) {
                            i++;
                            j -= 2;
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
        }
        return false;
    }

}
