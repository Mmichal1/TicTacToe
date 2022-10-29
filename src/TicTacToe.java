import java.util.Scanner;

public class TicTacToe {

    static String dashedLine(int size)
    {
        return "-".repeat(Math.max(0, size * 4 + 1)) +
                System.lineSeparator();
    }

    static void printBoard(String[] board, int size) {


        for (int i = 0; i < size * size; i++) {

            System.out.print(board[i] + "\t");

            if ((i + 1) % size == 0) {
                System.out.println();
            }
        }

    }

    static void makeMove(String[] board, boolean playerMove, int size, int movesToWin) {

        boolean isMoveMade = false;
        Scanner in = new Scanner(System.in);
        int pos;


        while(!isMoveMade) {

            // If playerMove == true then it's the players move, if it's false then it's computers move.
            if (playerMove) {

                System.out.println("Choose position:");
                pos = in.nextInt();

                // Check if selected position is correct and available. If it's not then loop until a
                // correct and available position is given.

                if (pos > 0 & pos <= board.length) {
                    if (!board[pos - 1].equals("X") & !board[pos - 1].equals("O")) {
                        board[pos - 1] = "X";

                        // Loop breaks after move is made
                        isMoveMade = true;
                    } else {
                        System.out.println("Position already taken");
                    }
                } else {
                    System.out.println("Incorrect position");
                }
            } else {

                int bestMove = MinMax.findBestMove(board, size, movesToWin);
                board[bestMove] = "O";
                isMoveMade = true;
            }
        }
    }



    public static char checkWinner(String[] board, int size, int movesToWin) {

        StringBuilder line = new StringBuilder();
        // Check is set to default as true
        int winX = 0;
        int winO = 0;
        char lineCheck;

        // Checking rows
        for (int i = 0; i < board.length; i++) {

            // Add boards element into a string
            line.append(board[i]);

            // If the loop reaches the end of a row check if there is enough of the same characters
            // in a line to win
            if ((i + 1) % size == 0) {
                lineCheck = checkLine(line, winX, winO, movesToWin);
                if (lineCheck == 'X') {
                    return lineCheck;
                }
                if (lineCheck == 'O') {
                    return lineCheck;
                }
                // If check is false then clear String and check next row.
                line = new StringBuilder();
            }
        }

        // Checking columns

        // Counters to help with appending the correct board element
        int m = 0;  // Counter moving through the board
        int remainder = 0;  // Counter to find correct indexes of elements in one column
        int noOfElements = 0;  // Counter to keep track of how many elements have been added to a string.

        while (true) {

            // If the end of an array is reached, reset counter
            if (m == board.length) {
                m = 0;
            }

            // All elements in a column have equal remainder when the index of an element
            // is a dividend and size (length of rows/columns) is the divisor.
            // They are then added to a string
            if ((m + 1) % size == remainder) {

                line.append(board[m]);
                noOfElements++;

            }

            // If number of added elements is equal to the length of a column
            // then check if there is enough of the same characters in a line to win
            if (noOfElements == size) {

                lineCheck = checkLine(line, winX, winO, movesToWin);

                if (lineCheck == 'X') {
                    return lineCheck;
                }

                if (lineCheck == 'O') {
                    return lineCheck;
                }

                line = new StringBuilder();
                remainder++; // Go to next column
                noOfElements = 0; // Reset
            }

            if (remainder == size) {
                break;
            }

            m++; // Go to next board element
        }


        // Checking diagonals

        // Indexes of all elements in the first diagonal (left top to right bottom) differ by line length + 1;
        for (int l = 0; l <= board.length; l += size + 1) {

            line.append(board[l]);
        }

        // Check if elements in a diagonal are the same
        lineCheck = checkLine(line, winX, winO, movesToWin);

        if (lineCheck == 'X') {
            return lineCheck;
        }

        if (lineCheck == 'O') {
            return lineCheck;
        }

        // Clear string
        line = new StringBuilder();

        // Indexes of all elements in the second diagonal (right top to left bottom) differ by line length + 1;
        for (int l = size - 1; noOfElements < size; l += size - 1) {

            line.append(board[l]);
            noOfElements++;
        }

        // Check if there is enough of the same characters in a line to win
        lineCheck = checkLine(line, winX, winO, movesToWin);

        if (lineCheck == 'X') {
            return lineCheck;
        }

        if (lineCheck == 'O') {
            return lineCheck;
        }

        // If none of the above checks return true, then there's no winner yet or there is no more available positions
        // which results in a draw.

        // Scan the entire board to see if it's full. If any element is not O or X then it's not filled and game goes on
        for (int i = 0; i < board.length; i++) {
            if (board[i].equals("X") || board[i].equals("O")) {

                // If last element is reached, and it's equal either to O or X then it's a draw
                if (i == board.length - 1) {
                    //System.out.println("Draw");
                    return 'D';
                }
            } else {
                return 'P';
            }
        }

        // Game goes on
        return 'P';
    }

    public static char checkLine(StringBuilder line, int winX, int winO, int movesToWin) {

        for (int j = 0; j < line.length(); j++){

            if (line.charAt(j) == 'X') {

                winX++;
                winO = 0;

                if (winX == movesToWin) {
                    return line.charAt(j);
                }

            } else if (line.charAt(j) == 'O'){

                winO++;
                winX = 0;

                if (winO == movesToWin) {
                    return line.charAt(j);
                }
            } else {
                winX = 0;
                winO = 0;
            }
        }
        return 'P';
    }
}