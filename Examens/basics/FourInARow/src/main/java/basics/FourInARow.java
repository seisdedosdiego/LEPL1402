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
          for (int i=0; i<ROWS; i++) {
               for (int j=0; j<COLUMNS; j++) {
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
          if (j<0||COLUMNS-1<j) {
               throw new IllegalArgumentException("Invalid column index.");
          } 
          if (board[0][j] == PLAYERS[0]||board[0][j] == PLAYERS[1]) {
               throw new IllegalArgumentException("Column already full.");
          } 
          if (player != PLAYERS[0] && player != PLAYERS[1]) {
               throw new IllegalArgumentException("Invalid player.");
          }
          board[ROWS - nInColumn[j] -1][j] = player;
          nInColumn[j]++;
     }

     /**
     * Returns true if the player has won the game.
     * @param player the player (X or O)
     * @return true if the player has won the game
     * @throws IllegalArgumentException if the player is not X or O
     */
     public boolean hasWon(char player) {
          if (player != PLAYERS[0] && player != PLAYERS[1]) {
               throw new IllegalArgumentException("Invalid player.");
          }

          // vérification des lignes 
          for (int i=0; i<ROWS; i++) {
               for (int j=0; j<COLUMNS-3; j++) {
                    boolean inRow = true;
                    for (int k=0; k<4; k++) {
                         if (board[i][j+k] != player) {
                              inRow = false;
                              break;
                         } 
                    }
                    if (inRow) {return true;}
               }
          }

          // vérification des colonnes
          for (int i=0; i<ROWS-3; i++) {
               for (int j=0; j<COLUMNS; j++) {
                    boolean inCol = true;
                    for (int k=0; k<4; k++) {
                         if (board[i+k][j] != player) {
                              inCol = false;
                              break;
                         } 
                    }  
                    if (inCol) {return true;}
               }
          }

          // vérification des diagonales descendantes
          for (int i=0; i<ROWS-3; i++) {
               for (int j=0; j<COLUMNS-3; j++) {
                    boolean inDiag = true;
                    for (int k=0; k<4; k++) {
                         if (board[i+k][j+k] != player) {
                              inDiag = false;
                              break;
                         } 
                    }  
                    if (inDiag) {return true;}
               }
          }

          // vérification des diagonales montantes
          for (int i=5; i>ROWS-3; i--) {
               for (int j=0; j<COLUMNS-3; j++) {
                    boolean inDiag = true;
                    for (int k=0; k<4; k++) {
                         if (board[i-k][j+k] != player) {
                              inDiag = false;
                              break;
                         } 
                    }  
                    if (inDiag) {return true;}
               }
          }
          
          return false;
     }
}
