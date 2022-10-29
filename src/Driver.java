import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int scan;
        boolean isPlayerMove;
        int size = 3;
        int movesToWin = 3;

        while (true) {

            System.out.println("Specify who goes first. (1 - you, 2 - AI)");
            scan = in.nextInt();

            if (scan == 1) {
                isPlayerMove = true;
                break;
            } else if (scan == 2) {
                isPlayerMove = false;
                break;
            } else {
                System.out.println("Incorrect input, try again.");
            }
        }

        String[] gameBoard = new String[size * size];

        for (int i = 0; i < size * size ; i++) {
            gameBoard[i] = String.valueOf(i + 1);
        }

        TicTacToe.printBoard(gameBoard, size);

        while(true) {

            if (isPlayerMove) {
                System.out.println("Your move");
                TicTacToe.makeMove(gameBoard, isPlayerMove, size, movesToWin);
                isPlayerMove = false;
                TicTacToe.printBoard(gameBoard, size);
                if (TicTacToe.checkWinner(gameBoard, size, movesToWin) != 'P') {
                    if (TicTacToe.checkWinner(gameBoard, size, movesToWin) == 'D') {
                        System.out.println("Draw");
                    } else {
                        System.out.println("Winner: " + TicTacToe.checkWinner(gameBoard, size, movesToWin));
                    }
                    break;
                }
            } else {
                System.out.println("Computers move");
                TicTacToe.makeMove(gameBoard, isPlayerMove, size, movesToWin);
                isPlayerMove = true;
                TicTacToe.printBoard(gameBoard, size);
                if (TicTacToe.checkWinner(gameBoard, size, movesToWin) != 'P') {
                    if (TicTacToe.checkWinner(gameBoard, size, movesToWin) == 'D') {
                        System.out.println("Draw");
                    } else {
                        System.out.println("Winner: " + TicTacToe.checkWinner(gameBoard, size, movesToWin));
                    }
                    break;
                }
            }
        }
    }
}
