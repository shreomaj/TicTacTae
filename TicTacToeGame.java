package application.tictactoe.game;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {
    private static final char EMPTY = ' ';
    private static char[][] board = new char[3][3];
    private static String player1Name, player2Name;
    private static char player1Symbol, player2Symbol;
    private static boolean isPlayer1Turn = true;

    public static void main(String[] args) {
        initializeBoard();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Player 1's name: ");
        player1Name = scanner.nextLine();
        System.out.print("Enter Player 1's symbol (default 'O'): ");
        player1Symbol = scanner.nextLine().toUpperCase().charAt(0);
        if (player1Symbol != 'X' && player1Symbol != 'O') player1Symbol = 'O';

        System.out.print("Will Player 2 be another human or a computer (h/c)? ");
        String choice = scanner.nextLine().toLowerCase();
        if (choice.equals("h")) {
            System.out.print("Enter Player 2's name: ");
            player2Name = scanner.nextLine();
            System.out.print("Enter Player 2's symbol (default 'X'): ");
            player2Symbol = scanner.nextLine().toUpperCase().charAt(0);
            if (player2Symbol != 'X' && player2Symbol != 'O') player2Symbol = 'X';
        } else {
            player2Name = "Computer";
            player2Symbol = 'X';
        }

        if (player1Symbol == player2Symbol) {
            System.out.println("Both players cannot have the same symbol. Changing Player 2's symbol to 'X'.");
            player2Symbol = (player1Symbol == 'O') ? 'X' : 'O';
        }

        while (true) {
            printBoard();
            if (isPlayer1Turn) {
                playerMove(player1Name, player1Symbol);
            } else {
                if (player2Name.equals("Computer")) {
                    computerMove();
                } else {
                    playerMove(player2Name, player2Symbol);
                }
            }

            if (isGameOver()) {
                printBoard();
                break;
            }
            isPlayer1Turn = !isPlayer1Turn;
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void playerMove(String playerName, char symbol) {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        while (true) {
            System.out.printf("%s's move (%c). Enter row and column (0, 1, or 2): ", playerName, symbol);
            row = scanner.nextInt();
            col = scanner.nextInt();
            if (isValidMove(row, col)) {
                board[row][col] = symbol;
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    private static void computerMove() {
        Random random = new Random();
        int row, col;
        while (true) {
            row = random.nextInt(3);
            col = random.nextInt(3);
            if (isValidMove(row, col)) {
                System.out.printf("Computer's move (%c) at (%d, %d).\n", player2Symbol, row, col);
                board[row][col] = player2Symbol;
                break;
            }
        }
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == EMPTY;
    }

    private static boolean isGameOver() {
        if (checkWin(player1Symbol)) {
            System.out.println(player1Name + " wins!");
            return true;
        } else if (checkWin(player2Symbol)) {
            System.out.println(player2Name + " wins!");
            return true;
        } else if (isBoardFull()) {
            System.out.println("It's a draw!");
            return true;
        }
        return false;
    }

    private static boolean checkWin(char symbol) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)) {
                return true;
            }
        }
        if ((board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
            (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)) {
            return true;
        }
        return false;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}

