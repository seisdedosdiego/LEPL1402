package basics;

/**
 * A class that represents a game of Four in a Row.
 * The game is played on a 6x7 board.
 * A player wins when he has 4 pieces in a row, column or diagonal.
 *
 * ForInARow is a two-player connection rack game, in which the players choose a color and
 * then take turns dropping colored tokens into a six-row, seven-column vertically suspended grid.
 * The pieces fall straight down, occupying the lowest available space within the column.
 *
 * The objective of the game is to be the first to form a horizontal,
 * vertical, or diagonal line of four of one's own tokens.
 *
 * Your taks is to model the game and implement the method hasWon(char player) that returns true.
 * if the player has won the game.
 * We advise you to model the state of the game with an internal 2D array of char.
 */

public class FourInARow {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;

    private static final char EMPTY = '-';
    private static final char[] PLAYERS = {'X', 'O'};

    private char[][] board;
    private int[] nInColumn;

    public FourInARow() {
         board = new char[ROWS][COLUMNS];
         for (int i=0 ; i < ROWS ; i++) {
             for (int j = 0 ; j < COLUMNS; j++) {
                 board[i][j] = EMPTY;
             }
         }
         nInColumn = new int[COLUMNS];
    }

    /**
     * Play a piece in column j for the given player.
     * @param j the column index
     * @param player the player (X or O)
     * @throws IllegalArgumentException if j is not a valid column index or if the column is full or if the player is not X or O
     */
    public void play(int j, char player) {
        if (player != PLAYERS[0] && player != PLAYERS[1]) throw new IllegalArgumentException("Illegal player");
        if (j < 0 || j >= COLUMNS) throw new IllegalArgumentException("Illegal column index");
        if (nInColumn[j] == ROWS) throw new IllegalArgumentException("Column is full");
        board[nInColumn[j]][j] = player;
        nInColumn[j]++;
    }


    /**
     * Returns true if the player has won the game.
     * @param player the player (X or O)
     * @return true if the player has won the game
     * @throws IllegalArgumentException if the player is not X or O
     */
    public boolean hasWon(char player) {
        if (player != PLAYERS[0] && player != PLAYERS[1]) throw new IllegalArgumentException("Illegal player");

        // vérification des lignes
        for (int row=0 ; row < ROWS ; row++) {
            for (int col=0 ; col <= COLUMNS-4 ; col++) {
                if (board[row][col] == player
                 && board[row][col+1] == player
                 && board[row][col+2] == player
                 && board[row][col+3] == player) {
                    return true;
                }
            }
        }
        // vérification des colonnes
        for (int col=0 ; col < COLUMNS ; col++) {
            for (int row=0 ; row < ROWS-4 ; row++) {
                if (board[row][col] == player
                        && board[row+1][col] == player
                        && board[row+2][col] == player
                        && board[row+3][col] == player) {
                    return true;
                }
            }
        }
        // vérification des diagonales <\>
        for (int row=0 ; row < ROWS-4 ; row++) {
            for (int col=0 ; col < COLUMNS-4 ; col++) {
                if (board[row][col] == player
                        && board[row+1][col+1] == player
                        && board[row+2][col+2] == player
                        && board[row+3][col+3] == player) {
                    return true;
                }
            }
        }
        // vérification des diagonales </>
        for (int col=COLUMNS-1 ; col > COLUMNS-5 ; col--) {
            for (int row=0 ; row < ROWS-4 ; row++) {
                if (board[row][col] == player
                        && board[row+1][col-1] == player
                        && board[row+2][col-2] == player
                        && board[row+3][col-3] == player) {
                    return true;
                }
            }
        }
        return false;
    }
}
