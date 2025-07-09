# 🎮 Tic Tac Toe

A full-featured, interactive, and replayable Tic Tac Toe game built in Java, where you challenge a computer opponent in a classic battle of Xs and Os!

![Java](https://img.shields.io/badge/Language-Java-blue.svg)
![Beginner Project](https://img.shields.io/badge/Level-Beginner-brightgreen)
![Console Game](https://img.shields.io/badge/Type-Console--App-lightgrey)
---

## ✨ Features

### 🧠 Intelligent Game Logic
- Implements **perfect grid-checking logic** for all winning scenarios (rows, columns, diagonals).
- Accurately identifies **draws** and declares them when the board is full without a winner.

### 🧍‍♂️ vs 🖥️ User vs Computer
- Play against a computer that selects random but valid moves.
- Ensures no overwriting of occupied spaces, keeping gameplay error-free.

### 🎭 Symbol Selection
- Choose whether you want to be **`X` or `O`**.
- The computer automatically takes the remaining symbol.
- Input validation ensures only valid choices are accepted.

### 🔄 First Move Randomizer
- Fairly choose between the user and the computer to make the **first move**.
- Adds unpredictability and variation to every game.

### 🗺️ Visual Game Board
- Displays a neatly formatted **3x3 board** with row/column headers.
- Updated after every move with clear visual structure.

### 📈 Score Tracker
- Tracks:
    - 🟢 Games Played
    - 🏆 Wins
    - ❌ Losses
    - 🤝 Draws
- Print out **cumulative results** after quitting the game.

### ♻️ Replay System
- After each game, the user is asked if they want to **play again** (`y/n`).
- If yes, the game **fully resets** the board and randomly re-selects the first player — no bugs, no lingering values.
- Smooth replay flow, no duplicated printing or stuck loops.

### ✅ Fully Validated Input
- Handles invalid numeric inputs like letters or symbols with **graceful messages**.
- Prevents crashing by checking `Scanner` inputs carefully.
- Ensures all moves are within bounds and in empty spots.

### 💤 Realism with Delays
- Adds **brief delays** before the computer moves, giving a more natural "thinking" effect.

---
## 🚀 Getting Started

### 📦 Requirements

- Java 8 or above
- Any terminal or command line interface

### 🛠️ How to Run
Download the jar file from the releases section
```bash
java -jar tictactoe.jar
```
---

## 🕹️ How to Play

1. Run the game.
2. Choose your symbol: `X` or `O`.
3. Watch who goes first — it could be you or the computer.
4. When it's your turn:
    - Enter your move as two numbers: **row column** (e.g., `0 2`).
5. The game board updates after every move.
6. Win the game by getting three of your symbols in a line.
7. After the game ends:
    - You'll see a message: **"You win!"**, **"Computer wins!"**, or **"It's a draw!"**
    - Then you can choose to replay or quit.
8. Upon quitting, a **score summary** is shown.

---

## 🧠 Future Improvements

- Add a **minimax AI** for unbeatable computer strategy.
- Convert into a **GUI version** using Swing or JavaFX.
- Add sound effects and animations for moves.
- Online multiplayer mode using sockets.
- Leaderboard that saves stats across sessions.

---

## 🔧 Tools Used

- Java 17 (or Java 8+)
- IntelliJ IDEA (recommended)
- Git + GitHub

---

## 🙌 Author Notes

Built as a project to demonstrate not only control structures and 2D arrays, but also robust user input handling, replay logic, and full game loop design — all in clean and beginner-friendly Java.

If you're new to Java and want to understand game loops, input validation, and board logic — **this is a perfect project to study!**


## 🖥️ Sample Output

```console
Welcome to Tic Tac Toe!
Enter your preferred symbol (O/X): x
-------------------------------
Reference of each cell in the board: 

    1    2    3
  ----------------
A | A1 | A2 | A3 |
  ----------------
B | B1 | B2 | B3 |
  ----------------
C | C1 | C2 | C3 |
  ----------------

The first move will be made by: You

Enter the position reference of your cell (eg: A1): a1
You made your move at: A1

    1   2   3
  -------------
A | X |   |   |
  -------------
B |   |   |   |
  -------------
C |   |   |   |
  -------------

Computer's turn
Computer has made its move
Computer placed at: A2

    1   2   3
  -------------
A | X | O |   |
  -------------
B |   |   |   |
  -------------
C |   |   |   |
  -------------

Your turn
Enter the position reference of your cell (eg: A1): b2
You made your move at: B2

    1   2   3
  -------------
A | X | O |   |
  -------------
B |   | X |   |
  -------------
C |   |   |   |
  -------------

Computer's turn
Computer has made its move
Computer placed at: B3

    1   2   3
  -------------
A | X | O |   |
  -------------
B |   | X | O |
  -------------
C |   |   |   |
  -------------

Your turn
Enter the position reference of your cell (eg: A1): 3c
You made your move at: C3

    1   2   3
  -------------
A | X | O |   |
  -------------
B |   | X | O |
  -------------
C |   |   | X |
  -------------
You win!

------------------------------

Do you wish to play again? (Y/N): n
Thank you for playing!

--------------Score---------------
Games Played: 1
Games Won: 1
Games Lost: 0
Games Drawn: 0
```