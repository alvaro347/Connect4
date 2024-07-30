# Connect 4 Game in Java

Welcome to the Connect 4 game project! This Java project implements a console-based Connect 4 game where one player competes against a computer player (CPU). The goal is to connect four tokens vertically, horizontally, or diagonally to win the game.

Example of the game:

```
---------------------------------
      Welcome to Connect 4
---------------------------------

To play the game type in the number of the column you want to drop your token in.
A player wins by connecting 4 counters in a row vertically, horizontally or diagonally.

---------------------------------

|   |   |   |   |   |   |   |

|   |   |   |   |   |   |   |

|   |   |   |   |   |   |   |

|   |   |   |   |   |   |   |

|   |   |   |   |   |   |   |

|   |   |   |   |   |   |   |

  1   2   3   4   5   6   7 

---- Player 1 ----
```

## Table of Contents

- [Connect 4 Game in Java](#connect-4-game-in-java)
  - [Table of Contents](#table-of-contents)
  - [Introduction](#introduction)
  - [Game Rules](#game-rules)
  - [Installation](#installation)
  - [Usage](#usage)
  - [Classes](#classes)
    - [Main](#main)
    - [Connect4](#connect4)
    - [Board](#board)
    - [PlayerInput](#playerinput)

## Introduction

This project provides a simple implementation of the classic Connect 4 game using Java. It includes a game board, player input handling, and logic to determine the winner. The game is played in the console.

## Game Rules

1. Players take turns dropping tokens into one of the columns of the game board.
2. The first player to connect four of their tokens vertically, horizontally, or diagonally wins the game.
3. If the board is filled completely without any player connecting four tokens, the game ends in a draw.

## Installation

1. Clone the repository:

```sh
git clone https://github.com/your-username/connect4-java.git
```

2. Navigate to the project directory:

```sh
cd connect4-java
```

3. Compile the Java files:

```sh
javac Main.java Connect4.java Board.java PlayerInput.java
```

4. Run the game:

```sh
java Main
```

## Usage

To play the game, follow the on-screen instructions. The player will be prompted to enter the column number where they want to drop their token. The CPU will make random moves.

## Classes

### Main

The Main class contains the entry point for the game.

### Connect4

The `Connect4` class manages the game logic, including the game loop, checking for a winner, and handling the end of the game.

### Board

The `Board` class handles displaying the game board and checking if the board is full.

### PlayerInput

The `PlayerInput` class handles player and CPU input, updating the game board, and checking if a player has won.
