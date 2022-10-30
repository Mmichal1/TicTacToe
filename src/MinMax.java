import java.util.Objects;

public class MinMax {

    static int MIN = -1000;
    static int MAX = 1000;

    // MinMax method which returns the score of the best move 
    public static int minMax(String[] board, int size, int movesToWin, int depth, boolean isMax, int alpha, int beta) {
        char evaluate = TicTacToe.checkWinner(board, size, movesToWin);

        // If computer (maximizer) won return +10
        if (evaluate == 'O') {
            return 10;
        }

        // If human (minimizer) won return -10
        if (evaluate == 'X') {
            return -10;
        }

        // If draw or depth limit is reached then return 0
        if (evaluate == 'D' || depth == 5) {
            return 0;
        }

        if (isMax) { // Maximizer

            int best = MIN;

            // Search board for available positions
            for (int i = 0; i < board.length; i++) {
                if (!(board[i].equals("O") || board[i].equals("X"))) {

                    // Make the move
                    String temp = board[i];
                    board[i] = "O";

                    // Call minMax recursively and choose max value
                    best = Math.max(best, minMax(board, size, movesToWin, depth + 1, !isMax, alpha, beta));
                    alpha = Math.max(alpha, best);

                    // Undo the move
                    board[i] = temp;

                    // Alpha-beta pruning
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            return best;

        } else { // Minimizer

            int best = MAX;

            // Search board for available positions
            for (int i = 0; i < board.length; i++) {
                if (!(board[i].equals("O") || board[i].equals("X"))) {

                    // Make the move
                    String temp = board[i];
                    board[i] = "X";

                    // Call minMax recursively and choose max value
                    best = Math.min(best, minMax(board, size, movesToWin, depth + 1, !isMax, alpha, beta));
                    beta = Math.min(beta, best);

                    // Undo the move
                    board[i] = temp;

                    // Alpha-beta pruning
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            return best;
        }
    }

    // Find the best move on the basis of the score returned by MinMax
    static int findBestMove(String[] board, int size, int movesToWin) {

        int bestScore = MIN;
        int bestMove = -1;
        boolean isBoardClear = true;

        // In case if board is clear and the algorithm is making the first move, then the best move will always be
        // the middle position on the board.
        for (int i = 0; i < board.length; i++) {
            if (Objects.equals(board[i], "X") || Objects.equals(board[i], "O")) {
                System.out.println("SHIT");
                isBoardClear = false;
                break;
            }
        }

        if (isBoardClear) {
            return 4;
        }

        // Search board for available positions
        for (int i = 0; i < board.length; i++) {
            if (!(board[i].equals("O") || board[i].equals("X"))) {

                // Make the move
                String temp = board[i];
                board[i] = "O";

                // Call minMax recursively and choose max value
                int moveScore = minMax(board, size, movesToWin, 0, false, MIN, MAX);

                // Undo the move
                board[i] = temp;

                // Check if current move score is higher than best move score 
                if (moveScore > bestScore) {

                    bestMove = i;
                    bestScore = moveScore;
                }
            }
        }
        return bestMove;
    }
}
