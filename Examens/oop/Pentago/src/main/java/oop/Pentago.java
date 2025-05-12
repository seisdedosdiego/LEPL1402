package oop;

/**
 * A class that represents a game of Pentago.
 *
 * The rules of the game are described in this short video
 * that we advise you to watch now.
 *   [FR] https://youtu.be/bpkpMxqDmjw
 *   [EN] https://youtu.be/VX6-n1Wm5zI?si=QSLC2auuy7Q1DXFZ
 *
 * Pentago is a two-player game on a 6x6 grid board composed of four
 * 3x3 subparts that can be rotated left or right. The board looks
 * like this:
 *
 * ------------------------------
 * |  x   x   x  |  x   x   x   |
 * |  x   x   x  |  x   x   x   |
 * |  x   x   x  |  x   x   x   |
 * ------------------------------
 * |  x   x   x  |  x   x   x   |
 * |  x   x   x  |  x   x   x   |
 * |  x   x   x  |  x   x   x   |
 * ------------------------------
 *
 * with the four subparts being:
 *
 * --------------------------------
 * |              |               |
 * |   TOP_LEFT   |   TOP_RIGHT   |
 * |              |               |
 * -------------------------------
 * |              |               |
 * | BOTTOM_LEFT  | BOTTOM_RIGHT  |
 * |              |               |
 * -------------------------------
 *
 * Two players A and B take turns. Each turn is composed of 2 successive actions:
 * 1) the player places one of his token on the board in a free position, then
 * 2) the player rotates by 90° or -90° one subpart of the board of his choice.
 *
 * A player wins when he has 5 consecutive own tokens in a row, a
 * column, or a diagonal.
 *
 * Your task is to model the game, by implementing:
 * 1) the main method "play()" that registers the move of the player, and
 * 2) the helper methods "rotateMatrix()", "checkWinPlayerVector()" and "checkWinPlayer()".
 *
 * The 3 helper methods are supposed to help you build the code for
 * the "play()" method.  Therefore, you should implement the helper
 * methods first. The helper methods are graded separately.
 *
 * We also provide the "isGridFilled()" helper method that can be
 * useful in your "play()" method.
 *
 * Do not modify the enumerations, the method signatures, or the
 * instance variables. Even if this is not required to succeed this
 * quiz, you can possibly add new methods and import Java classes.
 *
 */
public class Pentago {
    /**
     * An enum that represents the two players playing the game.
     */
    public enum Player {
        A,
        B
    }

    /**
     * An enum that represents the different outcomes of the game.
     * A_WINS is used when player A wins, B_WINS is used when player B wins, and
     * NO_WINNER is used when there is no winner yet, but also when
     * the board is full (which means that there is no winner) or when both
     * players win at the same time.
     */
    public enum Winner {
        A_WINS,
        B_WINS,
        NO_WINNER
    }

    /**
     * An enum that represents the four subparts of the board:
     * --------------------------------
     * |              |               |
     * |   TOP_LEFT   |   TOP_RIGHT   |
     * |              |               |
     * --------------------------------
     * |              |               |
     * | BOTTOM_LEFT  | BOTTOM_RIGHT  |
     * |              |               |
     * --------------------------------
     */
    public enum BoardSubpart {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }

    /**
     * An enum that represents the direction of the rotation applied on a subpart.
     */
    public enum RotationDirection {
        LEFT,
        RIGHT
    }

    // the top-left cell of the board has coordinates [0][0]
    // the top-right cell of the board has coordinates [0][5]
    // the bottom-left cell of the board has coordinates [5][0]
    // the bottom-right cell of the board has coordinates [5][5]
    Player[][] board;



    private static final int BOARD_SIZE = 6;
    private static final int SUBPART_SIZE = 3;

    public Pentago() {
        this.board = new Player[BOARD_SIZE][BOARD_SIZE];
    }

    /**
     * A helper method that verifies whether the grid is completely filled by player tokens, or not.
     *
     * @return true if the board is full and false otherwise
     */
    private boolean isGridFilled() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (this.board[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Implementation of the two steps move and computation of the winner:
     * 1) Plays a piece in the given position for the given player, then
     * 2) Rotates the given subpart in the given direction.
     *
     * The method returns the winner (A_WINS / B_WINS) if only one player is in a victory state.
     * Otherwise, NO_WINNER is returned. It means that NO_WINNER is also returned if both players
     * have aligned 5 pieces in a row, column or diagonal.
     *
     * <p>
     * The methods you should have implemented before are "rotateMatrix", "checkWinPlayerVector" and "checkWinPlayer"
     * Those methods should help you build the code for the play method.
     * Notice that rotating a matrix to the left three times is equivalent to rotating it once the right. 💡
     *
     * @param i         the row index
     * @param j         the column index
     * @param player    the player (A or B)
     * @param subpart   the subpart to rotate by +- 90°
     * @param direction the direction to rotate the subpart (LEFT for -90° or RIGHT for +90°)
     * @throws IllegalArgumentException if i or j is not a valid index or if the position is not empty
     * @return Winner   the winning status of the game after the play (cf. above)
     */
    public Winner play(int i, int j, Player player, BoardSubpart subpart, RotationDirection direction) {
        if (i<0 || j<0 || i>=BOARD_SIZE || j>=BOARD_SIZE) {
            throw new IllegalArgumentException();
        }
        if (this.board[i][j] != null) {
            throw new IllegalArgumentException();
        }

        this.board[i][j] = player;

        Player[][] rotated_part = new Player[SUBPART_SIZE][SUBPART_SIZE];

        switch (subpart) {
            case TOP_LEFT:
                for (int k=0; k<3;k++) {
                    for (int l=0; l<3; l++) {
                        rotated_part[k][l] = this.board[k][l];
                    }
                }
                break;

            case TOP_RIGHT:
                for (int k=0; k<3;k++) {
                    for (int l=3; l<BOARD_SIZE; l++) {
                        rotated_part[k][l-3] = this.board[k][l];
                    }
                }
                break;
            
            case BOTTOM_LEFT:
                for (int k=3; k<BOARD_SIZE;k++) {
                    for (int l=0; l<3; l++) {
                        rotated_part[k-3][l] = this.board[k][l];
                    }
                }
                break;

            case BOTTOM_RIGHT:
                for (int k=3; k<BOARD_SIZE;k++) {
                    for (int l=3; l<BOARD_SIZE; l++) {
                        rotated_part[k-3][l-3] = this.board[k][l];
                    }
                }
                break;
        
            default:
                break;
        }

        switch (direction) {
            case LEFT:
                rotated_part = rotateMatrix(rotateMatrix(rotateMatrix(rotated_part)));
                break;
            case RIGHT:
                rotated_part = rotateMatrix(rotated_part);
                break;
            default:
                break;
        }

        switch (subpart) {
            case TOP_LEFT:
                for (int k=0; k<3;k++) {
                    for (int l=0; l<3; l++) {
                        this.board[k][l] = rotated_part[k][l];
                    }
                }               
                break;

            case TOP_RIGHT:
                for (int k=0; k<3;k++) {
                    for (int l=3; l<BOARD_SIZE; l++) {
                        this.board[k][l] = rotated_part[k][l-3];
                    }
                }
                break;
            
            case BOTTOM_LEFT:
                for (int k=3; k<BOARD_SIZE;k++) {
                    for (int l=0; l<3; l++) {
                        this.board[k][l] = rotated_part[k-3][l];
                    }
                }                    
                break;

            case BOTTOM_RIGHT:
                for (int k=3; k<BOARD_SIZE;k++) {
                    for (int l=3; l<BOARD_SIZE; l++) {
                        this.board[k][l] = rotated_part[k-3][l-3];
                    }
                }
                break;
 
            default:
                break;
        }

        if (checkWinPlayer(Player.A) && checkWinPlayer(Player.B)) {
            return Winner.NO_WINNER;
        }

        if (checkWinPlayer(Player.A)) {
            return Winner.A_WINS;
        }
        if (checkWinPlayer(Player.B)) {
            return Winner.B_WINS;
        }

        if (this.isGridFilled()) {
            return Winner.NO_WINNER;
        }

         return Winner.NO_WINNER;
    }



    /**
     * Rotate the given matrix 90° to the right (i.e., clockwise).
     *
     * Beware that the matrix may have an arbitrary n x m shape (i.e.,
     * do NOT assume that the matrix has the 3 x 3 shape of the
     * subparts of the Pentago game).
     *
     * @param matrix   the n x m matrix to rotate
     * @return A version of the input matrix rotated to the right having thus an m x n shape.
     *         The input matrix is left unchanged.
     */
    public Player[][] rotateMatrix(Player[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        Player[][] res = new Player[m][n];

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                res[j][n-i-1] = matrix[i][j];
            }
        }
        return res;
    }

    /**
     * Returns if five consecutive positions of the given player are present in the array.
     * This  array can represent a row, a column or a diagonal of the board.
     *
     * @param vector    An array.
     * @param player    The player for which we want to find the possible win.
     * @return true if and only if the given player has five consecutive positions in the array.
     */
    public boolean checkWinPlayer(Player[] vector, Player player){
        for (int i=0; i<vector.length-4; i++) {
            boolean flag = true;
            for (int k=0; k<5; k++) {
                if (vector[i+k] != player) {
                    flag = false;
                }
            }
            if (flag) {return true;}
        }
        return false;
    }

    /**
     * Indicates if the given player has won.
     *
     * @return true if the given player has won and false otherwise.
     */
    private boolean checkWinPlayer(Player player) {

        Player[] vector = new Player[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (checkWinPlayer(this.board[i], player)) return true;
            
            for (int k = 0; k < BOARD_SIZE; k++) vector[k] = this.board[k][i];
            if (checkWinPlayer(vector, player)) return true;
        }
        
        for (int i = 0; i < BOARD_SIZE; i++) vector[i] = this.board[i][i];
        if (checkWinPlayer(vector, player)) return true;
        
        for (int i = 0; i < BOARD_SIZE; i++) vector[i] = this.board[i][BOARD_SIZE - i - 1];
        if (checkWinPlayer(vector, player)) return true;

        for (int i = 0; i < BOARD_SIZE - 1; i++) vector[i] = this.board[i][i + 1];
        vector[BOARD_SIZE - 1] = null;
        if (checkWinPlayer(vector, player)) return true;

        for (int i = 1; i < BOARD_SIZE; i++) vector[i] = this.board[i][i - 1];
        vector[0] = null;
        if (checkWinPlayer(vector, player)) return true;
        
        for (int i = 0; i < BOARD_SIZE - 1; i++) vector[i] = this.board[i][BOARD_SIZE - i - 2];
        vector[BOARD_SIZE - 1] = null;
        if (checkWinPlayer(vector, player)) return true;
        
        for (int i = 1; i < BOARD_SIZE; i++) vector[i] = this.board[i][BOARD_SIZE - i];
        if (checkWinPlayer(vector, player)) return true;

        return false;
    }
}

